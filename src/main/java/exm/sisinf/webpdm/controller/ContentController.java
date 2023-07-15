package exm.sisinf.webpdm.controller;

import exm.sisinf.webpdm.auth.AuthTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @Autowired
    private AuthTokenService authTokenService;

    @GetMapping("/areaclienti")
    public String areaClienti(Model model, HttpServletRequest request) {
        String token = authTokenService.retrieve(request);
        String username = authTokenService.getUtente(token).getUsername();

        model.addAttribute("username", username);

        return "areaclienti";
    }
}
