package com.GRQ_proyMuni.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "tipo_documento", length = 50)
    private String tipoDocumento;

    @Column(name = "numero_documento", length = 30)
    private String numeroDocumento;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellidos", nullable = false, length = 150)
    private String apellidos;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "genero", length = 30)
    private String genero;

    @Column(name = "correo", nullable = false, length = 150)
    private String correo;

    @Column(name = "celular", length = 20)
    private String celular;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "ultimo_acceso")
    private LocalDateTime ultimoAcceso;

    @Column(name = "estado_activo", nullable = false)
    private Boolean estadoActivo = true;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "id_area", nullable = false)
    private Area area;

    public Usuario() {
    }

    // Getters y setters
}