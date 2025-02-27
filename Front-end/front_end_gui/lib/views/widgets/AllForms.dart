import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:front_end_gui/views/cubit/SignUpCubit.dart';
import 'package:front_end_gui/views/widgets/Custom_Text_FormField.dart';
import 'package:front_end_gui/views/widgets/TelefonoInputFormatter.dart';

class PersonalFirstForm extends StatefulWidget {
  @override
  _PersonalFirstForm createState() => _PersonalFirstForm();
}

class _PersonalFirstForm extends State<PersonalFirstForm> {
  // const PersonalFirstForm({super.key});

  final _formKey = GlobalKey<FormState>();

  String? telefonoError;

  void validateTelefono(String value) {
    setState(() {
      if (value.isEmpty) {
        telefonoError = "Campo requerido";
      } else if (value.length < 5) {
        telefonoError = "Se necesitan más números";
      } else if (value.length >= 11) {
        telefonoError = "";
      }
    });
  }

  @override
  Widget build(BuildContext context) {

    final signUpCubit = context.watch<SignUpCubit>();

    final nombre = signUpCubit.state.nombre;
    final apellidos = signUpCubit.state.apellidos;
    final gmail = signUpCubit.state.gmail;
    final telefono = signUpCubit.state.telefono;

    

    return Form(
        key: _formKey,
          child: Padding(
              padding: const EdgeInsets.all(16.0),
            
              child: SingleChildScrollView(
                  child: Column(
                    
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      SizedBox(height: 20),
                      Container(),
                      Padding(
                        padding: const EdgeInsets.only(left: 20),
                        child: Text('Nombre',
                            style: TextStyle(
                                fontSize: 18, fontWeight: FontWeight.bold)),
                      ),
                      CustomTextFormfield(
                          hintText: 'Nombre',
                          onChanged: (value) {
                            signUpCubit.nombreChanged(value);
                            
                          },
                          erroreMessage: nombre.errorMessage,
                          obscureText: false
                      ),
                  
                      //SizedBox(height: 5),
                  
                      Padding(
                        padding: const EdgeInsets.only(left: 20, top: 15),
                        child: Text('Apellido(s)',
                            style: TextStyle(
                                fontSize: 18, fontWeight: FontWeight.bold)),
                      ),
                      CustomTextFormfield(
                        hintText: 'Apellidos',
                        onChanged: (value){
                          signUpCubit.apellidosChanged(value);
                        },
                        erroreMessage: apellidos.errorMessage,
                        obscureText: false),
                  
                      Padding(
                        padding: const EdgeInsets.only(left: 20,top: 15),
                        child: Text('Correo electrónico',
                            style: TextStyle(
                                fontSize: 18, fontWeight: FontWeight.bold)),
                      ),
                      CustomTextFormfield(
                        hintText: 'Usuario@gmail.com',
                        onChanged: (value){
                          signUpCubit.gmailChanged(value);
                        },
                        erroreMessage: gmail.errorMessage,
                        obscureText: false),
                  
                      Padding(
                        padding: const EdgeInsets.only(left: 20, top: 15),
                        child: Text(
                          'Teléfono',
                          style: TextStyle(
                              fontSize: 18, fontWeight: FontWeight.bold),
                        ),
                      ), 
                      CustomTextFormfield(
                        hintText: '000 000 000',
                        keyboardType: TextInputType.phone,
                        onChanged: (value){
                          signUpCubit.telefonoChanged(value);
                        },
                        erroreMessage: telefono.errorMessage,
                        inputFormatters:[PhoneFormatter()] ,
                        obscureText: false
                      ),
                      
                      // Botones de navegación


                    ],
                  ),
                ),
              ),
        );
  }
}



class ProfesionalFirstForm extends StatefulWidget {
  @override
  _ProfesionalFirstForm createState() => _ProfesionalFirstForm();
}

class _ProfesionalFirstForm extends State<ProfesionalFirstForm> {
  // const PersonalFirstForm({super.key});

  final _formKey = GlobalKey<FormState>();
  String?  _seleccionPreferencias;
  int? _disponibilidadHorasExtras;
  String? _seleccionPuesto;
  bool? _opcion1 = false;
  bool? _opcion2 = false;
  bool? _opcion3 = false;

  List<String> _puestoTrabajo = ['TCAE','Enfermero','Médico'];
  List<String> _preferenciasHorarias = ['TCAE','Enfermero','Médico'];
  @override
  Widget build(BuildContext context) {

    return Form(
        key: _formKey,
          child: Padding(
              padding: const EdgeInsets.all(16.0),
            
              child: SingleChildScrollView(
                  child: Column(
                    
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      SizedBox(height: 20),
                      Container(),
                      Padding(
                        padding: const EdgeInsets.only(left: 20),
                        child: Text('Centro de trabajo',
                            style: TextStyle(
                                fontSize: 18, fontWeight: FontWeight.bold)),
                      ),
                      CustomTextFormfield(
                        hintText: 'Centro de trabajo',
                        obscureText: false),
                  
                      //SizedBox(height: 5),
                  
                      Padding(
                        padding: const EdgeInsets.only(left: 20, top: 15),
                        child: Text('Puesto',
                            style: TextStyle(
                                fontSize: 18, fontWeight: FontWeight.bold)),
                      ),
                      DropdownButtonFormField<String>(
                          value: _seleccionPuesto,
                          hint: Text('Seleccione su puesto...', style: TextStyle(color: Colors.black)),
                          style: TextStyle(fontSize: 19, ),
                          isDense: false,
                          
                          alignment: AlignmentDirectional.bottomStart,
                          icon: Icon(Icons.arrow_drop_down, size: 35),
                          iconEnabledColor: Colors.black,
                          decoration: InputDecoration(
                            contentPadding: EdgeInsets.symmetric(horizontal: 20, vertical: 4),
                          ),
                          items: _puestoTrabajo.map((String option) {
                            return DropdownMenuItem<String>( 
                              value: option, 
                              child: Text(option, style: TextStyle(color: Colors.black,), )
                            );
                          }).toList(),

                          onChanged: (value) {
                            setState(() {
                              _seleccionPuesto = value;
                            });
                            print('Valor seleccionado en Dropdwon: $_seleccionPuesto');
                          },
                        ),        
                   
                          
                      Padding(
                        padding: const EdgeInsets.only(left: 20,top: 15),
                        child: Text('Localidad',
                            style: TextStyle(
                                fontSize: 18, fontWeight: FontWeight.bold)),
                      ),
                      CustomTextFormfield(hintText: 'Localidad',obscureText: false),
                  
                      Padding(
                        padding: const EdgeInsets.only(left: 20, top: 15),
                        child: Text(
                          'Preferencias horarias',
                          style: TextStyle(
                              fontSize: 18, fontWeight: FontWeight.bold),
                        ),
                      ), 
                      CheckboxListTile(
                        title: Text('Mañanas', style: TextStyle(fontSize: 18),),
                        value: _opcion1, 
                        onChanged: (bool? value) {
                          setState(() {
                            _opcion1 = value ?? false;
                          });
                        },      
                        controlAffinity: ListTileControlAffinity.leading,
                      ),
                      CheckboxListTile(
                        title: Text('Tardes', style: TextStyle(fontSize: 18),),
                        value: _opcion2, 
                        onChanged: (bool? value) {
                          setState(() {
                            _opcion2 =  value ?? false;
                          });
                        },
                        controlAffinity: ListTileControlAffinity.leading
                      ),
                      CheckboxListTile(
                        title: Text('Noches', style: TextStyle(fontSize: 18),),
                        value: _opcion3, 
                        onChanged: (bool? value) {
                          setState(() {
                            _opcion3 =  value ?? false;
                          });
                        },
                        controlAffinity: ListTileControlAffinity.leading
                      ),
      

                      Padding(
                        padding: const EdgeInsets.only(left: 20, top: 15),
                        child: Text(
                          'Disponibilidad horas extras',
                          style: TextStyle(
                              fontSize: 18, fontWeight: FontWeight.bold),
                        ),
                      ), 
                      Row(
                        children: [
                          Expanded(
                            child: RadioListTile(
                              title: Text('Sí', style: TextStyle(fontSize: 18),),
                              value: 1, 
                              groupValue: _disponibilidadHorasExtras, 
                              onChanged: (value) {
                                setState(() {
                                  _disponibilidadHorasExtras = value;
                                  print('Valor seleccionado: $_disponibilidadHorasExtras');
                                });
                              },
                            ),
                          ),
                          Expanded(
                            child: RadioListTile(
                            title: Text('No', style: TextStyle(fontSize: 18),),
                            value: 0, 
                            groupValue: _disponibilidadHorasExtras, 
                            onChanged: (value) {
                              setState(() {
                                _disponibilidadHorasExtras = value;
                                print('Valor seleccionado: $_disponibilidadHorasExtras');
                              });
                            },
                            ),
                          ),
                        ],
                      ),
                    
                    ],
                  ),
                ),
              ),
        );
  }
}


class CreateUserForm extends StatefulWidget {
  @override
  _CreateUserForm createState() => _CreateUserForm();
}

class _CreateUserForm extends State<CreateUserForm> {
  // const PersonalFirstForm({super.key});

  final _formKey = GlobalKey<FormState>();


  @override
  Widget build(BuildContext context) {



    return Form(
        key: _formKey,
          child: Padding(
              padding: const EdgeInsets.all(16.0),
            
              child: SingleChildScrollView(
                  child: Column(
                    
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      SizedBox(height: 14),

                      Padding(
                        padding: const EdgeInsets.only(left: 20, top: 15),
                        child: Text('Elige una contraseña',
                            style: TextStyle(
                                fontSize: 18, fontWeight: FontWeight.bold)),
                      ),
                      
                      CustomTextFormfield(
                        hintText: 'Tu contraseña',
                        obscureText: false),
                    // TODO Mostrar validación desde el inicio en gris e icono y si esta bien, que se ponga verde, interrogación si no cumple y check verde si
                    ],
                  ),
                ),
              ),
        );
  }
}
