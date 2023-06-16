package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.Prodotto;
import exm.sisinf.webpdm.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    // CREATE

    public Prodotto createProdotto(Prodotto prodotto){
        return prodottoRepository.save(prodotto);
    }

    // READ

    public List<Prodotto> getAllProdotti(){
        return prodottoRepository.findAll();
    }

    public Prodotto getProdotto(Integer id){
        return prodottoRepository.findById(id).orElse(null);
    }

    // UPDATE

    public Prodotto updateProdotto(Integer id, Prodotto prodotto){
        Prodotto toUpdate = prodottoRepository.findById(id).orElse(null);
        if(toUpdate!=null){
            toUpdate.setNome(prodotto.getNome());
            toUpdate.setPrezzoKg(prodotto.getPrezzoKg());
            toUpdate.setDataArrivo(prodotto.getDataArrivo());
            toUpdate.setQuantita(prodotto.getQuantita());
            return prodottoRepository.save(toUpdate);
        }
        return null;
    }

    // DELETE

    public void deleteProdotto(Integer id){
        prodottoRepository.deleteById(id);
    }

}
