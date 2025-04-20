package com.calendario.trabajadores.model.common;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class CamposComunes {

    private Date fechaCreacion;


    private Date fechaModificacion;

    private String creadoPor;
    private String modificadoPor;
}
