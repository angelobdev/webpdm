package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Vendita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenditaRepository extends JpaRepository<Vendita, Integer> {
}
