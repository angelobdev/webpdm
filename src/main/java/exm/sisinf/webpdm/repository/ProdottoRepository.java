package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {

    Optional<Prodotto> findByNome(String nome);

    Optional<List<Prodotto>> findAllByNomeLikeIgnoreCaseOrNomeContainingIgnoreCase(String nome1, String nome2);

}
