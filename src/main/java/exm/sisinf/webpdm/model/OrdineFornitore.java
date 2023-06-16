package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "ordini_fornitori")
@Data
@NoArgsConstructor
public class OrdineFornitore {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "data")
    private Date data;

    @Column(name = "totale")
    private String stato;

    @Column(name = "totale")
    private Double totale;

    @OneToOne
    @Column(name = "fornitore_piva")
    private Fornitore fornitore;

}
