package com.calendario.trabajadores.model.database;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "vehiculos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"matricula"}),

})
public class Vehiculo {
    //Es un campo identificador de la tabla y además es identity, es decir, se autoincrementa
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String modeloCoche;
    public String matricula;
    public int plazas;
    @ManyToOne(fetch = FetchType.LAZY)
    //Aseguro la relacion de un vehiculo por usuario, no puede ser nulo y el usuario debe ser unico
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    public Usuario usuario;
    //vehiculo activo o no
    public boolean activo;
}
