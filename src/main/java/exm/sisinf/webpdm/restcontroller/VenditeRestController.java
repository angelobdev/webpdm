package exm.sisinf.webpdm.restcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exm.sisinf.webpdm.model.Prodotto;
import exm.sisinf.webpdm.model.Vendita;
import exm.sisinf.webpdm.service.ProdottoService;
import exm.sisinf.webpdm.service.VenditaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class VenditeRestController {

    @Autowired
    private VenditaService venditaService;

    @Autowired
    private ProdottoService prodottoService;

    @PostMapping("/vendite/add")
    public ResponseEntity<?> registraVendita(@RequestBody Map<Integer, Integer> mappaProdotti) throws JsonProcessingException {

//        Map<Integer, Integer> prodottiQuantitaMap = new ObjectMapper().readValue(data, new TypeReference<Map<Integer, Integer>>() {
//        });

        double prezzo = 0.0d;
        Map<Prodotto, Integer> prdqt = new HashMap<>();
        for (var pq : mappaProdotti.entrySet()) {
            Prodotto p = prodottoService.getProdotto(pq.getKey());
            prezzo += p.getPrezzoAlKg() * pq.getValue();
            prdqt.put(p, pq.getValue());
        }

        Vendita vendita = new Vendita(new Date(), prezzo, prdqt);

        return ResponseEntity.ok(venditaService.createVendita(vendita));
    }

    @GetMapping("/vendite/all")
    public ResponseEntity<?> getVendite() {
        return ResponseEntity.ok(venditaService.getAllVendite());
    }

    @PostMapping("/vendite/modifica/{id}")
    public ResponseEntity<?> modificaVendita(@PathVariable Integer id, @RequestBody Vendita vendita) {
        return ResponseEntity.ok(venditaService.updateVendita(id, vendita));
    }

    @DeleteMapping("/vendite/elimina/{id}")
    public ResponseEntity<?> registraVendita(@PathVariable Integer id) {
        venditaService.deleteVendita(id);
        return ResponseEntity.ok("OK");
    }

}
