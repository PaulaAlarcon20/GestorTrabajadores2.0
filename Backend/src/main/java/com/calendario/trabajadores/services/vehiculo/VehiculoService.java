package com.calendario.trabajadores.services.vehiculo;

import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.dto.vehiculo.VehiculoDTO;
import com.calendario.trabajadores.repository.vehiculo.IVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehiculoService {
    //Inyeccion de dependencias
    private final IVehiculoRepository vehiculoRepository;

    //Constructor de VehiculoService
    @Autowired
    public VehiculoService(IVehiculoRepository vehiculoRepository){
        this.vehiculoRepository = vehiculoRepository;
    }

    //Crear un vehiculo
    public Optional<VehiculoDTO> crearVehiculo(VehiculoDTO input){
        //busco el vehiculoModel por matricula para saber si existe
        var vehiculoExists = vehiculoRepository.findVehiculoByMatricula(input.matricula);
        //si existe devolvemos un error
        if (vehiculoExists==null){
            return Optional.empty();
        }
        //si no existe creamos el vehiculoModel model
        Vehiculo vehiculoModel = new Vehiculo();
        vehiculoModel.matricula = input.matricula;
        vehiculoModel.modeloCoche = input.modeloCoche;
        vehiculoModel.activo = input.activo;
        //guardamos el vehiculoModel en la base de datos
        Vehiculo v_guardado = vehiculoRepository.save(vehiculoModel);

        //mapeamos los datos del vehiculoModel a un DTO
        //uso del lombok para crear el constructor vacio
        VehiculoDTO vehiculoDTOResponse = new VehiculoDTO();
        vehiculoDTOResponse.id = v_guardado.id;
        vehiculoDTOResponse.matricula = v_guardado.matricula;
        vehiculoDTOResponse.modeloCoche = v_guardado.modeloCoche;
        vehiculoDTOResponse.activo = v_guardado.activo;
        return Optional.of(vehiculoDTOResponse);
    }
}

