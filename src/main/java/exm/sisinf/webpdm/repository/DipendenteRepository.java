package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, String> {
}
