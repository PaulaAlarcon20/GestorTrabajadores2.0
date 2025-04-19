package com.calendario.trabajadores.model.dto.calendario;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class CrearEditarEventoResponse {
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private boolean diaCompleto;
}