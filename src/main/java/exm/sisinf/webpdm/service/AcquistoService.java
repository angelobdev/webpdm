package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.Acquisto;
import exm.sisinf.webpdm.repository.AcquistoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AcquistoService {

    @Autowired
    private AcquistoRepository acquistoRepository;

//    public Collection<Acquisto> getAcquistiByUsername(String username) {
//        return acquistoRepository.getAcquistiByUsername(username);
//    }


}
