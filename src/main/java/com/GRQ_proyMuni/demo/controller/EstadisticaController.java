package com.GRQ_proyMuni.demo.controller;

import com.GRQ_proyMuni.demo.service.ReclamoService;
import com.GRQ_proyMuni.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class EstadisticaController {

    @Autowired
    private ReclamoService reclamoService;

    @Autowired
    private UsuarioService usuarioService;

    // Panel de estadísticas 
    @GetMapping("/estadisticas")
    public String estadisticas(Model model) {
        // Enviar datos básicos para los gráficos
        model.addAttribute("totalReclamos", reclamoService.listarTodos().size());
        model.addAttribute("totalUsuarios", usuarioService.listarTodos().size());
        return "estadisticas";
    }
}