package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="coupon")
@Data
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(generator = "coupon_id_seq")
    @SequenceGenerator(name = "coupon_id_seq", allocationSize = 1)
    @Column(name="id")
    private Integer id;

    @Column(name="codice_sconto")
    private String codiceSconto;

    @Column(name="sconto_applicato")
    private Double scontoApplicato;

    @Column(name="data_scadenza")
    private Date dataScadenza;

    @Column(name="prezzo_minimo")
    private Double prezzoMinimo;

}
