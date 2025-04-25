import 'package:flutter/material.dart';

class Notificaciones extends StatefulWidget {
  const Notificaciones({super.key});

  @override
  // ignore: library_private_types_in_public_api
  _Notificaciones createState() => _Notificaciones();
}

class _Notificaciones extends State<Notificaciones> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.blue,
        leading: IconButton(
          icon: Icon(Icons.arrow_back),
          color: Colors.white,
          onPressed: _volver,
        ),
      ),
      body: Text("Lista de notificaciones..."),
    );
  }

  void _volver() {
    print("Acción de Volver");
  }
}
