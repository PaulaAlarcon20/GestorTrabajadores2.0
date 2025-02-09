package com.calendario.trabajadores.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
