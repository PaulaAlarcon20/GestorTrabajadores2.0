import 'package:formz/formz.dart';


enum passwordLoginInputError { empty, length} // Se definen los diferentes tipos de error de validación

// Clase que exteinde Formz y se le indica el tipo de dato y de error
class PasswordLoginInput extends FormzInput<String, passwordLoginInputError> {
  
  const PasswordLoginInput.pure() : super.pure(''); // Pure() es el valor inicial que tiene el campo
  const PasswordLoginInput.dirty({String value = ''}) : super.dirty(value); // Dirty() es llamado cuando se ha producido un cambio en el input

  String? get errorMessage {
    
    if (isValid || isPure ) return null;
    if(displayError == passwordLoginInputError.empty ) return  '* La contraseña es obligatoria' ;
    if(displayError == passwordLoginInputError.length) return '* Debe contener mínimo 8 caracteres.';
    return  null;
  }

  // Lugar donde se procede a la lógica de validaciones
  @override
  passwordLoginInputError? validator(String value) {

    if (value.isEmpty || value.trim().isEmpty) return passwordLoginInputError.empty;
    if(value.length < 8) return passwordLoginInputError.length;

    return null;
  }
}