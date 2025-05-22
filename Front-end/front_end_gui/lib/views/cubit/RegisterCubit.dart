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

  String contenidoMessageError = "";

  // Métodos en el cubic de las variables del formulario
  Future<void> onSubmit() async {
    log('Estado actual formulario al enviarlo, antes de emitir emit -> ${state.formStatus}');
    log('Enviando formulario... datos -> email: ${state.email.value}, contraseña: ${state.password.value}');
    emit( // Notifica a flutter blog que el estado ha cambiado
      state.copyWith(  // hace una copia del estado actual, pero con algunos valores modificados
        formStatus : FormStatus.validating, // Se indica que el estado de las validaciones es 'validando'
        email : GmailInput.dirty(value : state.email.value),
        password : PasswordLoginInput.dirty( value: state.password.value),
        
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

        final data = jsonDecode(response.body);
        final inicioSesion = data['inicioSesion'];

        log('Se inicia sesión...');
        log('Inicio de sesión de ${data['nombre']}  ${data['apellido']} / Puesto: ${data['puesto']} - ${data['telefono']}');
        
        if(inicioSesion == true){
          emit(state.copyWith(
            formStatus: FormStatus.valid,
            inicioSesion: inicioSesion,
            messageStatus: "Solicitud exitosa."
          ));
        }
        
      } else if(response.statusCode == 400){

        contenidoMessageError = "Petición mal formulada";
        print("Entra en status 400 -> ${response.statusCode}");
        
      } else if(response.statusCode == 401){

        contenidoMessageError = "Credenciales incorrectas.";
        print("Entra en status 401 -> ${response.statusCode}");

      } else if(response.statusCode >= 500){

        contenidoMessageError = "Error del servidor, intentelo más tarde.";
        print("Entra en status 500 -> ${response.statusCode}");

      } else {
        log('Error en la autenticación -> ${response.statusCode} /  ${FormStatus.failHttp}',);
        contenidoMessageError = "Error durante el proceso de registro";

        
        print("¡Error a revisar!");
      }

      if(response.statusCode != 200 && response.statusCode != 201){
        emit(state.copyWith(
          formStatus: FormStatus.invalid,
          messageStatus: contenidoMessageError
        ));
      }



    } catch (e, stackTrace) {
      log('1) Error durante petición Http -> $e');
      log('2) Impresión de error -> $stackTrace');
    }
  }

  void emailChanged(String value){
    final emailNewValue = GmailInput.dirty(value: value);
    emit( // Función utilizada en Cubit para notificar a Flutter de que el estado ha cambiado

      state.copyWith( // Crea nueva instancia manteniendo el estado anterior y solo cambia el campo email
        email: emailNewValue,
        isValid: Formz.validate([emailNewValue, state.password]), // Se le envía todos los campos porque necesita saber si son todos validos
        
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
