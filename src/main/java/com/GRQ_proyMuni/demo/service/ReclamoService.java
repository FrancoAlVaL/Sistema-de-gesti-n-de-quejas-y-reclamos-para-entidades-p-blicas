package com.GRQ_proyMuni.demo.service;

import com.GRQ_proyMuni.demo.entity.Reclamo;
import com.GRQ_proyMuni.demo.repository.ReclamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReclamoService {

    @Autowired
    private ReclamoRepository reclamoRepository;

    public List<Reclamo> listarTodos() {
        return reclamoRepository.findAll();
    }

    public Optional<Reclamo> buscarPorId(Integer id) {
        return reclamoRepository.findById(id);
    }

    public List<Reclamo> buscarPorUsuario(Integer idUsuario) {
        return reclamoRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<Reclamo> buscarPorEstado(Integer idEstado) {
        return reclamoRepository.findByEstadoIdEstado(idEstado);
    }

    public Optional<Reclamo> buscarPorCodigoYDocumento(String codigo, String numeroDocumento) {
        return reclamoRepository.findByCodigoSeguimientoAndUsuarioNumeroDocumento(codigo, numeroDocumento);
    }

    public Reclamo guardar(Reclamo reclamo) {
        return reclamoRepository.save(reclamo);
    }

    public void eliminar(Integer id) {
        reclamoRepository.deleteById(id);
    }
}