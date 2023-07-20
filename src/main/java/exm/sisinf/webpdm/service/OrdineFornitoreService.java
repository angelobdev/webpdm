package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.OrdineFornitore;
import exm.sisinf.webpdm.repository.OrdineFornitoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdineFornitoreService {
    @Autowired
    private OrdineFornitoreRepository ordineFornitoreRepository;

    //CREATE

    public OrdineFornitore createOrdineFornitore(OrdineFornitore ordineFornitore) {
        return ordineFornitoreRepository.save(ordineFornitore);
    }

    //READ

    public List<OrdineFornitore> getAllOrdineFornitore() {
        return ordineFornitoreRepository.findAll();
    }

    public OrdineFornitore getOrdineFornitore(Integer id) {
        return ordineFornitoreRepository.findById(id).orElse(null);
    }

    //UPDATE

    public OrdineFornitore updateOrdineFornitore(Integer id, OrdineFornitore ordineFornitore) {
        OrdineFornitore toUpdate = ordineFornitoreRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setDescrizione(ordineFornitore.getDescrizione());
            toUpdate.setData(ordineFornitore.getData());
            toUpdate.setStatoOrdine(ordineFornitore.getStatoOrdine());
            toUpdate.setImportoTotale(ordineFornitore.getImportoTotale());
            toUpdate.setFornitore(ordineFornitore.getFornitore());
        }
        return toUpdate;
    }

    //DELETE
    public void updateOrdineFornitore(Integer id) {
        ordineFornitoreRepository.deleteById(id);
    }
}
