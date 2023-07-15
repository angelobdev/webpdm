package exm.sisinf.webpdm.model.support;

import exm.sisinf.webpdm.model.Carrello;
import exm.sisinf.webpdm.model.Prodotto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class CarrelloProdottoId implements Serializable {

    @Column(name = "carrello_id")
    private Integer carrelloId;

    @Column(name = "prodotto_id")
    private Integer prodottoId;
}

@Entity
@Table(name = "carrelli_prodotti")
@Data
@NoArgsConstructor
public class CarrelloProdotto {

    @EmbeddedId
    private CarrelloProdottoId id;

    @ManyToOne
    @MapsId("carrelloId")
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
