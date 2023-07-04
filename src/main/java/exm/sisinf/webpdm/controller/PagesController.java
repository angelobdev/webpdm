package exm.sisinf.webpdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/azienda")
    public String azienda() {
        return "azienda";
    }

    @GetMapping("/contatti")
    public String contatti() {
        return "contatti";
    }

}
