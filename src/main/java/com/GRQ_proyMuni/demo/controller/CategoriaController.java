package com.GRQ_proyMuni.demo.controller;

import com.GRQ_proyMuni.demo.entity.Categoria;
import com.GRQ_proyMuni.demo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/lista")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodos());
        return "categorias/lista"; // Asumiendo que crearán esta vista o la integrarán en estadisticas
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.guardar(categoria);
        return "redirect:/categorias/lista";
    }
}