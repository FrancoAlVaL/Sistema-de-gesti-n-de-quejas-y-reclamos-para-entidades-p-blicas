package com.GRQ_proyMuni.demo.service;

import com.GRQ_proyMuni.demo.entity.Estado;
import com.GRQ_proyMuni.demo.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> listarTodos() {
        return estadoRepository.findAll();
    }

    public Optional<Estado> buscarPorId(Integer id) {
        return estadoRepository.findById(id);
    }

    public Estado guardar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void eliminar(Integer id) {
        estadoRepository.deleteById(id);
    }
}