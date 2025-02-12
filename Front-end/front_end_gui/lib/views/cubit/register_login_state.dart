//import 'package:front_end_gui/views/infraestructure/inputs/inputs.dart';
part of 'register_login_cubit.dart';

enum FormStatus {invalid, valid, validating, posting}

/// Clase que contiene las variables de los inputs del RegisterForm.
class RegisterState extends Equatable {
  
  final FormStatus formStatus;
  final GmailInput email;
  final PasswordInput password;
  final bool isValid;

  // Constructor
  const RegisterState({
    this.formStatus = FormStatus.invalid,
    this.email = const GmailInput.pure(),
    this.password = const PasswordInput.pure(),
    this.isValid = false,
  });


  // Método copyWith para cambiar valores sin perder inmutabilidad
  RegisterState copyWith({
    FormStatus? formStatus,
    bool? isValid,
    GmailInput? email,
    PasswordInput? password,
    
  }) {
    return RegisterState(
        formStatus: formStatus ?? this.formStatus,
        isValid: isValid ?? this.isValid,
        email: email ?? this.email,
        password: password ?? this.password,
        
    );
  }



  
  @override
  List<Object> get props => [formStatus, email, password, isValid]; // Este listado sirve para saber que estado anterior tenía
}
