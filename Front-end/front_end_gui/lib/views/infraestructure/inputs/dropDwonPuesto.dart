

import 'package:formz/formz.dart';

enum dropDownPuestoError {empty}

class DropDownPuesto extends FormzInput<String, dropDownPuestoError> {

  const DropDownPuesto.pure() : super.pure('');
  const DropDownPuesto.dirty({String value = ''}) : super.dirty(value);


  String? get errorMessage {
    if (isValid || isPure) return null;
    if (displayError == dropDownPuestoError.empty) return '* Debe seleccionar una opción';
    return null;
  }

  @override
  dropDownPuestoError? validator(String? value) {
    if (value == null) return dropDownPuestoError.empty;
    return null;
  }

 
}