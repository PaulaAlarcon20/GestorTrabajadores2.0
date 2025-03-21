import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class GoogleMapServices {

  final String apiKey = "AIzaSyDjBz4NKsp0Y1Bxnn8O_-TGrzwlrIgfVpI";

  Future<List<LatLng>> getRouteCoordinates(LatLng origen, LatLng destino) async {

    final String url =
        "https://maps.googleapis.com/maps/api/directions/json?origin=${origen.latitude},${origen.longitude}&destination=${destino.latitude},${destino.longitude}&key=$apiKey";

    final response = await http.get(Uri.parse(url));

    if (response.statusCode == 200) {
      final decodedData = json.decode(response.body);
      List<LatLng> ruta = [];

    
      List steps = decodedData["routes"][0]["legs"][0]["steps"]; // Se extraen los pasos de la ruta
      for (var step in steps) {
        ruta.add(LatLng(step["end_location"]["lat"], step["end_location"]["lng"]));
      }

      return ruta;
    } else {
      throw Exception("Error al obtener la ruta");
    }
  }
}