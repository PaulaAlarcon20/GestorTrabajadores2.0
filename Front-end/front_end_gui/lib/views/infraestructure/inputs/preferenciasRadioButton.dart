import 'package:formz/formz.dart';


enum preferenciasRadioButtonError { empty, maxLimit} // Se definen los diferentes tipos de error de validación

// Clase que exteinde Formz y se le indica el tipo de dato y de error
class PreferenciasRadioButton extends FormzInput<String, preferenciasRadioButtonError> {
  
  const PreferenciasRadioButton.pure() : super.pure(''); // Pure() es el valor inicial que tiene el campo
  const PreferenciasRadioButton.dirty({String value = ''}) : super.dirty(value); // Dirty() es llamado cuando se ha producido un cambio en el input

  String? get errorMessage {
    if(isValid || isPure) return null;
    if(displayError == preferenciasRadioButtonError.empty) return '* Campo requerido';
    if(displayError == preferenciasRadioButtonError.maxLimit) return '* Máximo 2 preferencias';


    return  null;
  }

  // Lugar donde se procede a la lógica de validaciones
  @override
  preferenciasRadioButtonError? validator(String value) {
    if(value.isEmpty) return preferenciasRadioButtonError.empty;
    

    return null;
  }
}