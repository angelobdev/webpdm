package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.BustaPaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BustaPagaRepository extends JpaRepository<BustaPaga, Integer> {
}
