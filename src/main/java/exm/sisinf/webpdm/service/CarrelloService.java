package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.BustaPaga;
import exm.sisinf.webpdm.model.Carrello;
import exm.sisinf.webpdm.model.Prodotto;
import exm.sisinf.webpdm.model.Utente;
import exm.sisinf.webpdm.repository.CarrelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrelloService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private CarrelloRepository carrelloRepository;

    // CREATE

    public Carrello creaCarrelloUtente(Utente u) {
        Carrello nuovoCarrello = new Carrello();
        nuovoCarrello.setUtente(u);
        return carrelloRepository.save(nuovoCarrello);
    }

    public Carrello createCarrello(Carrello carrello) {
        return carrelloRepository.save(carrello);
    }

    // READ

    public Carrello getCarrello(Integer id) {
        return carrelloRepository.findById(id).orElse(null);
    }

    public Carrello getCarrelloUtente(Integer utenteID) {
        Carrello carrello = carrelloRepository.findByUtente(utenteID).orElse(null);
        if (carrello != null) {
            if (carrello.getId() == null || carrello.getUtente() == null || carrello.getAcquistato() == null)
                return null;
        }
        return carrello;
    }

    public List<Carrello> getAllCarrelli() {
        return carrelloRepository.findAll();
    }

    // UPDATE

    public Carrello updateCarrello(Integer id, Carrello carrello) {
        Carrello toUpdate = carrelloRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setAcquistato(carrello.getAcquistato());
            toUpdate.setUtente(carrello.getUtente());
            toUpdate.setDataCreazione(carrello.getDataCreazione());
        }
        return toUpdate;
    }

    // DELETE

    public void deleteCarrello(Integer id) {
        carrelloRepository.deleteById(id);
    }


    // PRODOTTI

    public void aggiungiProdotto(Integer carrelloID, Integer prodottoID, Integer quantita) {
        carrelloRepository.aggiungiProdotto(carrelloID, prodottoID, quantita);
    }

    public void modificaProdotto(Integer carrelloID, Integer prodottoID, Integer nuovaQuantita) {
        carrelloRepository.modificaProdotto(carrelloID, prodottoID, nuovaQuantita);
    }

    public void eliminaProdotto(Integer carrelloID, Integer prodottoID) {
        carrelloRepository.eliminaProdotto(carrelloID, prodottoID);
    }

    public void svuotaCarrello(Integer carrelloID) {
        carrelloRepository.svuotaCarrello(carrelloID);
    }

}
