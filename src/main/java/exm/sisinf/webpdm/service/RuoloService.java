package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.Ruolo;
import exm.sisinf.webpdm.repository.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuoloService {

    @Autowired
    private RuoloRepository ruoloRepository;

    // CREATE

    public Ruolo createRuolo(Ruolo ruolo) {
        return ruoloRepository.save(ruolo);
    }

    // READ

    public Ruolo getRuolo(Integer id) {
        return ruoloRepository.findById(id).orElse(null);
    }

    public List<Ruolo> getAllRuoli() {
        return ruoloRepository.findAll();
    }

    // UPDATE

    public Ruolo updateRuolo(Integer id, Ruolo ruolo) {
        Ruolo toUpdate = ruoloRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setNome(ruolo.getNome());
            toUpdate.setGrado(ruolo.getGrado());
        }
        return toUpdate;
    }

    // DELETE

}
