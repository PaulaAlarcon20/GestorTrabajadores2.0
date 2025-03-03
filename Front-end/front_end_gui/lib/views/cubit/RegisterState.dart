//import 'package:front_end_gui/views/infraestructure/inputs/inputs.dart';
part of 'RegisterCubit.dart';

enum FormStatus {invalid, valid, validating, posting, failHttp} // Estados diferentes de validación

/// Clase que contiene las variables de los inputs del RegisterForm.
class RegisterState extends Equatable {
  
  final FormStatus formStatus;
  final GmailInput email;
  final IconData icono;
  final PasswordLoginInput password;
  final bool isValid;

  // Constructor
  const RegisterState({
    this.formStatus = FormStatus.invalid,
    this.email = const GmailInput.pure(),
    this.password = const PasswordLoginInput.pure(),
    this.isValid = false,
    this.icono = Icons.error
  });


  // Método copyWith para cambiar valores sin perder inmutabilidad
  RegisterState copyWith({
    FormStatus? formStatus,
    bool? isValid,
    GmailInput? email,
    PasswordLoginInput? password,
    IconData? icono
    
  }) {
    return RegisterState(
        formStatus: formStatus ?? this.formStatus,
        isValid: isValid ?? this.isValid,
        email: email ?? this.email,
        password: password ?? this.password,
        icono: icono ?? this.icono
        
    );
  }
  
  @override
  List<Object> get props => [formStatus, email, password, isValid, icono]; // Este listado sirve para saber que estado anterior tenía
}
