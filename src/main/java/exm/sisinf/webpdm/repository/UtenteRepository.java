package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    Optional<Utente> findByUsername(String username);

    boolean existsByUsername(String username);
}
