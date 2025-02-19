import 'package:flutter/material.dart';
import 'package:front_end_gui/views/RegisterView_screen.dart';

class SignupForm extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return AllProfesionalFormField();
  }
}

class AllProfesionalFormField extends StatefulWidget {
  
   @override
   _AllProfesionalFormField createState() => _AllProfesionalFormField();
  
}

class _AllProfesionalFormField extends State<AllProfesionalFormField> {

  int _currentStep = 0; // Inicio parada

  void _nextStep() {
    if(_currentStep < 2) {
      setState(() {
        _currentStep++;
      });
    }
  }

  void _prevStep() {
    if (_currentStep > 0) {
      setState(() {
        _currentStep--;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Formulario de alta')),
      body: Column(
        children: [
          // Barra de progreso - Crear widget para ello
          Padding(
            padding: EdgeInsets.all(16.0),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: List.generate(3, (index) {

                return Expanded(
                  child: Container(
                    margin: EdgeInsets.symmetric(horizontal: 4),
                    height: 30,
                    decoration: BoxDecoration(
                      color: Colors.red,
                      borderRadius: BorderRadius.circular(5)
                    ),
                  ));
              })
            ),
          ),

          Expanded(
            child: IndexedStack(
              index: _currentStep,
              children: [
                Center(child: Text('Paso 1: Datos Personales')),
                Center(child: Text('Paso 2: Datos profesionales')),
                Center(child: Text('Paso 3: Crear cuenta')),
              ],
            )
          ),

          // Botones de navegación
          Padding(
            padding: EdgeInsets.all(16.0),
            child: Container(
              decoration: BoxDecoration(
                color: Colors.green
              ),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  if(_currentStep == 0)
                   ElevatedButton(onPressed: () {
                     Navigator.pop(
                      context,
                      MaterialPageRoute(builder: (context) => RegisterView()));
                   }, child: Text('Atrás')), // Poner enlace
                  if(_currentStep > 0)
                    ElevatedButton(onPressed: _prevStep, child: Text('Atrás')),
                    ElevatedButton(onPressed: _nextStep, child: Text('Siguiente'))
                  
                ],
              ),
            ),
          )
          
        ],
    ),
    );
  }
}

// Widget que contiene el sistema de progreso durante el formulario de alta


// Widget que contiene el formulario de alta con datos personales-profesionales-creación usuario
