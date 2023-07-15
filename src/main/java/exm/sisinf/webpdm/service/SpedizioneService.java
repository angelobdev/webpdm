package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.Spedizione;
import exm.sisinf.webpdm.repository.SpedizioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpedizioneService {

    @Autowired
    private SpedizioneRepository spedizioneRepository;

    // CREATE

    public Spedizione createSpedizione(Spedizione spedizione) {
        return spedizioneRepository.save(spedizione);
    }

    // READ

    public Spedizione getSpedizione(Integer id) {
        return spedizioneRepository.findById(id).orElse(null);
    }

    public List<Spedizione> getAllSpedizioni() {
        return spedizioneRepository.findAll();
    }

    // UPDATE

    public Spedizione updateSpedizione(Integer id, Spedizione spedizione) {
        Spedizione toUpdate = spedizioneRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setDataSpedizione(spedizione.getDataSpedizione());
            toUpdate.setDataConsegna(spedizione.getDataConsegna());
            toUpdate.setDestinazione(spedizione.getDestinazione());
            toUpdate.setStatoSpedizione(spedizione.getStatoSpedizione());
            toUpdate.setOrdine(spedizione.getOrdine());
        }
        return toUpdate;
    }

    // DELETE

    public void deleteSpedizione(Integer id) {
        spedizioneRepository.deleteById(id);
    }

}
