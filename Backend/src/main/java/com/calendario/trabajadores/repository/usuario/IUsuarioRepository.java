package com.calendario.trabajadores.repository.usuario;

import com.calendario.trabajadores.entity.usuario.EntityUsuario;
import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.dto.usuario.LoginResponse;
import com.calendario.trabajadores.model.dto.usuario.UsuarioDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
 * JpaRepository hereda todos los métodos necesarios para realizar el CRUD
 * */
@Repository
public interface IUsuarioRepository extends JpaRepository<EntityUsuario, Integer> {
	
	
	

	// Para realizar consultar personalizadas avanzadas:
    @Query("SELECT u FROM Usuario u WHERE u.activo = :p")
    List<Usuario> findByActivo(@Param("p")boolean activo);
    
    //boolean findByEmail(String email);
    
    
    Optional<EntityUsuario> findByEmailAndContrasena(String email, String contrasena);
    
    Optional<EntityUsuario> findByEmail(String email);
    
    Optional<EntityUsuario> findById(int id);
    
    Optional<EntityUsuario> findByEmailAndInicioSesion(String email, boolean inicioSesion);
}