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

import java.time.LocalDate;
import java.util.Date;
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
        request.nombre = "Paco";
        request.apellido1 = "Longaniza";
        request.email = "paco@gmail.com";
        request.password = "123";
        request.rol = "user";
        request.activo = true;
        var response = userService.crearUsuario(request);
        // Se guarda el ID del usuario creado.
        id = response.getData().getId();
    }

    // 1. Test para crear un vehiculo
    @Test
    @DisplayName("AddVehicle")
    @Order(1)
    @Transactional
    void crearVehiculo() {
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.plazas = 3;
        vehiculo.modeloCoche = "Mazda";
        vehiculo.activo = true;
        vehiculo.idUsuario = id;
        vehiculo.matricula = "XYZ123_A"; // Matrícula única
        var response = vehiculoService.crearVehiculo(vehiculo);
        assertEquals("Mazda", response.getData().modeloCoche);
    }

    // 2. Crear sin matrícula
    @Test
    @DisplayName("FailCreateVehicleWithoutMatricula")
    @Order(2)
    @Transactional
    void crearVehiculoSinMatricula() {
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.plazas = 3;
        vehiculo.modeloCoche = "Mazda";
        vehiculo.activo = true;
        vehiculo.idUsuario = id;
        var response = vehiculoService.crearVehiculo(vehiculo);
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
        vehiculo1.plazas = 4;
        vehiculo1.modeloCoche = "Toyota";
        vehiculo1.activo = true;
        vehiculo1.idUsuario = id;
        vehiculo1.matricula = matricula;
        var response1 = vehiculoService.crearVehiculo(vehiculo1);
        assertNotNull(response1.getData());

        var vehiculo2 = new CrearVehiculoRequest();
        vehiculo2.plazas = 5;
        vehiculo2.modeloCoche = "Honda";
        vehiculo2.activo = true;
        vehiculo2.idUsuario = id;
        vehiculo2.matricula = matricula;
        var response2 = vehiculoService.crearVehiculo(vehiculo2);
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
        vehiculo.plazas = 3;
        vehiculo.modeloCoche = "Mazda";
        vehiculo.activo = true;
        vehiculo.idUsuario = id;
        vehiculo.matricula = "XYZ123_T";
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
        vehiculo.plazas = 3;
        vehiculo.modeloCoche = "Mazda";
        vehiculo.activo = true;
        vehiculo.idUsuario = id;
        vehiculo.matricula = "XYZ123_L";
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
        vehiculo.plazas = 3;
        vehiculo.modeloCoche = "Mazda";
        vehiculo.activo = true;
        vehiculo.idUsuario = id;
        vehiculo.matricula = "XYZ123_ID";
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
        vehiculo.plazas = 3;
        vehiculo.modeloCoche = "Mazda";
        vehiculo.activo = true;
        vehiculo.idUsuario = id;
        vehiculo.matricula = "XYZ123_M";
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
        vehiculo.plazas = 3;
        vehiculo.modeloCoche = "Mazda";
        vehiculo.activo = true;
        vehiculo.idUsuario = id;
        vehiculo.matricula = "XYZ123_DEL";
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
        vehiculo.plazas = 3;
        vehiculo.modeloCoche = "Mazda";
        vehiculo.activo = true;
        vehiculo.idUsuario = id;
        vehiculo.matricula = "XYZ123_MOD";
        var responseCreacion = vehiculoService.crearVehiculo(vehiculo);
        var vehiculoEditado = new EditarVehiculoRequest();
        vehiculoEditado.id = responseCreacion.getData().getId();
        vehiculoEditado.modeloCoche = "Honda";
        vehiculoEditado.plazas = 5;
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