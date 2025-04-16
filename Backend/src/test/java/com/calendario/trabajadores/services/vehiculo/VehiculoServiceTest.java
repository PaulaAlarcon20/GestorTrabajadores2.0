package com.calendario.trabajadores.services.vehiculo;

import static org.junit.jupiter.api.Assertions.*;

import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.dto.usuario.CrearUsuarioRequest;
import com.calendario.trabajadores.model.dto.vehiculo.CrearVehiculoRequest;
import com.calendario.trabajadores.model.dto.vehiculo.EditarVehiculoRequest;
import com.calendario.trabajadores.services.user.UserService;
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


    //Configuramos un usuario antes de los test porque necesitamos un usuario asociado a los vehiculos
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
    void crearVehiculo() {
        // Creamos un nuevo vehiculo
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.plazas = 3;
        vehiculo.modeloCoche = "Mazda";
        vehiculo.activo = true;
        // id del usuario creado en @BeforeAll
        vehiculo.idUsuario = id;
        vehiculo.matricula = "XYZ123";
        // Asociamos el vehiculo al usuario creado previamente
        // Llamamos al servicio para crear el vehículo
        var response = vehiculoService.crearVehiculo(vehiculo);
        // Comprobamos que el modelo del coche sea el que esperamos
        assertEquals("Mazda",response.getData().modeloCoche);
    }

    // 2. Test para modificar un vehículo
    @Test
    @DisplayName("ModifyVehicle")
    @Order(2)
    void modificarVehiculo() {
        //Se crea un vehiculo para poder modificarlo
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.plazas = 3;
        vehiculo.modeloCoche = "Mazda";
        vehiculo.activo = true;
        vehiculo.idUsuario = id;
        //La matricula debe ser unica
        vehiculo.matricula = "XYZ123";
        //Se crea el vehiculo
        var responseCreacion = vehiculoService.crearVehiculo(vehiculo);

        // Verificamos que la creacion fue exitosa
        assertNotNull(responseCreacion.getData().getId(), "El vehículo debe tener un ID después de la creación");

        // Ahora, se modifica el vehiculo
        var vehiculoEditado = new EditarVehiculoRequest();
        // Usamos el ID del vehiculo recien creado
        vehiculoEditado.id = responseCreacion.getData().getId();
        // Modificamos el modelo
        vehiculoEditado.modeloCoche = "Honda";
        // Y Modificamos el numero de plazas tambien
        vehiculoEditado.plazas = 5;
        // Modificamos el vehiculo
        var responseModificacion = vehiculoService.modificarVehiculo(vehiculoEditado);

        // Comprobamos que el vehiculo ha sido modificado
        assertNotNull(responseModificacion.getData(), "La respuesta no debe ser nula");
        assertEquals("Honda", responseModificacion.getData().getModeloCoche(), "El modelo del vehículo debe ser 'Honda'");
        assertEquals(5, responseModificacion.getData().getPlazas(), "El número de plazas debe ser 5");

    }

    // 3. Test para alternar el estado activo/inactivo de un vehiculo
    @Test
    @DisplayName("ToggleVehicleStatus")
    @Order(3)
    void toggleVehiculo() {
        // Primero crea un vehiculo
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.plazas = 3;
        vehiculo.modeloCoche = "Mazda";
        vehiculo.activo = true;
        vehiculo.idUsuario = id;
        //Matricula unica
        vehiculo.matricula = "XYZ123";
        var responseCreacion = vehiculoService.crearVehiculo(vehiculo);

        // Toggle del vehiculo
        var responseToggle = vehiculoService.toggleVehiculo(responseCreacion.getData().getId());

        assertNotNull(responseToggle.getData());
        // Verificar que se desactive
        assertEquals(false, responseToggle.getData().getActivo());
    }

    // 4. Test para listar los vehiculos
    @Test
    @DisplayName("ListVehicles")
    @Order(4)
    void listarVehiculos() {
        // Creamos un vehiculo para asegurarnos de que habra vehiculos en la base de datos
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.plazas = 3;
        vehiculo.modeloCoche = "Mazda";
        vehiculo.activo = true;
        vehiculo.idUsuario = id;
        //Matricula unica
        vehiculo.matricula = "XYZ123";
        //Creamos el vehiculo
        var responseCreacion = vehiculoService.crearVehiculo(vehiculo);

        // Verificamos que la creacion fue exitosa
        assertNotNull(responseCreacion.getData().getId(), "El vehículo debe tener un ID después de la creación");

        // Ahora, listamos los vehiculos con el filtro de activo = true
        // Creamos el Optional con valor 'true'
        var response = vehiculoService.listarVehiculos(id, Optional.of(true));

        // Comprobamos que el listado no sea nulo y que tenga elementos
        assertNotNull(response.getData(), "La respuesta no debe ser nula");
        assertFalse(response.getData().isEmpty(), "El listado de vehículos no debe estar vacío");
        assertTrue(response.getData().stream().allMatch(v -> v.getActivo()), "Todos los vehículos listados deben estar activos");
    }

    // 5. Test para obtener un vehiculo por ID
    @Test
    @DisplayName("GetVehicleById")
    @Order(5)
    void obtenerVehiculoPorId() {
        // Crear vehiculo
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.plazas = 3;
        vehiculo.modeloCoche = "Mazda";
        vehiculo.activo = true;
        vehiculo.idUsuario = id;
        //Continuamos con matricula unica
        vehiculo.matricula = "XYZ123";
        var responseCreacion = vehiculoService.crearVehiculo(vehiculo);

        // Obtener vehiculo por ID
        var response = vehiculoService.obtenerVehiculoPorId(responseCreacion.getData().getId());
        assertNotNull(response.getData());
        assertEquals("Mazda", response.getData().getModeloCoche());
    }

    // 6. Test para obtener un vehiculo por matricula
    @Test
    @DisplayName("GetVehicleByLicensePlate")
    @Order(6)
    void obtenerVehiculoPorMatricula() {
        // Crear vehiculo
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.plazas = 3;
        vehiculo.modeloCoche = "Mazda";
        vehiculo.activo = true;
        vehiculo.idUsuario = id;
        //Continuamos con matricula unica
        vehiculo.matricula = "XYZ123";
        var responseCreacion = vehiculoService.crearVehiculo(vehiculo);

        // Obtener vehiculo por matricula
        var response = vehiculoService.obtenerVehiculoPorMatricula("XYZ123");
        assertNotNull(response.getData());
        assertEquals("Mazda", response.getData().getModeloCoche());
    }

    // 7. Test para eliminar un vehiculo
    @Test
    @DisplayName("DeleteVehicle")
    @Order(7)
    void eliminarVehiculo() {
        // Crear vehiculo
        var vehiculo = new CrearVehiculoRequest();
        vehiculo.plazas = 3;
        vehiculo.modeloCoche = "Mazda";
        vehiculo.activo = true;
        vehiculo.idUsuario = id;
        //Continuamos con matricula unica
        vehiculo.matricula = "XYZ123";
        var responseCreacion = vehiculoService.crearVehiculo(vehiculo);

        // Eliminar vehiculo
        var response = vehiculoService.eliminarVehiculo(responseCreacion.getData().getId());
        assertEquals("Vehículo eliminado correctamente.", response.getData());
    }
}