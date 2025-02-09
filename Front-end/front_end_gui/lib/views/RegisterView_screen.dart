import 'package:flutter/material.dart';
import 'package:front_end_gui/views/widgets/Custom_Text_FormField.dart';

/// Pantalla creada para mostrar al usuario el login a la aplicación
/// Vista formada por widget con orden en forma de columna, en el cual se le
/// incrustan 'cajas' y el widget dinámico RegisterForm
class RegisterView extends StatelessWidget {
 
  //const RegisterView({super.key});

  @override
  Widget build(BuildContext context) {
    return  Padding(
      padding: const EdgeInsets.symmetric(horizontal: 10),
      child: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.end,
          children: const[
            SizedBox(height: 52),
            FlutterLogo(size: 50),
            RegisterForm(),
            SizedBox(height: 20,)
          ],
        ),
      ),
    );
  }
}



class RegisterForm extends StatefulWidget {
  const RegisterForm({super.key});

  @override
  State<RegisterForm> createState() => _RegisterFormState();
}

/// Widget dinámico que contiene un formulario, en donde se hace uso de un
/// widget personalizado llamado CustomTextFormField, el cual recibe por parametros
/// los parametros necesarios para las validaciones (label, onChange, hiontText, validator, obscureText)
class _RegisterFormState extends State<RegisterForm> {

  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  String email = "";
  String password = "";


  @override
  Widget build(BuildContext context) {
    return Form(
      key: _formKey,
      child: Column(
        children: [

          const SizedBox(height: 8),
          CustomTextFormfield(
            label: 'Correo electrónico',
            onChanged: (value) => email = value,
            hintText: 'usuario@gmail.com', 
            validator: (value) {
              if(value == null || value.isEmpty) return 'Campo requerido'; 
              if(value.trim().isEmpty) return 'Campo requerido'; 

              final emailRegExp = RegExp(
                r'^[\w-\.]+@([\w-]+\.)+[\w]{2,4}$',
              );
              if(!emailRegExp.hasMatch(value!)) return 'Formato incorrecto';
 
              return null;
            },
            obscureText: false,),
          const SizedBox(height: 8),

          CustomTextFormfield(
            label: 'Contraseña', 
            onChanged: (value) => password = value,
            hintText: 'Contraseña', 
            validator: (value) {
              if (value!.length < 9) return 'Más de 9 caracteres';
              return  null;
            },
            obscureText: true,),
          const SizedBox(height: 8),


          FilledButton.tonalIcon(
            onPressed: () {

              final isValid = _formKey.currentState!.validate();
              if (!isValid) return;

              print('$email, $password');
            },
            icon:  const Icon(Icons.save),
            label: Text('Iniciar sesión'),
            )
        ],
      ));
  }
}