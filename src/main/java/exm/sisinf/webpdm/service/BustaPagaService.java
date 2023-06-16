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
    public BustaPaga createBustaPaga(BustaPaga bustaPaga){
        return  bustaPagaRepository.save(bustaPaga);
    }

    // READ

    public List<BustaPaga> getAllBustePaga(){
        return bustaPagaRepository.findAll();
    }

    public BustaPaga getBustaPaga(Integer id){
        return bustaPagaRepository.findById(id).orElse(null);
    }

    // UPDATE

    public BustaPaga updateBustaPaga(Integer id, BustaPaga  bustaPaga){
        BustaPaga toUpdate = bustaPagaRepository.findById(id).orElse(null);
        if(toUpdate!= null){
            toUpdate.setTotale(bustaPaga.getTotale());
            toUpdate.setDataEmissione(bustaPaga.getDataEmissione());
            toUpdate.setDipendente(bustaPaga.getDipendente());
        }
        return null;
    }

    // DELETE

    public void deleteBustaPaga(Integer id){
        bustaPagaRepository.deleteById(id);
    }

}
