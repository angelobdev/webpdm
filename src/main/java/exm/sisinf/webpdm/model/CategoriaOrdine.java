package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name = "categorie_ordine")
@Data
@NoArgsConstructor
public class CategoriaOrdine {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @ManyToMany
    @JoinTable(
            name = "categorie_fornitori",
            joinColumns = @JoinColumn(name = "categoria_id"),
            inverseJoinColumns = @JoinColumn(name = "fornitore_piva")
    )
    private Collection<Fornitore> fornitori;

}
