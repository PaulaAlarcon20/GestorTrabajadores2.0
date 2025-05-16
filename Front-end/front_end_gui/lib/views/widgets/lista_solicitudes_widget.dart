import 'package:flutter/material.dart';
import 'package:front_end_gui/views/widgets/nueva_solicitud_widget.dart';
import '../gestionTurnos/Solicitudes.dart';

class ListaSolicitudesWidget extends StatefulWidget {
  const ListaSolicitudesWidget({super.key});

  @override
  State<ListaSolicitudesWidget> createState() => _ListaSolicitudesWidgetState();
}

class _ListaSolicitudesWidgetState extends State<ListaSolicitudesWidget> {
  // Hacer llamado a endpoint para obtener lista de Solicitudes

  List<ItemSolicitud> lSolicitudes = [
    ItemSolicitud('Elemento 1', false),
    ItemSolicitud('Elemento 2', false),
    ItemSolicitud('Elemento 3', false),
    ItemSolicitud('Elemento 4', false),
  ];

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: ListView.builder(
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
            }),

        //Botones de acción Listado
        floatingActionButton: Padding(
          padding: EdgeInsets.only(left: 30),
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.end,
            children: [
              //Boton de Eliminar
              FloatingActionButton(
                  backgroundColor: Colors.red,
                  child: Icon(
                    Icons.delete,
                    color: Colors.white,
                  ),
                  onPressed: () {
                    // Obtener los valores seleccionados
                    List<ItemSolicitud> selectedItems =
                        lSolicitudes.where((item) => item.isChecked).toList();

                    if (!selectedItems.isEmpty)
                      _showDialogEliminar(context);
                    else
                      mostrarAlerta(context,
                          'No se ha seleccionado ninguna solicitud a eliminar');
                  }),
              Expanded(child: Container()),
              Spacer(),
              //Boton de Adicionar
              FloatingActionButton(
                  backgroundColor: Colors.blue,
                  child: Icon(
                    Icons.add,
                    color: Colors.white,
                  ),
                  onPressed: () {
                    _showBottomAdicionar(context);
                  }),
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
                  onPressed: () {
                    Navigator.pop(context);
                    mostrarAlerta(context, "Solicitud(es) eliminada(s)");
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
