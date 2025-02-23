package com.calendario.trabajadores.mappings;

import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.database.Viaje;
import com.calendario.trabajadores.model.dto.viaje.CrearEditarViajeResponse;
import com.calendario.trabajadores.model.dto.viaje.CrearViajeRequest;
import com.calendario.trabajadores.model.dto.viaje.ViajeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface IViajeMapper {

    CrearEditarViajeResponse viajeToCrearEditarViajeResponse(Viaje viajeGuardado);

    @Mapping(source = "idVehiculo", target = "vehiculo.id") // Only sets ID
    @Mapping(source = "idConductor", target = "conductor.id") // Only sets ID
    @Mapping(source = "fechaSalida", target = "fecha")
    @Mapping(source = "horaSalida", target = "hora")
    @Mapping(target = "usuarioViajes", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "creadoPor", ignore = true)
    @Mapping(target = "modificadoPor", ignore = true)
    Viaje crearViajeRequestToViaje(CrearViajeRequest request);

}
