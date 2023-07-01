package exm.sisinf.webpdm.payload.request;


import exm.sisinf.webpdm.model.Ruolo;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

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
