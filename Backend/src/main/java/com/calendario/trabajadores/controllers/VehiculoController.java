package com.calendario.trabajadores.controllers;

import com.calendario.trabajadores.model.dto.usuario.UsuarioDTO;
import com.calendario.trabajadores.model.dto.vehiculo.VehiculoDTO;
import com.calendario.trabajadores.services.vehiculo.VehiculoService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.errorresponse.ErrorResponse;
import com.calendario.trabajadores.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;


@RestController
@Tag(name = "Vehiculo", description = "Endpoints para vehiculo")
public class VehiculoController {
    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private UserService userService;

    public VehiculoController(VehiculoService vehiculoService, UserService userService) {
        this.vehiculoService = vehiculoService;
        this.userService = userService;
    }

    //Crear un vehículo (WORKS)
    @Operation(summary = "Creación de vehiculo", description = "Endpoint para crear un vehiculo")
    @PostMapping("/vehiculo/crear")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehiculo creado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = VehiculoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))}
    )
    public ResponseEntity<?> CrearVehiculo(@RequestBody VehiculoDTO input) {
        Optional<VehiculoDTO> respuestaServicio = vehiculoService.crearVehiculo(input);
        if (respuestaServicio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "el vehiculo ya existe"));
        }
        return ResponseEntity.ok(respuestaServicio.get());
    }

    //Editar los datos de un vehiculo (no revisado)*****
    @Operation(summary = "Editar vehiculo", description = "Endpoint para editar un vehiculo")
    @PostMapping("/vehiculo/editar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehiculo editado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = VehiculoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))}
    )
    public ResponseEntity<?> editarVehiculo(@RequestBody VehiculoDTO input) {
        Optional<VehiculoDTO> respuestaServicio = vehiculoService.modificarVehiculo(input);
        if (respuestaServicio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "error al editar al vehiculo"));
        }
        return ResponseEntity.ok(respuestaServicio.get());
    }

    //Eliminar un vehículo (sofdelete)(no revisado)*****
    @Operation(summary = "Eliminar vehiculo", description = "Endpoint para eliminar un vehiculo")
    @PostMapping("/vehiculo/eliminar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "vehiculo eliminado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = VehiculoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))}
    )
    public ResponseEntity<?> eliminarVehiculo(@RequestBody Long id) {
        Optional<VehiculoDTO> respuestaServicio = vehiculoService.eliminarVehiculo(id);
        if (respuestaServicio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "error al eliminar al vehiculo"));
        }
        return ResponseEntity.ok(respuestaServicio.get());
    }

    //Listar usuariosDTO con vehiculos activos****** (no revisado)
    @Operation(summary = "Listar usuarios con vehiculos activos", description = "Endpoint para listar usuariosDTO con vehiculos activos")
    @GetMapping("/UsuariosDTO/vehiculosActivos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista creada",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsuarioDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> listaActivosDTO() {
        return ResponseEntity.ok(userService.usuariosConVehiculosActivosDTO());
    }

    //Listar todos los vehiculos de un usuarioDTO******* (no revisado)
    @Operation(summary = "Listar usuarios con vehiculos", description = "Endpoint para listar vehiculos de un usuarioDTO")
    @GetMapping("/UsuariosDTO/vehiculosAll")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista creada",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = VehiculoDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> listVehiculosByUserDTO(@RequestParam String email) {
        return ResponseEntity.ok(userService.vehiculosByUsuario(email));
    }

}
