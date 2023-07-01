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

    public enum ERuolo {
        ROLE_ADMIN, ROLE_USER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 32)
    private ERuolo nome;

}
