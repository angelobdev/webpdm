package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.BustaPaga;
import exm.sisinf.webpdm.repository.BustaPagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BustaPagaService {

    @Autowired
    private BustaPagaRepository bustaPagaRepository;

    // CREATE

    public BustaPaga createBustaPaga(BustaPaga bustaPaga) {
        return bustaPagaRepository.save(bustaPaga);
    }

    // READ

    public BustaPaga getBustaPaga(Integer id) {
        return bustaPagaRepository.findById(id).orElse(null);
    }

    public List<BustaPaga> getAllBustePaga() {
        return bustaPagaRepository.findAll();
    }

    // UPDATE

    public BustaPaga updateBustaPaga(Integer id, BustaPaga bustaPaga) {
        BustaPaga toUpdate = bustaPagaRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setImportoEmesso(bustaPaga.getImportoEmesso());
            toUpdate.setDataEmissione(bustaPaga.getDataEmissione());
            toUpdate.setDipendente(bustaPaga.getDipendente());
            bustaPagaRepository.save(toUpdate);
        }
        return toUpdate;
    }

    // DELETE

    public void deleteBustaPaga(Integer id) {
        bustaPagaRepository.deleteById(id);
    }

    public void deleteBustePagaDipendente(Integer dipendenteID) {
        bustaPagaRepository.deleteByDipendenteId(dipendenteID);
    }

}
