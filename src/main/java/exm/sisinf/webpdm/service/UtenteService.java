package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.Utente;
import exm.sisinf.webpdm.repository.DipendenteRepository;
import exm.sisinf.webpdm.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private DipendenteRepository dipendenteRepository;

    //CREATE

    public Utente createUtente(Utente utente){
        return utenteRepository.save(utente);
    }

    //READ
    public List<Utente> getAllUtente(){
        return utenteRepository.findAll();
    }

    public Utente getUtente(Integer id){
        return utenteRepository.findById(id).orElse(null);
    }

    //UPDATE

    public Utente updateUtente(Integer id, Utente utente){
        Utente toUpdate = utenteRepository.findById(id).orElse(null);
        if(toUpdate!=null){
            toUpdate.setPiva(utente.getPiva());
            toUpdate.setNome(utente.getNome());
            toUpdate.setSede(utente.getSede());
            toUpdate.setUsername(utente.getUsername());
            toUpdate.setPassword(utente.getPassword());
            toUpdate.setNumeroOrdini(utente.getNumeroOrdini());
            return utenteRepository.save(toUpdate);
        }
        return null;
    }

    //DELETE

    public void deleteUtente(int id){
        utenteRepository.deleteById(id);
    }
}
