import 'package:flutter/material.dart';
import 'package:front_end_gui/views/PersonalProfile_screen.dart';
import 'package:front_end_gui/views/Travel_screen.dart';

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
    Center(child: Text('PÁGINA INICIO'),),
    Center(child: Text('PÁGINA CALENDARIO'),),
    Center(child: TravelScreen(),),
    Center(child: PersonalprofileScreen())
  ]; // TODO VAMOS A CAMBIARLO POR WIDGETS O LOGICA DE NAVEGACIÓN

  void _itemTapped(int index){
    setState(() {
      _position = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      body: _screen[_position],
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _position,
        onTap: _itemTapped,
        items:  [
          BottomNavigationBarItem(icon: Icon(Icons.home), label: 'Inicio'),
          BottomNavigationBarItem(icon: Icon(Icons.calendar_month), label: 'Turnos'),
          BottomNavigationBarItem(icon: Icon(Icons.car_crash_outlined), label: 'Viajes'),
          BottomNavigationBarItem(icon: Icon(Icons.person_outline ), label: 'Perfil'), //Colors.lightBlueAccent
        ],

      )  ,
    );
  }

}