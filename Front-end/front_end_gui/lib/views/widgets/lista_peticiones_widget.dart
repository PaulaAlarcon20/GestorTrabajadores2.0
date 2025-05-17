import 'dart:convert';
import 'package:http/http.dart' as http;

import 'package:flutter/material.dart';
import '../gestionTurnos/Solicitudes.dart';

class ListaPeticionesWidget extends StatefulWidget {
  const ListaPeticionesWidget({super.key});

  @override
  State<ListaPeticionesWidget> createState() => _ListaPeticionesWidgetState();
}

class _ListaPeticionesWidgetState extends State<ListaPeticionesWidget> {
  List<ItemSolicitud> lSolicitudes = [];

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    // Hacer llamado a endpoint para obtener lista de peticiones
    int usuarioId = 1;
    var petis = sendHttpGet(usuarioId);

    return Scaffold(
      body: FutureBuilder<List<ItemSolicitud>>(
        future: convertirLista(petis),
        builder: (context, lSolicitudesConvert) {
          if (lSolicitudesConvert.connectionState == ConnectionState.waiting) {
            return Center(child: CircularProgressIndicator());
          } else if (lSolicitudesConvert.hasError) {
            return Center(child: Text("Error al cargar datos"));
          } else if (!lSolicitudesConvert.hasData ||
              lSolicitudesConvert.data!.isEmpty) {
            return Center(child: Text("No hay Peticiones abiertas"));
          } else {
            lSolicitudes = lSolicitudesConvert.data!;
            return ListView.builder(
              itemCount: lSolicitudes.length,
              itemBuilder: (context, index) {
                return Container(
                  margin: EdgeInsets.all(8),
                  padding: EdgeInsets.all(4),
                  decoration: BoxDecoration(color: Colors.blue.shade100),
                  child: ListTile(
                    title: Text(
                      lSolicitudes[index].text,
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                    subtitle: Text("Fecha 23-04-2025"),
                    leading: Icon(
                      Icons.calendar_month,
                      color: Colors.blue,
                    ),
                    trailing: Checkbox(
                      value: lSolicitudes[index].isChecked,
                      onChanged: (bool? newValue) {
                        setState(() {
                          lSolicitudes[index].isChecked = newValue ?? false;
                        });
                      },
                    ),
                  ),
                );
              },
            );
          }
        },
      ),
    );
  }

  // Alerta Peticion Aceptada
  void aceptarPeticion(BuildContext context) async {
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            content: Text(
              "¿Esta segur@ de aceptar la petición?",
              style: TextStyle(fontSize: 20),
            ),
            actions: [
              TextButton(
                  child: Text(
                    "Cancelar",
                    style: TextStyle(fontSize: 20),
                  ),
                  onPressed: () {
                    Navigator.pop(context);
                  }),
              TextButton(
                  child: Text(
                    "Confirmar",
                    style: TextStyle(fontSize: 20),
                  ),
                  onPressed: () {
                    Navigator.pop(context);
                    mostrarAlerta(context, "Petición aceptada");
                  })
            ],
          );
        });
  }

  // Mostrar Alerta genérica
  Future<dynamic> mostrarAlerta(BuildContext context, String Mjs) async {
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            content: Text(
              Mjs,
              style: TextStyle(fontSize: 20),
            ),
            actions: [
              TextButton(
                  child: Text(
                    "Ok",
                    style: TextStyle(fontSize: 15),
                  ),
                  onPressed: () {
                    Navigator.pop(context);
                  }),
            ],
            shape:
                RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
          );
        });
  }

  Future<List<dynamic>> sendHttpGet(int userId) async {
    final response = await http
        .get(Uri.parse('http://localhost:8080/api/peticiones?userId=$userId'));

    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception('Error al cargar los datos');
    }
  }

  Future<List<ItemSolicitud>> convertirLista(
      Future<List<dynamic>> futureLista) async {
    List<dynamic> lista = await futureLista;

    return lista.map((elemento) {
      return ItemSolicitud(
          elemento.toString(), elemento is bool ? elemento : false);
    }).toList();
  }
}
