package com.oo2.grupo20.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String login() {
        return "login/login"; // login.html en templates
    }

    @GetMapping("/home")
    public String home() {
        return "index"; // home.html para post-login
    }
}
