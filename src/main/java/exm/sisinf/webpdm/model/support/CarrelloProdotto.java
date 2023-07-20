package exm.sisinf.webpdm.model.support;

import com.fasterxml.jackson.annotation.JsonIgnore;
import exm.sisinf.webpdm.model.Carrello;
import exm.sisinf.webpdm.model.Prodotto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "carrelli_prodotti")
@Data
@NoArgsConstructor
public class CarrelloProdotto {

    @EmbeddedId
    private CarrelloProdottoId id;

    @ManyToOne
    @MapsId("carrelloId")
    @JsonIgnore
    private Carrello carrello;

    @ManyToOne
    @MapsId("prodottoId")
    private Prodotto prodotto;

    @Column(name = "quantita")
    private Integer quantita;

    public CarrelloProdotto(Carrello carrello, Prodotto prodotto, Integer quantita) {
        this.id = new CarrelloProdottoId(carrello.getId(), prodotto.getId());
        this.carrello = carrello;
        this.prodotto = prodotto;
        this.quantita = quantita;
    }
}
