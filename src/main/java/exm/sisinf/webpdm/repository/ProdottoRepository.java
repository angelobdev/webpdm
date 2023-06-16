package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {
}
