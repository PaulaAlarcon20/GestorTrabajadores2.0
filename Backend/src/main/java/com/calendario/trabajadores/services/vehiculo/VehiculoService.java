package com.calendario.trabajadores.services.vehiculo;

import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.dto.usuario.UsuarioDTO;
import com.calendario.trabajadores.model.dto.vehiculo.VehiculoDTO;
import com.calendario.trabajadores.repository.vehiculo.IVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehiculoService {
    //Inyeccion de dependencias
    private final IVehiculoRepository vehiculoRepository;

    //Constructor de VehiculoService
    @Autowired
    public VehiculoService(IVehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    //Crear un vehiculo (WORKS)
    public Optional<VehiculoDTO> crearVehiculo(VehiculoDTO input) {
        //busco el vehiculoModel por matricula para saber si existe
        var vehiculoExists = vehiculoRepository.findVehiculoByMatricula(input.matricula);
        //si existe devolvemos un error
        if (vehiculoExists.isPresent()) {
            return Optional.empty();
        }
        //si no existe creamos el vehiculoModel model
        Vehiculo vehiculoModel = new Vehiculo();
        vehiculoModel.matricula = input.matricula;
        vehiculoModel.modeloCoche = input.modeloCoche;
        vehiculoModel.activo = input.activo;
        vehiculoModel.usuario = new Usuario();
        vehiculoModel.usuario.id = input.usuario.id;
        //guardamos el vehiculoModel en la base de datos
        vehiculoModel.plazas = input.plazas;
        Vehiculo v_guardado = vehiculoRepository.save(vehiculoModel);

        //mapeamos los datos del vehiculoModel a un DTO
        //uso del lombok para crear el constructor vacio
        VehiculoDTO vehiculoDTOResponse = new VehiculoDTO();
        vehiculoDTOResponse.id = v_guardado.id;
        vehiculoDTOResponse.matricula = v_guardado.matricula;
        vehiculoDTOResponse.modeloCoche = v_guardado.modeloCoche;
        vehiculoDTOResponse.activo = v_guardado.activo;
        vehiculoDTOResponse.plazas = v_guardado.plazas;
        vehiculoDTOResponse.usuario = new UsuarioDTO();
        vehiculoDTOResponse.usuario.id = v_guardado.usuario.id;
        return Optional.of(vehiculoDTOResponse);
    }

    //Metodo para modificar un vehiculo ********************** (revisar!!!)
    public Optional<VehiculoDTO> modificarVehiculo(VehiculoDTO input) {
        Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(input.id);
        if (vehiculoOptional.isEmpty()) {
            return Optional.empty();
        }
        Vehiculo vehiculoModel = vehiculoOptional.get();
        // Actualizamos los datos que no son nulos
        if (input.matricula != null) {
            vehiculoModel.matricula = input.matricula;
        }
        if (input.modeloCoche != null) {
            vehiculoModel.modeloCoche = input.modeloCoche;
        }
        if (input.activo != null) {
            vehiculoModel.activo = input.activo;
        }
        if (input.plazas != null) {
            vehiculoModel.plazas = input.plazas;
        }
        if (input.usuario != null && input.usuario.id != null) {
            Usuario usuario = new Usuario();
            usuario.id = input.usuario.id;
            vehiculoModel.usuario = usuario;
        }
        Vehiculo v_actualizado = vehiculoRepository.save(vehiculoModel);
        VehiculoDTO vehiculoDTOResponse = new VehiculoDTO();
        vehiculoDTOResponse.id = v_actualizado.id;
        vehiculoDTOResponse.matricula = v_actualizado.matricula;
        vehiculoDTOResponse.modeloCoche = v_actualizado.modeloCoche;
        vehiculoDTOResponse.activo = v_actualizado.activo;
        vehiculoDTOResponse.plazas = v_actualizado.plazas;
        vehiculoDTOResponse.usuario = new UsuarioDTO();
        vehiculoDTOResponse.usuario.id = v_actualizado.usuario.id;

        return Optional.of(vehiculoDTOResponse);
    }

    //Metodo modificar sin usar tantos if*****
    /*public Optional<VehiculoDTO> modificarVehiculo(VehiculoDTO input) {
        return vehiculoRepository.findById(input.id)
                .map(vehiculo -> {
                    Optional.ofNullable(input.matricula).ifPresent(valor -> vehiculo.matricula = valor);
                    Optional.ofNullable(input.modeloCoche).ifPresent(valor -> vehiculo.modeloCoche = valor);
                    Optional.ofNullable(input.activo).ifPresent(valor -> vehiculo.activo = valor);
                    Optional.ofNullable(input.plazas).ifPresent(valor -> vehiculo.plazas = valor);
                    Optional.ofNullable(input.usuario)
                            .map(usuario -> usuario.id)
                            .ifPresent(id -> {
                                Usuario usuario = new Usuario();
                                usuario.id = id;
                                vehiculo.usuario = usuario;
                            });

                    Vehiculo v_actualizado = vehiculoRepository.save(vehiculo);

                    return new VehiculoDTO(
                            v_actualizado.id,
                            v_actualizado.matricula,
                            v_actualizado.modeloCoche,
                            v_actualizado.activo,
                            v_actualizado.plazas,
                            new UsuarioDTO(v_actualizado.usuario.id)
                    );
                });
    }
    */

    //Metodo para eliminar un vehiculo (activo = false) ***********************
    public Optional<VehiculoDTO> eliminarVehiculo(Long id) {
        // Buscar el vehículo por ID
        Optional<Vehiculo> vehiculoOpt = vehiculoRepository.findById(id);
        // Si no existe, devuelve vacío
        if (vehiculoOpt.isEmpty()) {
            return Optional.empty();
        }
        // Cambiar el estado a inactivo
        Vehiculo vehiculo = vehiculoOpt.get();
        vehiculo.activo = false;

        // Guardar cambios en la base de datos
        Vehiculo v_actualizado = vehiculoRepository.save(vehiculo);

        // Mapear a DTO para devolverlo
        VehiculoDTO vehiculoDTOResponse = new VehiculoDTO();
        vehiculoDTOResponse.id = v_actualizado.id;
        vehiculoDTOResponse.matricula = v_actualizado.matricula;
        vehiculoDTOResponse.modeloCoche = v_actualizado.modeloCoche;
        vehiculoDTOResponse.activo = v_actualizado.activo; // pasa de true a false
        vehiculoDTOResponse.plazas = v_actualizado.plazas;
        vehiculoDTOResponse.usuario = new UsuarioDTO();
        vehiculoDTOResponse.usuario.id = v_actualizado.usuario.id;

        return Optional.of(vehiculoDTOResponse);
    }

}

