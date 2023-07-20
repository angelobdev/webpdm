package exm.sisinf.webpdm.restcontroller;

import exm.sisinf.webpdm.model.Dipendente;
import exm.sisinf.webpdm.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiRestController {

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("/add")
    public ModelAndView creaDipendente(@RequestParam String codiceFiscale, @RequestParam String mansioni) {
        dipendenteService.createDipendente(new Dipendente(codiceFiscale, mansioni));
        return new ModelAndView("redirect:/dashboard/dipendenti");
    }

    @PostMapping("/modifica/{id}")
    public ResponseEntity<?> modificaDipendente(@PathVariable Integer id, @RequestBody Dipendente dipendente) {
        return ResponseEntity.ok(dipendenteService.updateDipendente(id, dipendente));
    }

    @DeleteMapping("/elimina/{id}")
    public RedirectView eliminaDipendente(@PathVariable Integer id) {
        dipendenteService.deleteDipendente(id);
        return new RedirectView("redirect:/dashboard/dipendenti");
    }

}
