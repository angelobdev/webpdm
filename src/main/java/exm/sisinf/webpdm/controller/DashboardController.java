package exm.sisinf.webpdm.controller;

import exm.sisinf.webpdm.auth.AuthTokenService;
import exm.sisinf.webpdm.service.ProdottoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpServletRequest request) {
        return magazzino(model, request);
    }

    @GetMapping("/dashboard/magazzino")
    public String magazzino(Model model, HttpServletRequest request) {
        String token = authTokenService.retrieve(request);
        model.addAttribute("username", authTokenService.getUtente(token).getUsername());

        model.addAttribute("prodotti", prodottoService.getAllProdotti());

        return "dashboard/magazzino";
    }

    @GetMapping("/dashboard/dipendenti")
    public String dipendenti(Model model, HttpServletRequest request) {
        String token = authTokenService.retrieve(request);
        model.addAttribute("username", authTokenService.getUtente(token).getUsername());
        return "dashboard/dipendenti";
    }

}
