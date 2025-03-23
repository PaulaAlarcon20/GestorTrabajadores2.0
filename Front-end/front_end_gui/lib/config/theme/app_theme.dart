import 'package:flutter/material.dart';
import 'dart:developer';
class AppTheme {

  final int selectionColor;

  static final List<Color> colorThemes = [
    Colors.white,
    Colors.black,
  ];

  AppTheme({this.selectionColor = 0})
  : assert(selectionColor >= 0 && selectionColor < colorThemes.length,
    'La selección debe ser entre 0 y ${colorThemes.length }');
  
  ThemeData theme(){

    final Color primaryColor = colorThemes[selectionColor];
    final bool isWhite = primaryColor == Colors.white;
    log('Valor de isDark: $isWhite');

    final enabledBorder = OutlineInputBorder(
      borderSide: BorderSide(color: isWhite ? const Color.fromARGB(255, 10, 10, 10) : Colors.white),
      borderRadius: BorderRadius.circular(40),
    );

    final focusedBorder = OutlineInputBorder(
      borderSide:  BorderSide(color: isWhite ? Colors.blue : Colors.blue.shade400),
      borderRadius: BorderRadius.circular(40),
    );

    final errorBorder = OutlineInputBorder(
       borderSide:  BorderSide(
        color: Colors.red.shade800
       ),
       borderRadius: BorderRadius.circular(40),
    );

    final focusedError = OutlineInputBorder(
      borderSide: BorderSide(
        color: Colors.red.shade800
      ),
      borderRadius: BorderRadius.circular(40),
    );

    return ThemeData(
      useMaterial3: true,
      //colorSchemeSeed:  colorThemes[selectionColor],
      
      colorScheme: ColorScheme(
        brightness: isWhite ? Brightness.dark : Brightness.light,                             // Brillo el color
        primary: isWhite ? Color.fromARGB(255, 54, 138, 223): Color(0xFFFFFFFF),                               // Entiendo que color primario
        onPrimary: isWhite ? const Color.fromARGB(255, 255, 249, 249) : Color(0xB3FFFFFF),                                 // Color de texto
        secondary: isWhite ? Color.fromARGB(255, 54, 138, 223) : Colors.blue.shade400,            // Botón Inicio sesión
        onSecondary: isWhite ? Colors.white : Colors.white,                               // Color de texto sobre secondary
        error: Color(0xFFD32F2F),                                                           // Color de errores
        onError: Colors.white,                                                               // Color de texto sobre error
        surface: isWhite ? Color(0xFFE3F2FD) :  Color(0xFF121212),                       // Color de la superficie de los widgets
        onSurface: isWhite ? Color(0xFF333333) : Colors.white
        
      ),
      inputDecorationTheme: InputDecorationTheme( 
        enabledBorder: enabledBorder,
        focusedBorder: focusedBorder,
        errorBorder: errorBorder,
      focusedErrorBorder: focusedError
        //errorBorder: border.copyWith(borderSide: BorderSide(color: Colors.red.shade800)),
        //focusedErrorBorder: border.copyWith(borderSide: BorderSide(color: Colors.red.shade800)),
      ),
      iconTheme: IconThemeData(
        color: isWhite ? Colors.black : Colors.white,
      ),   
      textTheme: TextTheme(
        bodyMedium: TextStyle(
          color: isWhite ? Colors.black : Colors.white,
        ),
        bodySmall: TextStyle(
          color: isWhite ? Colors.black : Colors.white,
        )
      )    ,
      outlinedButtonTheme: OutlinedButtonThemeData(
        style: OutlinedButton.styleFrom(
          side: BorderSide(
            color: isWhite ? Colors.black : Colors.white,
            width: 2
          )
        )
      )             
      // Color(0xFF4CAF50) para cuando haya boton secundario
    );
  }
}