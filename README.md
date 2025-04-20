# GestorTrabajadores
Plataforma de gestión de trabajadores de la salud, incluye una aplicación móvil para que los empleados puedan acceder a un calendario con sus horarios y turnos de trabajo.
Los trabajadores pueden compartir vehículos para desplazarse al trabajo, al estilo de plataformas de carpooling

 
Además, en el proyecto estamos usando Lombook, por lo que explico un poco esta sección:
al principio, lo más sencillo para agilizar el desarrollo era hacer que los atributos de las clases fueran públicos (public). Para acceder directamente a ellos sin tener que crear getters y setters.
Con Lombok, es posible generar automáticamente los métodos getter y setter sin tener que escribirlos manualmente.
Por eso el uso de @Getter, @Setter, @AllArgsConstructor, @NoArgsConstructor.
 
Explicación de la estructura del backend:
Tenemos las clases como Turnos, Usuarios, Coches, etc., separadas en dos paquetes: database y common.
 La diferencia principal es que las clases del paquete database son las que están directamente mapeadas a la base de datos. 
 Son entidades reales, con anotaciones como @Entity, @Table, @Column, etc.
Luego, por otro lado, están las clases con las que realmente trabajamos en los controladores y servicios: los DTO.
 No solo porque es más seguro no manipular directamente las entidades de base de datos, 
 sino porque con los DTO podemos controlar qué datos se mandan y reciben. Por ejemplo, puede que sepamos el ID de un usuario, pero no nos interesa mostrárselo a un cliente final en la app. 
 Tampoco nos conviene enseñar campos sensibles o que no aportan nada al caso de uso concreto.
Los DTO (Data Transfer Objects) sirven para transferir información entre el backend y otros componentes (como el frontend), sin exponer directamente nuestras entidades internas. Por eso son los que usamos en la lógica real: en los métodos del servicio y en los controladores.
 
Entidad de base de datos (Turno, Usuario, etc.)
Se encuentra en el paquete model.database.
Representa exactamente cómo es una tabla en la base de datos.
Está mapeada con anotaciones como @Entity, @Table, @Column, etc.
No debería salir directamente del backend hacia el frontend por seguridad, peso y control.
 
 
DTOs (TurnoDTO, CrearTurnoRequest, EditarTurnoRequest, etc.)
Se encuentran en model.dto.
No tienen anotaciones de JPA (@Entity, @Column, etc.), ya que no interactúan con la base de datos directamente.
Se diseñan según la necesidad concreta: crear, editar, mostrar, resumir...
Son objetos ligeros, seguros, y pensados para comunicarse con el exterior del backend.
 
¿Por qué no mandamos directamente la entidad Turno?
 No queremos exponer cosas como contraseña, modificadoPor, relaciones internas, etc.
El backend elige exactamente qué datos mostrar, y cómo. Si mañana cambiamos la estructura de la base de datos, no rompe el frontend.
 Las entidades están cargadas de relaciones y anotaciones que no son necesarias al mostrarlas.
 
Todo es MVC (Modelo Vista Controlador) por si tenéis que repasarlo
Tenemos:
Modelo: Las entidades de la base de datos.
Vista: En este caso no hay una vista como tal (no es una app web tradicional), pero los DTOs funcionan como la "presentación" de los datos.
Controlador: Es donde definimos todos los endpoints para que el frontend se comunique con el backend.
Servicio: Aquí vive la lógica de negocio.
Repositorio: Se comunica con la base de datos.
 
Por qué utilicé nombres como VehiculoRequest o VehiculoResponse:
En otras aplicaciones con las que estaba trabajando (por ejemplo en C# con .NET), este tipo de estructura no es obligatoria, 
y se puede trabajar con menos separación. Pero en Java (y especialmente usando Spring Boot que estamos utilizando), esta convención se considera buena práctica profesional.
Es cierto que a veces parece un poco repetitivo y un engorro de leer, pero da claridad, seguridad.
Los objetos con sufijo Request representan los datos que nos manda el cliente para hacer una operación: crear, editar, etc.
Ejemplo: VehiculoRequest podría tener campos como matricula, modelo, color, etc.
Los objetos con sufijo Response representan la información que nosotros devolvemos al cliente.
Ejemplo: VehiculoResponse podría devolver modelo, color, y el nombre del usuario que lo tiene asignado, pero no necesariamente el ID interno del coche.
Así evitamos mandar información innecesaria, y nos aseguramos de que cada parte del sistema ve solo lo que necesita ver.

También se esta utilizando MapStruct y el mapeo automático con @Mapper. En nuestro backend utilizamos MapStruct,
una librería de Java que nos permite mapear automáticamente los datos entre clases, especialmente entre las entidades de base de datos y los DTOs (Data Transfer Objects).
@Mapper(componentModel = "spring") le dice a MapStruct que genere una implementación de esa interfaz como un bean de Spring, 
de forma que podamos inyectarla con @Autowired en nuestros servicios. 
Por ejemplo: IUserMapper se encarga de raducir un Usuario a un UsuarioResponse o UsuarioVehiculosResponse.
Y viceversa. Adaptar diferencias de nombres, como password (del request) a contraseña (en la entidad), mediante @Mapping(source = "password", target = "contraseña")
 
En cuanto a la base de datos, cómo funciona:
Por ahora está configurado para conectarse a una base de datos MySQL que se encuentra en tu la máquina local (localhost), usando el puerto y nombre de base gestionturnos.
La base de datos no se crea automáticamente desde cero si no existe. MySQL requiere que la base de datos gestionturnos exista previamente, 
aunque luego Hibernate (el motor ORM que usa Spring Boot) se encargue de crear las tablas y estructuras dentro de ella.
Hay que crear manualmente la base de datos vacía con el nombre gestionturnos desde MySQL o phpMyAdmin:
CREATE DATABASE gestionturnos;
Spring Boot creará o actualizará automáticamente las tablas en la base de datos, basándose en las entidades de nuestro código que tengan @Entity.
Si una tabla no existe, se creará.
Si una entidad cambia (por ejemplo, le añades un campo nuevo), Hibernate intentará modificar la tabla correspondiente.
No borra datos existentes
 
 ----------------------------------------------------------------------

 Issue de backend pendiente turnos:
 Lo que tenemos hasta ahora (estructura):
Modelo (base de datos):
Ubicado en model.database:
Entidad Turno: Mapeada a la tabla turnos, con campos como horaInicio, horaFin, estadoTurno, peticionTurno, etc. Incluye relación con Usuario.
Enums:
EstadoTurno: SIN_EMPEZAR, EN_CURSO, FINALIZADO
PeticionTurno: PENDIENTE, ACEPTADA, RECHAZADA
Entidad Usuario: Relacionada con múltiples entidades (Vehiculo, Viaje, UsuarioViaje), incluyendo los turnos del trabajador.
 DTOs:
Tenemos varias clases DTO en model.dto.turno, como:
CrearTurnoRequest
EditarTurnoRequest
CrearEditarTurnoResponse
TurnoDTO
Faltan implementar detalles en estos DTOs.
Repositorios:
Ya existe una interfaz ITurnoRepository y una clase CustomTurnoRepository con su implementación correspondiente. Todavía no están definidas sus funciones concretas.
Servicios:
El servicio TurnoService está creado pero aún vacío. No contiene lógica de negocio implementada.
 Controladores:
TurnoController


Lo que debemos tener:
Métodos CRUD básicos para Turno:
Crear turno
POST /turno/create
Entrada: CrearTurnoRequest
Crea un turno para un usuario específico.
Editar turno
PUT /turno/edit/{id}
Entrada: EditarTurnoRequest
Permite modificar fecha, hora, notas, etc.
Listar turnos de un usuario
GET /turno/usuario/{id}
Opcional: filtrar por mes, semana, fecha, estado.
Eliminar turno (soft delete o desactivación)
DELETE /turno/delete/{id}
Marca el turno como inactivo (activo = false) en vez de borrarlo físicamente.
Ver detalles de un turno
GET /turno/{id}
Devuelve la información completa del turno.

Métodos para gestión de cambios de turno entre usuarios: lógica, flujos de validación, y aprobaciones.
 Solicitar cambio de turno
POST /turno/solicitar-cambio
Entrada: un DTO tipo PeticionCambioTurnoRequest que incluya:
ID del turno original
ID del usuario que solicita el cambio
ID del usuario al que se propone el intercambio
Nota opcional
Cambia el estado a PeticionTurno.PENDIENTE
 Aceptar o rechazar una solcitud
PUT /turno/responder-cambio
Entrada: DTO tipo RespuestaCambioTurnoRequest:
ID de la petición (o del turno)
Aceptado o rechazado
Actualiza:
peticionTurno → ACEPTADA o RECHAZADA
Si es ACEPTADA, se intercambian turnos o se reasigna el turno.
Ver solicitudes de cambio pendientes
GET /turno/peticiones/{usuarioId}
Devuelve los turnos donde peticionTurno == PENDIENTE y el usuario está involucrado.

Métodos de validación interna (en el service) no son endpoints, pero son funciones que necesitas para que la lógica funcione correctamente:
Verificar si un usuario tiene un turno ya asignado en ese horario (evitar solapamientos).
Comprobar que un usuario puede ceder un turno (por ejemplo, si está activo).
Validar que el destinatario del cambio está disponible.
Verificar permisos (por ejemplo, si un usuario solo puede cambiar sus propios turnos).

Funcionalidades extra:
Notificaciones o avisos al usuario cuando se solicita o aprueba un cambio.
Historial de turnos y cambios 
Calendario visual 
Sistema de prioridades en peticiones (urgencias, peticiones programadas, etc.).


El atributo activo y el estado del turno representado por el enum EstadoTurno no es lo mismo.

Diferencia entre activo y EstadoTurno:
activo (Boolean):


Indica si un turno está disponible para ser gestionado o no, independientemente de su progreso.


Valores posibles:


true: El turno está activo, es decir, está disponible para ser gestionado, asignado, visualizado, etc.


false: El turno está inactivo, es decir, no está disponible para ser gestionado. Esto puede ser útil cuando el turno se cancela o ya no es necesario. (es decir, para que no se borre de la base de datos y se mantenga aunque no se esté usando)



EstadoTurno (Enum):


Representa el progreso o fase de un turno específico.


SIN_EMPEZAR:


EN_CURSO:


FINALIZADO: El turno ha sido completado, pero podría ser marcado como inactivo si ya no es relevante.
