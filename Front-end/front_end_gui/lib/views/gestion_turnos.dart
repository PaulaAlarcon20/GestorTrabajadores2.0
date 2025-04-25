import 'package:flutter/material.dart';
import 'package:front_end_gui/views/widgets/lista_peticiones_widget.dart';
import 'package:front_end_gui/views/widgets/lista_solicitudes_widget.dart';

class GestionTurnos extends StatefulWidget {
  const GestionTurnos({super.key});

  @override
  State<GestionTurnos> createState() => _GestionTurnosState();
}

class _GestionTurnosState extends State<GestionTurnos> {
  int currentPageIndex = 0;

  @override
  Widget build(BuildContext context) {
    final ThemeData theme = Theme.of(context);
    return Scaffold(
      bottomNavigationBar: NavigationBar(
        onDestinationSelected: (int index) {
          setState(() {
            currentPageIndex = index;
          });
        },
        indicatorColor: Colors.blue,
        selectedIndex: currentPageIndex,
        destinations: const <Widget>[
          NavigationDestination(
            selectedIcon: Icon(Icons.accessibility_new_outlined),
            icon: Icon(Icons.accessibility_new_outlined),
            label: 'Peticiones',
          ),
          NavigationDestination(
            icon: Badge(child: Icon(Icons.account_box_outlined)),
            label: 'Solicitudes',
          ),
        ],
      ),
      body: <Widget>[
        /// Peticiones
        Card(
          shadowColor: Colors.transparent,
          margin: const EdgeInsets.all(8.0),
          child: SizedBox.expand(
            child: Center(
              child: ListaPeticionesWidget(),
            ),
          ),
        ),

        /// Solicitudes
        Card(
          shadowColor: Colors.transparent,
          margin: const EdgeInsets.all(8.0),
          child: SizedBox.expand(
            child: Center(
              child: ListaSolicitudesWidget(),
            ),
          ),
        ),
      ][currentPageIndex],
    );
  }

  void _volver() {
    print("Acción de Volver");
  }
}


/*
  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 2,
      child: Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.blue,
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
}
*/