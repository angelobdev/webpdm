package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categorie_fornitori")
@Data
@NoArgsConstructor
public class CategoriaFornitore {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private Fornitore fornitore;

    @OneToOne
    private CategoriaOrdine categoria;

}
