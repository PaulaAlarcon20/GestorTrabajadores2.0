package com.calendario.trabajadores.services.user;

import com.calendario.trabajadores.model.dto.usuario.CrearUsuarioRequest;
import com.calendario.trabajadores.model.dto.usuario.EditarUsuarioRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//(replace = AutoConfigureTestDatabase.Replace.ANY)
//@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {
    @Autowired
    private UserService userService;
    private Long id;

    private String email;


    @Test
    @DisplayName("CreateUser")
    @Order(1)
    void crearUsuario() {
        var userRequest = new CrearUsuarioRequest();
        userRequest.activo = true;
        userRequest.nombre = "Paco";
        userRequest.apellido1 = "Longaniza";
        userRequest.apellido2 = "Corta";
        userRequest.email = "paco@gmail.com";
        userRequest.localidad = "Leon";
        userRequest.rol = "user";
        userRequest.password = "123";
        var response = userService.crearUsuario(userRequest);
        id = response.getData().id;
        assertNotNull(id);
        assertEquals("Paco",response.getData().nombre);
    }
    @Test
    @DisplayName("FailCreateUser")
    @Order(3)
    void crearUsuarioFail() {
        var userRequest = new CrearUsuarioRequest();
        userRequest.activo = true;
        userRequest.nombre = "Paco";
        userRequest.apellido1 = "Longaniza";
        userRequest.apellido2 = "Corta";
        userRequest.email = "paco2@gmail.com";
        userRequest.localidad = "Leon";
        userRequest.rol = "user";
        var response = userService.crearUsuario(userRequest);
        //Esperar un error, campo de contraseña faltante
        assertNotNull(response.getError().getMessage());
    }

    /*@Test
    @Order(2)
    @Transactional
    void getUsuario() {
        var response = userService.getUsuario(id);
        var nombre = response.getData().nombre;
        assertEquals("Paco", nombre);
    }

    @Test
    @DisplayName("Editar usuario")
    @Order(4)
    @Transactional
    void editUsuario() {
        var userRequest = new EditarUsuarioRequest();
        userRequest.activo = true;
        userRequest.nombre = "Paco";
        userRequest.apellido1 = "Actualizado";
        userRequest.apellido2 = "Modificado";
        userRequest.email = "paco@gmail.com"; // mismo email
        userRequest.localidad = "Madrid";
        userRequest.rol = "admin";
        userRequest.password = "123";

        var response = userService.editUsuario(userRequest);
        assertEquals("Actualizado", response.getData().apellido1);
        assertEquals("admin", response.getData().rol);
    }

    @Test
    @DisplayName("Eliminar usuario lógicamente")
    @Order(5)
    @Transactional
    void softDelete() {
        userService.softDelete(id); // Asume que desactiva el usuario
        var response = userService.getUsuario(id);
        assertEquals(false, response.getData().activo);
    }
    @Test
    @DisplayName("Reactivar usuario")
    @Order(6)
    @Transactional
    void reactivar() {
        userService.reactivar(id); // Reactiva usuario
        var response = userService.getUsuario(id);
        assertEquals(true, response.getData().activo);
    }
    @Test
    @DisplayName("Listar todos los usuarios")
    @Order(7)
    void listar() {
        var lista = userService.listar(Optional.empty());
        assertNotNull(lista);
        assertTrue(lista.getData() != null && !lista.getData().isEmpty());
    }

    @Test
    @DisplayName("Listar usuarios con vehículos")
    @Order(8)
    void listarUsuariosVehiculos() {
        // pasamos el Optional vacio
        var lista = userService.listarUsuariosVehiculos(Optional.empty());
        assertNotNull(lista);
        // o assertFalse(lista.getData().isEmpty())
        assertTrue(lista.getData() != null);
    }
    @Test
    @DisplayName("Listar usuarios con viajes")
    @Order(9)
    void listarUsuariosViajes() {
        var lista = userService.listarUsuariosViajes(null);
        assertNotNull(lista);
        assertTrue(lista.getData() != null);
    }


    @Test
    @DisplayName("Borrar usuario definitivamente")
    @Order(10)
    void borrar() {
        userService.borrar(id, email); // borra completamente de la base de datos
        var response = userService.getUsuario(id);
        assertNotNull(response.getError()); // debería fallar al buscar un usuario que ya no existe
    }
    @Test
    void login() {
    }

    @Test
    void logout() {
    }*/

}