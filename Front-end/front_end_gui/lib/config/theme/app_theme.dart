import 'package:flutter/material.dart';

  List<Color> colorThemes = [
    Colors.white,
    Colors.black,
    Colors.greenAccent
  ];


class AppTheme {

  final int selectionColor;
  

  AppTheme({this.selectionColor = 0})
  : assert(selectionColor >= 0 && selectionColor < colorThemes.length,
    'La selección debe ser entre 0 y ${colorThemes.length }');
  
  ThemeData theme(){
    return ThemeData(
      useMaterial3: true,
      colorSchemeSeed:  colorThemes[selectionColor],
      //scaffoldBackgroundColor: Colors.grey,
    );
  }
 
}