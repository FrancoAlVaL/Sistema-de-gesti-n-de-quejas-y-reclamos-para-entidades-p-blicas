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

    public Integer getIdRespuesta() { return idRespuesta; }
    public void setIdRespuesta(Integer idRespuesta) { this.idRespuesta = idRespuesta; }
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
    public LocalDateTime getFechaRespuesta() { return fechaRespuesta; }
    public void setFechaRespuesta(LocalDateTime fechaRespuesta) { this.fechaRespuesta = fechaRespuesta; }
    public String getEstadoRespuesta() { return estadoRespuesta; }
    public void setEstadoRespuesta(String estadoRespuesta) { this.estadoRespuesta = estadoRespuesta; }
    public String getArchivoAdjunto() { return archivoAdjunto; }
    public void setArchivoAdjunto(String archivoAdjunto) { this.archivoAdjunto = archivoAdjunto; }
    public Reclamo getReclamo() { return reclamo; }
    public void setReclamo(Reclamo reclamo) { this.reclamo = reclamo; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}