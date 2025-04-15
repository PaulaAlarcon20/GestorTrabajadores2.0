package com.calendario.trabajadores.services.user;

import com.calendario.trabajadores.model.dto.usuario.CrearUsuarioRequest;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
//(replace = AutoConfigureTestDatabase.Replace.ANY)
//@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {
    @Autowired
    private UserService userService;
    private Long id;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void login() {
    }

    @Test
    void logout() {
    }

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
        var id = response.getData().id;
        assertNotNull(id);
        assertEquals("Paco",response.getData().nombre);
    }

    @Test
    void editUsuario() {
    }

    @Test
    void softDelete() {
    }

    @Test
    void reactivar() {
    }

    @Test
    void getUsuario() {
    }

    @Test
    void testGetUsuario() {
    }

    @Test
    void listar() {
    }

    @Test
    void listarUsuariosVehiculos() {
    }

    @Test
    void borrar() {
    }

    @Test
    void listarUsuariosViajes() {
    }
}