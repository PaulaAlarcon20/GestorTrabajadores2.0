import 'package:flutter/material.dart';


/// Widget con estilos personalizados para formularios de login
/// Se ha personalizado este widget de la siguiente manera:
/// * Bordes redondeados
/// * Foco en el campo a escribir
/// * Borde se vuelve del color seleccionado en el apartado Theme.
/// * Validación de variables
class CustomTextFormfield extends StatelessWidget {

  final String? label;
  final String? hintText;
  final String? erroreMessage;
  final bool obscureText;
  final Function(String)? onChanged;
  final String? Function(String?)? validator;

  const CustomTextFormfield({super.key, this.label, this.hintText, this.erroreMessage, this.onChanged, this.validator,  required this.obscureText});

  @override
  Widget build(BuildContext context) {

  final colors = Theme.of(context).colorScheme;
  final border = OutlineInputBorder(
    borderRadius: BorderRadius.circular(40)
  );


    return TextFormField(
      onChanged: onChanged,
  
      validator: validator,
      obscureText:  obscureText,

      decoration: InputDecoration(

        enabledBorder:  border,
        focusedBorder: border.copyWith(borderSide: BorderSide(color: colors.primary)),
        errorBorder: border.copyWith(borderSide: BorderSide(color: Colors.red.shade800)),
        focusedErrorBorder: border.copyWith(borderSide: BorderSide(color: Colors.red.shade800)),
        
        isDense: true,
        label: label != null ? Text(label!) : null,
        hintText: hintText,
        errorText: erroreMessage,
        focusColor: colors.primary
      ),
    );
  }
}