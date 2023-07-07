package exm.sisinf.webpdm.controller;

import exm.sisinf.webpdm.model.Prodotto;
import exm.sisinf.webpdm.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProdottiController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping("/prodotti")
    public String prodotti(Model model) {
        List<Prodotto> prodotti = prodottoService.getAllProdotti();
        model.addAttribute("prodotti", prodotti);
        return "prodotti";
    }

}
