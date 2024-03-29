package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.Carrello;
import exm.sisinf.webpdm.model.Prodotto;
import exm.sisinf.webpdm.model.Utente;
import exm.sisinf.webpdm.model.support.CarrelloProdotto;
import exm.sisinf.webpdm.repository.CarrelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarrelloService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private CarrelloRepository carrelloRepository;

    @Autowired
    private ProdottoService prodottoService;


    // CREATE

    public Carrello creaCarrelloUtente(Integer utenteID) {
        Utente u = utenteService.getUtente(utenteID);
        Carrello nuovoCarrello = new Carrello();
        nuovoCarrello.setUtente(u);
        nuovoCarrello.setAcquistato(false);
        nuovoCarrello.setCarrelloProdotti(new ArrayList<>());
        nuovoCarrello.setDataCreazione(new Date());
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
        Collection<Carrello> carrelli = carrelloRepository.findByUtente(utenteID);
        if (carrelli.size() != 0)
            return carrelli.stream().toList().get(0);
        return null;
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

    public void eliminaProdotto(Integer carrelloID, String nomeProdotto) {
        Prodotto prodotto = prodottoService.getProdotto(nomeProdotto);
        carrelloRepository.eliminaProdotto(carrelloID, prodotto.getId());
    }

    public Map<Prodotto, Integer> getProdottiNelCarrello(Integer carrelloID) {

        Carrello carrello = carrelloRepository.findById(carrelloID).orElse(null);
        assert (carrello != null);

        Collection<CarrelloProdotto> prodottiCarrello = carrello.getCarrelloProdotti();

        Map<Prodotto, Integer> prodottiNelCarrello = new HashMap<>();

        for (var cp : prodottiCarrello) {
            prodottiNelCarrello.put(cp.getProdotto(), cp.getQuantita());
        }

        return prodottiNelCarrello;
    }

    public void svuotaCarrello(Integer carrelloID) {
        carrelloRepository.svuotaCarrello(carrelloID);
    }

}
