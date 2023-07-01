package exm.sisinf.webpdm.controller.components;

import exm.sisinf.webpdm.auth.AuthTokenService;
import exm.sisinf.webpdm.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigatorController {

    @Autowired
    private AuthTokenService authTokenService;

    @GetMapping("/nav")
    public String navigator(Model model) {
        Utente utente = authTokenService.getUtente();
        String username = "";

        if (utente != null) {
            String utenteUsername = utente.getUsername();
            if (utenteUsername != null && !utenteUsername.isEmpty()) username = utenteUsername;
            else username = "NOT_FOUND";
        }

        model.addAttribute("username", username);

        return "components/navigator";
    }

}
