package com.calendario.trabajadores.services.vehiculo;

import com.calendario.trabajadores.mappings.IVehiculoMapper;
import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.dto.vehiculo.CrearEditarVehiculoResponse;
import com.calendario.trabajadores.model.dto.vehiculo.CrearVehiculoRequest;
import com.calendario.trabajadores.model.dto.vehiculo.EditarVehiculoRequest;
import com.calendario.trabajadores.repository.vehiculo.IVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehiculoService {
    //Inyeccion de dependencias
    private final IVehiculoRepository vehiculoRepository;
    private final IVehiculoMapper vehiculoMapper;

    //Constructor de VehiculoService
    @Autowired
    public VehiculoService(IVehiculoRepository vehiculoRepository, IVehiculoMapper vehiculoMapper) {

        this.vehiculoRepository = vehiculoRepository;
        this.vehiculoMapper = vehiculoMapper;
    }

    //Crear un vehiculo (WORKS)
    public Optional<CrearEditarVehiculoResponse> crearVehiculo(CrearVehiculoRequest request) {
        //busco el vehiculoModel por matricula para saber si existe
        var vehiculoExists = vehiculoRepository.findVehiculoByMatricula(request.matricula);
        //si existe devolvemos un error
        if (vehiculoExists.isPresent()) {
            return Optional.empty();
        }
        //si no existe creamos el vehiculoModel
        Vehiculo vehiculoModel = vehiculoMapper.createRequestToVehiculo(request);

        //guardamos el vehiculoModel en la base de datos
        Vehiculo v_guardado = vehiculoRepository.save(vehiculoModel);
        var response = vehiculoMapper.vehiculoToCreateEditResponse(v_guardado);
        return Optional.of(response);
    }

    //Metodo para modificar un vehiculo (.OK))
    public Optional<CrearEditarVehiculoResponse> modificarVehiculo(EditarVehiculoRequest request) {
        Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(request.id);
        if (vehiculoOptional.isEmpty()) {
            return Optional.empty();
        }
        Vehiculo vehiculoModel = vehiculoOptional.get();
        // Validaciones
        if (!request.getMatricula().isBlank()) {
            vehiculoModel.setMatricula(request.getMatricula());
        }
        if (!request.modeloCoche.isBlank()) {
            vehiculoModel.setModeloCoche(request.getModeloCoche());
        }
        if (request.activo != null) {
            vehiculoModel.setActivo(request.getActivo());
        }
        if (request.plazas != 0) {
            vehiculoModel.setPlazas(request.getPlazas());
        }
        //Creo un usuario vacio con el id del request
        if (request.getIdUsuario() != 0) {
            Usuario tempUser = new Usuario();
            tempUser.setId(request.getIdUsuario());
            //El ID que he conseguido con el get se lo paso a tempUser
            //Igualo tempUser con el usuario de vehiculoModel
            vehiculoModel.usuario = tempUser;
        }
        Vehiculo v_actualizado = vehiculoRepository.save(vehiculoModel);

        return Optional.of(vehiculoMapper.vehiculoToCreateEditResponse(v_actualizado));
    }

    //Metodo para desactivar un vehiculo (activo = false) (O.K)
    public Optional<CrearEditarVehiculoResponse> toggleVehiculo(Long id) {
        // Buscar el vehículo por ID
        Optional<Vehiculo> vehiculoOpt = vehiculoRepository.findById(id);
        // Si no existe, devuelve vacío
        if (vehiculoOpt.isEmpty()) {
            return Optional.empty();
        }
        // Cambiar el estado a inactivo
        Vehiculo tempV = vehiculoOpt.get();
        // Cambiar el estado del vehículo
        tempV.setActivo(!tempV.getActivo());

        // Guardar cambios en la base de datos
        Vehiculo v_actualizado = vehiculoRepository.save(tempV);

        // Mapear a DTO para devolverlo
        var response = vehiculoMapper.vehiculoToCreateEditResponse(v_actualizado);

        return Optional.of(response);
    }


}

