package exm.sisinf.webpdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {

    private static final String TITLE = "PERLA DEL MEDITERRANEO";

    @GetMapping()
    public String root(Model model) {
        model.addAttribute("title", TITLE);
        return "index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        return root(model);
    }

}
