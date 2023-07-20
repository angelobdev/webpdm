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
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "username")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utente implements UserDetails {

    @Id
    @GeneratedValue(generator = "utenti_id_seq")
    @SequenceGenerator(name = "utenti_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    // Dati aziendali

    @Column(name = "partita_iva")
    private String partitaIVA;

    @Column(name = "nome_azienda")
    private String nomeAzienda;

    @Column(name = "sede_aziendale")
    private String sedeAziendale;

    // Info Account

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @ManyToOne
    private Ruolo ruolo;

    public Utente(String partitaIVA, String nomeAzienda, String sedeAziendale, String email, String username, String password, String avatar, Ruolo ruolo) {
        this.partitaIVA = partitaIVA;
        this.nomeAzienda = nomeAzienda;
        this.sedeAziendale = sedeAziendale;
        this.email = email;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.ruolo = ruolo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(ruolo.getNome()));
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
