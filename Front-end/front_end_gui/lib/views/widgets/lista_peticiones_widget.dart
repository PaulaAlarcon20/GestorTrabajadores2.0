import 'dart:convert';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:front_end_gui/services/UsuarioDTO.dart';
import 'package:front_end_gui/views/cubit/RegisterCubit.dart';
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
  late Future<List<ItemPeticion>> futureSolicitudes = Future.value([]);
  List<ItemPeticion> lPeticiones = [];
  late UsuarioDTO usuario;

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((_) {
      // Hacer llamado a endpoint para obtener lista de peticiones
      usuario = context.read<RegisterCubit>().state.usuarioDTO!;

      if (usuario != null) {
        setState(() {
          futureSolicitudes = convertirLista(sendHttpGetPeticiones(usuario.id));
        });
      }
    });
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
                        lPeticiones[index].isChecked = newValue ?? false;

                        if (lPeticiones[index].isChecked) {
                          aceptarPeticion(
                              context, lPeticiones[index].cambioTurnoId);
                        }
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
  void aceptarPeticion(BuildContext context, int cambioTurnoId) async {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          content: Text(
            "¿Está segur@ de aceptar la petición?",
            style: TextStyle(fontSize: 20),
          ),
          actions: [
            TextButton(
                child: Text("Cancelar", style: TextStyle(fontSize: 20)),
                onPressed: () {
                  Navigator.pop(context);
                }),
            TextButton(
                child: Text("Confirmar", style: TextStyle(fontSize: 20)),
                onPressed: () async {
                  await sendHttpPostAceptarPeticion(cambioTurnoId);
                  setState(() {
                    futureSolicitudes =
                        convertirLista(sendHttpGetPeticiones(usuario.id));

                    Navigator.pop(context);
                    mostrarAlerta(context, "Petición aceptada");
                  });
                }),
          ],
        );
      },
    );
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

  Future<List<dynamic>> sendHttpGetPeticiones(int userId) async {
    final response = await http
        .get(Uri.parse('http://localhost:8080/api/peticiones?userId=$userId'));

    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception('Error al cargar los datos');
    }
  }

  Future<void> sendHttpPostAceptarPeticion(int idCambioTurno) async {
    int userId = usuario.id;
    final response = await http.post(Uri.parse(
        'http://localhost:8080/api/aceptar_sol?idCambioTurno=$idCambioTurno&usuarioAceptanteId=$userId'));

    if (response.statusCode == 200) {
      print("Solicitud enviada exitosamente: ${response.body}");
    } else {
      throw Exception('Error al enviar la solicitud: ${response.statusCode}');
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
          descripcion = elemento['jornadaID']['descripcion'] != null
              ? utf8.decode(elemento['jornadaID']['descripcion']
                  .toString()
                  .runes
                  .toList())
              : 'N/A';
        }

        int cambioTurnoId = elemento['id'] is int
            ? elemento['id']
            : int.tryParse(elemento['id'].toString()) ?? 0;

        return ItemPeticion(
          descripcion,
          elemento['fechaSolicitada']?.toString() ?? 'N/A',
          elemento['isChecked'] is bool ? elemento['isChecked'] : false,
          cambioTurnoId,
        );
      } else {
        return ItemPeticion(elemento.toString(), '', false, 0);
      }
    }).toList();
  }
}
