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
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // si quisiera usar la bbdd real
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    private Long id;
    private final String email = "paco@gmail.com";
    private final String password = "123";

    @Test
    @DisplayName("1. Crear usuario exitosamente")
    @Order(1)
    void crearUsuario() {
        var userRequest = new CrearUsuarioRequest();
        userRequest.setActivo(true);
        userRequest.setNombre("Paco");
        userRequest.setApellido1("Manzanares");
        // apellido2 es ahora opcional
        userRequest.setApellido2(null); // O simplemente no incluirlo
        userRequest.setEmail(email);
        userRequest.setLocalidad("Leon");
        userRequest.setRol("user");
        userRequest.setPassword(password);

        var response = userService.crearUsuario(userRequest);
        id = response.getData().getId();
        assertNotNull(id);
        assertEquals("Paco", response.getData().getNombre());
    }


    @Test
    @DisplayName("2. Crear usuario existente (debería fallar)")
    @Order(2)
    void crearUsuarioExistente() {
        var userRequest = new CrearUsuarioRequest();
        userRequest.setActivo(true);
        userRequest.setNombre("Repetido");
        userRequest.setApellido1("Apellido");
        userRequest.setApellido2("Apellido");
        userRequest.setEmail(email); // Mismo email
        userRequest.setLocalidad("Leon");
        userRequest.setRol("user");
        userRequest.setPassword("otraClave");

        var response = userService.crearUsuario(userRequest);
        assertNotNull(response.getError());
        assertEquals("El usuario con el email proporcionado ya existe", response.getError().getMessage());
    }

    @Test
    @DisplayName("3. Crear usuario sin contraseña (debe fallar)")
    @Order(3)
    void crearUsuarioSinPassword() {
        var userRequest = new CrearUsuarioRequest();
        userRequest.setActivo(true);
        userRequest.setNombre("SinClave");
        userRequest.setApellido1("Error");
        userRequest.setApellido2("Grave");
        userRequest.setEmail("error@gmail.com");
        userRequest.setLocalidad("Leon");
        userRequest.setRol("user");

        // No asignamos la contraseña
        userRequest.setPassword("");

        var response = userService.crearUsuario(userRequest);

        // Verificamos que haya un error
        assertNotNull(response.getError());

        // Verificamos que el mensaje de error sea el esperado
        assertEquals("El campo 'contraseña' es obligatorio", response.getError().getMessage());
    }


    @Test
    @Order(4)
    @Transactional
    @DisplayName("4. Obtener usuario por ID")
    void getUsuarioPorId() {
        var response = userService.getUsuario(id);
        assertEquals("Paco", response.getData().getNombre());
    }

    @Test
    @Order(5)
    @Transactional
    @DisplayName("5. Obtener usuario por email")
    void getUsuarioPorEmail() {
        var response = userService.getUsuario(email);
        assertNotNull(response.getData());
        assertEquals("Paco", response.getData().getNombre());
    }

    @Test
    @Order(6)
    @DisplayName("6. Editar usuario correctamente")
    void editarUsuario() {
        var editRequest = new EditarUsuarioRequest();
        editRequest.setId(id);
        editRequest.setNombre("Paco");
        editRequest.setApellido1("Actualizado");
        editRequest.setApellido2("Cambiado");
        editRequest.setEmail(email);
        editRequest.setLocalidad("Madrid");
        editRequest.setRol("admin");
        editRequest.setPassword(password);
        editRequest.setActivo(true);

        var response = userService.editUsuario(editRequest);
        assertEquals("Actualizado", response.getData().getApellido1());
        assertEquals("admin", response.getData().getRol());
    }

    @Test
    @Order(7)
    @Transactional
    @DisplayName("7. Soft delete del usuario")
    void desactivarUsuario() {
        var response = userService.softDelete(id);
        assertFalse(response.getData().getActivo());
    }

    @Test
    @Order(8)
    @Transactional
    @DisplayName("8. Reactivar usuario")
    void reactivarUsuario() {
        var response = userService.reactivar(id);
        assertTrue(response.getData().getActivo());
    }

    @Test
    @Order(9)
    @Transactional
    @DisplayName("9. Listar todos los usuarios")
    void listarUsuarios() {
        var lista = userService.listar(Optional.empty());
        assertNotNull(lista.getData());
        assertFalse(lista.getData().isEmpty());
    }

    @Test
    @Order(10)
    @Transactional
    @DisplayName("10. Login exitoso")
    void loginExitoso() {
        var response = userService.login(email, password);
        assertNotNull(response.getData());
        assertEquals(email, response.getData().getEmail());
    }

    @Test
    @Order(11)
    @DisplayName("11. Login con contraseña incorrecta")
    void loginPasswordIncorrecta() {
        var response = userService.login(email, "claveErrónea");
        assertNotNull(response.getError());
        assertEquals("Contraseña incorrecta", response.getError().getMessage());
    }

    @Test
    @Order(12)
    @DisplayName("12. Login con usuario no existente")
    void loginUsuarioNoExiste() {
        var response = userService.login("noexiste@gmail.com", "clave");
        assertNotNull(response.getError());
        assertEquals("Usuario no encontrado", response.getError().getMessage());
    }

    @Test
    @Order(13)
    @Transactional
    @DisplayName("13. Logout exitoso")
    void logoutExitoso() {
        var response = userService.logout(email, password);
        assertNotNull(response.getData());
        assertEquals(email, response.getData().getEmail());
    }

    @Test
    @Order(14)
    @Transactional
    @DisplayName("14. Listar usuarios con vehículos")
    void listarUsuariosConVehiculos() {
        var response = userService.listarUsuariosVehiculos(Optional.empty());
        assertNotNull(response.getData());
    }

    @Test
    @Order(15)
    @Transactional
    @DisplayName("15. Listar usuarios con viajes")
    void listarUsuariosConViajes() {
        var response = userService.listarUsuariosViajes(null);
        assertNotNull(response.getData());
    }

    @Test
    @Order(16)
    @Transactional
    @DisplayName("16. Borrar usuario definitivamente")
    void borrarUsuarioDefinitivamente() {
        var response = userService.borrar(id, email);
        assertNotNull(response.getData());

        var check = userService.getUsuario(id);
        assertNotNull(check.getError());
    }
}