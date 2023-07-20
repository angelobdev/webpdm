package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name = "fornitori")
@Data
@NoArgsConstructor
public class Fornitore {

    @Id
    @GeneratedValue(generator = "fornitori_id_seq")
    @SequenceGenerator(name = "fornitori_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "partita_iva")
    private String partitaIVA;

    @Column(name = "nome_azienda")
    private String nomeAzienda;

    @Column(name = "sede_aziendale")
    private String sedeAziendale;

}
