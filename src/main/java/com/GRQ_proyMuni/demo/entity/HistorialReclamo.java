package com.GRQ_proyMuni.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_reclamo")
public class HistorialReclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;

    @Column(name = "fecha_cambio", nullable = false)
    private LocalDateTime fechaCambio;

    @Column(name = "observacion", columnDefinition = "TEXT")
    private String observacion;

    @ManyToOne
    @JoinColumn(name = "id_reclamo", nullable = false)
    private Reclamo reclamo;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public HistorialReclamo() {
    }

    // Getters y setters
}