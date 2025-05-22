package com.calendario.trabajadores.services.user;


import com.calendario.trabajadores.entity.usuario.EntityUsuario;
import java.util.Random;
import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.dto.usuario.*;
import com.calendario.trabajadores.model.errorresponse.ErrorResponse;
import com.calendario.trabajadores.model.errorresponse.GenericResponse;
import com.calendario.trabajadores.repository.usuario.IUsuarioRepository;

import ch.qos.logback.core.testUtil.RandomUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

@Service
public class UserService {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	
	
	
	// CREAR usuario
	public UsuarioDTO crearUsuario(UsuarioDTO dto) {
		
		System.out.println("Ejecución de método crearUsuario");		
		Random random = new Random();
		int createIDJornada = random.nextInt(1,3);
		System.out.println("ID JORNADA -> " + createIDJornada);
		
		//TODO TENEMOS QUE LANZAR MENSAJES ERROR SI YA ESTA REGISTRADO CORREO
		
		// 1- Se convierte el DTO en una entity para poder guardarlo
		EntityUsuario entityUsuario = new EntityUsuario();
		//entityUsuario.setId(dto.getId()); // - VEREMOS QUE HACER CON EL **************
		entityUsuario.setNombre(dto.getNombre());
		entityUsuario.setApellido(dto.getApellido());
		entityUsuario.setEmail(dto.getEmail());
		entityUsuario.setPassword(dto.getPassword());
		entityUsuario.setTelefono(dto.getTelefono());
		entityUsuario.setCentroTrabajo(dto.getCentroTrabajo());
		entityUsuario.setPuesto(dto.getPuesto());
		entityUsuario.setJornadaID(createIDJornada); // ESTA LOGICA SE TIENE QUE CAMBIAR
		entityUsuario.setLocalidad(dto.getLocalidad());
		entityUsuario.setPreferenciasHorarias(dto.getPreferenciasHorarias());
		entityUsuario.setDisponibilidadHorasExtras(dto.getDisponibilidadHorasExtras());
		entityUsuario.setInicioSesion(true);
		
		// 2- Guardamos en la base de datos la entidad rellena (la tabla con los datos)
		EntityUsuario guardado = usuarioRepository.save(entityUsuario);
		System.out.println("-------------DATOS RECIBIDOS DEL FRONT-END---------");
		System.out.println("Nombre: " + dto.getNombre() +  dto.getApellido());
		System.out.println(dto.getEmail()  + " / " + dto.getPassword() + " / "  + dto.getTelefono());
		System.out.println(dto.getCentroTrabajo() + " / " + dto.getPuesto() + " / "  + " ID JORNADA: " + createIDJornada);
		System.out.println(dto.getLocalidad() + " / "  + dto.getPreferenciasHorarias() + " "  + dto.getDisponibilidadHorasExtras());
		System.out.println("---------------------------------------------------");
		
		// 3- Devolvemos un DTO
		return new UsuarioDTO(
				guardado.getId(),
				guardado.getNombre(),
				guardado.getApellido(),
				guardado.getEmail(),
				guardado.getPassword(),
				guardado.getTelefono(),
				guardado.getCentroTrabajo(),
				guardado.getPuesto(),
				guardado.getJornadaID(),
				guardado.getLocalidad(),
				guardado.getPreferenciasHorarias(),
				guardado.getDisponibilidadHorasExtras(),
				guardado.getInicioSesion());
	}
	
	// Obtener todos los usuarios
	public List<UsuarioDTO> obtenerTodosLosUsuarios(){
		List<EntityUsuario> usuarios = usuarioRepository.findAll();
		
		return usuarios.stream()
				.map(usuario -> new UsuarioDTO(
						usuario.getId(),
						usuario.getNombre(),
						usuario.getApellido(),
						usuario.getEmail(),
						usuario.getPassword(),
						usuario.getTelefono(),
						usuario.getCentroTrabajo(),
						usuario.getPuesto(),
						usuario.getJornadaID(),
						usuario.getLocalidad(),
						usuario.getPreferenciasHorarias(),
						usuario.getDisponibilidadHorasExtras(),
						usuario.getInicioSesion()
				))
				.collect(Collectors.toList());
	}
	
	//Obtener usuario por email
	public UsuarioDTO obtenerUsuarioByEmail(String email){
		
		Optional<EntityUsuario> usuario = usuarioRepository.findByEmail(email);
		
		if(usuario.isPresent()) {
			return new UsuarioDTO(
					usuario.get().getId(), 
					usuario.get().getNombre(), 
					usuario.get().getApellido(), 
					usuario.get().getEmail(), 
					usuario.get().getPassword(), 
					usuario.get().getTelefono(), 
					usuario.get().getCentroTrabajo(), 
					usuario.get().getPuesto(),
					usuario.get().getJornadaID(), 
					usuario.get().getLocalidad(), 
					usuario.get().getPreferenciasHorarias(), 
					usuario.get().getDisponibilidadHorasExtras(), 
					usuario.get().getInicioSesion());
		} else {
			throw new EntityNotFoundException("Usuario no encontrado");
		}
	}
	
	//Obtener usuario por ID
	public UsuarioDTO obtenerUsuarioByID(int id) {
		Optional<EntityUsuario> usuario = usuarioRepository.findById(id);
		
		if(usuario.isPresent()) {
			return new UsuarioDTO(
					usuario.get().getId(), 
					usuario.get().getNombre(), 
					usuario.get().getApellido(), 
					usuario.get().getEmail(), 
					usuario.get().getPassword(), 
					usuario.get().getTelefono(), 
					usuario.get().getCentroTrabajo(), 
					usuario.get().getPuesto(),
					usuario.get().getJornadaID(), 
					usuario.get().getLocalidad(), 
					usuario.get().getPreferenciasHorarias(), 
					usuario.get().getDisponibilidadHorasExtras(), 
					usuario.get().getInicioSesion());
		} else {
			throw new EntityNotFoundException("Usuario no encontrado");
		}
	}
	

	// BUSCAR por ID
	public Optional<EntityUsuario> buscarPorId(Integer id){
		return usuarioRepository.findById(id);
	}
	
	// Buscar por GMAIL y CONTRASENA
	public Optional<LoginResponse> login(String email, String contrasena){
		Optional<EntityUsuario> user = usuarioRepository.findByEmailAndContrasena(email, contrasena);
		
		
		if(user.isPresent()) {
			
			EntityUsuario usuario = user.get();
			
			
			System.out.println("IMPRIMO TODOS LOS DATOS:");
			System.out.println(usuario.getNombre()+ " * " + usuario.getInicioSesion());
			
			usuario.setInicioSesion(true);
			System.out.println(usuario.getNombre()+ " * " + usuario.getInicioSesion());
			
			usuarioRepository.save(usuario);
			
			LoginResponse response = new LoginResponse(usuario.getEmail(), usuario.getInicioSesion(), "Usuario existe en la base de datos.");
			System.out.println("Devuelve -> " + response.getEmail() + " / Estado inicio sesión: " + response.getInicioSesion());
			
			return Optional.of(response);
		} else {
			
			return Optional.empty();
		}
		
	}
	
	
	// VERIFICAR conexión de usuario 
	public boolean existeInicioSesion(Integer id) {
		return usuarioRepository.existsById(id);
	}
	
	// CONTAR Número total usuarios
	public long contarUsuarios() {
		System.out.print("Número de usuarios totales de la tabla trabajadores sanitarios: " + usuarioRepository.count());
		return usuarioRepository.count();
	}
	
	
}
/*
    // Inyección de dependencias
    private final IUsuarioRepository userRepository;
    private final IUserMapper userMapper;
    private final IViajeMapper viajeMapper; // Inyectamos el IViajeMapper

    // Constructor de UserService
    public UserService(IUsuarioRepository userRepository, IUserMapper userMapper, IViajeMapper viajeMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.viajeMapper = viajeMapper; // Asignamos el IViajeMapper
    }

    //Metodo para hacer login
    public GenericResponse<UsuarioResponse> login(String username, String password) {
        var wrapperResponse = new GenericResponse<UsuarioResponse>();

        // Buscar el usuario por el email (username)
        Optional<Usuario> usuario = userRepository.findUsuarioByEmail(username);
        if (usuario.isEmpty()) {
            // Si no existe el usuario, devolvemos un error
            wrapperResponse.setError(new ErrorResponse("Usuario no encontrado"));
            return wrapperResponse;
        }

        // Verificar si la contraseña es correcta
        if (!usuario.get().getContraseña().equals(password)) {
            wrapperResponse.setError(new ErrorResponse("Contraseña incorrecta"));
            return wrapperResponse;
        }

        // Si el login es correcto, mapeamos los datos
        UsuarioResponse tempDTO = userMapper.usuarioToUsuarioResponse(usuario.get());

        // Devolvemos el DTO envuelto en GenericResponse
        wrapperResponse.setData(tempDTO);
        return wrapperResponse;
    }

    // Método para hacer logout
    public GenericResponse<UsuarioResponse> logout(String username, String password) {
        var wrapperResponse = new GenericResponse<UsuarioResponse>();

        // Buscar el usuario por el email (username)
        Optional<Usuario> usuario = userRepository.findUsuarioByEmail(username);
        if (usuario.isEmpty()) {
            // Si no existe el usuario, devolvemos un error
            wrapperResponse.setError(new ErrorResponse("Usuario no encontrado"));
            return wrapperResponse;
        }

        // Si el usuario existe, mapeamos los datos a UsuarioResponse (sin vehículos)
        UsuarioResponse tempDTO = userMapper.usuarioToUsuarioResponse(usuario.get());

        // Devolvemos el DTO con el mensaje de éxito en GenericResponse
        wrapperResponse.setData(tempDTO);
        return wrapperResponse;
    }

    //Metodo para crear un usuario
    public GenericResponse<UsuarioResponse> crearUsuario(CrearUsuarioRequest request) {

        //Buscar por email si el usuario ya existe
        var usuarioExists = userRepository.findUsuarioByEmail(request.getEmail());
        var wrapperResponse = new GenericResponse<UsuarioResponse>();
        if (usuarioExists.isPresent()) {
            wrapperResponse.setError(new ErrorResponse("El usuario con el email proporcionado ya existe"));
            return wrapperResponse;
        }
        // Validamos que el nombre no sea nulo
        if (request.getNombre() == null || request.getNombre().isEmpty()) {
            wrapperResponse.setError(new ErrorResponse("El campo 'nombre' es obligatorio"));
            return wrapperResponse;  // Retorna con un error si el nombre es nulo o vacío
        }
        //Si no existe, creamos un nuevo usuario
        var usuario = userMapper.createRequestToUser(request);
        //Activamos el usuario por defecto
        usuario.setActivo(true);
       try { //Guardamos el usuario en la base de datos
           var usuarioSaved = userRepository.save(usuario);
           // Mapeamos el usuario guardado a un DTO
           var response = userMapper.userToCreateEditResponse(usuarioSaved);

           // Envolvemos la respuesta en GenericResponse y la devolvemos
           wrapperResponse.setData(response);
           return wrapperResponse;
       } catch (DataIntegrityViolationException Ex){
           wrapperResponse.setError(new ErrorResponse(Ex.getMessage()));
           return wrapperResponse;
       }
    }


    //Metodo para editar un usuario
    public GenericResponse<UsuarioResponse> editUsuario(EditarUsuarioRequest request) {
        var usuario = userRepository.findById(request.getId());
        if (usuario.isEmpty()) {
            var error = new GenericResponse<UsuarioResponse>();
            error.setError(new ErrorResponse("Usuario no encontrado"));
            return error;
        }

        var Usuario = userMapper.editRequestToUser(request);
        var usuarioSaved = userRepository.save(Usuario);
        var response = userMapper.userToCreateEditResponse(usuarioSaved);
        var wrapperResponse = new GenericResponse<UsuarioResponse>();
        wrapperResponse.setData(response);
        return wrapperResponse;
    }

    //Metodo para desactivar un usuario
    public GenericResponse<UsuarioResponse> softDelete(Long id) {
        //Buscamos al usuario por su id
        var usuario = userRepository.findById(id);
        //Si no existe devolvemos un Optional vacio
        if (usuario.isEmpty()) {
            var error = new GenericResponse<UsuarioResponse>();
            error.setError(new ErrorResponse("Usuario no encontrado"));
            return error;
        }
        //Obtenemos el usuario
        Usuario temp = usuario.get();
        // Desactivamos al usuario
        temp.setActivo(false);
        //Guardamos el usuario en la base de datos
        Usuario usuarioSaved = userRepository.save(temp);
        //Mapeamos el usuario a un DTO
        var response = userMapper.userToCreateEditResponse(usuarioSaved);
        // Envolvemos el DTO en un GenericResponse y lo devolvemos
        var wrapperResponse = new GenericResponse<UsuarioResponse>();
        wrapperResponse.setData(response);
        return wrapperResponse;
    }

    //Metodo para reactivar a un usuario
    public GenericResponse<UsuarioResponse> reactivar(Long id) {
        //Buscamos al usuario por su id
        var usuario = userRepository.findById(id);
        //Si no existe devolvemos un Optional vacio
        if (usuario.isEmpty()) {
            var error = new GenericResponse<UsuarioResponse>();
            error.setError(new ErrorResponse("Usuario no encontrado"));
            return error;
        }
        //Obtenemos el usuario
        Usuario temp = usuario.get();
        //Activamos al usuario
        temp.setActivo(true);
        //Guardamos el usuario en la base de datos
        Usuario usuarioSaved = userRepository.save(temp);
        //Mapeamos el usuario a un DTO
        var response = userMapper.userToCreateEditResponse(usuarioSaved);
        // Envolvemos el DTO en un GenericResponse y lo devolvemos
        var wrapperResponse = new GenericResponse<UsuarioResponse>();
        wrapperResponse.setData(response);
        return wrapperResponse;
    }

    //Metodo para obtener un usuario por su email
    public GenericResponse<UsuarioResponse> getUsuario(String email) {
        var usuario = userRepository.findUsuarioByEmail(email);
        if (usuario.isEmpty()) {
            var error = new GenericResponse<UsuarioResponse>();
            error.setError(new ErrorResponse("Usuario no encontrado"));
            return error;
        }
        // Mapear usuario a DTO
        var response = userMapper.userToCreateEditResponse(usuario.get());

        // Envolver en GenericResponse y devolver
        var wrapperResponse = new GenericResponse<UsuarioResponse>();
        wrapperResponse.setData(response);
        return wrapperResponse;
    }

    //Metodo para obtener un usuario por su id
    public GenericResponse<UsuarioResponse> getUsuario(Long id) {
        var usuario = userRepository.findById(id);
        if (usuario.isEmpty()) {
            var error = new GenericResponse<UsuarioResponse>();
            error.setError(new ErrorResponse("Usuario no encontrado"));
            return error;
        }
        // Mapear usuario a DTO
        var response = userMapper.userToCreateEditResponse(usuario.get());

        // Envolver en GenericResponse y devolver
        var wrapperResponse = new GenericResponse<UsuarioResponse>();
        wrapperResponse.setData(response);
        return wrapperResponse;
    }

    public GenericResponse<List<UsuarioResponse>> listar(Optional<Boolean> activo) {
        List<Usuario> lista;
        List<UsuarioResponse> listaResponse;

        // Si no se pasa el parámetro activo, devolvemos todos los usuarios
        if (activo.isEmpty()) {
            lista = userRepository.findAll();
        } else {
            // Si se pasa el parámetro activo, devolvemos los usuarios activos o inactivos
            lista = userRepository.findByActivo(activo.get());
        }

        var wrapperResponse = new GenericResponse<List<UsuarioResponse>>();

        if (lista.isEmpty()) {
            wrapperResponse.setError(new ErrorResponse("No se encontraron usuarios"));
        } else {
            // Mapeamos los usuarios a UsuarioResponse
            listaResponse = lista.stream()
                    .map(usuario -> {
                        // Mapeo del usuario
                        UsuarioResponse usuarioResponse = userMapper.userToCreateEditResponse(usuario);

                        // Mapeo de los viajes del usuario a ViajeDTO
                        List<ViajeDTO> viajesDTO = usuario.getViajes().stream()
                                .map(viaje -> viajeMapper.viajeToViajeDTO(viaje)) // Aquí usamos el mapeo correcto
                                .collect(Collectors.toList());

                        // Asignamos los viajes al usuario
                        usuarioResponse.setViajes(viajesDTO);

                        return usuarioResponse;
                    })
                    .collect(Collectors.toList());

            wrapperResponse.setData(listaResponse);
        }

        return wrapperResponse;
    }



//Antiguo metodo listar *J*
//En lugar de tener varios metodos para encontrar según los parametros que tenga, creamos un método que
//sea capaz de devolvernos una lista de usuarios según los parametros que le pasemos
    /*public GenericResponse<List<UsuarioResponse>> listar(Optional<Boolean> activo) {
        List<Usuario> lista;
        List<UsuarioResponse> listaResponse;
        if (activo.isEmpty()) {
            //si no se pasa el parametro activo, devolvemos todos los usuarios
            lista = userRepository.findAll();
        } else {
            //si se pasa el parametro activo y es true o false, devolvemos los usuarios activos o inactivos respectivamente
            lista = userRepository.findByActivo(activo.get());
            //var lista = userRepository.findByActivo(true);
            //var lista = userRepository.findByActivo(false);
        }
        var wrapperResponse = new GenericResponse<List<UsuarioResponse>>();
        if (lista.isEmpty()) {
            wrapperResponse.setError(new ErrorResponse("No se encontraron usuarios"));
        } else {
            listaResponse = lista.stream().map(usuario -> userMapper.userToCreateEditResponse(usuario)).toList();
            wrapperResponse.setData(listaResponse);

        }
        return wrapperResponse;
    }


    //Metodo para listar usuarios con vehiculos
    public GenericResponse<List<UsuarioVehiculosResponse>> listarUsuariosVehiculos(Optional<Boolean> activo) {
        List<UsuarioVehiculosResponse> lista;
        //primero compruebo si es null
        if (activo.isEmpty()) {
            //Evitar una dependencia circular (findAll)
            lista = userRepository.findAllUsuariosVehiculos();

        } else {
            //sabemos que no esta vacío
            lista = userRepository.findAllUsuariosVehiculosFiltrados(activo.get());
        }
        var wrapperResponse = new GenericResponse<List<UsuarioVehiculosResponse>>();
        if (lista.isEmpty()) {
            wrapperResponse.setError(new ErrorResponse("No se encontraron usuarios"));
        } else {
            wrapperResponse.setData(lista);
        }
        return wrapperResponse;
    }

    //listar los viajes de un usuario: *F*
    public GenericResponse<List<UsuarioViajeResponse>> listarUsuariosViajes(Boolean activo) {
        List<UsuarioViajeResponse> lista = new ArrayList<>();

        // Recuperamos los usuarios con viajes (según si están activos o no)
        List<Usuario> usuarios;
        if (activo == null) {
            // Si no se pasa el parámetro activo, obtenemos todos los usuarios
            usuarios = userRepository.findAll();
        } else {
            // Si se pasa el parámetro activo, filtramos según el estado
            usuarios = userRepository.findByActivo(activo);
        }

        // Recorremos cada usuario y mapeamos los datos a DTO
        for (Usuario usuario : usuarios) {
            // Usamos el mapeador para convertir el usuario a UsuarioViajeResponse
            UsuarioViajeResponse usuarioViajeResponse = userMapper.userToUsuarioViajeResponse(usuario);

            // Mapeamos los viajes del usuario a ViajeDTO
            List<ViajeDTO> viajesDTO = usuario.getViajes().stream()
                    .map(viaje -> viajeMapper.viajeToViajeDTO(viaje))  // Mapeamos cada viaje a ViajeDTO
                    .collect(Collectors.toList());

            // Asignamos la lista de viajes al UsuarioViajeResponse
            usuarioViajeResponse.setViajes(viajesDTO);

            // Añadimos el usuarioViajeResponse a la lista final
            lista.add(usuarioViajeResponse);
        }

        // Creamos la respuesta genérica con los datos mapeados
        var wrapperResponse = new GenericResponse<List<UsuarioViajeResponse>>();
        if (lista.isEmpty()) {
            // Si no encontramos usuarios con viajes, devolvemos un error
            wrapperResponse.setError(new ErrorResponse("No se encontraron usuarios con viajes"));
        } else {
            // Si encontramos, devolvemos los datos
            wrapperResponse.setData(lista);
        }

        return wrapperResponse;
    }

    //Metodo para borrar un usuario (IMPORTANTE: los usuarios no deben usar este. Riesgo de borrado de la base de datos)
    public GenericResponse<UsuarioResponse> borrar(Long id, String email) {
        var usuario = userRepository.findById(id);
        if (usuario.isEmpty()) {
            var error = new GenericResponse<UsuarioResponse>();
            error.setError(new ErrorResponse("Usuario no encontrado"));
            return error;
        }
        if (!usuario.get().getEmail().equals(email)) {
            var error = new GenericResponse<UsuarioResponse>();
            error.setError(new ErrorResponse("El email no coincide con el del usuario"));
            return error;
        }
        //Borrado total del usuario de la bbdd
        userRepository.delete(usuario.get());
        // Mapeamos la respuesta y la envolvemos en GenericResponse
        var response = userMapper.userToCreateEditResponse(usuario.get());
        var wrapperResponse = new GenericResponse<UsuarioResponse>();
        wrapperResponse.setData(response);

        return wrapperResponse;
    }
    
    /**
     * 
     * 
     * 
     * */





