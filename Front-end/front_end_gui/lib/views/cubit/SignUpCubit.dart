import 'package:bloc/bloc.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';
import 'package:front_end_gui/views/cubit/SignUpState.dart';
import 'package:front_end_gui/views/infraestructure/inputs/inputs.dart';
import 'package:front_end_gui/views/infraestructure/inputs/multiInput.dart';
import 'package:front_end_gui/views/infraestructure/inputs/telefonoInput.dart';


/// Clase RegisterLoginCubit, en donde vamos a desarrollar los métodos de la validación
class SignUpCubit extends Cubit<SignUpState> {
  SignUpCubit() : super(SignUpState(isValid: false));

  void setValidState(bool isValid) {
    emit(state.copyWith(isValid: isValid));
  }


  void onSubmit() {
    // Más tarde desarrollar emit, nuevo estado y petición Http y validación estado petición
    emit(
      state.copyWith(
        formStatus: FormStatus.validating,
        nombre: MultiInput.dirty(value: state.nombre.value),
        apellidos: MultiInput.dirty(value: state.apellidos.value),
        gmail: GmailInput.dirty(value: state.gmail.value),
        telefono: TelefonoInput.dirty(value: state.telefono.value),

        isValid: Formz.validate([
          state.nombre,
          state.apellidos,
          state.gmail,
          state.telefono
        ])
      )
    );
  }

  void nombreChanged(String  nombre){
    final nombreNewValor = MultiInput.dirty(value: nombre);

    emit(
      state.copyWith(
        nombre: nombreNewValor,
        isValid: Formz.validate([nombreNewValor, state.apellidos, state.gmail, state.telefono]) // le vamos a tener que enviar todo slos campos porque necesita saber si todos los campos son validos
      )
    );
  }

  void apellidosChanged(String apellidos){
    final apellidosNewValor = MultiInput.dirty(value: apellidos);

    emit(
      state.copyWith(
        apellidos: apellidosNewValor,
        isValid: Formz.validate([apellidosNewValor, state.nombre, state.gmail, state.telefono])
      )
    );
  }

  void gmailChanged(String email){
    final gmailNewValor = GmailInput.dirty(value: email);

    emit(
      state.copyWith(
        gmail: gmailNewValor,
        isValid: Formz.validate([gmailNewValor, state.nombre, state.apellidos, state.telefono])
      )
    );
  }

  void telefonoChanged(String telefono){
    final telefonoNewValor = TelefonoInput.dirty(value: telefono);

    emit(
      state.copyWith(
        telefono: telefonoNewValor,
        isValid: Formz.validate([telefonoNewValor, state.nombre, state.apellidos, state.gmail])
      )
    );
  }

}
