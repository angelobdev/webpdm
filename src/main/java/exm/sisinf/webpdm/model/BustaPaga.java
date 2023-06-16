package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "buste_paga")
@Data
@NoArgsConstructor
public class BustaPaga {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "totale")
    private Double totale;

    @Column(name = "data_emissione")
    private Date dataEmissione;

    @OneToOne
    @Column(name = "dipendente_cf")
    private Dipendente dipendente;

}
