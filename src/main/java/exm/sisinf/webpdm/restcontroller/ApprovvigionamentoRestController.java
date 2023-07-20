package exm.sisinf.webpdm.restcontroller;

import exm.sisinf.webpdm.model.Approvvigionamento;
import exm.sisinf.webpdm.service.ApprovvigionamentoService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/approvvigionamenti")
public class ApprovvigionamentoRestController {

    private static final Logger logger = LoggerFactory.getLogger(ApprovvigionamentoRestController.class);


    @Autowired
    private ApprovvigionamentoService approvvigionamentoService;

    @PostMapping("/add")
    public RedirectView creaApprovvigionamento(@RequestParam String nomeProdotto, @RequestParam Integer quantita) {
        logger.warn("Aggiungo " + nomeProdotto);
        approvvigionamentoService.createApprovvigionamento(new Approvvigionamento(nomeProdotto, quantita));
        return new RedirectView("/dashboard/approvvigionamenti");
    }

    @GetMapping("/cerca/{nome}")
    public ResponseEntity<?> cercaApprovvigionamenti(@PathVariable String nome) {
        logger.warn("Cerco" + nome);
        return ResponseEntity.ok(approvvigionamentoService.cercaApprovvigionamenti(nome));
    }

    @PostMapping("/modifica/{id}")
    public ResponseEntity<?> modificaApprovvigionamento(@PathVariable Integer id, @RequestBody Approvvigionamento approvvigionamento) {
        return ResponseEntity.ok(approvvigionamentoService.updateApprovvigionamento(id, approvvigionamento));
    }

    @DeleteMapping("/elimina/{id}")
    public RedirectView eliminaApprovvigionamento(@PathVariable Integer id) {
        approvvigionamentoService.deleteApprovvigionamento(id);
        return new RedirectView("/dashboard/approvvigionamenti");
    }

}
