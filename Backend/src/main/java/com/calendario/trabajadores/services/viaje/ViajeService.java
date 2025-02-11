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

@Service
public class ViajeService {

    private final IViajeRepository viajeRepository;
    @Autowired
    public ViajeService(IViajeRepository viajeRepository){
        this.viajeRepository = viajeRepository;
    }

    //Crear un viaje
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
}
