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
    public Fornitore createFornitore(Fornitore fornitore){
        return fornitoreRepository.save(fornitore);
    }

    // READ

    public List<Fornitore> getAllForitori(){
        return fornitoreRepository.findAll();
    }

    public Fornitore getFornitore(String piva){
        return fornitoreRepository.findById(piva).orElse(null);
    }

    // UPDATE

    public Fornitore updateFornitore(String piva, Fornitore fornitore){
        Fornitore toUpdate = fornitoreRepository.findById(piva).orElse(null);
        if(toUpdate != null){
            toUpdate.setPartitaIVA(fornitore.getPartitaIVA());
            toUpdate.setNome(fornitore.getNome());
            toUpdate.setCategorie(fornitore.getCategorie());
            toUpdate.setNumeroOrdini(fornitore.getNumeroOrdini());
            return fornitoreRepository.save(toUpdate);
        }
        return null;
    }

    // DELETE

    public void deleteFornitore(String piva){
        fornitoreRepository.deleteById(piva);
    }

}
