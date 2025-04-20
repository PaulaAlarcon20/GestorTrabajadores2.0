package com.calendario.trabajadores.services.vehiculo;

import static org.junit.jupiter.api.Assertions.*;

import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.dto.usuario.CrearUsuarioRequest;
import com.calendario.trabajadores.model.dto.vehiculo.CrearVehiculoRequest;
import com.calendario.trabajadores.model.dto.vehiculo.EditarVehiculoRequest;
import com.calendario.trabajadores.services.user.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
//@AutoConfigureTestDatabase
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VehiculoServiceTest {
    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private UserService userService;
    private Long id;

    //Configuramos un usuario antes de los test
    //porque necesitamos un usuario asociado a los vehiculos
    @BeforeAll
    void setupUsuario() {
        var request = new CrearUsuarioRequest();
        request.setNombre("Paco");
        request.setApellido1("Longaniza");
        request.setEmail("paco@gmail.com");
        request.setPassword("123");
        request.setRol("user");
        request.setActivo(true);

        // Llamada al servicio para crear el usuario
        var response = userService.crearUsuario(request);
        var result = response.getData();
        // Verificamos si hay un error en la respuesta
        assertNotNull(result.getId(), "Los datos de la respuesta no pueden ser nulos");

        // Se guarda el ID del usuario creado
        id = result.getId();
    }

    // 1. Test para crear un vehiculo
    @Test
    @DisplayName("AddVehicle")
    @Order(1)
    @Transactional
    void crearVehiculo() {
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.setPlazas(3);
        vehiculo.setModeloCoche("Mazda");
        vehiculo.setActivo(true);
        vehiculo.setIdUsuario(id);
        // Matrícula única
        vehiculo.setMatricula("XYZ123_A");
        var response = vehiculoService.crearVehiculo(vehiculo);
        assertEquals("Mazda", response.getData().getModeloCoche());
    }

    // 2. Crear sin matrícula
    @Test
    @DisplayName("FailCreateVehicleWithoutMatricula")
    @Order(2)
    @Transactional
    void crearVehiculoSinMatricula() {
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.setPlazas(3);
        vehiculo.setModeloCoche("Mazda");
        vehiculo.setActivo(true);
        vehiculo.setIdUsuario(id);

        // No asignamos matrícula
        var response = vehiculoService.crearVehiculo(vehiculo);

        // Verificamos que se lanza un error debido a la falta de matrícula
        assertNotNull(response.getError());
        assertEquals("La matrícula es un campo obligatorio", response.getError().getMessage());
    }

    // 3. Crear con matrícula duplicada
    @Test
    @DisplayName("FailCreateVehicleWithDuplicateMatricula")
    @Order(3)
    @Transactional
    void crearVehiculoConMatriculaDuplicada() {
        var matricula = "DUPLI123";
        var vehiculo1 = new CrearVehiculoRequest();
        vehiculo1.setPlazas(4);
        vehiculo1.setModeloCoche("Toyota");
        vehiculo1.setActivo(true);
        vehiculo1.setIdUsuario(id);
        vehiculo1.setMatricula(matricula);
        var response1 = vehiculoService.crearVehiculo(vehiculo1);
        assertNotNull(response1.getData());

        var vehiculo2 = new CrearVehiculoRequest();
        vehiculo2.setPlazas(5);
        vehiculo2.setModeloCoche("Honda");
        vehiculo2.setActivo(true);
        vehiculo2.setIdUsuario(id);
        vehiculo2.setMatricula(matricula);
        var response2 = vehiculoService.crearVehiculo(vehiculo2);

        // Verificamos que se lanza un error cuando intentamos duplicar la matrícula
        assertNotNull(response2.getError());
        assertEquals("El vehículo ya existe", response2.getError().getMessage());
    }

    // 4. Toggle estado
    @Test
    @DisplayName("ToggleVehicleStatus")
    @Order(4)
    @Transactional
    void toggleVehiculo() {
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.setPlazas(3);
        vehiculo.setModeloCoche("Mazda");
        vehiculo.setActivo(true);
        vehiculo.setIdUsuario(id);
        vehiculo.setMatricula("XYZ123_T");
        var responseCreacion = vehiculoService.crearVehiculo(vehiculo);
        var responseToggle = vehiculoService.toggleVehiculo(responseCreacion.getData().getId());
        assertNotNull(responseToggle.getData());
        assertFalse(responseToggle.getData().getActivo());
    }

    // 5. Listar
    @Test
    @DisplayName("ListVehicles")
    @Order(5)
    @Transactional
    void listarVehiculos() {
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.setPlazas(3);
        vehiculo.setModeloCoche("Mazda");
        vehiculo.setActivo(true);
        vehiculo.setIdUsuario(id);
        vehiculo.setMatricula("XYZ123_L");
        vehiculoService.crearVehiculo(vehiculo);
        var response = vehiculoService.listarVehiculos(id, Optional.of(true));
        assertNotNull(response.getData());
        assertFalse(response.getData().isEmpty());
        assertTrue(response.getData().stream().allMatch(v -> v.getActivo()));
    }

    // 6. Obtener por ID
    @Test
    @DisplayName("GetVehicleById")
    @Order(6)
    @Transactional
    void obtenerVehiculoPorId() {
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.setPlazas(3);
        vehiculo.setModeloCoche("Mazda");
        vehiculo.setActivo(true);
        vehiculo.setIdUsuario(id);
        vehiculo.setMatricula("XYZ123_ID");
        var responseCreacion = vehiculoService.crearVehiculo(vehiculo);
        var response = vehiculoService.obtenerVehiculoPorId(responseCreacion.getData().getId());
        assertNotNull(response.getData());
        assertEquals("Mazda", response.getData().getModeloCoche());
    }

    // 7. Obtener por matrícula
    @Test
    @DisplayName("GetVehicleByLicensePlate")
    @Order(7)
    @Transactional
    void obtenerVehiculoPorMatricula() {
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.setPlazas(3);
        vehiculo.setModeloCoche("Mazda");
        vehiculo.setActivo(true);
        vehiculo.setIdUsuario(id);
        vehiculo.setMatricula("XYZ123_M");
        vehiculoService.crearVehiculo(vehiculo);
        var response = vehiculoService.obtenerVehiculoPorMatricula("XYZ123_M");
        assertNotNull(response.getData());
        assertEquals("Mazda", response.getData().getModeloCoche());
    }

    // 8. Eliminar vehículo
    @Test
    @DisplayName("DeleteVehicle")
    @Order(8)
    @Transactional
    void eliminarVehiculo() {
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.setPlazas(3);
        vehiculo.setModeloCoche("Mazda");
        vehiculo.setActivo(true);
        vehiculo.setIdUsuario(id);
        vehiculo.setMatricula("XYZ123_DEL");
        var responseCreacion = vehiculoService.crearVehiculo(vehiculo);
        var response = vehiculoService.eliminarVehiculo(responseCreacion.getData().getId());
        assertEquals("Vehículo eliminado correctamente.", response.getData());
    }

    // 9. Modificar vehículo
    @Test
    @DisplayName("ModifyVehicle")
    @Order(9)
    @Transactional
    void modificarVehiculo() {
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.setPlazas(3);
        vehiculo.setModeloCoche("Mazda");
        vehiculo.setActivo(true);
        vehiculo.setIdUsuario(id);
        vehiculo.setMatricula("XYZ123_MOD");
        var responseCreacion = vehiculoService.crearVehiculo(vehiculo);
        var vehiculoEditado = new EditarVehiculoRequest();
        vehiculoEditado.setId(responseCreacion.getData().getId());
        vehiculoEditado.setModeloCoche("Honda");
        vehiculoEditado.setPlazas(5);
        var responseModificacion = vehiculoService.modificarVehiculo(vehiculoEditado);
        assertNotNull(responseModificacion.getData());
        assertEquals("Honda", responseModificacion.getData().getModeloCoche());
        assertEquals(5, responseModificacion.getData().getPlazas());
    }

    // 10. Eliminar vehículo inexistente
    @Test
    @DisplayName("DeleteNonExistentVehicle")
    @Order(10)
    void eliminarVehiculoInexistente() {
        var response = vehiculoService.eliminarVehiculo(9999L);
        assertNotNull(response.getError());
        assertTrue(response.getError().getMessage().contains("No se puede eliminar: vehículo no encontrado"));
    }
}
