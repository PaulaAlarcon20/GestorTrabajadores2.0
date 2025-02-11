package com.calendario.trabajadores.model.dto;

import com.calendario.trabajadores.model.database.Puesto;
import com.calendario.trabajadores.model.database.Vehiculo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

public class UsuarioDTO {

    public Long id;

    public String nombre;

    public String apellido1;
    public String apellido2;

    public String email;

    public List<VehiculoDTO> vehiculos;

    public UsuarioDTO(Long id, String nombre, String apellido1, String apellido2, String email, List<VehiculoDTO> vehiculos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.vehiculos = vehiculos;
    }
}
