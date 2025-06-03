package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Opción 1: Ruta raíz
    @GetMapping("/")
    public String index() {
        return "index";
    }


}