package com.GRQ_proyMuni.demo.service;

import com.GRQ_proyMuni.demo.entity.Respuesta;
import com.GRQ_proyMuni.demo.repository.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    public List<Respuesta> listarTodos() {
        return respuestaRepository.findAll();
    }

    public Optional<Respuesta> buscarPorId(Integer id) {
        return respuestaRepository.findById(id);
    }

    public List<Respuesta> buscarPorReclamo(Integer idReclamo) {
        return respuestaRepository.findByReclamoIdReclamo(idReclamo);
    }

    public Respuesta guardar(Respuesta respuesta) {
        return respuestaRepository.save(respuesta);
    }
}