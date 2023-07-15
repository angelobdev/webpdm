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
    @GeneratedValue(generator = "buste_paga_id_seq")
    @SequenceGenerator(name = "buste_paga_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "importo_emesso")
    private Double importoEmesso;

    @Column(name = "data_emissione")
    private Date dataEmissione;

    @OneToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

}
