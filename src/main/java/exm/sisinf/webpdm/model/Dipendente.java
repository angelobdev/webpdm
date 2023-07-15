package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dipendenti")
@Data
@NoArgsConstructor
public class Dipendente {

    @Id
    @GeneratedValue(generator = "dipendenti_id_seq")
    @SequenceGenerator(name = "dipendenti_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "codice_fiscale")
    private String codiceFiscale;

    @Column(name = "mansioni")
    private String mansioni;

}
