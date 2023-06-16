package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "reclami")
@Data
@NoArgsConstructor
public class Reclamo {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private Integer email;

    @Column(name = "segnalazione")
    private String segnalazione;

    @Column(name = "data")
    private Date data;

    @Column(name = "stato")
    private String stato;

    @OneToOne
    @JoinColumn(name = "vendita_id")
    private Vendita vendita;

    @OneToOne
    @JoinColumn(name = "rimborso_id")
    private Rimborso rimborso;
}
