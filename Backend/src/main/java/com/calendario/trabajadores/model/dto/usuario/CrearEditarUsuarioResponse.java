package com.calendario.trabajadores.model.dto.usuario;

import com.calendario.trabajadores.model.database.Puesto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrearEditarUsuarioResponse {

    public Long id;
    public String nombre;
    public String apellido1;
    public String apellido2;
    public String email;
    public String telefono;
    public String centroTrabajo;
    public Puesto puesto;
    public String localidad;
    public String preferenciasHorarias;
    public Boolean disponibilidadHorasExtras;
    public String rol;
    public Boolean activo;
    @CreationTimestamp          //TODO:Revisar
    @Column(updatable = false)
    private Date fechaCreacion;

    @UpdateTimestamp
    private Date fechaModificacion;


}
