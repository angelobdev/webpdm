package exm.sisinf.webpdm.controller;

import exm.sisinf.webpdm.auth.AuthTokenService;
import exm.sisinf.webpdm.repository.AcquistoRepository;
import exm.sisinf.webpdm.service.AcquistoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private AcquistoService acquistoService;

    @GetMapping("/areaclienti")
    public String areaClienti(Model model, HttpServletRequest request) {
        String token = authTokenService.retrieve(request);
        String username = authTokenService.getUtente(token).getUsername();

        model.addAttribute("username", username);
//        model.addAttribute("acquisti", acquistoService.getAcquistiByUsername(username));

        return "areaclienti";
    }
}
