package exm.sisinf.webpdm.restcontroller;

import exm.sisinf.webpdm.model.Ordine;
import exm.sisinf.webpdm.service.CarrelloService;
import exm.sisinf.webpdm.service.OrdineService;
import exm.sisinf.webpdm.service.ReclamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdiniRestController {

    @Autowired
    private OrdineService ordineService;

    @Autowired
    private ReclamoService reclamoService;

    @Autowired
    private CarrelloService carrelloService;

    @DeleteMapping("/ordini/elimina/{ordineID}")
    public ResponseEntity<?> eliminaOrdine(@PathVariable Integer ordineID) {
        Ordine ordine = ordineService.getOrdine(ordineID);
        reclamoService.deleteReclamoByOrdine(ordineID);
        ordineService.deleteOrdine(ordineID);
        Integer carrelloID = ordine.getCarrello().getId();
        carrelloService.deleteCarrello(carrelloID);
        return ResponseEntity.ok("OK");
    }

}
