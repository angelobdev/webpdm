package exm.sisinf.webpdm.restcontroller;

import exm.sisinf.webpdm.auth.AuthTokenService;
import exm.sisinf.webpdm.auth.JwtUtils;
import exm.sisinf.webpdm.model.Ruolo;
import exm.sisinf.webpdm.model.Utente;
import exm.sisinf.webpdm.payload.request.LoginRequest;
import exm.sisinf.webpdm.payload.request.RegisterRequest;
import exm.sisinf.webpdm.payload.response.JwtResponse;
import exm.sisinf.webpdm.payload.response.MessageResponse;
import exm.sisinf.webpdm.repository.RuoloRepository;
import exm.sisinf.webpdm.repository.UtenteRepository;
import exm.sisinf.webpdm.service.UtenteService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    AuthTokenService authTokenService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    RuoloRepository ruoloRepository;

    @Autowired
    UtenteService utenteService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        Utente utente = (Utente) authentication.getPrincipal();
        List<String> roles = utente.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(new JwtResponse(jwt, "Bearer", utente.getId(), utente.getUsername(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (utenteRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        Utente utente = new Utente(
                registerRequest.getPartitaIVA(),
                registerRequest.getNomeAzienda(),
                registerRequest.getSedeAziendale(),

                registerRequest.getEmail(),
                registerRequest.getUsername(),
                encoder.encode(registerRequest.getPassword()),

                registerRequest.getAvatar(),
                ruoloRepository.findByNome("ROLE_USER").orElse(null)
        );

        utenteRepository.save(utente);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    // LOGIN
    @PostMapping("/login")
    public ModelAndView actionLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse httpResponse) {

        // Controllo se l'utente esiste
        if (utenteService.existsByUsername(username)) {

            LoginRequest loginInfo = new LoginRequest(username, password);

            // Tento il login
            try {
                ResponseEntity<?> response = authenticateUser(loginInfo);

                if (response.getBody() instanceof JwtResponse jwtRes) {

                    String token = jwtRes.getToken();

                    // Il login ha successo
                    authTokenService.store(httpResponse, token); // Salvo il token di accesso
//                    Ruolo ruolo = authTokenService.getUtente(token).getRuolo();
//                    logger.info("RUOLO UTENTE: {}", ruolo.name());

                    return new ModelAndView("redirect:/areaclienti");

                } else {
                    // Se fallisce ritorno l'errore
                    ModelAndView erroreSconosciutoView = new ModelAndView("login");
                    erroreSconosciutoView.addObject("error", "Login fallito, errore sconosciuto!");
                    return erroreSconosciutoView;
                }

            } catch (Exception e) {
                // Il login non ha successo
                ModelAndView passwordErrataView = new ModelAndView("login");
                passwordErrataView.addObject("error", "Password Errata!");
                return passwordErrataView;
            }

        } else {
            ModelAndView utenteNonTrovatoView = new ModelAndView("login");
            utenteNonTrovatoView.addObject("error", "Utente non trovato!");
            return utenteNonTrovatoView;
        }

    }

    // REGISTER
    @PostMapping("/register")
    public RedirectView registerAction(
            @RequestParam String piva,
            @RequestParam String nome,
            @RequestParam String sede,

            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String password
    ) {

        RegisterRequest registerRequest = new RegisterRequest(piva, nome, sede, email, username, password);
        ResponseEntity<?> response = registerUser(registerRequest);

        if (response.getBody() instanceof MessageResponse) {
            return new RedirectView("/login?message=Registrato");
        }

        return new RedirectView("/index");
    }
}
