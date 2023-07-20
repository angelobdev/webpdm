package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Reclamo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {

    @Modifying
    @Transactional
    void deleteAllByOrdineId(Integer id);

}
