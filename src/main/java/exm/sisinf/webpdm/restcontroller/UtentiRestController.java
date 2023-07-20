package exm.sisinf.webpdm.restcontroller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import exm.sisinf.webpdm.model.Utente;
import exm.sisinf.webpdm.service.UtenteService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
class ModificaUtenteDTO {

    @JsonProperty("partitaIVA")
    private String partitaIVA;

    @JsonProperty("nomeAzienda")
    private String nomeAzienda;

    @JsonProperty("sedeAziendale")
    private String sedeAziendale;

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("avatar")
    private String avatar;

}

@RestController
public class UtentiRestController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/utenti/modifica/{utenteID}")
    public ResponseEntity<?> modificaUtente(@PathVariable Integer utenteID, @RequestBody ModificaUtenteDTO utenteModificato) {
        Utente utente = utenteService.getUtente(utenteID);

        utente.setPartitaIVA(utenteModificato.getPartitaIVA());
        utente.setNomeAzienda(utenteModificato.getNomeAzienda());
        utente.setSedeAziendale(utenteModificato.getSedeAziendale());

        utente.setEmail(utenteModificato.getEmail());
        utente.setPassword(passwordEncoder.encode(utenteModificato.getPassword()));
        utente.setAvatar(utenteModificato.getAvatar());

        return ResponseEntity.ok(utenteService.updateUtente(utenteID, utente));
    }

}
