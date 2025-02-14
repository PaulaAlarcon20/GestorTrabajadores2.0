package com.calendario.trabajadores.services.viaje;

import com.calendario.trabajadores.model.database.EstadoViaje;
import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.database.Viaje;
import com.calendario.trabajadores.model.dto.viaje.ViajeDTO;
import com.calendario.trabajadores.repository.viaje.IViajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ViajeService {

    private final IViajeRepository viajeRepository;

    @Autowired
    public ViajeService(IViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
    }

    //Crear un viaje (WORKS)   convertir a DTO?????
    public Viaje crearViaje(ViajeDTO param) {
        Viaje viaje = new Viaje();
        viaje.conductor = new Usuario();
        viaje.conductor.id = param.idConductor;
        viaje.origen = param.origen;
        viaje.destino = param.destino;
        viaje.fecha = param.fechaSalida;
        viaje.hora = param.horaSalida;
        viaje.vehiculo = new Vehiculo();
        viaje.vehiculo.id = param.idVehiculo;
        viaje.plazas = param.plazas;
        viaje.estado = EstadoViaje.DISPONIBLE;
        return viajeRepository.save(viaje);
    }

    // Cambiar el estado de un viaje con validaciones
    public Optional<ViajeDTO> cambiarEstadoViaje(Long idViaje, EstadoViaje nuevoEstado) {
        // Buscar el viaje por ID
        Optional<Viaje> viajeOptional = viajeRepository.findById(idViaje);

        // Si no existe, retornamos vacío
        if (viajeOptional.isEmpty()) {
            return Optional.empty();
        }

        Viaje viajeModel = viajeOptional.get();

        // Validación: evitar que un viaje finalizado vuelva a en curso o disponible
        if (viajeModel.getEstado() == EstadoViaje.FINALIZADO && nuevoEstado != EstadoViaje.FINALIZADO) {
            throw new RuntimeException("No se puede cambiar el estado de un viaje finalizado.");
        }

        // Validación: para que el nuevo estado no sea el mismo que el actual
        if (viajeModel.getEstado() == nuevoEstado) {
            throw new RuntimeException("El viaje ya está en el estado solicitado.");
        }

        // Actualizamos el estado del viaje
        viajeModel.setEstado(nuevoEstado);

        // Guardamos los cambios
        Viaje viajeActualizado = viajeRepository.save(viajeModel);

        // Mapeamos los datos del viajeModel a un DTO
        //no se si el mapeo esta bien*****************
        ViajeDTO viajeDTOResponse = new ViajeDTO();
        viajeDTOResponse.idVehiculo = viajeActualizado.getVehiculo().getId();
        viajeDTOResponse.idConductor = viajeActualizado.getConductor().getId();
        viajeDTOResponse.origen = viajeActualizado.getOrigen();
        viajeDTOResponse.destino = viajeActualizado.getDestino();
        viajeDTOResponse.fechaSalida = viajeActualizado.getFecha();
        viajeDTOResponse.horaSalida = viajeActualizado.getHora();
        viajeDTOResponse.plazas = viajeActualizado.getPlazas();

        return Optional.of(viajeDTOResponse);
    }

    /* Cambiar estado de un viaje (otra opcion)
    public Optional<Viaje> cambiarEstadoViajeA(Long idViaje, EstadoViaje nuevoEstado) {
        return viajeRepository.findById(idViaje)
                .map(viaje -> {
                    viaje.estado = nuevoEstado;
                    return viajeRepository.save(viaje);
                });
    }
    */

    //Editar datos de un viaje *********** tengo otra opcion abajo (estado me da igual porque tengo otro metodo
    // que solo cambia los estados de un viaje??)

    public Viaje editarViaje(Long id, ViajeDTO param) {
        // Buscar el viaje en la base de datos
        Optional<Viaje> viajeOptional = viajeRepository.findById(id);

        // Si no existe, lanzar una excepción o devolver null
        if (viajeOptional.isEmpty()) {
            throw new RuntimeException("Viaje no encontrado");
        }

        Viaje viaje = viajeOptional.get();
        // Actualizar solo los campos que no son null en el DTO
        if (param.idConductor != null) {
            viaje.conductor = new Usuario();
            viaje.conductor.id = param.idConductor;
        }
        if (param.origen != null) {
            viaje.origen = param.origen;
        }
        if (param.destino != null) {
            viaje.destino = param.destino;
        }
        if (param.fechaSalida != null) {
            viaje.fecha = param.fechaSalida;
        }
        if (param.horaSalida != null) {
            viaje.hora = param.horaSalida;
        }
        if (param.idVehiculo != null) {
            viaje.vehiculo = new Vehiculo();
            viaje.vehiculo.id = param.idVehiculo;
        }
        if (param.plazas != null) {
            viaje.plazas = param.plazas;
        }
        /*    estado no se si quitarlo****
        if (param.estado != null) {
            viaje.estado = param.estado;
        }
        */
        // Guardar los cambios en la base de datos
        return viajeRepository.save(viaje);
    }

    //Editar viaje pero sin usar tantos if: ************
    // Optional.ofNullable() para asignar valores solo si no son null
    /*public Viaje editarViaje(Long id, ViajeDTO param) {
        Viaje viaje = viajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));

        Optional.ofNullable(param.idConductor).ifPresent(idConductor -> {
            viaje.conductor = new Usuario();
            viaje.conductor.id = idConductor;
        });
        Optional.ofNullable(param.origen).ifPresent(v -> viaje.origen = v);
        Optional.ofNullable(param.destino).ifPresent(v -> viaje.destino = v);
        Optional.ofNullable(param.fechaSalida).ifPresent(v -> viaje.fecha = v);
        Optional.ofNullable(param.horaSalida).ifPresent(v -> viaje.hora = v);
        Optional.ofNullable(param.idVehiculo).ifPresent(idVehiculo -> {
            viaje.vehiculo = new Vehiculo();
            viaje.vehiculo.id = idVehiculo;
        });
        Optional.ofNullable(param.plazas).ifPresent(v -> viaje.plazas = v);
        Optional.ofNullable(param.estado).ifPresent(v -> viaje.estado = v);

        return viajeRepository.save(viaje);
    }
    */

}


