package exm.sisinf.webpdm.controller.components;

import exm.sisinf.webpdm.auth.AuthTokenService;
import exm.sisinf.webpdm.controller.ContentController;
import exm.sisinf.webpdm.model.Carrello;
import exm.sisinf.webpdm.model.Utente;
import exm.sisinf.webpdm.service.CarrelloService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigatorController {

    private static final Logger logger = LoggerFactory.getLogger(NavigatorController.class);

    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private CarrelloService carrelloService;

    @GetMapping("/nav")
    public String navigator(Model model, HttpServletRequest request) {
        Utente utente = authTokenService.getUtente(authTokenService.retrieve(request));
        model.addAttribute("utente", utente);

        Carrello carrello = null;
        if (utente != null) {
            carrello = carrelloService.getCarrelloUtente(utente.getId());
//        Carrello carrello = carrelloService.getCarrello(1);

            logger.warn("CARRELLO: " + carrello);
            model.addAttribute("carrello", carrello);
        }

        return "components/navigator";
    }

    @GetMapping("/adminnav")
    public String adminNavigator(Model model, HttpServletRequest request) {
        Utente utente = authTokenService.getUtente(authTokenService.retrieve(request));
        model.addAttribute("utente", utente);
        return "components/adminnav";
    }

}
