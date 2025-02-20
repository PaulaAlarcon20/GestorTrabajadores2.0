import 'package:flutter/material.dart';
import 'package:front_end_gui/views/RegisterView_screen.dart';
import 'package:front_end_gui/views/widgets/PersonalForm.dart';



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


  int _currentStep = 0; // Inicio parada
  int _pasos = 1;
  void _nextStep() {
    if(_currentStep < 2) {
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


    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          icon: Icon(Icons.arrow_back_ios_new),
          onPressed: () {
            print('Contador currentStep -> $_currentStep / contador pasos $_pasos');
            _prevStep();
            if(_pasos == 0){
              Navigator.pop(
              context,
              MaterialPageRoute(builder: (context) => RegisterView())
            );
            }
          }

        ),
         
        title: Container(
          decoration: BoxDecoration(
            //maincolor: Colors.red
          ),
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              Text('Crear cuenta', style: TextStyle(fontWeight: FontWeight.bold),),
              Text('Paso $_pasos de 3', style: TextStyle(fontSize: 17, color: const Color.fromARGB(255, 128, 128, 128)),)
            ],
          ),
        ),
        
        centerTitle: true,

      ),
      body: Column(
        children: [
          // Barra de progreso - Crear widget para ello

          Expanded(
            child: IndexedStack(
              index: _currentStep,
              children: [
                PersonalFirstForm(),
                ProfesionalSecondForm(),
                CreateUserForm(),
              ],
            )
          ),

          // Botones de navegación
          Padding(
            padding: EdgeInsets.all(6.0),
            child: Container(
              decoration: BoxDecoration(
                //color: Colors.green
              ),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  if(_currentStep == -1)
                   ElevatedButton(onPressed: () {
                     Navigator.pop(
                      context,
                      MaterialPageRoute(builder: (context) => RegisterView()));
                   }, child: Text('Atrás')), // Poner enlace
                  if(_currentStep >= 0)
                    Expanded(child: FilledButton(
                      onPressed: _nextStep, 
                      child: Text('Siguiente', style: TextStyle( fontSize: screenWidth * 0.045),)))
                    
                  
                ],
              ),
            ),
          )
          
        ],
    ),
    );
  }
}


