package exm.sisinf.webpdm.restcontroller;

import exm.sisinf.webpdm.auth.JwtUtils;
import exm.sisinf.webpdm.model.Ruolo;
import exm.sisinf.webpdm.model.Utente;
import exm.sisinf.webpdm.payload.request.LoginRequest;
import exm.sisinf.webpdm.payload.request.RegisterRequest;
import exm.sisinf.webpdm.payload.response.JwtResponse;
import exm.sisinf.webpdm.payload.response.MessageResponse;
import exm.sisinf.webpdm.repository.RuoloRepository;
import exm.sisinf.webpdm.repository.UtenteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    RuoloRepository ruoloRepository;

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
                registerRequest.getPiva(),
                registerRequest.getNome(),
                registerRequest.getSede(),
                registerRequest.getUsername(),
                encoder.encode(registerRequest.getPassword())
        );

        Ruolo ruoloUtente = ruoloRepository.findByNome(Ruolo.ERuolo.ROLE_USER).orElse(null);
        utente.setRuolo(ruoloUtente);
        utente.setNumeroOrdini(0);
        utenteRepository.save(utente);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
