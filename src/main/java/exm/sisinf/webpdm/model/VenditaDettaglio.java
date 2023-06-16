package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vendite_dettaglio")
@Data
@NoArgsConstructor
public class VenditaDettaglio {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @Column(name = "vendita_id")
    private Vendita vendita;
}
