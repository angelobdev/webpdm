package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.BustaPaga;
import exm.sisinf.webpdm.model.Carrello;
import exm.sisinf.webpdm.repository.CarrelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrelloService {

    @Autowired
    private CarrelloRepository carrelloRepository;

    // CREATE

    public Carrello createCarrello(Carrello carrello) {
        return carrelloRepository.save(carrello);
    }

    // READ

    public Carrello getCarrello(Integer id) {
        return carrelloRepository.findById(id).orElse(null);
    }

    public List<Carrello> getAllCarrelli() {
        return carrelloRepository.findAll();
    }

    // UPDATE

    public Carrello updateCarrello(Integer id, Carrello carrello) {
        Carrello toUpdate = carrelloRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setAcquistato(carrello.getAcquistato());
            toUpdate.setUtente(carrello.getUtente());
        }
        return toUpdate;
    }

    // DELETE

    public void deleteCarrello(Integer id) {
        carrelloRepository.deleteById(id);
    }

}
