package com.GRQ_proyMuni.demo.repository;

import com.GRQ_proyMuni.demo.entity.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Integer> {
    // Buscar todas las respuestas de un reclamo específico
    List<Respuesta> findByReclamoIdReclamo(Integer idReclamo);
}