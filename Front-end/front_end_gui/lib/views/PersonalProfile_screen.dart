import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:front_end_gui/views/cubit/RegisterCubit.dart';
import 'package:front_end_gui/views/cubit/SignUpCubit2.dart';

class PersonalprofileScreen extends StatefulWidget {
  @override
  _PersonalprofileScreenState createState() => _PersonalprofileScreenState();
}

class _PersonalprofileScreenState extends State<PersonalprofileScreen> {
  @override
  Widget build(BuildContext context) {

   // final SignUpCubit2 = context.read<SignUpCubit2>();
   final usuario = context.watch<RegisterCubit>().state.usuarioDTO;
   //final nombre = usuario?.nombre;


    if(usuario == null){ 
      return const Center(child: CircularProgressIndicator());
    }




    return Scaffold(
        body: Padding(
      padding: const EdgeInsets.symmetric(horizontal: 32, vertical: 36),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [Text('Nombre del usuario: ${usuario.nombre}'), 
          Text('Prueba variable nombre:  ${usuario.nombre}')
        ],
      ),
    ));
  }
}
