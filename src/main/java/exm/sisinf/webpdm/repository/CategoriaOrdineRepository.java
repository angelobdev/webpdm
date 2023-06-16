package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.CategoriaOrdine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaOrdineRepository extends JpaRepository<CategoriaOrdine, Integer> {
}
