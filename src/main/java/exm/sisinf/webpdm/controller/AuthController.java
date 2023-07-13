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

    // LOGIN
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("error", "");
        return "login";
    }

    // REGISTER
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("error", "");
        return "register";
    }

}
