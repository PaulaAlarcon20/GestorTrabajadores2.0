package com.calendario.trabajadores.model;
import jakarta.persistence.*;

@Entity
@Table(name = "trabajadorsanitario")
public class TrabajadorSanitario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String contraseña;
    private String telefono;
    private String centroTrabajo;

    @Enumerated(EnumType.STRING)
    private Puesto puesto; // Enum: Médico, Enfermero, TCAE

    private String localidad;
    private String preferenciasHorarias;
    private Boolean disponibilidadHorasExtras;

    public TrabajadorSanitario() {}

    public TrabajadorSanitario(String nombre, String apellido, String email, String contraseña, String telefono, String centroTrabajo, Puesto puesto, String localidad, String preferenciasHorarias, Boolean disponibilidadHorasExtras) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.centroTrabajo = centroTrabajo;
        this.puesto = puesto;
        this.localidad = localidad;
        this.preferenciasHorarias = preferenciasHorarias;
        this.disponibilidadHorasExtras = disponibilidadHorasExtras;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCentroTrabajo() { return centroTrabajo; }
    public void setCentroTrabajo(String centroTrabajo) { this.centroTrabajo = centroTrabajo; }

    public Puesto getPuesto() { return puesto; }
    public void setPuesto(Puesto puesto) { this.puesto = puesto; }

    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }

    public String getPreferenciasHorarias() { return preferenciasHorarias; }
    public void setPreferenciasHorarias(String preferenciasHorarias) { this.preferenciasHorarias = preferenciasHorarias; }

    public Boolean getDisponibilidadHorasExtras() { return disponibilidadHorasExtras; }
    public void setDisponibilidadHorasExtras(Boolean disponibilidadHorasExtras) { this.disponibilidadHorasExtras = disponibilidadHorasExtras; }

}
