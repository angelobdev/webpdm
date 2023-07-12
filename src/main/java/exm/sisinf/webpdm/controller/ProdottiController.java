package exm.sisinf.webpdm.controller;

import exm.sisinf.webpdm.model.Prodotto;
import exm.sisinf.webpdm.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @PostMapping("/prodotti/add")
    public RedirectView prodottiAddAction(@RequestParam String nome, @RequestParam Double prezzoKg, @RequestParam String dataArrivo, @RequestParam Integer quantita) throws ParseException {
        Prodotto prodotto = new Prodotto(nome, prezzoKg, new SimpleDateFormat("yyyy-MM-dd").parse(dataArrivo), quantita);
        prodottoService.createProdotto(prodotto);
        return new RedirectView("/dashboard/magazzino");
    }

    @DeleteMapping("/prodotti/delete/{id}")
    public RedirectView prodottiDeleteAction(@PathVariable Integer id) {
        prodottoService.deleteProdotto(id);
        return new RedirectView("/dashboard/magazzino");
    }

}
