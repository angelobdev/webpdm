package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "prodotti")
@Data
@NoArgsConstructor
public class Prodotto {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "id")
    private String nome;

    @Column(name = "id")
    private Double prezzoKg;

    @Column(name = "id")
    private Date dataArrivo;

    @Column(name = "quantita")
    private Integer quantita;

    @ManyToMany
    @JoinTable(
            name = "acquisti",
            joinColumns = @JoinColumn(name = "prodotto_id"),
            inverseJoinColumns = @JoinColumn(name = "vendita_id")
    )
    private Collection<Vendita> vendite;

}
