

import 'package:formz/formz.dart';

enum disponibilidadhorasextrasError {empty}

class DisponibilidadHorasExtras extends FormzInput<int?, disponibilidadhorasextrasError> {

  const DisponibilidadHorasExtras.pure() : super.pure(null);
  const DisponibilidadHorasExtras.dirty({int? value}) : super.dirty(value);


  String? get errorMessage {
    if (isValid || isPure) return null;
    if (displayError == disponibilidadhorasextrasError.empty) return '* Debe seleccionar una opción';
    return null;
  }

  @override
  disponibilidadhorasextrasError? validator(int? value) {
    if (value == null) return disponibilidadhorasextrasError.empty;
    return null;
  }

 
}