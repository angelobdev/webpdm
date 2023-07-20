package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.Dipendente;
import exm.sisinf.webpdm.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private BustaPagaService bustaPagaService;

    // CREATE

    public Dipendente createDipendente(Dipendente dipendente) {
        return dipendenteRepository.save(dipendente);
    }

    // READ

    public Dipendente getDipendente(String codiceFiscale) {
        return dipendenteRepository.findByCodiceFiscale(codiceFiscale).orElse(null);
    }

    public Dipendente getDipendente(Integer id) {
        return dipendenteRepository.findById(id).orElse(null);
    }

    public List<Dipendente> getAllDipendenti() {
        return dipendenteRepository.findAll();
    }

    // UDPATE

    public Dipendente updateDipendente(Integer id, Dipendente dipendente) {
        Dipendente toUpdate = dipendenteRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setCodiceFiscale(dipendente.getCodiceFiscale());
            toUpdate.setMansioni(dipendente.getMansioni());
            dipendenteRepository.save(toUpdate);
        }
        return toUpdate;
    }

    // DELETE

    public void deleteDipendente(Integer id) {
        bustaPagaService.deleteBustePagaDipendente(id);
        dipendenteRepository.deleteById(id);
    }

}
