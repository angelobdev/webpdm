package exm.sisinf.webpdm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prova")
public class ProvaController {

    @GetMapping()
    public String welcome(Model model){
        model.addAttribute("welcome", "Ciao coglione!");
        return "prova";
    }

}
