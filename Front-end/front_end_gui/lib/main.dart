import 'package:flutter/material.dart';

/// Fichero principal de la aplicación
void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Turn App',
      home: Scaffold(
        appBar: AppBar(
          title: const Text('App Turn App'),
        ),
        body: const Center(
          child: Text('Proyecto final de grado'),
        ),
      ),
    );
  }
}