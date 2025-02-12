import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';
import 'package:front_end_gui/views/infraestructure/inputs/inputs.dart';

part 'register_login_state.dart';

/// Clase RegisterLoginCubit, en donde vamos a desarrollar los métodos de la validación
class RegisterLoginCubit extends Cubit<RegisterState> {
  RegisterLoginCubit() : super(RegisterState());


  // Métodos en el cubic de las variables del formulario
  void onSubmit(){
    
    emit(
      state.copyWith(
        formStatus : FormStatus.validating,
        email : GmailInput.dirty(value : state.email.value),
        password : PasswordInput.dirty( value: state.password.value),

        isValid: Formz.validate([
          state.email,
          state.password
        ])
      )
    );
  }

  void emailChanged(String value){
    final email = GmailInput.dirty(value: value);
    emit( // Función utilizada en Cubit para notificar a Flutter de que el estado ha cambiado

      state.copyWith( // Crea nueva instancia manteniendo el estado anterior y solo cambia el campo email
        email: email,
        isValid: Formz.validate([email, state.password]) // Se le envía todos los campos porque necesita saber si son todos validos
      )
    );
  }

  void passwordChanged(String value){
    final password = PasswordInput.dirty(value: value);

    emit(

      state.copyWith(
        password: password,
        isValid: Formz.validate([state.email, password]),
      )
    );
  }
}
