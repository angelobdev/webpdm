package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.CategoriaFornitore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaFornitoreRepository extends JpaRepository<CategoriaFornitore, Integer> {
}
