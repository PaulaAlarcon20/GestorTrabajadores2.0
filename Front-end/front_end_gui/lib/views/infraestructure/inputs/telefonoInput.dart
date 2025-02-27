import 'package:formz/formz.dart';


enum tlfInputError { empty, lenght} // Se definen los diferentes tipos de error de validación

// Clase que exteinde Formz y se le indica el tipo de dato y de error
class TelefonoInput extends FormzInput<String, tlfInputError> {
  
  const TelefonoInput.pure() : super.pure(''); // Pure() es el valor inicial que tiene el campo
  const TelefonoInput.dirty({String value = ''}) : super.dirty(value); // Dirty() es llamado cuando se ha producido un cambio en el input

  String? get errorMessage { // Devuelve un mensaje de error basado en la validación
    if(isValid || isPure ) return null;
    if(displayError == tlfInputError.empty) return '* Campo requerido';
    if(displayError == tlfInputError.lenght) return 'El número debe tener al menos 9 dígitos';

    return null;
  }

  // Lugar donde se procede a la lógica de validaciones
  @override
  tlfInputError? validator(String value) { // Se ejecuta cada vez que el usuario escribe un campo

    if ( value.isEmpty || value.trim().isEmpty) return  tlfInputError.empty;
    if( value.length < 11 ) return tlfInputError.lenght;

    return null;
  }
}
