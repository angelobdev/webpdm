package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.Vendita;
import exm.sisinf.webpdm.repository.VenditaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenditaService {

    @Autowired
    private VenditaRepository venditaRepository;

    // CREATE

    public Vendita createVendita(Vendita vendita) {
        return venditaRepository.save(vendita);
    }

    // READ

    public Vendita getVendita(Integer id) {
        return venditaRepository.findById(id).orElse(null);
    }

    public List<Vendita> getAllVendite() {
        return venditaRepository.findAll();
    }

    // UPDATE

    public Vendita updateVendita(Integer id, Vendita vendita) {
        Vendita toUpdate = venditaRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setData(vendita.getData());
            toUpdate.setImportoTotale(vendita.getImportoTotale());
            toUpdate.setProdottiQuantita(vendita.getProdottiQuantita());
        }
        return toUpdate;
    }

    // DELETE

    public void deleteVendita(Integer id) {
        venditaRepository.deleteById(id);
    }

}
