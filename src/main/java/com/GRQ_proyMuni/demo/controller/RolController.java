package com.GRQ_proyMuni.demo.controller;

import com.GRQ_proyMuni.demo.entity.Rol;
import com.GRQ_proyMuni.demo.service.RolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("/lista")
    public String listarRoles(Model model) {
        model.addAttribute("roles", rolService.listarTodos());
        return "roles/lista";
    }

    @PostMapping("/guardar")
    public String guardarRol(@ModelAttribute Rol rol) {
        rolService.guardar(rol);
        return "redirect:/roles/lista";
    }
}