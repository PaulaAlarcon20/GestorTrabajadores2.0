import 'package:flutter/material.dart';
import 'package:front_end_gui/views/widgets/NuevaSolicitudWidget.dart';
import 'package:front_end_gui/views/widgets/checkbox_widget.dart';

class ListaSolicitudesWidget extends StatefulWidget {
  const ListaSolicitudesWidget({super.key});

  @override
  State<ListaSolicitudesWidget> createState() => _ListaSolicitudesWidgetState();
}

class _ListaSolicitudesWidgetState extends State<ListaSolicitudesWidget> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    //validadores
    final _formkey = GlobalKey<FormState>();

    return Scaffold(
        body: ListView.builder(
            itemCount: 1,
            itemBuilder: (context, index) {
              index += 1;

              return Container(
                margin: EdgeInsets.all(8),
                padding: EdgeInsets.all(4),
                decoration: BoxDecoration(color: Colors.blue.shade100),
                child: ListTile(
                  title: Text(
                    "Turno $index",
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                  subtitle: Text("Fecha dia-mes-año"),
                  leading: Icon(
                    Icons.calendar_month,
                    color: Colors.blue,
                  ),
                  trailing: CheckboxWidget(),
                ),
              );
            }),

        //Botones de acción Listado
        floatingActionButton: Padding(
          padding: EdgeInsets.only(left: 30),
          child: Row(
            key: _formkey,
            crossAxisAlignment: CrossAxisAlignment.end,
            children: [
              FloatingActionButton(
                  backgroundColor: Colors.red,
                  child: Icon(
                    Icons.delete,
                    color: Colors.white,
                  ),
                  onPressed: () {
                    _showDialogEliminar(context);
                  }),
              Expanded(child: Container()),
              Spacer(),
              FloatingActionButton(
                  backgroundColor: Colors.blue,
                  child: Icon(
                    Icons.add,
                    color: Colors.white,
                  ),
                  onPressed: () {
                    _showBottomAdicionar(context);
                  }),

              //Boton para Eliminar
            ],
          ),
        ));
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
              child: NuevaSolicitudWidget(),
            ));
  }

// boton alerta Eliminar
  Future<dynamic> _showDialogEliminar(BuildContext context) async {
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            content: Text(
              "Desea eliminar la solicitud",
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
                  onPressed: () {
                    Navigator.pop(context);
                    mostrarAlerta(context, "Solicitud eliminada");
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
}
