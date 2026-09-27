package com.GRQ_proyMuni.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "respuesta")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_respuesta")
    private Integer idRespuesta;

    @Column(name = "contenido", nullable = false, columnDefinition = "TEXT")
    private String contenido;

    @Column(name = "fecha_respuesta", nullable = false)
    private LocalDateTime fechaRespuesta;

    @Column(name = "estado_respuesta", length = 50)
    private String estadoRespuesta;

    @Column(name = "archivo_adjunto", length = 255)
    private String archivoAdjunto;

    @ManyToOne
    @JoinColumn(name = "id_reclamo", nullable = false)
    private Reclamo reclamo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public Respuesta() {
    }

    // Getters y setters
}