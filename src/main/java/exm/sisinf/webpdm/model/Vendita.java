package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "vendite")
@Data
@NoArgsConstructor
public class Vendita {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "data")
    private Date data;

    @Column(name = "totale")
    private Double totale;

    @ManyToMany
    @JoinTable(
            name = "acquisti",
            joinColumns = @JoinColumn(name = "vendita_id"),
            inverseJoinColumns = @JoinColumn(name = "prodotto_id")
    )
    private Collection<Prodotto> prodotti;


    @ManyToOne
    @Column(name = "codice_coupon")
    private Coupon coupon;

}
