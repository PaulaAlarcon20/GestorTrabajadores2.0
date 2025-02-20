import 'package:flutter/material.dart';
import 'package:front_end_gui/config/theme/app_theme.dart';
import 'package:front_end_gui/views/RegisterView_screen.dart';


/// Fichero principal de la aplicación que contiene nuestra aplicación
void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: AppTheme(selectionColor: 0).theme(),
      title: 'Turn App',
      home: Scaffold(
        ///appBar: AppBar(//title: const Text('Nuevo usuario'),),
        body: 
          RegisterView()
          // añadir vista
       
      ),
    );
  }
}