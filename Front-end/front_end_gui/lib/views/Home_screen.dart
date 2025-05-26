import 'package:flutter/material.dart';
import 'package:front_end_gui/views/CalendarScreen.dart';
import 'package:front_end_gui/views/PersonalProfile_screen.dart';
import 'package:front_end_gui/views/Travel_screen.dart';
import 'package:front_end_gui/views/gestion_turnos.dart';
import 'package:front_end_gui/views/widgets/notificaciones.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return HomeScreenGo();
  }
}

class HomeScreenGo extends StatefulWidget {
  @override
  _HomeScreenGoState createState() => _HomeScreenGoState();
}

class _HomeScreenGoState extends State<HomeScreenGo> {
  int _position = 0;

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.blue,
        title: Text(
          "Gestión turnos",
          style: TextStyle(color: Colors.white, fontSize: 17),
        ),
        centerTitle: true,
        actions: <Widget>[
          IconButton(
            icon: Icon(Icons.notification_add),
            color: Colors.white,
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => Notificaciones(),
                ),
              );
            },
          )
        ],
      ),
      body: getScreen(),
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _position,
        onTap: _itemTapped,
        items: [
          BottomNavigationBarItem(icon: Icon(Icons.home), label: 'Inicio'),
          BottomNavigationBarItem(
              icon: Icon(Icons.calendar_month), label: 'Turnos'),
          BottomNavigationBarItem(
              icon: Icon(Icons.app_registration_rounded),
              label: 'Gestión Turnos'),
          /*BottomNavigationBarItem(
              icon: Icon(Icons.car_crash_outlined), label: 'Viajes'),*/
          BottomNavigationBarItem(
              icon: Icon(Icons.person_outline),
              label: 'Perfil'), //Colors.lightBlueAccent
        ],
      ),
    );
  }

  void _itemTapped(int index) {
    setState(() {
      _position = index;
    });
  }

  Widget getTextHomeWidget() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        Text(
          'Bienvenid@',
          textAlign: TextAlign.center,
          style: TextStyle(
            fontSize: 24,
            fontWeight: FontWeight.bold,
            color: Colors.blue,
          ),
        ),
        SizedBox(height: 15),
        Text(
          'Facilitamos la gestión de turnos para trabajadores sanitarios, permitiéndote coordinar horarios de manera eficiente y sin complicaciones.',
          textAlign: TextAlign.center,
          style: TextStyle(fontSize: 18, color: Colors.black87),
        ),
        SizedBox(height: 10),
        Text(
          'Consulta tus asignaciones, solicita cambios y mantente organizado con nuestra plataforma intuitiva.',
          textAlign: TextAlign.center,
          style: TextStyle(fontSize: 16, color: Colors.black54),
        ),
        SizedBox(height: 20),
      ],
    );
  } // TODO VAMOS A CAMBIARLO POR WIDGETS O LOGICA DE NAVEGACIÓN

  Widget getScreen() {
    switch (_position) {
      case 0:
        return Center(child: getTextHomeWidget());
      case 1:
        return CalendarScreen();
      case 2:
        return GestionTurnos();
      case 3:
        return PersonalprofileScreen();
      default:
        return Center(child: Text("Pantalla no encontrada"));
    }
  }
}
