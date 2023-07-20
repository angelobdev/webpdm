package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.Reclamo;
import exm.sisinf.webpdm.repository.ReclamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReclamoService {

    @Autowired
    private ReclamoRepository reclamoRepository;

    // CREATE

    public Reclamo createReclamo(Reclamo reclamo) {
        return reclamoRepository.save(reclamo);
    }

    // READ

    public Reclamo getReclamo(Integer id) {
        return reclamoRepository.findById(id).orElse(null);
    }

    public List<Reclamo> getAllReclami() {
        return reclamoRepository.findAll();
    }

    // UPDATE

    public Reclamo updateReclamo(Integer id, Reclamo reclamo) {
        Reclamo toUpdate = reclamoRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setMessaggio(reclamo.getMessaggio());
            toUpdate.setData(reclamo.getData());
            toUpdate.setStato(reclamo.getStato());
            toUpdate.setOrdine(reclamo.getOrdine());
        }
        return toUpdate;
    }

    // DELETE

    public void deleteReclamo(Integer id) {
        reclamoRepository.deleteById(id);
    }

    public void deleteReclamoByOrdine(Integer ordineID) {
        reclamoRepository.deleteAllByOrdineId(ordineID);
    }

}
