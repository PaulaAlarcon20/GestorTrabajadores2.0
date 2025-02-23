package com.calendario.trabajadores.controllers;

import com.calendario.trabajadores.model.dto.vehiculo.CrearEditarVehiculoResponse;
import com.calendario.trabajadores.model.dto.vehiculo.CrearVehiculoRequest;
import com.calendario.trabajadores.model.dto.vehiculo.EditarVehiculoRequest;
import com.calendario.trabajadores.services.vehiculo.VehiculoService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@Tag(name = "Vehiculo", description = "Endpoints para vehiculo")
public class VehiculoController {
    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private UserService userService;

    //No necesito el constructor porque ya tengo la inyeccion de dependencias con @Autowired ***********
    public VehiculoController(VehiculoService vehiculoService, UserService userService) {
        this.vehiculoService = vehiculoService;
        this.userService = userService;
    }

    //Crear un vehículo
    @Operation(summary = "Creación de vehiculo", description = "Endpoint para crear un vehiculo")
    @PostMapping("/vehiculo/crear")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehiculo creado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CrearEditarVehiculoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))}
    )
    public ResponseEntity<?> CrearVehiculo(@RequestBody CrearVehiculoRequest input) {
        Optional<CrearEditarVehiculoResponse> respuestaServicio = vehiculoService.crearVehiculo(input);
        if (respuestaServicio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "el vehiculo ya existe"));
        }
        return ResponseEntity.ok(respuestaServicio.get());
    }

    //Editar los datos de un vehiculo
    @Operation(summary = "Editar vehiculo", description = "Endpoint para editar un vehiculo")
    @PostMapping("/vehiculo/editar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehiculo editado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CrearEditarVehiculoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))}
    )
    public ResponseEntity<?> editarVehiculo(@RequestBody EditarVehiculoRequest input) {
        Optional<CrearEditarVehiculoResponse> respuestaServicio = vehiculoService.modificarVehiculo(input);
        if (respuestaServicio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "error al editar al vehiculo"));
        }
        return ResponseEntity.ok(respuestaServicio.get());
    }

    //Toggle Vehiculo (Activar o Desactivar un vehiculo)
    @Operation(summary = "Activar/desactivar vehiculo", description = "Endpoint para activar/desactivar un vehiculo")
    @PostMapping("/vehiculo/toggle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "vehiculo activado/desactivado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CrearEditarVehiculoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))}
    )
    public ResponseEntity<?> toggleVehiculo(@RequestBody Long id) {
        Optional<CrearEditarVehiculoResponse> respuestaServicio = vehiculoService.toggleVehiculo(id);
        if (respuestaServicio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "error al cambiar estado del vehiculo"));
        }
        return ResponseEntity.ok(respuestaServicio.get());
    }

    //TODO:Listar todos los vehiculos de un usuario (Las tres opciones)
    @Operation(summary = "listar todos los vehiculos de un usuario", description = "Endpoint para listar todos vehiculos" +
            " de un usuario")
    @GetMapping("/vehiculo/list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehiculos listados",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CrearEditarVehiculoResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request  - Parámetro inválido o falta de vehículos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> listAll(@RequestParam(value = "usuarioId") Long usuarioId,
                                     //Se supone que tengo que añadir ativo como filtrado porque si no me devuelve todos
                                     //los vehiculos sin filtrar?
                                     @RequestParam(value = "activo", required = false) Optional<Boolean> activo) {
        List<CrearEditarVehiculoResponse> respuestaServicio = vehiculoService.listarVehiculos(usuarioId, activo);
        if (respuestaServicio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "..."));
        }
        return ResponseEntity.ok(respuestaServicio);
    }


}
