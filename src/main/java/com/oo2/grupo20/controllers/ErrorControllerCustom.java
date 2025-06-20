package com.oo2.grupo20.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorControllerCustom {

    @GetMapping("/error/403")
    public String error403(Model model) {
        model.addAttribute("errorMessage", "No tenés permiso para acceder a esta página.");
        return "error/403"; 
    }
}
