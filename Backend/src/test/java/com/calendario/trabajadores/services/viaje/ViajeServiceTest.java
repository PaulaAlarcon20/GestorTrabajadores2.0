package com.calendario.trabajadores.services.viaje;

import com.calendario.trabajadores.mappings.IViajeMapper;
import com.calendario.trabajadores.model.database.EstadoViaje;
import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.database.Viaje;
import com.calendario.trabajadores.model.dto.viaje.CrearViajeRequest;
import com.calendario.trabajadores.model.dto.viaje.EditarViajeRequest;
import com.calendario.trabajadores.model.dto.viaje.CrearEditarViajeResponse;
import com.calendario.trabajadores.repository.usuario.IUsuarioRepository;
import com.calendario.trabajadores.repository.vehiculo.IVehiculoRepository;
import com.calendario.trabajadores.repository.viaje.IViajeRepository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@AutoConfigureTestDatabase
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ViajeServiceTest {

    @Autowired
    private ViajeService viajeService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IVehiculoRepository vehiculoRepository;

    @Autowired
    private IViajeRepository viajeRepository;

    @Autowired
    private IViajeMapper viajeMapper;

    private Usuario conductor;
    private Vehiculo vehiculo;

    @BeforeEach
    public void setup() {
        // Configuramos el usuario (conductor)
        conductor = new Usuario();
        conductor.setNombre("Juan");
        conductor.setApellido1("Perez");
        conductor.setEmail("juan.perez@gmail.com");
        conductor.setContraseña("12345");
        conductor.setActivo(true);
        conductor.setRol("user");
        usuarioRepository.save(conductor);

        // Configuración del vehículo con todos los campos obligatorios
        vehiculo = new Vehiculo();
        vehiculo.setModeloCoche("Seat");
        vehiculo.setMatricula("ABC1234");
        vehiculo.setPlazas(5);
        vehiculo.setActivo(true);
        vehiculo.setUsuario(conductor);
        vehiculoRepository.save(vehiculo);
    }

    @Transactional
    @Test
    public void testCrearViaje() {
        CrearViajeRequest request = new CrearViajeRequest();
        request.setIdConductor(conductor.getId());
        request.setIdVehiculo(vehiculo.getId());
        request.setOrigen("Alicante");
        request.setDestino("Madrid");
        request.setFechaSalida(LocalDate.now().plusDays(1));
        request.setHoraSalida(LocalTime.of(10, 30));
        request.setPlazas(3);

        var response = viajeService.crearViaje(request);

        assertNotNull(response);
        assertNull(response.getError());
        assertNotNull(response.getData());
        assertEquals("Alicante", response.getData().getOrigen());
    }

    @Transactional
    @Test
    public void testEditarViaje() {
        // Creamos primero un viaje
        CrearViajeRequest request = new CrearViajeRequest();
        request.setIdConductor(conductor.getId());
        request.setIdVehiculo(vehiculo.getId());
        request.setOrigen("Valencia");
        request.setDestino("Sevilla");
        request.setFechaSalida(LocalDate.now().plusDays(1));
        request.setHoraSalida(LocalTime.of(9, 0));
        request.setPlazas(4);

        var responseCreate = viajeService.crearViaje(request);
        Long viajeId = responseCreate.getData().getId();

        // Verifica que el viaje fue creado correctamente
        assertNotNull(viajeId, "El viaje no fue creado correctamente");

        // Modificamos el viaje
        EditarViajeRequest editRequest = new EditarViajeRequest();
        editRequest.setDestino("Granada");

        var response = viajeService.editarViaje(viajeId, editRequest);
        //ojo viaje optional*
        var result = response.get();
        // Verifica que el resultado sea el esperado
        assertNotNull(result, "El viaje no fue editado correctamente");
        assertEquals("Granada", result.getDestino(), "El destino no se actualizó correctamente");
    }


    @Transactional
    @Test
    public void testCambiarEstadoViaje_confirmar() {
        var viaje = new Viaje();
        viaje.setConductor(conductor);
        viaje.setVehiculo(vehiculo);
        viaje.setEstado(EstadoViaje.DISPONIBLE);
        viaje.setOrigen("Zaragoza");
        viaje.setDestino("Bilbao");
        viaje.setFechaSalida(LocalDate.now().plusDays(1));
        viaje.setHoraSalida(LocalTime.of(8, 0));
        viaje.setPlazas(2);
        viaje = viajeRepository.save(viaje);

        var result = viajeService.cambiarEstadoViaje(viaje.getId(), "confirmar");

        assertTrue(result.isPresent());
        assertEquals(EstadoViaje.EN_CURSO, viajeRepository.findById(viaje.getId()).get().getEstado());
    }

    @Transactional
    @Test
    public void testCambiarEstadoViaje_finalizar() {
        var viaje = new Viaje();
        viaje.setConductor(conductor);
        viaje.setVehiculo(vehiculo);
        viaje.setEstado(EstadoViaje.EN_CURSO);
        viaje.setOrigen("Toledo");
        viaje.setDestino("Córdoba");
        viaje.setFechaSalida(LocalDate.now().plusDays(1));
        viaje.setHoraSalida(LocalTime.of(7, 0));
        viaje.setPlazas(1);
        viaje = viajeRepository.save(viaje);
        var result = viajeService.cambiarEstadoViaje(viaje.getId(), "finalizar");
        assertTrue(result.isPresent());
        assertEquals(EstadoViaje.FINALIZADO, viajeRepository.findById(viaje.getId()).get().getEstado());
    }

    @Test
    public void testListarDatosViaje() {
        var viaje = new Viaje();
        viaje.setConductor(conductor);
        viaje.setVehiculo(vehiculo);
        viaje.setOrigen("Málaga");
        viaje.setDestino("Barcelona");
        viaje.setEstado(EstadoViaje.DISPONIBLE);
        viaje.setFechaSalida(LocalDate.now().plusDays(2));
        viaje.setHoraSalida(LocalTime.of(12, 0));
        viaje.setPlazas(5);
        viaje = viajeRepository.save(viaje);

        var result = viajeService.listarDatosViaje(viaje.getId());

        assertTrue(result.isPresent());
        assertEquals("Málaga", result.get().getOrigen());
        assertEquals("Barcelona", result.get().getDestino());
    }

}
