import 'package:flutter/material.dart';
import 'package:front_end_gui/views/widgets/Custom_Text_FormField.dart';  
import 'package:front_end_gui/views/widgets/TelefonoInputFormatter.dart';  



class PersonalFirstForm extends StatefulWidget {
  @override
  _PersonalFirstForm createState() => _PersonalFirstForm();
}



class _PersonalFirstForm extends State<PersonalFirstForm> {
 // const PersonalFirstForm({super.key});

 final _formKey = GlobalKey<FormState>();
 
 final TextEditingController _nameController = TextEditingController();
 final TextEditingController _secondNameController = TextEditingController();
 final TextEditingController _emailController = TextEditingController();
 final TextEditingController _telefonoController = TextEditingController();

  @override
  Widget build(BuildContext context) {

    return Form(
       child: Padding(
         padding: const EdgeInsets.all(16.0),
         child: SingleChildScrollView(
           child: Column(
             crossAxisAlignment: CrossAxisAlignment.start,
             children: [
                SizedBox(height: 20),
                Padding(
                  padding: const EdgeInsets.only(left: 20),
                  child: Text('Nombre', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold )),
                ),
                TextFormField(
                  controller: _nameController,
                  decoration: InputDecoration(
                    
                    helperText: '',
                    hintText: 'Nombre',
                    contentPadding: EdgeInsets.only(left: 20),
                    hintStyle: TextStyle(fontSize: 20)
                  ),
                  style: TextStyle(fontSize: 20),
                  
                ),
           
                //SizedBox(height: 5),
           
                Padding(
                  padding: const EdgeInsets.only(left: 20),
                  child: Text('Apellido(s)', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold )),
                ),
                TextFormField(
                  controller: _secondNameController,
                  decoration: InputDecoration(
                    
                    helperText: '',
                    hintText: 'Apellidos',
                    contentPadding: EdgeInsets.only(left: 20),
                    hintStyle: TextStyle(fontSize: 20)
                    
                  ),
                  style: TextStyle(fontSize: 20),

                ),
           
                Padding(
                  padding: const EdgeInsets.only(left: 20),
                  child: Text('Correo electrónico', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
                ),
                TextFormField(
                  controller: _emailController,
                  decoration: InputDecoration(
                    
                    helperText: '',
                    hintText: 'usuario@gmail.com',
                    contentPadding: EdgeInsets.only(left: 20),
                    hintStyle: TextStyle(fontSize: 20),
                  ),
                  style: TextStyle(fontSize: 20),

                ),
           
                Padding(
                  padding: const EdgeInsets.only(left: 20),
                  child: Text('Teléfono', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),),
                ),
                TextFormField(
                  controller: _telefonoController,
                  decoration: InputDecoration(
                    
                    helperText: '',
                    hintText: '000 000 000',
                    contentPadding: EdgeInsets.only(left: 20),
                    hintStyle: TextStyle(fontSize: 20),
                    
                  ),
                  style: TextStyle(fontSize: 20),
                  keyboardType: TextInputType.number,
                  inputFormatters: [PhoneFormatter()],
                  
                ),
                // Cogeria la longuitud de la cadena
                // Condición If y en cada 3 espacio
             ],
           ),
         )
         )
       );
  }
}

class ProfesionalSecondForm extends StatelessWidget {
 // const PersonalFirstForm({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(top: 10),
      child: Column(
        children: [
          Text('Paso 2 formulario')
        ],
      ),    
    );
  }
}
class CreateUserForm extends StatelessWidget {
 // const PersonalFirstForm({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(top: 10),
      child: Column(
        children: [
          Text('Paso 3 formulario')
        ],
      ),    
    );
  }
}