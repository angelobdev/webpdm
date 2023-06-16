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
    @Column(name="codice_sconto")
    private String codiceSconto;

    @Column(name="sconto_applicato")
    private Double scontoApplicato;

    @Column(name="data_scadenza")
    private Date dataScadenza;

    @Column(name="prezzo_minimo")
    private Double prezzoMinimo;

    @OneToMany
    private Collection<Vendita> vendite;

}
