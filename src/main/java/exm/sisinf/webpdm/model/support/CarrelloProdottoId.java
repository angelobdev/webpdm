package exm.sisinf.webpdm.model.support;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarrelloProdottoId implements Serializable {

    @Column(name = "carrello_id")
    private Integer carrelloId;

    @Column(name = "prodotto_id")
    private Integer prodottoId;
}
