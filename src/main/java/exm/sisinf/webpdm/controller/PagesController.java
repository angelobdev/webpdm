package exm.sisinf.webpdm.controller;

import exm.sisinf.webpdm.model.Prodotto;
import exm.sisinf.webpdm.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PagesController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping()
    public String root() {
        return "index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        return root();
    }

    @GetMapping("/ricette")
    public String ricette() {
        return "ricette";
    }

    @GetMapping("/contatti")
    public String contatti() {
        return "contatti";
    }

    @GetMapping("/catalogo")
    public String catalogo(Model model) {
        List<Prodotto> prodotti = prodottoService.getAllProdotti();
        model.addAttribute("prodotti", prodotti);
        return "catalogo";
    }

}
