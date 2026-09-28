package com.GRQ_proyMuni.demo.controller;

import com.GRQ_proyMuni.demo.entity.Usuario;
import com.GRQ_proyMuni.demo.service.UsuarioService;
import com.GRQ_proyMuni.demo.service.RolService;
import com.GRQ_proyMuni.demo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @Autowired
    private AreaService areaService;

    // Mostrar formulario de registro 
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolService.listarTodos());
        return "registro";
    }

    // Procesar el registro de un nuevo usuario
    @PostMapping("/registro")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setEstadoActivo(true);
        usuarioService.guardar(usuario);
        return "redirect:/login";
    }

    // Lista de usuarios para el administrador
    @GetMapping("/lista")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("roles", rolService.listarTodos());
        model.addAttribute("areas", areaService.listarTodos());
        return "usuarios";
    }
}