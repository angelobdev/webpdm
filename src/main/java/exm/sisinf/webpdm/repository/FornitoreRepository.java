package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Fornitore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornitoreRepository extends JpaRepository<Fornitore, Integer> {
}
