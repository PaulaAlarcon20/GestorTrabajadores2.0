package com.calendario.trabajadores.model.dto.vehiculo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//CrearVehiculoRequest no necesita el id del vehiculo porque se autogenera
//Anotaciones de Lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrearVehiculoRequest {
    //Atributos de CrearVehiculoRequest
    public String modeloCoche;
    public String matricula;
    public Integer plazas;
    public Long idUsuario;
    public Boolean activo;

}
