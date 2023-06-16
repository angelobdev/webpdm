package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.VenditaDettaglio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenditaDettaglioRepository extends JpaRepository<VenditaDettaglio, Integer> {
}
