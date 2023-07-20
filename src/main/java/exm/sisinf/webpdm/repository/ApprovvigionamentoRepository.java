package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Approvvigionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApprovvigionamentoRepository extends JpaRepository<Approvvigionamento, Integer> {

    Optional<List<Approvvigionamento>> findAllByNomeProdottoLikeIgnoreCaseOrNomeProdottoContainingIgnoreCase(String nome1, String nome2);


}
