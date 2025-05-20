import 'dart:convert';
import 'package:http/http.dart' as http;

import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import '../gestionTurnos/Peticiones.dart';

class ListaPeticionesWidget extends StatefulWidget {
  const ListaPeticionesWidget({super.key});

  @override
  State<ListaPeticionesWidget> createState() => _ListaPeticionesWidgetState();
}

class _ListaPeticionesWidgetState extends State<ListaPeticionesWidget> {
  late Future<List<ItemPeticion>> futureSolicitudes;
  List<ItemPeticion> lPeticiones = [];

  @override
  void initState() {
    super.initState();
    // Hacer llamado a endpoint para obtener lista de peticiones
    int usuarioId = 1;
    futureSolicitudes = convertirLista(sendHttpGet(usuarioId));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: FutureBuilder<List<ItemPeticion>>(
        future: futureSolicitudes,
        builder: (context, lPeticionesConvert) {
          if (lPeticionesConvert.connectionState == ConnectionState.waiting) {
            return Center(child: CircularProgressIndicator());
          } else if (lPeticionesConvert.hasError) {
            return Center(child: Text("Error al cargar datos"));
          } else if (!lPeticionesConvert.hasData ||
              lPeticionesConvert.data!.isEmpty) {
            return Center(child: Text("No hay Peticiones abiertas"));
          } else {
            lPeticiones = lPeticionesConvert.data!;
            return ListView.builder(
              itemCount: lPeticiones.length,
              itemBuilder: (context, index) {
                return Container(
                  margin: EdgeInsets.all(8),
                  padding: EdgeInsets.all(4),
                  decoration: BoxDecoration(color: Colors.blue.shade100),
                  child: ListTile(
                    title: Text(
                      "Turno: ${lPeticiones[index].turno}",
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                    subtitle: Text(
                        "Fecha Solicitada: ${DateFormat('yyyy-MM-dd').format(DateTime.parse(lPeticiones[index].fechaSolicitada))}"),
                    leading: Icon(
                      Icons.calendar_month,
                      color: Colors.blue,
                    ),
                    trailing: Checkbox(
                      value: lPeticiones[index].isChecked,
                      onChanged: (bool? newValue) {
                        setState(() {
                          lPeticiones[index].isChecked = newValue ?? false;
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

  Future<List<ItemPeticion>> convertirLista(
      Future<List<dynamic>> futureLista) async {
    List<dynamic> lista = await futureLista;

    return lista.map((elemento) {
      if (elemento is Map<String, dynamic>) {
        // Se accede al objeto 'jornadaID', que se asume es un Map
        String descripcion = 'N/A';
        if (elemento['jornadaID'] != null &&
            elemento['jornadaID'] is Map<String, dynamic>) {
          descripcion =
              elemento['jornadaID']['descripcion']?.toString() ?? 'N/A';
        }
        return ItemPeticion(
          descripcion,
          elemento['fechaSolicitada']?.toString() ?? 'N/A',
          elemento['isChecked'] is bool ? elemento['isChecked'] : false,
        );
      } else {
        return ItemPeticion(elemento.toString(), '', false);
      }
    }).toList();
  }
}
