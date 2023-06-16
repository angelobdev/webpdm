package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Approvvigionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovvigionamentoRepository extends JpaRepository<Approvvigionamento, Integer> {
}
