package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "ordini_fornitori")
@Data
@NoArgsConstructor
public class OrdineFornitore {

    @Id
    @GeneratedValue(generator = "ordini_fornitori_id_seq")
    @SequenceGenerator(name = "ordini_fornitori_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "data")
    private Date data;

    @Column(name = "stato_ordine")
    private String statoOrdine;

    @Column(name = "importo_totale")
    private Double importoTotale;

    @OneToOne
    @JoinColumn(name = "fornitore_id")
    private Fornitore fornitore;

    @ElementCollection
    @CollectionTable(
            name = "approvvigionamenti_ordini_fornitori",
            joinColumns = @JoinColumn(name = "ordine_fornitore_id", referencedColumnName = "id")
    )
    @MapKeyColumn(name = "quantita")
    private Map<Approvvigionamento, Integer> approvvigionamentiQuantita;

}
