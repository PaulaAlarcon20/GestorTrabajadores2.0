package com.calendario.trabajadores.model.dto;

import com.calendario.trabajadores.model.database.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

public class VehiculoDTO {

    public Long id;

    public String modeloCoche;
    public String matricula;
    public int plazas;
    public UsuarioDTO usuario;
    public boolean activo;

    public VehiculoDTO(Long id, String modeloCoche, String matricula, boolean activo) {
        this.id = id;
        this.modeloCoche = modeloCoche;
        this.matricula = matricula;
        this.activo = activo;
    }
}
