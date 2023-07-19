package exm.sisinf.webpdm.restcontroller;

import exm.sisinf.webpdm.controller.ContentController;
import exm.sisinf.webpdm.model.Prodotto;
import exm.sisinf.webpdm.service.ProdottoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/prodotti")
public class ProdottiRestController {

    private static final Logger logger = LoggerFactory.getLogger(ProdottiRestController.class);

    @Autowired
    private ProdottoService prodottoService;

    @PostMapping("/add")
    public RedirectView prodottiAddAction(
            @RequestParam String nome,
            @RequestParam Double prezzoKg,
            @RequestParam String dataArrivo,
            @RequestParam Integer quantita,
            @RequestParam String descrizione,
            @RequestParam String immagine) throws ParseException {
        Prodotto prodotto = new Prodotto(
                nome,
                prezzoKg,
                new SimpleDateFormat("yyyy-MM-dd").parse(dataArrivo),
                quantita,
                descrizione,
                immagine);

        prodottoService.createProdotto(prodotto);
        return new RedirectView("/dashboard/magazzino");
    }

    @GetMapping("/cerca/{nome}")
    public List<Prodotto> getAllProdotti(@PathVariable String nome) {
        return prodottoService.cercaProdotto(nome);
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView prodottiDeleteAction(@PathVariable Integer id) {
        prodottoService.deleteProdotto(id);
        return new RedirectView("/dashboard/magazzino");
    }

    @PostMapping("/modifica/{id}")
    public Prodotto modificaProdotto(@PathVariable Integer id, @RequestBody Prodotto prodotto) {
        return prodottoService.updateProdotto(id, prodotto);
    }

}
