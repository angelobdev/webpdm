package exm.sisinf.webpdm.restcontroller;

import com.fasterxml.jackson.annotation.JsonProperty;
import exm.sisinf.webpdm.model.Carrello;
import exm.sisinf.webpdm.model.Ordine;
import exm.sisinf.webpdm.model.support.CarrelloProdotto;
import exm.sisinf.webpdm.service.CarrelloService;
import exm.sisinf.webpdm.service.OrdineService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
class CarrelloProdottoDTO {
    @JsonProperty("prodottoID")
    private Integer prodottoID;

    @JsonProperty("quantita")
    private Integer quantita;
}

@RestController
public class CarrelloRestController {

    @Autowired
    private CarrelloService carrelloService;

    @Autowired
    private OrdineService ordineService;

    @PostMapping("/carrello/crea/{utenteID}")
    public ResponseEntity<?> creaNuovoCarrello(@PathVariable Integer utenteID) {
        return ResponseEntity.ok().body(carrelloService.creaCarrelloUtente(utenteID));
    }

    @PostMapping("/carrello/aggiungi/{utenteID}")
    public ResponseEntity<?> aggiungiProdotto(@PathVariable Integer utenteID, @RequestBody CarrelloProdottoDTO carrelloProdottoDTO) {
        Carrello carrello = carrelloService.getCarrelloUtente(utenteID);
        if (carrello == null) carrello = carrelloService.creaCarrelloUtente(utenteID);

        Collection<CarrelloProdotto> prodottiNelCarrello = carrello.getCarrelloProdotti();
        if (prodottiNelCarrello.isEmpty()) {
            carrelloService.aggiungiProdotto(carrello.getId(), carrelloProdottoDTO.getProdottoID(), carrelloProdottoDTO.getQuantita());
        } else {
            Integer quantitaTotale = carrelloProdottoDTO.getQuantita();

            for (var cp : prodottiNelCarrello) {
                if (cp.getProdotto().getId().equals(carrelloProdottoDTO.getProdottoID())) {
                    quantitaTotale += cp.getQuantita();
                }
            }

            carrelloService.modificaProdotto(carrello.getId(), carrelloProdottoDTO.getProdottoID(), quantitaTotale);
        }

        return ResponseEntity.ok(carrello);
    }

    @GetMapping("/carrello/{utenteID}")
    public ResponseEntity<?> getCarrelloUtente(@PathVariable Integer utenteID) {
        return ResponseEntity.ok().body(carrelloService.getCarrelloUtente(utenteID));
    }

    @GetMapping("/carrello/prodotti/{carrelloID}")
    public ResponseEntity<?> getProdottiNelCarrello(@PathVariable Integer carrelloID) {
        return ResponseEntity.ok(carrelloService.getProdottiNelCarrello(carrelloID));
    }

    @DeleteMapping("/carrello/elimina/{carrelloID}/{nomeProdotto}")
    public ResponseEntity<?> eliminaProdotto(@PathVariable Integer carrelloID, @PathVariable String nomeProdotto) {
        carrelloService.eliminaProdotto(carrelloID, nomeProdotto);

        Carrello carrello = carrelloService.getCarrello(carrelloID);

        if (carrello.getCarrelloProdotti().isEmpty()) {
            carrelloService.deleteCarrello(carrelloID);
        }

        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/carrello/svuota/{carrelloID}")
    public ResponseEntity<?> svuotaCarrello(@PathVariable Integer carrelloID) {
        carrelloService.deleteCarrello(carrelloID);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/carrello/acquista/{carrelloID}")
    public ResponseEntity<?> acquistaCarrello(@PathVariable Integer carrelloID) {

        Carrello carrello = carrelloService.getCarrello(carrelloID);
        List<CarrelloProdotto> prodotti = carrello.getCarrelloProdotti();

        double importoTotale = 0.0d;
        for (var p : prodotti) {
            importoTotale += p.getProdotto().getPrezzoAlKg() * p.getQuantita();
        }

        Ordine ordine = new Ordine(new Date(), (float) importoTotale, "ACQUISTATO", carrello);
        ordineService.createOrdine(ordine);

        return ResponseEntity.ok("ACQUISTATO");
    }

}
