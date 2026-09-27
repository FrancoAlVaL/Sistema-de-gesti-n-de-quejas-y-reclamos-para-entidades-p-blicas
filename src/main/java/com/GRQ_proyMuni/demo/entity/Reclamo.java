package com.GRQ_proyMuni.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reclamo")
public class Reclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reclamo")
    private Integer idReclamo;

    @Column(name = "codigo_seguimiento", length = 50)
    private String codigoSeguimiento;

    @Column(name = "numero_documento", length = 30)
    private String numeroDocumento;

    @Column(name = "fecha_documento")
    private LocalDate fechaDocumento;

    @Column(name = "asunto", nullable = false, length = 200)
    private String asunto;

    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "archivo_evidencia", length = 255)
    private String archivoEvidencia;

    private Integer folios;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "prioridad", length = 30)
    private String prioridad;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @Column(name = "fecha_vencimiento")
    private LocalDateTime fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_area", nullable = false)
    private Area area;

    public Reclamo() {
    }

    // Getters y setters
}