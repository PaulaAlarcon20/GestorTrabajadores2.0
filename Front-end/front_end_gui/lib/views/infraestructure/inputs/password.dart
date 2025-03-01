import 'package:flutter/material.dart';
import 'package:formz/formz.dart';


enum passwordInputError { empty, length, invalidMayuscula, invalidMinuscula, invalidNumero, invalidCaracterSpecial} // Se definen los diferentes tipos de error de validación

// Clase que exteinde Formz y se le indica el tipo de dato y de error
class PasswordInput extends FormzInput<String, passwordInputError> {
  
  const PasswordInput.pure() : super.pure(''); // Pure() es el valor inicial que tiene el campo
  const PasswordInput.dirty({String value = ''}) : super.dirty(value); // Dirty() es llamado cuando se ha producido un cambio en el input
  


  String? get errorMessage {
    //List<passwordInputError> errors = [];
    
    if (isValid || isPure ) return null;
    if(displayError == passwordInputError.empty ) return  '* La contraseña es obligatoria' ;
    if(displayError == passwordInputError.invalidMayuscula) return '* Debe contener al menos una letra mayúscula .';
    if(displayError == passwordInputError.invalidMinuscula) return '* Debe contener al menos una letra minúscula.';
    if(displayError == passwordInputError.invalidNumero) return '* Debe contener al menos un número.';
    if(displayError == passwordInputError.invalidCaracterSpecial) return '* Debe contener al menos un carácter especial.';
    if(displayError == passwordInputError.length) return '* Debe contener mínimo 8 caracteres.';
    
    return  null;
  }

  // Lugar donde se procede a la lógica de validaciones
  @override
  passwordInputError? validator(String value) {


    //final passwordRegExp = RegExp(r'^[\w-\.]+@([\w-]+\.)+[\w]{2,4}$',);
    final passwordRegExpMayuscula = RegExp(r'[A-Z]');
    final passwordRegExpMinuscula = RegExp(r'[a-z]');
    final passwordRegExpNumero =    RegExp(r'\d');
    final passwordRegExCaracterSpecial = RegExp(r'[@$!%*?&]');

    if (value.isEmpty || value.trim().isEmpty) return passwordInputError.empty;
    if (!passwordRegExpMayuscula.hasMatch(value)) return passwordInputError.invalidMayuscula;
    if(!passwordRegExpMinuscula.hasMatch(value)) return passwordInputError.invalidMinuscula;
    if(!passwordRegExpNumero.hasMatch(value)) return passwordInputError.invalidNumero;
    if(!passwordRegExCaracterSpecial.hasMatch(value)) return passwordInputError.invalidCaracterSpecial;
    if(value.length < 8) return passwordInputError.length;

    return null;
  }
}