package com.calendario.trabajadores.model;
import jakarta.persistence.*;

@Entity
@Table(name = "administradorservicio")
public class AdministradorServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String contraseña;
    private String permisosEspeciales;

    public AdministradorServicio() {}

    public AdministradorServicio(String nombre, String apellido, String email, String contraseña, String permisosEspeciales) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contraseña = contraseña;
        this.permisosEspeciales = permisosEspeciales;
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

    public String getPermisosEspeciales() { return permisosEspeciales; }
    public void setPermisosEspeciales(String permisosEspeciales) { this.permisosEspeciales = permisosEspeciales; }
}
