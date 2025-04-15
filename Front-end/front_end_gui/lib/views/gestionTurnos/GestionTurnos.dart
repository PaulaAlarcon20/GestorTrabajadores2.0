import 'package:flutter/material.dart';
import 'package:front_end_gui/views/widgets/ListaPeticionesWidget.dart';
import 'package:front_end_gui/views/widgets/ListaSolicitudesWidget.dart';

class GestionTurnos extends StatelessWidget {
  const GestionTurnos({super.key});

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 2,
      child: Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.blue,
          title: Text(
            "Gestión turno trabajadores",
            style: TextStyle(color: Colors.white, fontSize: 17),
          ),
          actions: <Widget>[
            IconButton(
              icon: Icon(Icons.notification_add),
              color: Colors.white,
              onPressed: _verNotificaciones,
            )
          ],
          leading: IconButton(
            icon: Icon(Icons.arrow_back),
            color: Colors.white,
            onPressed: _volver,
          ),
          bottom: const TabBar(
            indicatorColor: Colors.white,
            labelColor: Colors.white,
            tabs: [
              Tab(
                  icon: Icon(Icons.accessibility_new_outlined),
                  text: "Peticiones"),
              Tab(
                icon: Icon(Icons.account_box_outlined),
                text: "Solicitudes",
              ),
            ],
          ),
        ),
        body: const TabBarView(children: <Widget>[
          Center(
            child: ListaPeticionesWidget(),
          ),
          Center(
            child: ListaSolicitudesWidget(),
          )
        ]),
      ),
    );
  }

//ACCION PARA EL BOTON DE NOTIFICACIONES
  void _verNotificaciones() {
    print("Ver Notificaciones");
  }

  void _volver() {
    print("Acción de Volver");
  }
}
