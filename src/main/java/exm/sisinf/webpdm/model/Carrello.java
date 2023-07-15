package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "carrelli")
@Data
@NoArgsConstructor
public class Carrello {

    @Id
    @GeneratedValue(generator = "carrelli_id_seq")
    @SequenceGenerator(name = "carrelli_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "acquistato")
    private Boolean acquistato;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ElementCollection
    @CollectionTable(
            name = "carrelli_prodotti",
            joinColumns = @JoinColumn(name = "carrello_id", referencedColumnName = "id")
    )
    @MapKeyColumn(name = "quantita")
    private Map<Prodotto, Integer> prodottiQuantita;

}
