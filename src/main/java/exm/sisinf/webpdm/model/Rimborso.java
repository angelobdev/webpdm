package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "rimborsi")
@Data
@NoArgsConstructor
public class Rimborso {

    @Id
    @GeneratedValue(generator = "rimborsi_id_seq")
    @SequenceGenerator(name = "rimborsi_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data_emissione")
    private Date dataEmissione;

    @OneToOne
    @JoinColumn(name = "reclamo_id")
    private Reclamo reclamo;

}
