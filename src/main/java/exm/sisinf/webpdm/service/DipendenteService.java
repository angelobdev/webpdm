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

    // CREATE

    public Dipendente createDipendente(Dipendente dipendente){
        return dipendenteRepository.save(dipendente);
    }

    // READ

    public List<Dipendente> getAllDipendenti(){
        return dipendenteRepository.findAll();
    }

    public Dipendente getDipendente(String codiceFiscale){
        return dipendenteRepository.findById(codiceFiscale).orElse(null);
    }

    // UDPATE

    public Dipendente updateDipendente(String codiceFiscale, Dipendente dipendente){
        Dipendente toUpdate = dipendenteRepository.findById(codiceFiscale).orElse(null);
        if(toUpdate!=null){
            toUpdate.setCodiceFiscale(dipendente.getCodiceFiscale());
            toUpdate.setMansioni(dipendente.getMansioni());
            return dipendenteRepository.save(toUpdate);
        }
        return null;
    }

    // DELETE

    public void deleteDipendente(String codiceFiscale){
        dipendenteRepository.deleteById(codiceFiscale);
    }

}
