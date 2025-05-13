package com.calendario.trabajadores.repository.CambioTurno;

import java.util.List;
import java.util.Optional;

import com.calendario.trabajadores.entity.usuario.EntityUsuario;
import com.calendario.trabajadores.model.dto.usuario.UsuarioVehiculosResponse;

public interface CustomCambioTurnoRepository {
    //Métodos de consulta

    //Método para buscar un AdministradorServicio por su correo electrónico
    Optional<EntityUsuario> findEntityUsuarioByEmail(String email);

   //Listar usuarios con sus vehiculos
    List<UsuarioVehiculosResponse> findAllUsuariosVehiculos();
    //
    List<UsuarioVehiculosResponse> findAllUsuariosVehiculosFiltrados(boolean param);
}
