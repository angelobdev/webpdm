package exm.sisinf.webpdm.restcontroller;

import exm.sisinf.webpdm.model.BustaPaga;
import exm.sisinf.webpdm.model.Dipendente;
import exm.sisinf.webpdm.service.BustaPagaService;
import exm.sisinf.webpdm.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/bustepaga")
public class BustePagaRestController {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private BustaPagaService bustaPagaService;

    @PostMapping("/add")
    public ModelAndView creaBustaPaga(@RequestParam Double importoEmesso, @RequestParam String dataEmissione, @RequestParam String codiceFiscale) throws ParseException {
        Dipendente dipendente = dipendenteService.getDipendente(codiceFiscale);
        bustaPagaService.createBustaPaga(new BustaPaga(importoEmesso, new SimpleDateFormat("yyyy-MM-dd").parse(dataEmissione), dipendente));
        return new ModelAndView("redirect:/dashboard/dipendenti");
    }

//    @PostMapping("/modifica/{id}")
//    public ResponseEntity<?> modificaBustaPaga(@PathVariable Integer id, @RequestBody BustaPaga bustaPaga) {
//        return ResponseEntity.ok(bustaPagaService.updateBustaPaga(id, bustaPaga));
//    }

    @DeleteMapping("/elimina/{id}")
    public RedirectView eliminaDipendente(@PathVariable Integer id) {
        bustaPagaService.deleteBustaPaga(id);
        return new RedirectView("redirect:/dashboard/dipendenti");
    }

}
