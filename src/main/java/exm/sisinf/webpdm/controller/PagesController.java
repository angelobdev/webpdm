package exm.sisinf.webpdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/chisiamo")
    public String chiSiamo() {
        return "chisiamo";
    }

    @GetMapping("/dovesiamo")
    public String doveSiamo() {
        return "dovesiamo";
    }

    @GetMapping("/contatti")
    public String contatti() {
        return "contatti";
    }

}
