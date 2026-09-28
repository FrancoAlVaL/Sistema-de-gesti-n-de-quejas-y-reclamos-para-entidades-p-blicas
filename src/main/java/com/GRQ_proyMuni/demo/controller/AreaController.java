package com.GRQ_proyMuni.demo.controller;

import com.GRQ_proyMuni.demo.entity.Area;
import com.GRQ_proyMuni.demo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/areas")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/lista")
    public String listarAreas(Model model) {
        model.addAttribute("areas", areaService.listarTodos());
        return "areas/lista"; 
    }

    @PostMapping("/guardar")
    public String guardarArea(@ModelAttribute Area area) {
        areaService.guardar(area);
        return "redirect:/areas/lista";
    }
}
