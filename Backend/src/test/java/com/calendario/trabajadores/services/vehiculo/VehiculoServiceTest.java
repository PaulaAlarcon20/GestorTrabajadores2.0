package com.calendario.trabajadores.services.vehiculo;

import static org.junit.jupiter.api.Assertions.*;

import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.dto.vehiculo.CrearVehiculoRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

@SpringBootTest
@AutoConfigureTestDatabase
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VehiculoServiceTest {
    @Autowired
    private VehiculoService vehiculoService;
    @Test
    @DisplayName("AddVehicle")
    @Order(1)
    void crearVehiculo() {
        /*var vehiculo = new CrearVehiculoRequest();
        vehiculo.plazas = 3;
        vehiculo.modeloCoche = "mazda";
        vehiculo.activo = true;
        vehiculoService.crearVehiculo();*/
    }

    @Test
    void modificarVehiculo() {
    }

    @Test
    void toggleVehiculo() {
    }

    @Test
    @DisplayName("Listvehicles")
    void listarVehiculos() {
    }

    @Test
    void obtenerVehiculoPorId() {
    }

    @Test
    void obtenerVehiculoPorMatricula() {
    }

    @Test
    void eliminarVehiculo() {
    }
}