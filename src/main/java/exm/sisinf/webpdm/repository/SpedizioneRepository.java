package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Spedizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpedizioneRepository extends JpaRepository<Spedizione, Integer> {
}
