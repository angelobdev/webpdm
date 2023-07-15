package exm.sisinf.webpdm.service;


import exm.sisinf.webpdm.model.Approvvigionamento;
import exm.sisinf.webpdm.repository.ApprovvigionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovvigionamentoService {

    @Autowired
    ApprovvigionamentoRepository approvvigionamentoRepository;

    // CREATE
    public Approvvigionamento createApprovvigionamento(Approvvigionamento approvvigionamento) {
        return approvvigionamentoRepository.save(approvvigionamento);
    }

    // READ

    public Approvvigionamento getApprovvigionamento(Integer id) {
        return approvvigionamentoRepository.findById(id).orElse(null);
    }

    public List<Approvvigionamento> getAllApprovvigionamenti() {
        return approvvigionamentoRepository.findAll();
    }

    // UPDATE

    public Approvvigionamento updateApprovvigionamento(Integer id, Approvvigionamento approvvigionamento) {
        Approvvigionamento toUpdate = approvvigionamentoRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setNomeProdotto(approvvigionamento.getNomeProdotto());
            toUpdate.setQuantita(approvvigionamento.getQuantita());
        }
        return toUpdate;
    }

    // DELETE
    public void deleteApprovvigionamento(Integer id) {
        approvvigionamentoRepository.deleteById(id);
    }
}
