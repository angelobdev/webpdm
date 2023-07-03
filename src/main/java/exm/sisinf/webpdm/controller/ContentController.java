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

    @GetMapping("/content")
    public String content(Model model, HttpServletRequest request) {
        String token = authTokenService.retrieve(request);
        model.addAttribute("username", authTokenService.getUtente(token).getUsername());
        return "content";
    }
}
