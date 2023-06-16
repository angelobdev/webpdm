package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name = "fornitori")
@Data
@NoArgsConstructor
public class Fornitore {

    @Id
    @Column(name = "piva")
    private String partitaIVA;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sede")
    private String sede;

    @Column(name = "numero_ordini")
    private Integer numeroOrdini;

    @ManyToMany
    @JoinTable(
            name = "categorie_fornitori",
            joinColumns = @JoinColumn(name = "fornitore_piva"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Collection<CategoriaOrdine> categorie;

}
