package com.calendario.trabajadores.model.database;

public enum Puesto {
    MEDICO,
    ENFERMERO,
    TCAE
}

/* private final String descripcion;

Puesto(String descripcion) {
    this.descripcion = descripcion;
}

@JsonValue
public String getDescripcion() {
    return descripcion;
}

@JsonCreator
public static Puesto fromString(String value) {
    for (Puesto puesto : Puesto.values()) {
        if (puesto.descripcion.equalsIgnoreCase(value)) {
            return puesto;
        }
    }
    throw new IllegalArgumentException("Valor no válido para Puesto: " + value);
}
* 
* */