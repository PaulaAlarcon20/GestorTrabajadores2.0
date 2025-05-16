import 'package:flutter/material.dart';
import '../gestionTurnos/Solicitudes.dart';

class ListaPeticionesWidget extends StatefulWidget {
  const ListaPeticionesWidget({super.key});

  @override
  State<ListaPeticionesWidget> createState() => _ListaPeticionesWidgetState();
}

class _ListaPeticionesWidgetState extends State<ListaPeticionesWidget> {
  // Hacer llamado a endpoint para obtener lista de peticiones

  List<ItemSolicitud> lSolicitudes = [
    ItemSolicitud('Elemento 1', false),
    ItemSolicitud('Elemento 2', false),
    ItemSolicitud('Elemento 3', false),
    ItemSolicitud('Elemento 4', false),
    ItemSolicitud('Elemento 5', false),
    ItemSolicitud('Elemento 6', false),
    ItemSolicitud('Elemento 7', false),
  ];

  @override
  void initState() {
    super.initState();
  }

  //bool peticion = true;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView.builder(
          itemCount: 10,
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
                  subtitle: Text("Fecha 23-04-2025"),
                  leading: Icon(
                    Icons.calendar_month,
                    color: Colors.blue,
                  ),
                  trailing: IconButton(
                    onPressed: () => aceptarPeticion(context),
                    icon: Icon(Icons.check),
                  ),
                  onTap: () {
                    // print(name);
                  },
                ));
          }),
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
}
