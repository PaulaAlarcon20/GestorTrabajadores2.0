package com.calendario.trabajadores.repository.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calendario.trabajadores.entity.usuario.EntityUsuario;

/*
 * JpaRepository hereda todos los métodos necesarios para realizar el CRUD
 * */
@Repository
public interface IUsuarioRepository extends JpaRepository<EntityUsuario, Integer> {
	
	// Para realizar consultar personalizadas avanzadas:
    @Query("SELECT u FROM Usuario u WHERE u.activo = :p")
    List<EntityUsuario> findByActivo(@Param("p")boolean activo);
    
    EntityUsuario findEntityUsuarioById(int userId);
    
    boolean findByEmail(String email);
    

    Optional<EntityUsuario> findByEmailAndContrasena(String email, String contrasena);
    
    Optional<EntityUsuario> findByEmailAndInicioSesion(String email, boolean inicioSesion);

}