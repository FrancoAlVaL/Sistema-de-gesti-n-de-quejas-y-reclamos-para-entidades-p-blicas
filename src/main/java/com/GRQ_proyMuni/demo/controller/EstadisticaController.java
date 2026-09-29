package com.GRQ_proyMuni.demo.controller;

import com.GRQ_proyMuni.demo.service.ReclamoService;
import com.GRQ_proyMuni.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.GRQ_proyMuni.demo.entity.Reclamo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<Reclamo> reclamos = reclamoService.listarTodos();
        var estados = reclamos.stream().collect(Collectors.groupingBy(
                reclamo -> reclamo.getEstado().getNombreEstado(), Collectors.counting()));
        var tipos = reclamos.stream().collect(Collectors.groupingBy(
                reclamo -> reclamo.getCategoria().getNombreCategoria(), Collectors.counting()));
        var areas = reclamos.stream().collect(Collectors.groupingBy(
                reclamo -> reclamo.getArea().getNombreArea(), Collectors.counting()));
        long resueltos = reclamos.stream()
                .filter(reclamo -> "Resuelto".equalsIgnoreCase(reclamo.getEstado().getNombreEstado())).count();
        long pendientes = reclamos.stream()
                .filter(reclamo -> "Pendiente".equalsIgnoreCase(reclamo.getEstado().getNombreEstado())).count();
        double promedio = reclamos.stream().filter(reclamo -> reclamo.getFechaCierre() != null)
                .mapToLong(reclamo -> Duration.between(reclamo.getFechaRegistro(), reclamo.getFechaCierre()).toDays())
                .average().orElse(0);
        model.addAttribute("totalReclamos", reclamos.size());
        model.addAttribute("totalUsuarios", usuarioService.listarTodos().size());
        model.addAttribute("resueltos", resueltos);
        model.addAttribute("pendientes", pendientes);
        model.addAttribute("promedio", String.format(java.util.Locale.ROOT, "%.1f", promedio));
        model.addAttribute("datosMeses", monthlyData(reclamos));
        model.addAttribute("datosTipos", chartData(tipos));
        model.addAttribute("datosEstados", chartData(estados));
        model.addAttribute("datosAreas", chartData(areas));
        return "estadisticas";
    }

    private Map<String, Object> chartData(Map<String, Long> grouped) {
        List<String> labels = new ArrayList<>(grouped.keySet());
        List<Long> values = labels.stream().map(grouped::get).toList();
        return Map.of("labels", labels, "data", values);
    }

    private Map<String, Object> monthlyData(List<Reclamo> reclamos) {
        int year = LocalDateTime.now().getYear();
        List<String> labels = List.of("Ene", "Feb", "Mar", "Abr", "May", "Jun",
                "Jul", "Ago", "Sep", "Oct", "Nov", "Dic");
        List<Long> values = java.util.stream.IntStream.rangeClosed(1, 12)
                .mapToObj(month -> reclamos.stream()
                        .filter(reclamo -> reclamo.getFechaRegistro().getYear() == year
                                && reclamo.getFechaRegistro().getMonthValue() == month)
                        .count())
                .toList();
        return Map.of("labels", labels, "data", values);
    }
}