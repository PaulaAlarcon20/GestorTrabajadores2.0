package com.calendario.trabajadores.repository;

import com.calendario.trabajadores.model.AdministradorServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
//UsuarioRepository hereda de JpaRepository que es una interfaz de Spring Data JPA, que proporciona las operaciones CRUD para la entidad AdministradorServicio
public interface IAdminRepository extends JpaRepository<AdministradorServicio, Long> {
    //Métodos de consulta
    //Método para buscar un AdministradorServicio por su correo electrónico
    Optional<AdministradorServicio> findAdministradorByEmail(String email);

}