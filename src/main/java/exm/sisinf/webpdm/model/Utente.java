package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "utenti",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utente implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

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

    @OneToOne
    private Ruolo ruolo;

    @Column(name = "numero_ordini")
    private Integer numeroOrdini;

    public Utente(String piva, String nome, String sede, String username, String password) {
        this.piva = piva;
        this.nome = nome;
        this.sede = sede;
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(ruolo.getNome().name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
