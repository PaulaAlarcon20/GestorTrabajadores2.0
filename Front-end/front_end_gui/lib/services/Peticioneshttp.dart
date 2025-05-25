import 'dart:convert';
import 'dart:math';

import 'package:http/http.dart';
import 'package:http/http.dart' as http;

class Peticioneshttp {

  
  final String email = "";
  final bool inicioSesion = false; // todo OJO CON LA RESPUESTA DEL BACK

  Peticioneshttp({ email,  inicioSesion});

  Future<bool> cerrarSesion({ required email, required inicioSesion}) async {

    try {
      final url = Uri.parse('http://10.0.2.2:8080/api/usuarios/LogOut');

    final response = await http.post(
      url,
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({
        'email' : email,
        'inicio_sesion' : inicioSesion
      })
    );

    

    if(response.statusCode == 200) {
      print("Se envia al backend: $email y valor inicioSesion: $inicioSesion");
      print('Se ha cerrado la sesión ...');
      // TODO NAVEGA HACIA PANTALLA REGISTER
      return false;
    } else {
      print('No se ha cerrado la sesión -> ${response.statusCode}');
      print("Se ha intentado enviar al backend: $email y valor inicioSesion: $inicioSesion");
      return true;
    }

    } catch (e, stackTrace) {
      print("Error al enviar petición cierre de sesión -> $e");
      print("Detalles: $stackTrace" );
      return true;
    }

    
  }
}