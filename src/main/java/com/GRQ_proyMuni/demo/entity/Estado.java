package com.GRQ_proyMuni.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "nombre_estado", nullable = false, length = 100)
    private String nombreEstado;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "orden_secuencia")
    private Integer ordenSecuencia;

    public Estado() {
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getOrdenSecuencia() {
        return ordenSecuencia;
    }

    public void setOrdenSecuencia(Integer ordenSecuencia) {
        this.ordenSecuencia = ordenSecuencia;
    }
}