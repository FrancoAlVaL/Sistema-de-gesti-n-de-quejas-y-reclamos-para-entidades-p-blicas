package com.GRQ_proyMuni.demo.controller;

import com.GRQ_proyMuni.demo.entity.Reclamo;
import com.GRQ_proyMuni.demo.entity.Respuesta;
import com.GRQ_proyMuni.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/reclamos")
public class ReclamoController {

    @Autowired private ReclamoService reclamoService;
    @Autowired private CategoriaService categoriaService;
    @Autowired private TipoDocumentoService tipoDocumentoService;
    @Autowired private EstadoService estadoService;
    @Autowired private AreaService areaService;
    @Autowired private RespuestaService respuestaService;

    // Formulario para nuevo reclamo 
    @GetMapping("/nuevo")
    public String mostrarNuevoReclamo(Model model) {
        model.addAttribute("reclamo", new Reclamo());
        model.addAttribute("categorias", categoriaService.listarTodos());
        model.addAttribute("tipos", tipoDocumentoService.listarTodos());
        return "index"; 
    }

    // Guardar el reclamo enviado desde el formulario
    @PostMapping("/guardar")
    public String guardarReclamo(@ModelAttribute Reclamo reclamo) {
        reclamo.setFechaRegistro(LocalDateTime.now());
        // Generar código único simple (ej: EXP-2026-123456789)
        reclamo.setCodigoSeguimiento("EXP-2026-" + System.currentTimeMillis()); 
        reclamoService.guardar(reclamo);
        return "redirect:/reclamos/mis-reclamos";
    }

    // Ver mis reclamos 
    @GetMapping("/mis-reclamos")
    public String misReclamos(Model model) {
        // Nota: Aquí luego se filtrará por el usuario logueado en sesión
        model.addAttribute("reclamos", reclamoService.listarTodos());
        return "mis-reclamos";
    }

    // Ver detalle de un reclamo 
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Integer id, Model model) {
        reclamoService.buscarPorId(id).ifPresent(reclamo -> {
            model.addAttribute("reclamo", reclamo);
            // Cargar respuestas e historial si es necesario
            model.addAttribute("respuestas", respuestaService.buscarPorReclamo(id));
        });
        return "detalle-reclamo";
    }

    // Bandeja de entrada para el personal 
    @GetMapping("/bandeja")
    public String bandejaPersonal(Model model) {
        model.addAttribute("reclamos", reclamoService.listarTodos());
        model.addAttribute("estados", estadoService.listarTodos());
        return "bandeja";
    }

    // Pantalla de atención para el personal
    @GetMapping("/atencion/{id}")
    public String atenderReclamo(@PathVariable Integer id, Model model) {
        reclamoService.buscarPorId(id).ifPresent(reclamo -> {
            model.addAttribute("reclamo", reclamo);
            model.addAttribute("estados", estadoService.listarTodos());
            model.addAttribute("areas", areaService.listarTodos());
        });
        return "atencion";
    }

    // Procesar la atención del reclamo (Guardar respuesta y cambiar estado)
    @PostMapping("/atencion/{id}")
    public String guardarAtencion(@PathVariable Integer id, @ModelAttribute Reclamo reclamoActualizado) {
        reclamoService.buscarPorId(id).ifPresent(reclamo -> {
            reclamo.setEstado(reclamoActualizado.getEstado());
            reclamo.setArea(reclamoActualizado.getArea());
            reclamo.setPrioridad(reclamoActualizado.getPrioridad());
            reclamo.setObservaciones(reclamoActualizado.getObservaciones());
            reclamoService.guardar(reclamo);
        });
        return "redirect:/reclamos/bandeja";
    }
}