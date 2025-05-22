package com.calendario.trabajadores.services.turno;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendario.trabajadores.entity.usuario.EntityUsuario;
import com.calendario.trabajadores.model.database.CambioTurno;
import com.calendario.trabajadores.model.database.PeticionTurno;
import com.calendario.trabajadores.model.database.Turno;
import com.calendario.trabajadores.model.dto.cambioTurno.CambioTurnoResponse;
import com.calendario.trabajadores.model.dto.cambioTurno.CrearCambioTurnoRequest;
import com.calendario.trabajadores.model.errorresponse.ErrorResponse;
import com.calendario.trabajadores.model.errorresponse.GenericResponse;
import com.calendario.trabajadores.repository.CambioTurno.ICambioTurnoRepository;
import com.calendario.trabajadores.repository.turno.ITurnoRepository;
import com.calendario.trabajadores.repository.usuario.IUsuarioRepository;

@Service
public class CambioTurnoService {

    @Autowired
    private ICambioTurnoRepository cambioTurnoRepository;
    
    @Autowired
    private IUsuarioRepository userRepository;

    @Autowired
    private ITurnoRepository jornadaRepository;

    // Obtener todos los cambios de turno
	public List<CambioTurno> obtenerCambioTurnos(){
        System.out.println("Entra a service lista de cambioTurnos");
		return cambioTurnoRepository.findAll();
	}

    public List<CambioTurno> obtenerSolicitudes(int userId) {
        Optional<EntityUsuario> user = userRepository.findById(userId);

        return cambioTurnoRepository.findCambioTurnoByTrabajadorSolicitante(user);
    }

    public List<CambioTurno> obtenerPeticiones(int userId) {
        Optional<EntityUsuario> user = userRepository.findById(userId);

        return cambioTurnoRepository.findCambioTurnoByTrabajadorSolicitanteNotAndEstadoCambio(user, PeticionTurno.PENDIENTE);
    }

    // Creación de ueva solicitud
    public GenericResponse<CambioTurnoResponse> guardarSolicitud(CrearCambioTurnoRequest request) {
        GenericResponse<CambioTurnoResponse> response = new GenericResponse<>();
        CambioTurno cmTurno = new CambioTurno();
        ErrorResponse err;
        try {
            EntityUsuario user = userRepository.findEntityUsuarioById(request.getUsuarioId());
            Optional<Turno> jornada = jornadaRepository.findById(request.getJornadaId());
            
            if (user != null && jornada != null && request.getFechaSolicitada()!=null) {
                cmTurno.setTrabajadorSolicitante(user);
                cmTurno.setJornadaID(jornada.get());
                cmTurno.setFechaSolicitada(request.getFechaSolicitada());
                
                System.out.println("ID del trabajador solicitante: " + cmTurno.getTrabajadorSolicitante().getId());
                
                cambioTurnoRepository.save(cmTurno);
                
                CambioTurnoResponse cambioTurnoResponse = new CambioTurnoResponse();
                cambioTurnoResponse.setId(cmTurno.getId());
                cambioTurnoResponse.setIdUsuario(cmTurno.getTrabajadorSolicitante().getId());
                cambioTurnoResponse.setPeticionTurno(cmTurno.getEstadoCambio());
        
                response.setData(cambioTurnoResponse);
            }else{
                err = new ErrorResponse("Validar datos.");
                response.setError(err);
            }
        } catch (Exception e) {
            err = new ErrorResponse(e);
            response.setError(err);
        }

        return response;
    }

    // Eliminar Cambio de turno
    public GenericResponse<CambioTurnoResponse> eliminarCambioTurno(int idCambioTurno) {
        GenericResponse<CambioTurnoResponse> responseWrapper = new GenericResponse<>();

        // Buscar el turno por ID
        Optional<CambioTurno> cambioTurnoOptional = cambioTurnoRepository.findById(idCambioTurno);

        if (cambioTurnoOptional.isEmpty()) {
            responseWrapper.setError(new ErrorResponse("El cambio de turno no existe"));
            return responseWrapper;
        }

        CambioTurno cturno = cambioTurnoOptional.get();
        cturno.setActivo(false); // se cambia estado a false para indicar que esta eliminado

        // Guardamos los cambios en la base de datos
        CambioTurno turnoActualizado = cambioTurnoRepository.save(cturno);
        
        // Creamos respuesta
        CambioTurnoResponse cambioTurnoResponse = new CambioTurnoResponse();
        cambioTurnoResponse.setId(turnoActualizado.getId());
        cambioTurnoResponse.setIdUsuario(turnoActualizado.getTrabajadorSolicitante().getId());
        cambioTurnoResponse.setPeticionTurno(turnoActualizado.getEstadoCambio());
        
        // Mapeamos la respuesta y la devolvemos
        responseWrapper.setData(cambioTurnoResponse);
        return responseWrapper;
    }

    // Modificar Cambio de turno (Rechazar)
    public GenericResponse<CambioTurnoResponse> modificarCambioTurno(int idCambioTurno, PeticionTurno estado) {
        GenericResponse<CambioTurnoResponse> responseWrapper = new GenericResponse<>();

        // Buscar el turno por ID
        Optional<CambioTurno> cambioTurnoOptional = cambioTurnoRepository.findById(idCambioTurno);

        if (cambioTurnoOptional.isEmpty()) {
            responseWrapper.setError(new ErrorResponse("El cambio de turno no existe"));
            return responseWrapper;
        }

        CambioTurno cturno = cambioTurnoOptional.get();
        cturno.setEstadoCambio(estado);

        // Guardamos los cambios en la base de datos
        CambioTurno turnoActualizado = cambioTurnoRepository.save(cturno);

        // Creamos respuesta
        CambioTurnoResponse cambioTurnoResponse = new CambioTurnoResponse();
        cambioTurnoResponse.setId(turnoActualizado.getId());
        cambioTurnoResponse.setIdUsuario(turnoActualizado.getTrabajadorSolicitante().getId());
        cambioTurnoResponse.setPeticionTurno(turnoActualizado.getEstadoCambio());
        
        // Mapeamos la respuesta y la devolvemos
        responseWrapper.setData(cambioTurnoResponse);
        return responseWrapper;
    }

    // modificar Cambio de turno para aceptar cambio turno
    public GenericResponse<CambioTurnoResponse> aceptarCambioTurno(int idCambioTurno, int usuarioAceptanteId) {
        GenericResponse<CambioTurnoResponse> responseWrapper = new GenericResponse<>();

        // Buscar el turno por ID
        Optional<CambioTurno> cambioTurnoOptional = cambioTurnoRepository.findById(idCambioTurno);

        if (cambioTurnoOptional.isEmpty()) {
            responseWrapper.setError(new ErrorResponse("El cambio de turno no existe"));
            return responseWrapper;
        }

        // Buscar el turno por ID
        Optional<EntityUsuario> usuarioAceptanteOpt = userRepository.findById(usuarioAceptanteId);

        if (usuarioAceptanteOpt.isEmpty()) {
            responseWrapper.setError(new ErrorResponse("El usuario no existe"));
            return responseWrapper;
        }

        CambioTurno cturno = cambioTurnoOptional.get();
        cturno.setEstadoCambio(PeticionTurno.ACEPTADA);
        cturno.setTrabajadorAceptante(usuarioAceptanteOpt.get());;

        // Guardamos los cambios en la base de datos
        CambioTurno turnoActualizado = cambioTurnoRepository.save(cturno);

        // Creamos respuesta
        CambioTurnoResponse cambioTurnoResponse = new CambioTurnoResponse();
        cambioTurnoResponse.setId(turnoActualizado.getId());
        cambioTurnoResponse.setIdUsuario(turnoActualizado.getTrabajadorSolicitante().getId());
        cambioTurnoResponse.setPeticionTurno(turnoActualizado.getEstadoCambio());
        
        // Mapeamos la respuesta y la devolvemos
        responseWrapper.setData(cambioTurnoResponse);
        return responseWrapper;
    }

}
