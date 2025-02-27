import 'package:formz/formz.dart';


enum multiInputError { empty} // Se definen los diferentes tipos de error de validación

// Clase que exteinde Formz y se le indica el tipo de dato y de error
class MultiInput extends FormzInput<String, multiInputError> {
  
  const MultiInput.pure() : super.pure(''); // Pure() es el valor inicial que tiene el campo
  const MultiInput.dirty({String value = ''}) : super.dirty(value); // Dirty() es llamado cuando se ha producido un cambio en el input

  String? get errorMessage { // Devuelve un mensaje de error basado en la validación
    if(isValid || isPure ) return null;
    if(displayError == multiInputError.empty) return '* Campo requerido';

    return null;
  }

  // Lugar donde se procede a la lógica de validaciones
  @override
  multiInputError? validator(String value) { // Se ejecuta cada vez que el usuario escribe un campo

    if ( value.isEmpty || value.trim().isEmpty) return  multiInputError.empty;

    return null;
  }
}
