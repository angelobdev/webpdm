package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Acquisto;
import exm.sisinf.webpdm.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto, Integer> {

//    @Query(
//            value = """
//                    SELECT a.*
//                    FROM acquisti a
//                    JOIN vendite_ingrosso vi ON a.vendita_id = vi.vendita_id
//                    JOIN utenti u ON vi.utente_id = u.id
//                    WHERE u.username = ?1
//                    """,
//            nativeQuery = true
//    )
//    Collection<Acquisto> getAcquistiByUsername(String username);
}
