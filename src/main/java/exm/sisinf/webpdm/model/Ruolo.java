package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ruoli")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ruolo {

    @Id
    @GeneratedValue(generator = "ruoli_id_seq")
    @SequenceGenerator(name = "ruoli_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "grado")
    private Integer grado;

}
