package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "utenti")
@Data
@NoArgsConstructor
public class Utente {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    //Dati aziendali

    @Column(name = "piva")
    private String piva;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sede")
    private String sede;

    //Info accesso

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "numero_ordini")
    private Integer numeroOrdini;

}
