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
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "data_emissione")
    private Date dataEmissione;

}
