package exm.sisinf.webpdm.controller;

import exm.sisinf.webpdm.auth.AuthTokenService;
import exm.sisinf.webpdm.service.ApprovvigionamentoService;
import exm.sisinf.webpdm.service.BustaPagaService;
import exm.sisinf.webpdm.service.DipendenteService;
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

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private ApprovvigionamentoService approvvigionamentoService;

    @Autowired
    private BustaPagaService bustaPagaService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpServletRequest request) {
        String token = authTokenService.retrieve(request);
        model.addAttribute("username", authTokenService.getUtente(token).getUsername());

        return "dashboard/index";
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

        model.addAttribute("dipendenti", dipendenteService.getAllDipendenti());
        model.addAttribute("bustepaga", bustaPagaService.getAllBustePaga());

        return "dashboard/dipendenti";
    }

    @GetMapping("/dashboard/approvvigionamenti")
    public String approvvigionamenti(Model model, HttpServletRequest request) {
        String token = authTokenService.retrieve(request);
        model.addAttribute("username", authTokenService.getUtente(token).getUsername());

        model.addAttribute("approvvigionamenti", approvvigionamentoService.getAllApprovvigionamenti());

        return "dashboard/approvvigionamenti";
    }

    @GetMapping("/dashboard/ordini")
    public String ordini(Model model, HttpServletRequest request) {
        String token = authTokenService.retrieve(request);
        model.addAttribute("username", authTokenService.getUtente(token).getUsername());

        return "dashboard/ordini";
    }


    @GetMapping("/dashboard/vendite")
    public String vendite(Model model, HttpServletRequest request) {
        String token = authTokenService.retrieve(request);
        model.addAttribute("username", authTokenService.getUtente(token).getUsername());

        return "dashboard/vendite";
    }


}
