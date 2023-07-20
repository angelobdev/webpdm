package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RuoloRepository extends JpaRepository<Ruolo, Integer> {
    Optional<Ruolo> findByNome(String nome);
}
