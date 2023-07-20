package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {

    @Query(
            value = "SELECT o.* " +
                    "FROM ordini o " +
                    "JOIN carrelli c ON o.carrello_id = c.id " +
                    "WHERE c.utente_id = ?1",
            nativeQuery = true
    )
    List<Ordine> findByUtenteId(Integer id);




}
