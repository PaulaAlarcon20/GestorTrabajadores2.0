import 'package:equatable/equatable.dart';
import 'package:front_end_gui/views/infraestructure/inputs/inputs.dart';
import 'package:front_end_gui/views/infraestructure/inputs/multiInput.dart';
import 'package:front_end_gui/views/infraestructure/inputs/disponibilidadHorasExtras.dart';
import 'package:front_end_gui/views/infraestructure/inputs/dropDwonPuesto.dart';
import 'package:front_end_gui/views/infraestructure/inputs/preferenciasRadioButton.dart';


enum FormStatus {invalid, valid, validating, posting, failHttp}
enum FormStatus3 {invalid, valid, validating, posting, failHttp} // Estados diferentes de validación


// Clase que contiene todas las variables del formulario de Alta
class SignUpState2 extends Equatable { // Equatable es una clase qeu ayuda a comparar objetos en dart

  //Paso 2/3
  final FormStatus formStatus2;
  final FormStatus3 formStatus3;
  final MultiInput centroDeTrabajo;
  final MultiInput localidad;
  final DropDownPuesto puesto;
  final PreferenciasRadioButton preferenciasHorarias;

  final PasswordInput password;

  final bool isValid2;
  final bool isValid3;
  // ENUM

  final DisponibilidadHorasExtras disponibilidadHorasExtras;


  const SignUpState2({
    this.formStatus2 = FormStatus.invalid,
    this.formStatus3 = FormStatus3.invalid,
    this.centroDeTrabajo = const MultiInput.pure(),
    this.puesto = const DropDownPuesto.pure(),
    this.localidad = const MultiInput.pure(),
    this.preferenciasHorarias = const PreferenciasRadioButton.pure(),
    this.disponibilidadHorasExtras = const DisponibilidadHorasExtras.pure(),
    this.password = const PasswordInput.pure(),
    this.isValid2 = false,
    this.isValid3 = false
  });

  SignUpState2 copyWith({

    FormStatus? formStatus2,
    FormStatus3? formStatus3,
    MultiInput? centroDeTrabajo,
    DropDownPuesto? puesto,
    MultiInput? localidad,
    PreferenciasRadioButton? preferenciasHorarias,
    DisponibilidadHorasExtras? disponibilidadHorasExtras,
    PasswordInput? password,
    bool? isValid2,
    bool? isValid3
  }) {
    return SignUpState2(

      formStatus2: formStatus2 ?? this.formStatus2,
      formStatus3: formStatus3 ?? this.formStatus3,
      centroDeTrabajo: centroDeTrabajo ?? this.centroDeTrabajo,
      localidad: localidad ?? this.localidad,
      preferenciasHorarias: preferenciasHorarias ?? this.preferenciasHorarias,
      puesto: puesto ?? this.puesto,
      disponibilidadHorasExtras: disponibilidadHorasExtras ?? this.disponibilidadHorasExtras,
      password: password ?? this.password,
      isValid2: isValid2 ?? this.isValid2,
      isValid3: isValid3 ?? this.isValid3
    );
  }

  @override
  List<Object> get props => [ formStatus2, formStatus3, centroDeTrabajo, localidad, puesto, preferenciasHorarias, disponibilidadHorasExtras,password,isValid3, isValid2]; // Este listado sirve para saber que estado anterior tenía

}