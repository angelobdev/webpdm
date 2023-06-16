package exm.sisinf.webpdm.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "spedizioni")
@Data
@NoArgsConstructor
public class Spedizione {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "data_spedizione")
    private Date dataSpedizione;

    @Column(name = "data_consegna")
    private Date dataConsegna;

    @Column(name = "destinazione")
    private String destinazione;

    @Column(name = "stato")
    private String stato;

   @OneToOne
   private VenditaIngrosso vendita;
}
