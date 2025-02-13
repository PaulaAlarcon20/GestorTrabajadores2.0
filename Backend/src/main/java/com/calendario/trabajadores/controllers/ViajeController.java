package com.calendario.trabajadores.controllers;

import com.calendario.trabajadores.model.database.Viaje;
import com.calendario.trabajadores.model.dto.usuario.UsuarioDTO;
import com.calendario.trabajadores.model.dto.viaje.ViajeDTO;
import com.calendario.trabajadores.model.errorresponse.ErrorResponse;
import com.calendario.trabajadores.services.viaje.ViajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Viaje", description = "Endpoints para viajes")
public class ViajeController {
    @Autowired
    private ViajeService viajeService;

    //Crear un nuevo viaje
    @Operation(summary = "Crear un viaje", description = "Endpoint crear viaje")
    @PostMapping("/crearViaje")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje creado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Viaje.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )

    public ResponseEntity<?> crearViaje (
            @RequestBody ViajeDTO model
    ) {
        return ResponseEntity.ok(viajeService.crearViaje(model));
    }
    /////////////////////////////////////
    //Listar todos los viajes (uso para admin)
    @Operation(summary = "Listar todos los viajes", description = "Endpoint para listar todos los viajes")
    @GetMapping("/listViajes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista creada",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ViajeDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> listarViajes (
            @RequestBody ViajeDTO model
    ) {
        return ResponseEntity.ok(viajeService.crearViaje(model));
    }

    //Listar todos los viajes por usuario
    @Operation(summary = "Listar viajes por usuario", description = "Endpoint para listar viajes por usuario")
    @GetMapping("/listViajesByUser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista creada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Viaje.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> listarViajesByUser (
            @RequestBody ViajeDTO model
    ) {
        return ResponseEntity.ok(viajeService.crearViaje(model));
    }

    //Listar viajes activos de un usuario
    @Operation(summary = "Listar viajes activos de un usuario", description = "Endpoint para listar viajes activos de un usuario")
    @GetMapping("/listViajesActivosByUser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista creada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Viaje.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> listarViajesActivosByUser (
            @RequestBody ViajeDTO model
    ) {
        return ResponseEntity.ok(viajeService.crearViaje(model));
    }

    //Listar viajes inactivos de un usuario ***?
    @Operation(summary = "Listar viajes inactivos de un usuario", description = "Endpoint para listar viajes inactivos de un usuario")
    @GetMapping("/listViajesInactivosByUser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista creada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Viaje.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> listarViajesInactivosByUser (
            @RequestBody ViajeDTO model
    ) {
        return ResponseEntity.ok(viajeService.crearViaje(model));
    }

    //Eliminar un viaje
    @Operation(summary = "Eliminar un viaje", description = "Endpoint para eliminar un viaje")
    @GetMapping("/eliminarViaje")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje eliminado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Viaje.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> eliminarViaje (
            @RequestBody ViajeDTO model
    ) {
        return ResponseEntity.ok(viajeService.crearViaje(model));
    }

    //

}
