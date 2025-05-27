
## Secciones de la Aplicación

- **Sesión** (✅ Front, Back)
- **Cambio Turnos** (✅ Front, Back)
- **Calendario** (✅ Front, - Back)
- **Viajes** (✅ ???? )

## ✅ Resumen de tareas realizadas
---
**Usuario:** `PaulaAlarcon`
- Cambios sobre estructura de BD y relaciones
  - cambioturno, jornada, trabajadorsanitario
  - Eliminación de campos obsoletos y adición de campos para conservar integridad referencial.
  - adición de datos de prueba para flujo de Cambio de Turno (Solicitudes y Peticiones)
  - adición de campo para eliminado logido de tabla cambioturno.
- Actualización de Repositorio a versión 2.0 (Migración de repositorio por cambios internos del equipo de trabajo)

---

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
- Generación de base de datos 

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

### Diferencia entre `activo` y `estadoTurno`. Indica si un turno está disponible para ser gestionado o no, independientemente de su progreso
Es decir, desactivar un turno es un softdelete.
