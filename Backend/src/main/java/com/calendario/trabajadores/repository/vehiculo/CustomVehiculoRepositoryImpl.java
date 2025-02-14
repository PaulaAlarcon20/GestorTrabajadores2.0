package com.calendario.trabajadores.repository.vehiculo;

import com.calendario.trabajadores.model.database.Vehiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class CustomVehiculoRepositoryImpl implements CustomVehiculoRepository {

    @PersistenceContext
    private EntityManager entityManager;


    //Listar vehiculos por usuario
    @Override
    public List<Vehiculo> listarVehiculosUsuario(Long usuarioId) {
        List<Vehiculo> vehiculos = entityManager.createQuery(
                "SELECT v FROM Vehiculo v JOIN FETCH v.usuario u WHERE u.id = :usuario_id",
                Vehiculo.class
        ).setParameter("usuario_id", usuarioId).getResultList();
        return vehiculos;
    }




}
