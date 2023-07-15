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
    @GeneratedValue(generator = "reclami_id_seq")
    @SequenceGenerator(name = "reclami_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "messaggio")
    private String messaggio;

    @Column(name = "data")
    private Date data;

    @Column(name = "stato")
    private String stato;

    @OneToOne
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;
}
