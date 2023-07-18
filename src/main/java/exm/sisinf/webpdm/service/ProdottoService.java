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

    public Prodotto createProdotto(Prodotto prodotto) {
        return prodottoRepository.save(prodotto);
    }

    // READ

    public List<Prodotto> cercaProdotto(String nome) {
        return prodottoRepository.findAllByNomeLikeIgnoreCaseOrNomeContainingIgnoreCase(nome, nome).orElse(null);
    }

    public Prodotto getProdotto(Integer id) {
        return prodottoRepository.findById(id).orElse(null);
    }

    public List<Prodotto> getAllProdotti() {
        return prodottoRepository.findAll();
    }

    // UPDATE

    public Prodotto updateProdotto(Integer id, Prodotto prodotto) {
        Prodotto toUpdate = prodottoRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setNome(prodotto.getNome());
            toUpdate.setPrezzoAlKg(prodotto.getPrezzoAlKg());
            toUpdate.setDataArrivo(prodotto.getDataArrivo());
            toUpdate.setQuantitaStoccata(prodotto.getQuantitaStoccata());
            toUpdate.setDescrizione(prodotto.getDescrizione());
            toUpdate.setImmagine(prodotto.getImmagine());
            prodottoRepository.save(toUpdate);
        }
        return toUpdate;
    }

    // DELETE

    public void deleteProdotto(Integer id) {
        prodottoRepository.deleteById(id);
    }

}
