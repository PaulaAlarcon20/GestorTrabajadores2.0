import 'package:formz/formz.dart';


enum gmailInputError { empty, invalidFormat , length} // Se definen los diferentes tipos de error de validación

// Clase que exteinde Formz y se le indica el tipo de dato y de error
class GmailInput extends FormzInput<String, gmailInputError> {
  
  const GmailInput.pure() : super.pure(''); // Pure() es el valor inicial que tiene el campo

  const GmailInput.dirty({String value = ''}) : super.dirty(value); // Dirty() es llamado cuando se ha producido un cambio en el input

  String? get errorMessage {
    if(isValid || isPure ) return null;
    if(displayError == gmailInputError.empty) return '* Campo requerido';
    if(displayError == gmailInputError.invalidFormat) return 'Dirección no valida';

    return null;
  }
  // Lugar donde se procede a la lógica de validaciones
  @override
  gmailInputError? validator(String value) {

    final emailRegExp = RegExp(r'^[\w-\.]+@([\w-]+\.)+[\w]{2,4}$',);

    if ( value.isEmpty || value.trim().isEmpty) return  gmailInputError.empty;
    if(!emailRegExp.hasMatch(value)) return gmailInputError.invalidFormat; //  Esta verificación no esta saltando
    

    // if(value.length < 2) return gmailInputError.length;
  
    return null;
  }
}
