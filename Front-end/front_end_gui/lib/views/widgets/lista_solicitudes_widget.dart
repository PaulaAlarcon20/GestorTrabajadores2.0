import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:front_end_gui/services/UsuarioDTO.dart';
import 'package:front_end_gui/views/cubit/RegisterCubit.dart';
import 'package:front_end_gui/views/widgets/nueva_solicitud_widget.dart';
import 'package:intl/intl.dart';
import '../gestionTurnos/Solicitudes.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

class ListaSolicitudesWidget extends StatefulWidget {
  const ListaSolicitudesWidget({super.key});

  @override
  State<ListaSolicitudesWidget> createState() => _ListaSolicitudesWidgetState();
}

class _ListaSolicitudesWidgetState extends State<ListaSolicitudesWidget> {
  late Future<List<ItemSolicitud>> futureSolicitudes = Future.value([]);
  List<ItemSolicitud> lSolicitudes = [];
  late UsuarioDTO usuario;

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((_) {
      if (usuario != null) {
        setState(() {
          futureSolicitudes =
              convertirLista(sendHttpGetSolicitudes(usuario.id));
        });
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    // Hacer llamado a endpoint para obtener lista de peticiones
    usuario = context.read<RegisterCubit>().state.usuarioDTO!;

    return Scaffold(
      body: FutureBuilder<List<ItemSolicitud>>(
        future: futureSolicitudes,
        builder: (context, lSolicitudesConvert) {
          if (lSolicitudesConvert.connectionState == ConnectionState.waiting) {
            return Center(child: CircularProgressIndicator());
          } else if (lSolicitudesConvert.hasError) {
            return Center(child: Text("Error al cargar datos"));
          } else if (!lSolicitudesConvert.hasData ||
              lSolicitudesConvert.data!.isEmpty) {
            return Center(child: Text("No hay solicitudes disponibles"));
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
                      "Turno: ${lSolicitudes[index].turno}",
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                    subtitle: Text(
                        "Fecha Solicitada: ${DateFormat('yyyy-MM-dd').format(DateTime.parse(lSolicitudes[index].fechaSolicitada))}"),
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
      floatingActionButton: Padding(
        padding: EdgeInsets.only(left: 30),
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.end,
          children: [
            FloatingActionButton(
              backgroundColor: Colors.red,
              child: Icon(Icons.delete, color: Colors.white),
              onPressed: () {
                setState(() {
                  List<ItemSolicitud> selectedItems =
                      lSolicitudes.where((item) => item.isChecked).toList();
                  if (selectedItems.isEmpty) {
                    mostrarAlerta(context,
                        'No se ha seleccionado ninguna solicitud a eliminar');
                  } else {
                    _showDialogEliminar(context, lSolicitudes);
                  }
                });
              },
            ),
            Spacer(),
            FloatingActionButton(
              backgroundColor: Colors.blue,
              child: Icon(Icons.add, color: Colors.white),
              onPressed: () async {
                await _showBottomAdicionar(context);
                setState(() {
                  futureSolicitudes =
                      convertirLista(sendHttpGetSolicitudes(usuario.id));
                });
              },
            ),
          ],
        ),
      ),
    );
  }

  //Boton para agregar una solicitud "+"
  Future<void> _showBottomAdicionar(BuildContext context) {
    return showModalBottomSheet(
        isScrollControlled: true,
        context: context,
        builder: (contexto) => Container(
              width: MediaQuery.of(context).size.width,
              height: 500,
              color: Colors.white,
              child: NuevaSolicitudWidget(usuario: usuario),
            ));
  }

  // boton alerta Eliminar
  Future<dynamic> _showDialogEliminar(
      BuildContext context, List<ItemSolicitud> lSolicitudes) async {
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            content: Text(
              "Desea eliminar la(s) solicitud(es)",
              style: TextStyle(fontSize: 20),
            ),
            actions: [
              TextButton(
                  child: Text(
                    "Cancelar",
                    style: TextStyle(fontSize: 15),
                  ),
                  onPressed: () {
                    Navigator.pop(context);
                  }),
              TextButton(
                  child: Text(
                    "Confirmar",
                    style: TextStyle(fontSize: 15),
                  ),
                  onPressed: () async {
                    for (var i = 0; i < lSolicitudes.length; i++) {
                      if (lSolicitudes[i].isChecked) {
                        await sendHttpPostEliminarSolicitudes(
                            lSolicitudes[i].cambioTurnoId);
                      }
                    }

                    setState(() {
                      futureSolicitudes =
                          convertirLista(sendHttpGetSolicitudes(usuario.id));

                      Navigator.pop(context);
                      mostrarAlerta(context, "Solicitud(es) eliminada(s)");
                    });
                  }),
            ],
            shape:
                RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
          );
        });
  }

  //Alerta generica
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

  Future<List<dynamic>> sendHttpGetSolicitudes(int userId) async {
    final response = await http
        .get(Uri.parse('http://localhost:8080/api/solicitudes?userId=$userId'));

    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception('Error al cargar los datos');
    }
  }

  Future<void> sendHttpPostEliminarSolicitudes(int idCambioTurno) async {
    final response = await http.post(Uri.parse(
        'http://localhost:8080/api/delete_sol?idCambioTurno=$idCambioTurno'));

    if (response.statusCode == 200) {
      print("Solicitud enviada exitosamente: ${response.body}");
    } else {
      throw Exception('Error al enviar la solicitud: ${response.statusCode}');
    }
  }

  Future<List<ItemSolicitud>> convertirLista(
      Future<List<dynamic>> futureLista) async {
    List<dynamic> lista = await futureLista;

    return lista.map((elemento) {
      if (elemento is Map<String, dynamic>) {
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
            : int.tryParse(elemento['cambioTurnoId'].toString()) ?? 0;

        return ItemSolicitud(
          descripcion,
          elemento['fechaSolicitada']?.toString() ?? 'N/A',
          elemento['isChecked'] is bool ? elemento['isChecked'] : false,
          cambioTurnoId,
        );
      } else {
        return ItemSolicitud(elemento.toString(), '', false, 0);
      }
    }).toList();
  }
}
