package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vendite_ingrosso")
@Data
@NoArgsConstructor
public class VenditaIngrosso {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @Column(name = "vendita_id")
    private Vendita vendita;

    @OneToOne
    @Column(name = "utente_id")
    private Utente utente;

    @OneToOne
    @Column(name = "spedizione_id")
    private Spedizione spedizione;

    @Column(name = "numero_fattura")
    private Integer numeroFattura;
}
