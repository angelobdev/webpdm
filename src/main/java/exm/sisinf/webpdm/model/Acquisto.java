package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "acquisti")
@Data
@NoArgsConstructor
public class Acquisto {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @Column(name = "vendita_id")
    private Vendita vendita;

    @OneToOne
    @Column(name = "prodotto_id")
    private Prodotto prodotto;

    @Column(name = "quantita")
    private  Integer quantita;

}
