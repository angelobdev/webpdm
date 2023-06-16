package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Acquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto, Integer> {
}
