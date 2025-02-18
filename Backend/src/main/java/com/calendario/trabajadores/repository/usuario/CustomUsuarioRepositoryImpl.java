package com.calendario.trabajadores.repository.usuario;

import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.dto.usuario.UsuarioVehiculosResponse;
import com.calendario.trabajadores.model.dto.vehiculo.VehiculoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomUsuarioRepositoryImpl implements CustomUsuarioRepository {
    @PersistenceContext
    private EntityManager entityManager;
    //Método para buscar un AdministradorServicio por su correo electrónico
    @Override
    public Optional<Usuario> findUsuarioByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst();
    }
    //Listar usuarios con sus vehiculos
    @Override
    public List<UsuarioVehiculosResponse> findAllUsuariosVehiculos() {
        var resp = entityManager.createQuery("SELECT com.calendario.trabajadores.model.dto.usuario.UsuarioVehiculosResponse" +
                        "(u.id, u.nombre, u.apellido1, u.apellido2, u.email,  " +
                        "(SELECT new com.calendario.trabajadores.model.dto.vehiculo.VehiculoDTO(v.id, v.marca, v.modelo) " +
                        "FROM Vehiculo v WHERE v.usuario.id = u.id))  u.activo) FROM Usuario u", UsuarioVehiculosResponse.class)
                .getResultList();
        return resp;
    }
    //
    @Override
    public List<UsuarioVehiculosResponse> findAllUsuariosVehiculosFiltrados(boolean param) {
        var resp = entityManager.createQuery(
                        "SELECT new com.calendario.trabajadores.model.dto.usuario.UsuarioVehiculosResponse(" +
                                "   u.id, u.nombre, u.apellido1, u.apellido2, u.email, " +
                                "   (SELECT COALESCE(" +
                                "       CAST(COUNT(v) AS integer), 0) FROM Vehiculo v " +
                                "    WHERE v.usuario.id = u.id AND v.activo = :activo)) " +
                                "FROM Usuario u", UsuarioVehiculosResponse.class)
                .setParameter("activo", param)
                .getResultList();
        return resp;
    }

}
