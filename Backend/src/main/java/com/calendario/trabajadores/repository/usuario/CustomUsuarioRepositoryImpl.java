package com.calendario.trabajadores.repository.usuario;

import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.dto.UsuarioDTO;
import com.calendario.trabajadores.model.dto.VehiculoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomUsuarioRepositoryImpl implements CustomUsuarioRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Usuario> findUsuarioByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<Usuario> findUsuariosWithActiveVehiculos() {
        return entityManager.createQuery("SELECT u FROM Usuario u LEFT JOIN u.vehiculos v WHERE v.activo = true",
                Usuario.class).getResultList();
    }

    @Override
    public List<UsuarioDTO> findUsuariosWithTheirActiveVehiculos() {
        List<Usuario> usuarios = entityManager.createQuery(
                "SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.vehiculos v WHERE v.activo = true",
                Usuario.class
        ).getResultList();

        return usuarios.stream().map(u -> new UsuarioDTO(
                u.id,
                u.nombre,
                u.apellido1,
                u.apellido2,
                u.email,
                u.vehiculos.stream()
                        //Expresion lambda para filtrar los vehiculos activos
                        .filter(v -> v.activo)
                        .map(v -> new VehiculoDTO(v.id, v.modeloCoche, v.matricula, v.activo))
                        .toList()
        )).toList();
    }
    //Para obtener una lista de vehiculos activos de un usuario
    @Override
    public List<VehiculoDTO> findVehiculosByUsuario(String email) {
        List<Vehiculo> vehiculos = entityManager.createQuery(
                "SELECT v FROM Vehiculo v JOIN FETCH v.usuario u WHERE u.email = :email AND v.activo = true",
                Vehiculo.class
        ).setParameter("email", email).getResultList();
        return vehiculos.stream().map(v -> new VehiculoDTO(
                v.id,
                v.modeloCoche,
                v.matricula,
                v.activo
        )).toList();
    }

}
