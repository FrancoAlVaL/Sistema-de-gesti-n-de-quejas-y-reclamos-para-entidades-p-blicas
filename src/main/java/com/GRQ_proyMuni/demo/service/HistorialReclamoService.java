package com.GRQ_proyMuni.demo.service;

import com.GRQ_proyMuni.demo.entity.HistorialReclamo;
import com.GRQ_proyMuni.demo.repository.HistorialReclamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HistorialReclamoService {

    @Autowired
    private HistorialReclamoRepository historialRepository;

    public List<HistorialReclamo> listarTodos() {
        return historialRepository.findAll();
    }

    public Optional<HistorialReclamo> buscarPorId(Integer id) {
        return historialRepository.findById(id);
    }

    public List<HistorialReclamo> buscarPorReclamo(Integer idReclamo) {
        return historialRepository.findByReclamoIdReclamoOrderByFechaCambioDesc(idReclamo);
    }

    public HistorialReclamo guardar(HistorialReclamo historial) {
        return historialRepository.save(historial);
    }
}