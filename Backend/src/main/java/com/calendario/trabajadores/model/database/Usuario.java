package com.calendario.trabajadores.model.database;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
//Utilizo lombok para no tener que escribir los getters y setters
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario {
    //Es un campo identificador de la tabla y además es identity, es decir, se autoincrementa
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    // Campo obligatorio
    @Column(nullable = false)
    public String nombre;
    @Column(nullable = false)
    public String apellido1;
    public String apellido2;
    @Column(nullable = false)
    public String email;
    @Column(nullable = false)
    public String contraseña;
    public String telefono;
    public String centroTrabajo;
    public Puesto puesto;
    public String localidad;
    public String preferenciasHorarias;
    public Boolean disponibilidadHorasExtras;
    @Column(nullable = false)
    public String rol;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, orphanRemoval = false)
    @JsonManagedReference
    public List<Vehiculo> vehiculos = new ArrayList();
    @OneToMany(mappedBy = "conductor", fetch = FetchType.LAZY, orphanRemoval = false)
    public List<Viaje> viajes = new ArrayList();
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, orphanRemoval = false)
    public List<UsuarioViaje> usuarioViajes = new ArrayList<>();
    public boolean activo;

}
