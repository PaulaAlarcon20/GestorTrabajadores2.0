package com.calendario.trabajadores.model.dto.calendario;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class EditarEventoRequest {
    @NotNull
    private Long id;

    @NotBlank
    private String titulo;

    private String descripcion;

    @NotNull
    private LocalDateTime inicio;

    @NotNull
    private LocalDateTime fin;

    private boolean diaCompleto;
}
