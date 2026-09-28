package com.GRQ_proyMuni.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Página principal 
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Pantalla de inicio de sesión 
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Pantalla de consulta pública 
    @GetMapping("/consulta")
    public String consulta() {
        return "consulta";
    }
}