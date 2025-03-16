import 'package:flutter/material.dart';
import 'package:front_end_gui/views/RegisterView_screen.dart';
import 'package:front_end_gui/views/cubit/SignUpCubit.dart';
import 'package:front_end_gui/views/cubit/SignUpCubit2.dart';
import 'package:front_end_gui/views/cubit/SignUpState2.dart';
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

  bool? changedStateSpinner;
  bool ? isLoading3;

  void _nextStep() {
    if (_currentStep < 2) {
      setState(() {
        _currentStep++;
        _pasos++;

        if(_pasos == 3){
          changedStateSpinner = true;
          isLoading3 = false;
        }
        
        print('Estado de _pasos ($_pasos) es -> $changedStateSpinner  y _currentStep $_currentStep');
      });
    }
  }

  void _prevStep() {
    if (_currentStep >= 0) {
      setState(() {
        _currentStep--;
        _pasos--;
        
        if(_pasos == 3){
          changedStateSpinner = false;
        }
        
        print('BACKEstado de _pasos ($_pasos) es -> $changedStateSpinner');
        // Desarrollar lógica para que cuando de atrás en el ultimo paso del spinner cambiar valor a false de is Loading3
      });
    }
  }
  
  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.of(context).size.width;
    //double screenHeight = MediaQuery.of(context).size.height;
    
    final signUpCubit = context.watch<SignUpCubit>();
    final signUpCubit2 = context.watch<SignUpCubit2>();
    

    isLoading3 = signUpCubit2.state.formStatus3 == FormStatus3.valid;
    
    final bool stateForm1 = signUpCubit.state.isValid == true; 
    final bool stateForm2 = signUpCubit2.state.isValid2 == true;
    final bool stateForm3 = signUpCubit2.state.isValid3 == true;

    

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
            padding: EdgeInsets.only(top: 15, bottom: 15),
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
                         signUpCubit.onSubmit(0);
                         _nextStep();
                      }
                      : null, 
                      child: Text('Siguiente', style: TextStyle( fontSize: screenWidth * 0.045),))
                    )
                  else if(_currentStep == 1)
                   Expanded(
                     child: FilledButton(
                      onPressed: stateForm2 && !statePuesto // Si estado true y puesto rellenado
                      ? () {
                        signUpCubit2.onSubmit2(0);
                        _nextStep();
                        isLoading3 = false;
                      }
                      : null,
                      child: Text('Siguiente', style: TextStyle( fontSize: screenWidth * 0.045),)
                    ),
                   ) 
                   else if(_currentStep == 2 && !changedStateSpinner!)
                   Expanded(
                    child: FilledButton.tonalIcon(
                      
                      onPressed: stateForm3 
                      ? () {
                        signUpCubit.onSubmit(1);
                        signUpCubit2.onSubmit2(1);
                        signUpCubit2.onSubmit3(1);
                      }
                      : null, 
                      label: isLoading3!
                      ? const CircularProgressIndicator() 
                      : Text('Finalizar', style: TextStyle( fontSize: screenWidth * 0.045),)
                      ),
                      
                    )
                  else if(_currentStep == 2 && changedStateSpinner!)
                  Expanded(
                    child: FilledButton.tonalIcon(
                      
                      onPressed: stateForm3 
                      ? () {
                        signUpCubit.onSubmit(1);
                        signUpCubit2.onSubmit2(1);
                        signUpCubit2.onSubmit3(1);
                      }
                      : null, 
                      label: isLoading3!
                      ? const CircularProgressIndicator() 
                      : Text('Finalizar', style: TextStyle( fontSize: screenWidth * 0.045),)
                      ),
                      
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
