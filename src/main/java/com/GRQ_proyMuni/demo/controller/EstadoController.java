package com.GRQ_proyMuni.demo.controller;

import com.GRQ_proyMuni.demo.entity.Estado;
import com.GRQ_proyMuni.demo.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/lista")
    public String listarEstados(Model model) {
        model.addAttribute("estados", estadoService.listarTodos());
        return "estados/lista"; 
    }

    @PostMapping("/guardar")
    public String guardarEstado(@ModelAttribute Estado estado) {
        estadoService.guardar(estado);
        return "redirect:/estados/lista";
    }
}