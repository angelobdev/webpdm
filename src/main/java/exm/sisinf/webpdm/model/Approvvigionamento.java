package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "approvvigionamenti")
@Data
@NoArgsConstructor
public class Approvvigionamento {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @OneToOne
    @JoinColumn(name = "categoria")
    private CategoriaOrdine categoria;

    @Column(name = "quantita")
    private Integer quantita;

}
