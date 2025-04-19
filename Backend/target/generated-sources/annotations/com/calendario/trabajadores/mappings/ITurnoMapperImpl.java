package com.calendario.trabajadores.mappings;

import com.calendario.trabajadores.model.database.EstadoTurno;
import com.calendario.trabajadores.model.database.PeticionTurno;
import com.calendario.trabajadores.model.database.Turno;
import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.dto.turno.CrearEditarTurnoResponse;
import com.calendario.trabajadores.model.dto.turno.CrearTurnoRequest;
import com.calendario.trabajadores.model.dto.turno.EditarTurnoRequest;
import com.calendario.trabajadores.model.dto.turno.TurnoDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-19T13:05:46+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class ITurnoMapperImpl implements ITurnoMapper {

    @Override
    public CrearEditarTurnoResponse turnoToCrearEditarTurnoResponse(Turno turno) {
        if ( turno == null ) {
            return null;
        }

        CrearEditarTurnoResponse crearEditarTurnoResponse = new CrearEditarTurnoResponse();

        crearEditarTurnoResponse.setIdUsuario( turnoUsuarioId( turno ) );
        crearEditarTurnoResponse.setActivo( turno.getActivo() );
        crearEditarTurnoResponse.setEstadoTurno( turno.getEstadoTurno() );
        crearEditarTurnoResponse.setHoraFin( turno.getHoraFin() );
        crearEditarTurnoResponse.setHoraInicio( turno.getHoraInicio() );
        crearEditarTurnoResponse.setId( turno.getId() );
        crearEditarTurnoResponse.setNotasPeticion( turno.getNotasPeticion() );
        crearEditarTurnoResponse.setPeticionTurno( turno.getPeticionTurno() );

        return crearEditarTurnoResponse;
    }

    @Override
    public Turno crearTurnoRequestToTurno(CrearTurnoRequest request) {
        if ( request == null ) {
            return null;
        }

        Turno turno = new Turno();

        turno.setUsuario( crearTurnoRequestToUsuario( request ) );
        turno.setHoraFin( request.getHoraFin() );
        turno.setHoraInicio( request.getHoraInicio() );
        turno.setNotasPeticion( request.getNotasPeticion() );

        turno.setEstadoTurno( EstadoTurno.SIN_EMPEZAR );
        turno.setPeticionTurno( PeticionTurno.PENDIENTE );

        return turno;
    }

    @Override
    public Turno editarTurnoRequestToTurno(EditarTurnoRequest request) {
        if ( request == null ) {
            return null;
        }

        Turno turno = new Turno();

        turno.setActivo( request.getActivo() );
        turno.setEstadoTurno( request.getEstadoTurno() );
        turno.setHoraFin( request.getHoraFin() );
        turno.setHoraInicio( request.getHoraInicio() );
        turno.setId( request.getId() );
        turno.setNotasPeticion( request.getNotasPeticion() );
        turno.setPeticionTurno( request.getPeticionTurno() );

        return turno;
    }

    @Override
    public TurnoDTO turnoToTurnoDTO(Turno turno) {
        if ( turno == null ) {
            return null;
        }

        TurnoDTO turnoDTO = new TurnoDTO();

        turnoDTO.setNombreUsuario( turnoUsuarioNombre( turno ) );
        turnoDTO.setEmailUsuario( turnoUsuarioEmail( turno ) );
        turnoDTO.setActivo( turno.getActivo() );
        turnoDTO.setEstadoTurno( turno.getEstadoTurno() );
        turnoDTO.setHoraFin( turno.getHoraFin() );
        turnoDTO.setHoraInicio( turno.getHoraInicio() );
        turnoDTO.setId( turno.getId() );
        turnoDTO.setPeticionTurno( turno.getPeticionTurno() );

        return turnoDTO;
    }

    private Long turnoUsuarioId(Turno turno) {
        Usuario usuario = turno.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getId();
    }

    protected Usuario crearTurnoRequestToUsuario(CrearTurnoRequest crearTurnoRequest) {
        if ( crearTurnoRequest == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( crearTurnoRequest.getIdUsuario() );

        return usuario;
    }

    private String turnoUsuarioNombre(Turno turno) {
        Usuario usuario = turno.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getNombre();
    }

    private String turnoUsuarioEmail(Turno turno) {
        Usuario usuario = turno.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getEmail();
    }
}
