import 'package:formz/formz.dart';


enum passwordInputError { empty, length} // Se definen los diferentes tipos de error de validación

// Clase que exteinde Formz y se le indica el tipo de dato y de error
class PasswordInput extends FormzInput<String, passwordInputError> {
  
  const PasswordInput.pure() : super.pure(''); // Pure() es el valor inicial que tiene el campo
 
  const PasswordInput.dirty({String value = ''}) : super.dirty(value); // Dirty() es llamado cuando se ha producido un cambio en el input

  String? get errorMessage {
    if (isValid || isPure ) return null;
    if(displayError == passwordInputError.empty ) return 'Campo requerido';
    // En el futuro implementar validadión núm caracteres especiales
  }

  // Lugar donde se procede a la lógica de validaciones
  @override
  passwordInputError? validator(String value) {
    if (value.isEmpty || value.trim().isEmpty) return passwordInputError.empty;
    if (value.length < 8) return passwordInputError.length;
    // Caracteres especiales

    return null;
  }
}