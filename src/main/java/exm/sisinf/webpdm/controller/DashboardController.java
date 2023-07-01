package exm.sisinf.webpdm.controller;

import exm.sisinf.webpdm.auth.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private AuthTokenService authTokenService;

    @GetMapping("/dashboard")
    public String content(Model model) {
        model.addAttribute("username", authTokenService.getUtente().getUsername());
        return "dashboard";
    }

}
