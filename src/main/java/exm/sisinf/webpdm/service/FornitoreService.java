package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.Fornitore;
import exm.sisinf.webpdm.repository.FornitoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornitoreService {

    @Autowired
    FornitoreRepository fornitoreRepository;

    // CREATE
    public Fornitore createFornitore(Fornitore fornitore) {
        return fornitoreRepository.save(fornitore);
    }

    // READ

    public List<Fornitore> getAllForitori() {
        return fornitoreRepository.findAll();
    }

    public Fornitore getFornitore(Integer id) {
        return fornitoreRepository.findById(id).orElse(null);
    }

    // UPDATE

    public Fornitore updateFornitore(Integer id, Fornitore fornitore) {
        Fornitore toUpdate = fornitoreRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setPartitaIVA(fornitore.getPartitaIVA());
            toUpdate.setNomeAzienda(fornitore.getNomeAzienda());
            toUpdate.setSedeAziendale(fornitore.getSedeAziendale());
            return fornitoreRepository.save(toUpdate);
        }
        return toUpdate;
    }

    // DELETE

    public void deleteFornitore(Integer id) {
        fornitoreRepository.deleteById(id);
    }

}
