package exm.sisinf.webpdm.payload.request;


import exm.sisinf.webpdm.model.Ruolo;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank
    private String partitaIVA;

    @NotBlank
    private String nomeAzienda;

    @NotBlank
    private String sedeAziendale;

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String avatar = "";

    private String ruolo = "ROLE_USER";

    public RegisterRequest(String partitaIVA, String nomeAzienda, String sedeAziendale, String email, String username, String password) {
        this.partitaIVA = partitaIVA;
        this.nomeAzienda = nomeAzienda;
        this.sedeAziendale = sedeAziendale;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
