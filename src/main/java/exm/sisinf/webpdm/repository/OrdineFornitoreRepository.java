package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.OrdineFornitore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineFornitoreRepository extends JpaRepository<OrdineFornitore, Integer> {
}
