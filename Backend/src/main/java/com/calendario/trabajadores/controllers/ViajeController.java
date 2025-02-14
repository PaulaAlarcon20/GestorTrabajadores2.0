package com.calendario.trabajadores.controllers;

import com.calendario.trabajadores.model.database.EstadoViaje;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@Tag(name = "Viaje", description = "Endpoints para viajes")
public class ViajeController {
    @Autowired
    private ViajeService viajeService;

    //Crear un nuevo viaje (WORKS) pero deberia pasar a usar DTO***
    @Operation(summary = "Crear un viaje", description = "Endpoint crear viaje")
    @PostMapping("/viaje/crear")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje creado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ViajeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> crearViaje(
            @RequestBody ViajeDTO model
    ) {
        return ResponseEntity.ok(viajeService.crearViaje(model));
    }

    // Cambiar estado de un viaje (revisar!!!)
    @Operation(summary = "Cambiar estado de un viaje", description = "Endpoint para cambiar el estado de un viaje")
    @PatchMapping("/viaje/estado")   //PATCH para actualizar solo el estado NO POST NI GET*?
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado del viaje cambiado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ViajeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<?> cambiarEstadoViaje(
            @PathVariable Long idViaje,
            @RequestParam EstadoViaje nuevoEstado
    ) {
        Optional<ViajeDTO> viajeDTOResponse = viajeService.cambiarEstadoViaje(idViaje, nuevoEstado);

        if (viajeDTOResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Viaje no encontrado"));
        }

        return ResponseEntity.ok(viajeDTOResponse.get());
    }

    //Editar datos de un viaje (no revisado)
    //(la mayoria de los datos del viaje son editables mientras no tenga pasajero asignado!)**
    //este endpoint deberia ser solo para viajes sin pasajero asignado !!!**L
    @Operation(summary = "Editar datos de un viaje", description = "Endpoint para editar datos de un viaje")
    @GetMapping("/viaje/editarDatos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Datos del viaje modificados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ViajeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> editarViaje(
            @RequestBody ViajeDTO model
    ) {
        return ResponseEntity.ok(viajeService.crearViaje(model));
    }

    //Listar todos los viajes (uso para admin) (No revisado)
    @Operation(summary = "Listar todos los viajes", description = "Endpoint para listar todos los viajes")
    @GetMapping("/viaje/listar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista creada",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ViajeDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> listarViajes(
            @RequestBody ViajeDTO model
    ) {
        return ResponseEntity.ok(viajeService.crearViaje(model));
    }

    //Listar todos los viajes por usuario (no revisado)
    @Operation(summary = "Listar viajes por usuario", description = "Endpoint para listar viajes por usuario")
    @GetMapping("/viaje/listarByUser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista creada",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ViajeDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> listarViajesByUser(
            @RequestBody ViajeDTO model
    ) {
        return ResponseEntity.ok(viajeService.crearViaje(model));
    }

    //Listar viajes activos de un usuario ***************** (no revisado)
    @Operation(summary = "Listar viajes activos de un usuario", description = "Endpoint para listar viajes activos de un usuario")
    @GetMapping("/viaje/listarActivosByUser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista creada",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ViajeDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> listarViajesActivosByUser(
            @RequestBody ViajeDTO model
    ) {
        return ResponseEntity.ok(viajeService.crearViaje(model));
    }

    //Listar viajes inactivos de un usuario ********* (no revisado)
    @Operation(summary = "Listar viajes inactivos de un usuario", description = "Endpoint para listar viajes inactivos de un usuario")
    @GetMapping("/viaje/listarInactivosByUser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista creada",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ViajeDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> listarViajesInactivosByUser(
            @RequestBody ViajeDTO model
    ) {
        return ResponseEntity.ok(viajeService.crearViaje(model));
    }

}
