package exm.sisinf.webpdm.repository;

import exm.sisinf.webpdm.model.Carrello;
import exm.sisinf.webpdm.model.Prodotto;
import exm.sisinf.webpdm.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Integer> {

    @Query(value = "SELECT c.* FROM carrelli c WHERE (c.utente_id = ?1 AND c.acquistato = false)", nativeQuery = true)
    Optional<Carrello> findByUtente(Integer utenteID);

    @Query(value = "INSERT INTO carrelli_prodotti (carrello_id, prodotto_id, quantita) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void aggiungiProdotto(Integer carrelloID, Integer prodottoID, Integer quantita);

    @Query(value = "UPDATE carrelli_prodotti SET quantita = ?3 WHERE carrello_id = ?1 AND prodotto_id = ?2", nativeQuery = true)
    void modificaProdotto(Integer carrelloID, Integer prodottoID, Integer nuovaQuantita);

    @Query(value = "DELETE FROM carrelli_prodotti WHERE carrello_id = ?1 AND prodotto_id = ?2", nativeQuery = true)
    void eliminaProdotto(Integer carrelloID, Integer prodottoID);

    @Query(value = "DELETE FROM carrelli_prodotti WHERE carrello_id = ?1", nativeQuery = true)
    void svuotaCarrello(Integer carrelloID);

}
