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

    @Column(name = "nome")
    private String nome;

    @Column(name = "prezzo_kg")
    private Double prezzoKg;

    @Column(name = "data_arrivo")
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
