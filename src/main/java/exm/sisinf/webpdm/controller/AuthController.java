package exm.sisinf.webpdm.controller;

import exm.sisinf.webpdm.auth.AuthEntryPointJwt;
import exm.sisinf.webpdm.auth.AuthTokenService;
import exm.sisinf.webpdm.model.Ruolo;
import exm.sisinf.webpdm.payload.request.LoginRequest;
import exm.sisinf.webpdm.payload.request.RegisterRequest;
import exm.sisinf.webpdm.payload.response.JwtResponse;
import exm.sisinf.webpdm.payload.response.MessageResponse;
import exm.sisinf.webpdm.repository.RuoloRepository;
import exm.sisinf.webpdm.restcontroller.AuthRestController;
import exm.sisinf.webpdm.service.UtenteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private AuthRestController authRestController;

    @Autowired
    private UtenteService utenteService;

    // LOGIN

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("error", "");
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView actionLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse httpResponse) {

        // Controllo se l'utente esiste
        if (utenteService.existsByUsername(username)) {

            LoginRequest loginInfo = new LoginRequest(username, password);

            // Tento il login
            try {
                ResponseEntity<?> response = authRestController.authenticateUser(loginInfo);

                if (response.getBody() instanceof JwtResponse jwtRes) {

                    String token = jwtRes.getToken();

                    // Il login ha successo
                    authTokenService.store(httpResponse, token); // Salvo il token di accesso
                    Ruolo.ERuolo ruolo = authTokenService.getUtente(token).getRuolo().getNome();
                    logger.info("RUOLO UTENTE: {}", ruolo.name());

                    return new ModelAndView("redirect:/content");

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
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public RedirectView registerAction(@RequestParam String piva, @RequestParam String nome, @RequestParam String sede, @RequestParam String username, @RequestParam String password) {

        RegisterRequest registerRequest = new RegisterRequest(piva, nome, sede, username, password);
        ResponseEntity<?> response = authRestController.registerUser(registerRequest);

        if (response.getBody() instanceof MessageResponse) {
            return new RedirectView("/login?message=Registrato");
        }

        return new RedirectView("/index");
    }

}
