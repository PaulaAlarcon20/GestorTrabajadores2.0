package com.calendario.trabajadores.controllers;

import com.calendario.trabajadores.model.database.EstadoViaje;
import com.calendario.trabajadores.model.dto.usuario.UsuarioResponse;
import com.calendario.trabajadores.model.dto.viaje.CrearEditarViajeResponse;
import com.calendario.trabajadores.model.dto.viaje.CrearViajeRequest;
import com.calendario.trabajadores.model.dto.viaje.EditarViajeRequest;
import com.calendario.trabajadores.model.dto.viaje.ViajeResponse;
import com.calendario.trabajadores.model.errorresponse.ErrorResponse;
import com.calendario.trabajadores.model.errorresponse.GenericResponse;
import com.calendario.trabajadores.services.user.UserService;
import com.calendario.trabajadores.services.viaje.ViajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/viajes")
@Tag(name = "Viajes", description = "Operaciones relacionadas con los viajes")
public class ViajeController {

    private final ViajeService viajeService;

    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    @Operation(summary = "Crear un nuevo viaje", description = "Permite al conductor crear un viaje indicando origen, destino, fecha y plazas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje creado correctamente",
                    content = @Content(schema = @Schema(implementation = CrearEditarViajeResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error en la validación de los datos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<GenericResponse<CrearEditarViajeResponse>> crearViaje(@RequestBody @Valid CrearViajeRequest request) {
        return ResponseEntity.ok(viajeService.crearViaje(request));
    }

    @Operation(summary = "Editar un viaje existente", description = "Permite al conductor editar los detalles de un viaje.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje editado correctamente",
                    content = @Content(schema = @Schema(implementation = CrearEditarViajeResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error en la validación de los datos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<CrearEditarViajeResponse>> editarViaje(@PathVariable Long id,
                                                                                 @RequestBody @Valid EditarViajeRequest request) {
        return ResponseEntity.ok(viajeService.editarViaje(id, request));
    }

    @Operation(summary = "Cancelar un viaje", description = "Permite al conductor cancelar un viaje.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje cancelado correctamente"),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> cancelarViaje(@PathVariable Long id) {
        return ResponseEntity.ok(viajeService.cancelarViaje(id));
    }

    @Operation(summary = "Listar todos los viajes", description = "Listar viajes disponibles o creados por un usuario, dependiendo de su rol.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viajes listados correctamente",
                    content = @Content(schema = @Schema(implementation = CrearEditarViajeResponse.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron viajes",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<GenericResponse<List<CrearEditarViajeResponse>>> listarViajes(@RequestParam Long usuarioId,
                                                                                        @RequestParam String rol,
                                                                                        @RequestParam EstadoViaje estado) {
        return ResponseEntity.ok(viajeService.listarViajes(usuarioId, rol, estado));
    }

    @Operation(summary = "Obtener datos del viaje", description = "Obtener detalles de un viaje por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Datos del viaje obtenidos correctamente",
                    content = @Content(schema = @Schema(implementation = ViajeResponse.class))),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ViajeResponse>> listarDatosViaje(@PathVariable Long id) {
        return ResponseEntity.ok(viajeService.listarDatosViaje(id));
    }

    @Operation(summary = "Solicitar unirse a un viaje", description = "Permite a un usuario solicitar unirse a un viaje disponible.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud enviada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuario o viaje no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/{viajeId}/solicitar")
    public ResponseEntity<GenericResponse<Void>> solicitarUnirseViaje(@RequestParam Long usuarioId,
                                                                      @PathVariable Long viajeId) {
        return ResponseEntity.ok(viajeService.solicitarUnirseViaje(usuarioId, viajeId));
    }

    @Operation(summary = "Aceptar solicitud de viaje", description = "Permite al conductor aceptar una solicitud de un usuario para unirse a un viaje.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud aceptada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al aceptar la solicitud",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/{viajeId}/aceptarSolicitud")
    public ResponseEntity<GenericResponse<Void>> aceptarSolicitud(@RequestParam Long usuarioViajeId) {
        return ResponseEntity.ok(viajeService.aceptarSolicitud(usuarioViajeId));
    }

    @Operation(summary = "Rechazar solicitud de viaje", description = "Permite al conductor rechazar una solicitud de un usuario para unirse a un viaje.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud rechazada correctamente"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada o ya procesada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/{viajeId}/rechazarSolicitud")
    public ResponseEntity<GenericResponse<Void>> rechazarSolicitud(@RequestParam Long usuarioId,
                                                                   @PathVariable Long viajeId) {
        return ResponseEntity.ok(viajeService.rechazarSolicitud(usuarioId, viajeId));
    }

    @Operation(summary = "Listar los pasajeros de un viaje", description = "Obtener la lista de los pasajeros aceptados en un viaje.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pasajeros listados correctamente",
                    content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{viajeId}/pasajeros")
    public ResponseEntity<GenericResponse<List<UsuarioResponse>>> listarPasajeros(@PathVariable Long viajeId) {
        return ResponseEntity.ok(viajeService.listarPasajeros(viajeId));
    }

    @Operation(summary = "Iniciar un viaje", description = "Permite al conductor cambiar el estado del viaje a EN_CURSO.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje iniciado correctamente"),
            @ApiResponse(responseCode = "400", description = "El viaje no puede ser iniciado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/{viajeId}/iniciar")
    public ResponseEntity<GenericResponse<Void>> iniciarViaje(@PathVariable Long viajeId) {
        return ResponseEntity.ok(viajeService.iniciarViaje(viajeId));
    }

    @Operation(summary = "Finalizar un viaje", description = "Permite al conductor cambiar el estado del viaje a FINALIZADO.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje finalizado correctamente"),
            @ApiResponse(responseCode = "400", description = "El viaje no puede ser finalizado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/{viajeId}/finalizar")
    public ResponseEntity<GenericResponse<Void>> finalizarViaje(@PathVariable Long viajeId) {
        return ResponseEntity.ok(viajeService.finalizarViaje(viajeId));
    }


    /*//Listar todos los viajes (uso para admin)
    // diferencia si es admin o no, + estado viaje (enum) para filtrar
    @Operation(summary = "Listar todos los viajes", description = "Endpoint para listar todos los viajes con filtro por estado y rol de usuario")
    @GetMapping("/viaje/listar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de viajes obtenida",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CrearEditarViajeResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron viajes",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<?> listarViajes(
            @RequestParam Long usuarioId,  // ID del usuario para filtrar
            @RequestParam String rol,  // Rol del usuario (admin o no)
            @RequestParam EstadoViaje estado  // Estado del viaje para filtrar
    ) {
        // Llamamos al servicio para listar los viajes filtrados
        List<CrearEditarViajeResponse> viajesResponse = viajeService.listarViajes(usuarioId, rol, estado);

        // Verificamos si la lista está vacía
        if (viajesResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontraron viajes"));
        }

        // Si la lista no está vacía, retornamos la lista de viajes
        return ResponseEntity.ok(viajesResponse);
    }
    */
}
