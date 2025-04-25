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

  final List<Widget> _screen = [
    Center(
      child: Text('PÁGINA INICIO'),
    ),
    Center(
      child: CalendarScreen(),
    ),
    Center(
      child: GestionTurnos(),
    ),
    Center(
      child: TravelScreen(),
    ),
    Center(child: PersonalprofileScreen())
  ]; // TODO VAMOS A CAMBIARLO POR WIDGETS O LOGICA DE NAVEGACIÓN

  void _itemTapped(int index) {
    setState(() {
      _position = index;
    });
  }

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
      body: _screen[_position],
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
          BottomNavigationBarItem(
              icon: Icon(Icons.car_crash_outlined), label: 'Viajes'),
          BottomNavigationBarItem(
              icon: Icon(Icons.person_outline),
              label: 'Perfil'), //Colors.lightBlueAccent
        ],
      ),
    );
  }
}
