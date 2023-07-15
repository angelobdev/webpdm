package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "ordini")
@Data
@NoArgsConstructor
public class Ordine {

    @Id
    @GeneratedValue(generator = "ordini_id_seq")
    @SequenceGenerator(name = "ordini_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data")
    private Date data;

    @Column(name = "importo_totale")
    private Float importoTotale;

    @Column(name = "stato_ordine")
    private String statoOridine;

    @OneToOne
    @JoinColumn(name = "carrello_id")
    private Carrello carrello;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ORDINE #" + id);
        sb.append("\nData: " + data);
        sb.append("\nTotale: " + importoTotale);

        sb.append("\n\nCarrello: " + carrello);

        return sb.toString();
    }
}
