package com.calendario.trabajadores.repository.CambioTurno;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.calendario.trabajadores.model.database.CambioTurno;

@Repository
public class CustomCambioTurnoRepositoryImpl implements CustomCambioTurnoRepository {

    private ICambioTurnoRepository cambioTurnoRepo;
	
    public List<CambioTurno> buscarTurnosActivos(){
        return cambioTurnoRepo.findCambioTurnoByActivo(true);
    }

}
