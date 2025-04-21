# Backend - Gestión de Turnos


## ✅ Resumen de tareas realizadas en este repositorio

**Usuario:** `fganuarve/Fabiola`
- Creación del repositorio y del esqueleto backend del proyecto (arquitectura MVC).
- Implementación de:
  - Clases de base de datos (`model.database`)
  - DTOs (`model.dto`)
  - Controladores y capa de servicios
- Manejo de errores con `GenericResponse` y `ErrorResponse`.
- Testing:
  - Pruebas unitarias con **JUnit**
  - Pruebas funcionales con **Postman**
  - Base de datos embebida para pruebas
- Operaciones CRUD para:
  - Turnos
  - Viajes en coche
  - Vehículos
  - Usuarios
- Finalización de la sección **Viajes**
- Código inicial para **login** y **logout**
- Generación de base de datos al ejecutar la app
- Documentación con **Swagger**
- Conversor de fechas/horas reutilizable
- Pantallas de gestión de viajes y vehículos (en base al resto de frontend de compañeros)
- Readme con documentación backend.

---

##  Estructura del Backend (copia de la misma explicación en carpeta Google Drive sobre Backend)

### Lombok

Uso de anotaciones como:

```java
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
```

Ayuda para evitar escribir manualmente getters/setters y constructores.

---

###  Organización del Código

#### 🔹 Entidades (model.database)

- Mapeadas con `@Entity`, `@Table`, `@Column`, etc.
- Representan las tablas de la base de datos.
- **No** se exponen directamente al frontend por seguridad y control.

#### 🔹 DTOs (model.dto)

- No contienen anotaciones JPA.
- Pensados para comunicar datos entre backend y frontend.
  - `VehiculoRequest`: datos que recibimos para crear/editar.
  - `VehiculoResponse`: datos que devolvemos al cliente.

---

###  MapStruct

Librería usada para mapear automáticamente entidades y DTOs.

```java
@Mapper(componentModel = "spring")
public interface IUserMapper {
    @Mapping(source = "password", target = "contraseña")
    Usuario toUsuario(UsuarioRequest request);
}
```

Para adaptar nombres de atributos y simplifica el código.

---

### Base de Datos

- Conexión a base MySQL local (`localhost`)
- Requiere crear manualmente la base:

```sql
CREATE DATABASE gestionturnos;
```

- Spring Boot + Hibernate se encarga de crear/modificar las tablas.
- No elimina datos existentes si ya hay información cargada.

---

##  Arquitectura MVC

| Componente    | Descripción                                                                 |
|---------------|-----------------------------------------------------------------------------|
| **Modelo**     | Entidades ubicadas en `model.database`                                     |
| **Vista**      | DTOs usados como representación de datos                                   |
| **Controlador**| Define los endpoints                                                       |
| **Servicio**   | Contiene la lógica de negocio                                              |
| **Repositorio**| Encargado de la comunicación con la base de datos                          |

---

##  Issues Pendientes: Sección Turnos ⚠️ incompleto



### ✔ Estructura Actual:

####  Modelo (`model.database`)
- `Turno`: campos como `horaInicio`, `horaFin`, `estadoTurno`, `peticionTurno`, relación con `Usuario`.
- Enums:
  - `EstadoTurno`: `SIN_EMPEZAR`, `EN_CURSO`, `FINALIZADO`
  - `PeticionTurno`: `PENDIENTE`, `ACEPTADA`, `RECHAZADA`
- `Usuario`: relacionado con `Vehiculo`, `Viaje`, `Turno`, etc.

####  DTOs (`model.dto.turno`)
- `CrearTurnoRequest`
- `EditarTurnoRequest`
- `CrearEditarTurnoResponse`
- `TurnoDTO`
> ⚠️ incompleto

#### Repositorios
- `ITurnoRepository`
- `CustomTurnoRepository` (sin funciones definidas aún)

####  Servicios
- `TurnoService`: creado, falta lógica

####  Controlador
- `TurnoController`

---

### Funcionalidades Faltantes 

#### Métodos CRUD

- `POST /turno/create`: crear turno (entrada: `CrearTurnoRequest`)
- `PUT /turno/edit/{id}`: editar turno (entrada: `EditarTurnoRequest`)
- `GET /turno/usuario/{id}`: listar turnos por usuario
  - Filtros: mes, semana, fecha, estado
- `DELETE /turno/delete/{id}`: baja lógica (no eliminación física)
- `GET /turno/{id}`: ver detalles de un turno

#### Cambios de Turno entre Usuarios

- `POST /turno/solicitar-cambio`
  - Entrada: `PeticionCambioTurnoRequest`
- `PUT /turno/responder-cambio`
  - Entrada: `RespuestaCambioTurnoRequest`
- `GET /turno/peticiones/{usuarioId}`: ver solicitudes pendientes

#### Validaciones Internas (en servicio)

- Verificar solapamiento de turnos
- Validar disponibilidad del turno y del usuario
- Verificación de permisos de quien solicita

#### Extras

- Notificaciones o avisos
- Historial de cambios
- Calendario visual
- Sistema de prioridades (urgencias, turnos programados, etc.)

---

### Diferencia entre `activo` y `estadoTurno`. Indica si un turno está disponible para ser gestionado o no, independientemente de su progreso
Es decir, desactivar un turno es un softdelete.

| Atributo       | Tipo    | Descripción                                                                 |
|----------------|---------|-----------------------------------------------------------------------------|
| `activo`       | Boolean | Si el turno está disponible para gestión (`true` = sí, `false` = no)        |
| `estadoTurno`  | Enum    | Progreso del turno: `SIN_EMPEZAR`, `EN_CURSO`, `FINALIZADO`                 |

---

