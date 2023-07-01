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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    AuthRestController authRestController;

    // LOGIN

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public RedirectView actionLogin(@RequestParam String username, @RequestParam String password) {
        LoginRequest loginInfo = new LoginRequest(username, password);

        logger.info("LOGIN BY: {}", loginInfo);

        try {
            ResponseEntity<?> response = authRestController.authenticateUser(loginInfo);

            if (response.getBody() instanceof JwtResponse jwtRes) {

                logger.info("LOGGED IN");

                authTokenService.store(jwtRes.getToken());

                return switch (authTokenService.getUtente().getRuolo().getNome()) {
                    case ROLE_ADMIN -> new RedirectView("/dashboard");
                    case ROLE_USER -> new RedirectView("/content");
                };
            }
        } catch (Exception e) {
            logger.info("INVALID LOGIN, Error: {}", e.getMessage());
            return new RedirectView("/index");
        }

        return new RedirectView("/index");
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

    // LOGOUT

    @GetMapping("/logout")
    public RedirectView logout() {
        authTokenService.invalidate();
        return new RedirectView("/logout");
    }

}
