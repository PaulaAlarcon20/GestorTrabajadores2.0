import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';
import 'package:front_end_gui/views/infraestructure/inputs/inputs.dart';
import 'package:front_end_gui/views/infraestructure/inputs/passwordLogin.dart';
import 'package:http/http.dart' as http;
import 'dart:convert'; // Para convertir el JSON
import 'dart:developer';
part 'RegisterState.dart';


/// Clase RegisterLoginCubit, en donde vamos a desarrollar los métodos de la validación
class RegisterCubit extends Cubit<RegisterState> {
  RegisterCubit() : super(RegisterState());

 

  // Métodos en el cubic de las variables del formulario
  Future<void> onSubmit() async {
    log('Estado actual formulario al enviarlo, antes de emitir emit -> ${state.formStatus}');
    log('Enviando formulario... datos -> email: ${state.email.value}, contraseña: ${state.password.value}');
    emit( // Notifica a flutter blog que el estado ha cambiado
      state.copyWith(  // hace una copia del estado actual, pero con algunos valores modificados
        formStatus : FormStatus.validating, // Se indica que el estado de las validaciones es 'validando'
        email : GmailInput.dirty(value : state.email.value),
        password : PasswordLoginInput.dirty( value: state.password.value),
        //icono: state.isValid ? Icons.check : Icons.error,
        
        isValid: Formz.validate([
          state.email,
          state.password, 
        ])
      ),
      
    ); 


    try {
      final url = Uri.parse('http://10.0.2.2:8080/api/usuarios/login');

      final response = await http.post(
        url,
        headers: {
          'Content-Type': 'application/json',
        },
        body: jsonEncode({
          'email': state.email.value,
          'contrasena': state.password.value,
          'mensaje': ""
        })
      );

      

      if (response.statusCode == 200){
        log('Se inicia sesión...');

        final data = jsonDecode(response.body);
        final email = data['email'];
        final inicioSesion = data['inicioSesion'];
        final mensaje = data['mensaje']; //todo OTRA LOGICA PUEDE SER PONER SI MENAJE ES TAL, SE MUESTRA ---------------------------------------

        log('Login correcto -> $email, inicio de sesión: $inicioSesion  --- mensaje: $mensaje / statusHttp -> ${response.statusCode}' ); 

        if(inicioSesion == true){
          emit(state.copyWith(
            formStatus: FormStatus.valid,
            inicioSesion: inicioSesion,
            messageStatus: "Solicitud exitosa."
          ));
        }
        
      } else if(response.statusCode == 400){
        
        emit(state.copyWith(
          formStatus: FormStatus.invalid,
          messageStatus: "Petición mal formulada"
        ));
        print("Entra en status 400 -> ${response.statusCode}");
        
      } else if(response.statusCode == 401){
        emit(state.copyWith(
            formStatus: FormStatus.invalid,
            messageStatus: "Credenciales incorrectas."
        ));
        print("Entra en status 401 -> ${response.statusCode}");

      } else if(response.statusCode >= 500){
        emit(state.copyWith(
          formStatus: FormStatus.invalid,
          messageStatus: "Error del servidor, intentelo más tarde."
        ));
        print("Entra en status 500 -> ${response.statusCode}");

      } else {
        log('Error en la autenticación -> ${response.statusCode} /  ${FormStatus.failHttp}',);
        emit(state.copyWith(
          formStatus:  FormStatus.failHttp,
          inicioSesion: false,
          messageStatus: "Error"
        ));
        print("¡Error a revisar!");
      }
    } catch (e, stackTrace) {
      log('Error durante petición Http -> $e');
      log('stackTrace -> $stackTrace');
      //emit(state.copyWith(formStatus: FormStatus.failHttp));
    }
  }

  void emailChanged(String value){
    final emailNewValue = GmailInput.dirty(value: value);
    emit( // Función utilizada en Cubit para notificar a Flutter de que el estado ha cambiado

      state.copyWith( // Crea nueva instancia manteniendo el estado anterior y solo cambia el campo email
        email: emailNewValue,
        isValid: Formz.validate([emailNewValue, state.password]), // Se le envía todos los campos porque necesita saber si son todos validos
        //icono: state.isValid ? Icons.check : Icons.error,
      )
    );
  }

  void passwordChanged(String value){
    final passwordNewValue = PasswordLoginInput.dirty(value: value);

    emit(

      state.copyWith(
        password: passwordNewValue,
        isValid: Formz.validate([state.email, passwordNewValue]),
        //icono: state.isValid ? Icons.check : Icons.error,
      )
    );
  }
}
