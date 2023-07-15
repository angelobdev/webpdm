package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.Ordine;
import exm.sisinf.webpdm.repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    // CREATE

    public Ordine createOrdine(Ordine ordine) {
        return ordineRepository.save(ordine);
    }

    // READ

    public Ordine getOrdine(Integer id) {
        return ordineRepository.findById(id).orElse(null);
    }

    public List<Ordine> getAllOrdini() {
        return ordineRepository.findAll();
    }

    // UPDATE

    public Ordine updateOrdine(Integer id, Ordine ordine) {
        Ordine toUpdate = ordineRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setData(ordine.getData());
            toUpdate.setImportoTotale(ordine.getImportoTotale());
            toUpdate.setStatoOridine(ordine.getStatoOridine());
            toUpdate.setCarrello(ordine.getCarrello());
        }
        return toUpdate;
    }

    // DELETE

    public void deleteOrdine(Integer id) {
        ordineRepository.deleteById(id);
    }
}
