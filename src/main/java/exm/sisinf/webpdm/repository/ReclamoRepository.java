package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Reclamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {
}
