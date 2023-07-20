package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Table(name = "approvvigionamenti")
@Data
@NoArgsConstructor
public class Approvvigionamento {

    @Id
    @GeneratedValue(generator = "approvvigionamenti_id_seq")
    @SequenceGenerator(name = "approvvigionamenti_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome_prodotto")
    private String nomeProdotto;

    @Column(name = "quantita")
    private Integer quantita;

    public Approvvigionamento(String nomeProdotto, Integer quantita) {
        this.nomeProdotto = nomeProdotto;
        this.quantita = quantita;
    }
}
