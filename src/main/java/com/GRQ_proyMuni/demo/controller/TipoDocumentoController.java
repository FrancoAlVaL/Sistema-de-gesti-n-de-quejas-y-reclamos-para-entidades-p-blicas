package com.GRQ_proyMuni.demo.controller;

import com.GRQ_proyMuni.demo.entity.TipoDocumento;
import com.GRQ_proyMuni.demo.service.TipoDocumentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tipos-documento")
public class TipoDocumentoController {

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @GetMapping("/lista")
    public String listarTiposDocumento(Model model) {
        model.addAttribute("tiposDocumento", tipoDocumentoService.listarTodos());
        return "tipos-documento/lista";
    }

    @PostMapping("/guardar")
    public String guardarTipoDocumento(@ModelAttribute TipoDocumento tipoDocumento) {
        tipoDocumentoService.guardar(tipoDocumento);
        return "redirect:/tipos-documento/lista";
    }
}