package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "vendite")
@Data
@NoArgsConstructor
public class Vendita {

    @Id
    @GeneratedValue(generator = "vendite_id_seq")
    @SequenceGenerator(name = "vendite_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data")
    private Date data;

    @Column(name = "importo_totale")
    private Double importoTotale;

    @ElementCollection
    @CollectionTable(
            name = "vendite_prodotti",
            joinColumns = @JoinColumn(name = "vendita_id", referencedColumnName = "id")
    )
    @MapKeyColumn(name = "quantita")
    private Map<Prodotto, Integer> prodottiQuantita;

}
