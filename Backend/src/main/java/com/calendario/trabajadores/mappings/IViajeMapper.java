package com.calendario.trabajadores.mappings;

import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.database.Viaje;
import com.calendario.trabajadores.model.dto.viaje.CrearEditarViajeResponse;
import com.calendario.trabajadores.model.dto.viaje.CrearViajeRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IViajeMapper {

    CrearEditarViajeResponse viajeToCrearEditarViajeResponse(Viaje viajeGuardado);

    Viaje crearViajeRequestToViaje(CrearViajeRequest request, Usuario conductor, Vehiculo vehiculo);
}
