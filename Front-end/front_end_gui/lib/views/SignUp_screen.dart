import 'dart:io';

import 'package:flutter/material.dart';
import 'package:front_end_gui/views/RegisterView_screen.dart';
import 'package:front_end_gui/views/cubit/SignUpCubit.dart';
import 'package:front_end_gui/views/cubit/SignUpCubit2.dart';
import 'package:front_end_gui/views/widgets/AllForms.dart';
import 'package:flutter_bloc/flutter_bloc.dart';




class SignupForm extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return AllProfesionalFormField();
  }
}

class AllProfesionalFormField extends StatefulWidget {
  const AllProfesionalFormField({super.key});
  @override
  _AllProfesionalFormField createState() => _AllProfesionalFormField();
}

class _AllProfesionalFormField extends State<AllProfesionalFormField> {
  int _currentStep = 0; // Inicio parada  TODO Vamos a jugar con esta variable 
  int _pasos = 1;

  
  void _nextStep() {
    if (_currentStep < 2) {
      setState(() {
        _currentStep++;
        _pasos++;
      });
    }
  }

  void _prevStep() {
    if (_currentStep >= 0) {
      setState(() {
        _currentStep--;
        _pasos--;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.of(context).size.width;
    double screenHeight = MediaQuery.of(context).size.height;
    
    final signUpCubit = context.watch<SignUpCubit>();
    final signUpCubit2 = context.watch<SignUpCubit2>();

    final bool stateForm1 = signUpCubit.state.isValid == true; 
    final bool stateForm2 = signUpCubit2.state.isValid2 == true;
    //final signUpCubit = context.watch<SignUpCubit>(); 
    //final bool stateForm = signUpCubit.state.isValid == true;
    final bool statePuesto = signUpCubit2.state.puesto.value.isEmpty;

    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
            icon: Icon(Icons.arrow_back_ios_new),
            onPressed: () {
              print(
                  'Contador currentStep -> $_currentStep / contador pasos $_pasos');
              _prevStep();
              if (_pasos == 0) {
                Navigator.pop(context,
                    MaterialPageRoute(builder: (context) => RegisterView()));
              }
            }),
        title: Container(
          decoration: BoxDecoration(
              //maincolor: Colors.red
              ),
          child: Padding(
            padding: EdgeInsets.only(top: 15),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                Text(
                  'Crear cuenta',
                  style: TextStyle(fontWeight: FontWeight.bold),
                ),
                Text(
                  'Paso $_pasos de 3',
                  style: TextStyle(
                      fontSize: 17,
                      color: const Color.fromARGB(255, 128, 128, 128)),
                )
              ],
            ),
          ),
        ),
        centerTitle: true,
      ),
      body: Column(
          children: [
            // Barra de progreso - Crear widget para ello
            // TODO Tienes la opción de sustituir el IndexedStack por el PageView
            Expanded(
                child: IndexedStack(
              index: _currentStep,
              children: [
                PersonalFirstForm(),
                ProfesionalFirstForm(),
                CreateUserForm(),
              ],
            )),

            Padding(
            padding: EdgeInsets.all(6.0),
            child: Container(
              decoration: BoxDecoration(
                //color: Colors.green
              ),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.start,
                children: [

                  if(_currentStep == 0)
                    Expanded(child: FilledButton(
                      onPressed: stateForm1
                      ? (){
                         print('Pasa pora quí 1  _currentState -> $_currentStep *** $_pasos');
                        signUpCubit.onSubmit();
                        print('Pasa pora quí 2');
                        _nextStep();
                         print('Pasa pora quí 3');
                      }
                      : null, 
                      child: Text('Siguiente', style: TextStyle( fontSize: screenWidth * 0.045),))
                    )
                  else if(_currentStep == 1)
                   Expanded(
                     child: FilledButton(
                      onPressed: stateForm2 && !statePuesto // Si estado true y puesto rellenado
                      ? () {
                        signUpCubit2.onSubmit();
                        _nextStep();
                    
                      }
                      : null,
                      child: Text('Siguiente', style: TextStyle( fontSize: screenWidth * 0.045),)
                    ),
                   ) 
                   else if(_currentStep == 2)
                   Expanded(
                    child: FilledButton(
                      onPressed: () { print('Vas a crear un usuario');}, 
                      child: Text('Finalizar', style: TextStyle( fontSize: screenWidth * 0.045),))
                    )
                  
                  
                  
                ],
              ),
            ),
          )
      
          ],
        ),
    );
  }
}
