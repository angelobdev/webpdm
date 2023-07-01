package exm.sisinf.webpdm.payload.request;


import exm.sisinf.webpdm.model.Ruolo;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    public RegisterRequest(String piva, String nome, String sede, String username, String password) {
        this.piva = piva;
        this.nome = nome;
        this.sede = sede;
        this.username = username;
        this.password = password;
    }

    @NotBlank
    private String piva;

    @NotBlank
    private String nome;

    @NotBlank
    private String sede;


    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String ruolo = Ruolo.ERuolo.ROLE_USER.name();


    private Integer numeroOrdini = 0;
}
