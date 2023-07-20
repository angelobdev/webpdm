package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.Utente;
import exm.sisinf.webpdm.repository.DipendenteRepository;
import exm.sisinf.webpdm.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService implements UserDetailsService {

    @Autowired
    private UtenteRepository utenteRepository;

    //CREATE

    public Utente createUtente(Utente utente) {
        return utenteRepository.save(utente);
    }

    //READ

    public Utente getUtente(Integer id) {
        return utenteRepository.findById(id).orElse(null);
    }

    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    //UPDATE

    public Utente updateUtente(Integer id, Utente utente) {
        Utente toUpdate = utenteRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setPartitaIVA(utente.getPartitaIVA());
            toUpdate.setNomeAzienda(utente.getNomeAzienda());
            toUpdate.setSedeAziendale(utente.getSedeAziendale());
            toUpdate.setEmail(utente.getEmail());
            toUpdate.setUsername(utente.getUsername());
            toUpdate.setPassword(utente.getPassword());
            toUpdate.setAvatar(utente.getAvatar());
            toUpdate.setRuolo(utente.getRuolo());
            utenteRepository.save(utente);
        }
        return toUpdate;
    }

    //DELETE

    public void deleteUtente(Integer id) {
        utenteRepository.deleteById(id);
    }

    // UserDetailsService Override

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utenteRepository.findByUsername(username).orElse(null);
    }

    // UTILITY

    public boolean existsByUsername(String username) {
        return utenteRepository.existsByUsername(username);
    }
}
