package exm.sisinf.webpdm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dipendenti")
@Data
@NoArgsConstructor
public class Dipendente {

    @Id
    @Column(name = "codice_fiscale")
    private String codiceFiscale;

    @Column(name = "mansioni")
    private String mansioni;

}
