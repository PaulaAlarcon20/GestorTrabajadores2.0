import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:front_end_gui/views/cubit/register_login_cubit.dart';
import 'package:front_end_gui/views/infraestructure/inputs/gmail.dart';
import 'package:front_end_gui/views/widgets/Custom_Text_FormField.dart';

/// Pantalla creada para mostrar al usuario el login a la aplicación
/// Vista formada por widget con orden en forma de columna, en el cual se le
/// incrustan 'cajas' y el widget dinámico RegisterForm
class RegisterView extends StatelessWidget {
 
  const RegisterView({super.key});

  @override
  Widget build(BuildContext context) {
    return  Scaffold(

      //appBar: AppBar(title: const Text('Nuevo usuario'),),
      body: BlocProvider( // Inyecta el cubic en el árbol de widgets y permite que los hijos accedan
        create:  (context) => RegisterLoginCubit(),
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 10),
          child: SingleChildScrollView( // Define que hijos pueden acceder al cubit
            child: Column(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                SizedBox(height: 90),
                //FlutterLogo(size: 40),
                Image.asset(
                  'assets/images/IconsApp.png',
                  width: 50,
                  height: 50,
                  fit: BoxFit.cover,
                ),
                RegisterForm(),
                SizedBox(height: 20),
               
              ],
            ),
          ),
        ),
      )
    );
  }


}


class RegisterForm extends StatelessWidget {
  const RegisterForm({super.key});

  @override
  Widget build(BuildContext context) {

    final registerLoginCubit = context.watch<RegisterLoginCubit>(); // Escucha los cambio sy se reconstruye automaticamente
    final email = registerLoginCubit.state.email;
    final password = registerLoginCubit.state.password;

    return Form(

      child: Column(
        children: [
          const SizedBox(height: 8),
          CustomTextFormfield(
            //erroreMessage: 'Este campo necesita ayuda',
            label: 'Correo electrónico',
            onChanged: (value) {
              registerLoginCubit.emailChanged(value);
            },
            //hintText: 'usuario@gmail.com', 
            erroreMessage: email.errorMessage,
            obscureText: false,),
          const SizedBox(height: 8),

          CustomTextFormfield(
            label: 'Contraseña', 
            onChanged: (value) {
              registerLoginCubit.passwordChanged(value);
            },
            //hintText: 'Contraseña', 
            erroreMessage: password.errorMessage, // En este widget no va a haber validación password
            obscureText: true,),
          const SizedBox(height: 8),


          FilledButton.tonalIcon(
            onPressed: () {


              registerLoginCubit.onSubmit();
            },
            //icon:  const Icon(Icons.save),
            label: Text('Iniciar sesión'),
            )
        ],
      ));
  }
}