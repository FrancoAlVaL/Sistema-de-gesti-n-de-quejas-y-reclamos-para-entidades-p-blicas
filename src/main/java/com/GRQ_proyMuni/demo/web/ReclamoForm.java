package com.GRQ_proyMuni.demo.web;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class ReclamoForm {

    private String tipoDocumentoRemitente;
    private String numeroDocumento;
    private String nombre;
    private String apellidos;
    private String celular;
    private String correo;
    private Integer tipoDocumentoId;
    private Integer categoriaId;
    private String numeroDoc;
    private LocalDate fechaDocumento;
    private String asunto;
    private String descripcion;
    private String observaciones;
    private MultipartFile archivoEvidencia;

    public String getTipoDocumentoRemitente() { return tipoDocumentoRemitente; }
    public void setTipoDocumentoRemitente(String tipoDocumentoRemitente) { this.tipoDocumentoRemitente = tipoDocumentoRemitente; }
    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public Integer getTipoDocumentoId() { return tipoDocumentoId; }
    public void setTipoDocumentoId(Integer tipoDocumentoId) { this.tipoDocumentoId = tipoDocumentoId; }
    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }
    public String getNumeroDoc() { return numeroDoc; }
    public void setNumeroDoc(String numeroDoc) { this.numeroDoc = numeroDoc; }
    public LocalDate getFechaDocumento() { return fechaDocumento; }
    public void setFechaDocumento(LocalDate fechaDocumento) { this.fechaDocumento = fechaDocumento; }
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public MultipartFile getArchivoEvidencia() { return archivoEvidencia; }
    public void setArchivoEvidencia(MultipartFile archivoEvidencia) { this.archivoEvidencia = archivoEvidencia; }
}
