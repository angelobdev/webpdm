package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.Rimborso;
import exm.sisinf.webpdm.repository.RimborsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RimborsoService {

    @Autowired
    private RimborsoRepository rimborsoRepository;

    // CREATE

    public Rimborso createRimborso(Rimborso rimborso) {
        return rimborsoRepository.save(rimborso);
    }

    // READ

    public Rimborso getRimborso(Integer id) {
        return rimborsoRepository.findById(id).orElse(null);
    }

    public List<Rimborso> getAllRimborsi() {
        return rimborsoRepository.findAll();
    }

    // UPDATE

    public Rimborso updateRimborso(Integer id, Rimborso rimborso) {
        Rimborso toUpdate = rimborsoRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setDataEmissione(rimborso.getDataEmissione());
            toUpdate.setReclamo(rimborso.getReclamo());
        }
        return toUpdate;
    }

    // DELETE

    public void deleteRimborso(Integer id) {
        rimborsoRepository.deleteById(id);
    }

}
