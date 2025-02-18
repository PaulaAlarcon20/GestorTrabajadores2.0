package com.calendario.trabajadores.mappings;


import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.dto.vehiculo.CrearEditarVehiculoResponse;
import com.calendario.trabajadores.model.dto.vehiculo.CrearVehiculoRequest;
import com.calendario.trabajadores.model.dto.vehiculo.EditarVehiculoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IVehiculoMapper {

    CrearEditarVehiculoResponse vehiculoToCreateEditResponse(Vehiculo vehiculoDB);



    Vehiculo createRequestToVehiculo(CrearVehiculoRequest request);



    Vehiculo editRequestToUser(EditarVehiculoRequest request);
}

