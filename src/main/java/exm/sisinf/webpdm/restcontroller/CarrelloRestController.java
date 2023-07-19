package exm.sisinf.webpdm.restcontroller;

import com.fasterxml.jackson.annotation.JsonProperty;
import exm.sisinf.webpdm.model.Carrello;
import exm.sisinf.webpdm.model.Prodotto;
import exm.sisinf.webpdm.model.Utente;
import exm.sisinf.webpdm.service.CarrelloService;
import exm.sisinf.webpdm.service.UtenteService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/carrello/crea/{utenteID}")
    public ResponseEntity<?> creaNuovoCarrello(@PathVariable Integer utenteID) {
        return ResponseEntity.ok().body(carrelloService.creaCarrelloUtente(utenteID));
    }

    @PostMapping("/carrello/aggiungi/{utenteID}")
    public ResponseEntity<?> aggiungiProdotto(@PathVariable Integer utenteID, @RequestBody CarrelloProdottoDTO carrelloProdottoDTO) {
        Carrello carrello = carrelloService.getCarrelloUtente(utenteID);
        if (carrello == null) carrello = carrelloService.creaCarrelloUtente(utenteID);
        carrelloService.aggiungiProdotto(carrello.getId(), carrelloProdottoDTO.getProdottoID(), carrelloProdottoDTO.getQuantita());
        return ResponseEntity.ok(carrello);
    }

    @GetMapping("/carrello/{utenteID}")
    public ResponseEntity<?> getCarrelloUtente(@PathVariable Integer utenteID) {
        return ResponseEntity.ok().body(carrelloService.getCarrelloUtente(utenteID));
    }

    @GetMapping("/carrello/prodotti/{carrelloID}")
    ResponseEntity<List<Prodotto>> getProdottiNelCarrello(@PathVariable Integer carrelloID) {
        return ResponseEntity.ok(carrelloService.getProdottiNelCarrello(carrelloID));
    }

}
