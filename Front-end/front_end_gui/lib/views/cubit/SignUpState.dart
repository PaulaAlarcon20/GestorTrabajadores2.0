import 'package:equatable/equatable.dart';
import 'package:front_end_gui/views/infraestructure/inputs/gmail.dart';
import 'package:front_end_gui/views/infraestructure/inputs/multiInput.dart';
import 'package:front_end_gui/views/infraestructure/inputs/telefonoInput.dart';


enum FormStatus {invalid, valid, validating, posting, failHttp} // Estados diferentes de validación


// Clase que contiene todas las variables del formulario de Alta
class SignUpState extends Equatable { // Equatable es una clase qeu ayuda a comparar objetos en dart

  // Paso 1/3
  final FormStatus formStatus;
  final MultiInput nombre;
  final MultiInput apellidos;
  final GmailInput gmail;
  final TelefonoInput telefono;
  final bool isValid;

  //Paso 2/3
  // ENUM
  //final MultiInput localidad;
  // Preferencias horarias radio Buttons
  //DisponibilidadHorasExtras RadioButtons


  const SignUpState({
    this.formStatus = FormStatus.invalid,
    this.nombre = const MultiInput.pure(),
    this.apellidos = const MultiInput.pure(),
    this.gmail = const GmailInput.pure(),
    this.telefono = const TelefonoInput.pure(),
    this.isValid = false,

  });

  SignUpState copyWith({
    FormStatus? formStatus,
    MultiInput? nombre,
    MultiInput? apellidos,
    GmailInput? gmail,
    TelefonoInput? telefono,
    bool? isValid,

    FormStatus? formStatus2,
    MultiInput? centroDeTrabajo,
    bool? isValid2,
  }) {
    return SignUpState(
      formStatus: formStatus ?? this.formStatus,
      nombre: nombre ?? this.nombre,
      apellidos: apellidos ?? this.apellidos,
      gmail: gmail ?? this.gmail,
      telefono: telefono ?? this.telefono,
      isValid: isValid ?? this.isValid,
    );
  }

  @override
  List<Object> get props => [formStatus, nombre, apellidos, gmail, telefono, isValid]; // Este listado sirve para saber que estado anterior tenía

}