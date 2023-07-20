package exm.sisinf.webpdm.restcontroller;

import com.fasterxml.jackson.annotation.JsonProperty;
import exm.sisinf.webpdm.model.Ordine;
import exm.sisinf.webpdm.model.Reclamo;
import exm.sisinf.webpdm.service.OrdineService;
import exm.sisinf.webpdm.service.ReclamoService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
class ReclamoDTO {
    @JsonProperty("ordine")
    public Integer ordineID;

    @JsonProperty("segnalazione")
    public String messaggio;

    @JsonProperty("data")
    public String data;
}

@RestController
public class ReclamiRestController {

    @Autowired
    private ReclamoService reclamoService;

    @Autowired
    private OrdineService ordineService;

    @PostMapping("/reclami/add")
    public ResponseEntity<?> creaReclamo(@RequestBody ReclamoDTO reclamoDTO) throws ParseException {
        Ordine ordine = ordineService.getOrdine(reclamoDTO.getOrdineID());
        Date data = new SimpleDateFormat("yyyy-MM-dd").parse(reclamoDTO.getData());
        Reclamo reclamo = new Reclamo(reclamoDTO.getMessaggio(), data, "APERTO", ordine);
        return ResponseEntity.ok(reclamoService.createReclamo(reclamo));
    }

}
