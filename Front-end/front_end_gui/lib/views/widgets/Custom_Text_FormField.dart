import 'package:flutter/material.dart';
import 'package:flutter/services.dart';


/// Widget con estilos personalizados para formularios de login
/// Se ha personalizado este widget de la siguiente manera:
/// * Bordes redondeados
/// * Foco en el campo a escribir
/// * Borde se vuelve del color seleccionado en el apartado Theme.
/// * Validación de variables
class CustomTextFormfield extends StatelessWidget {

  final IconData? prefixIcon;
  final String? label;
  final String? hintText;
  final String? erroreMessage;
  final bool obscureText;
  final Function(String)? onChanged;
  final  List<TextInputFormatter>? inputFormatters;
  final TextInputType? keyboardType;
  final String? Function(String?)? validator;



  const CustomTextFormfield({
    super.key, 
    this.label, 
    this.hintText, 
    this.erroreMessage, 
    this.onChanged, 
    this.validator,  
    this.inputFormatters,
    this.keyboardType,
    required this.obscureText,  
    this.prefixIcon
  });

  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.of(context).size.width;
    double screenHeight = MediaQuery.of(context).size.height;
    
    final colors = Theme.of(context).colorScheme;

    final bool mododark = Theme.of(context).brightness == Brightness.light;
  
    return TextFormField(
      
        onChanged: onChanged,
        validator: validator,
        obscureText:  obscureText,
        inputFormatters: inputFormatters,
        keyboardType: keyboardType,
        style: TextStyle(  // Estilos caja input
          color: mododark ? Colors.white : Colors.black,
          fontSize: screenWidth * 0.05),

        decoration: InputDecoration(      
          contentPadding: EdgeInsets.symmetric(horizontal: 20, vertical: 12),
          border: InputBorder.none,
          prefixIcon: prefixIcon != null 
            ? Icon(
              prefixIcon,
              color: mododark ? Colors.white : Colors.black
              ) 
            : null,
          isDense: true,
          label: label != null ? Text(label!) : null,
          labelStyle: TextStyle(
            fontSize: 19
          ),
          hintText: hintText,
          hintStyle: TextStyle(
            
            //color: Colors.amber
          ),
          errorText: erroreMessage,
          errorStyle: TextStyle(
            fontSize: 15
          ),
          focusColor: colors.primary,
      ),
    );
  }
}