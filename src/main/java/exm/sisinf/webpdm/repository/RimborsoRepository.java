package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Rimborso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RimborsoRepository extends JpaRepository<Rimborso, Integer> {
}
