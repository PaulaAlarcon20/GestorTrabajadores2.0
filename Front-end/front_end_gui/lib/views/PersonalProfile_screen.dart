import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:front_end_gui/services/PeticionesHTTP.dart';
import 'package:front_end_gui/views/RegisterView_screen.dart';
import 'package:front_end_gui/views/cubit/RegisterCubit.dart';
import 'package:front_end_gui/views/cubit/SignUpCubit2.dart';
import 'package:front_end_gui/views/widgets/buildInfoCard.dart';

class PersonalprofileScreen extends StatefulWidget {
  @override
  _PersonalprofileScreenState createState() => _PersonalprofileScreenState();
}

class _PersonalprofileScreenState extends State<PersonalprofileScreen> {
  @override
  Widget build(BuildContext context) {

   // final SignUpCubit2 = context.read<SignUpCubit2>();
   final usuario = context.watch<RegisterCubit>().state.usuarioDTO;
   Peticioneshttp peticiones = new Peticioneshttp();
   //final nombre = usuario?.nombre;


    if(usuario == null){ 
      return const Center(child: CircularProgressIndicator());
    }

    return Scaffold(
        body: Padding(
      padding: const EdgeInsets.symmetric(horizontal: 32, vertical: 36),
      child: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            
            CircleAvatar(
              radius: 50,
              backgroundImage: AssetImage('assets/images/perfil2.jpeg'),
              backgroundColor: Colors.grey[200],
            ),
            const SizedBox(height: 10),
            
            Text('${usuario.nombre} ${usuario.apellido}', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)), 

            buildInfoCard('Puesto:', usuario.puesto),
            buildInfoCard('Centro:', usuario.centroTrabajo),
            buildInfoCard('Localidad:', usuario.localidad),
            buildInfoCard('Teléfono:', usuario.telefono),

            Spacer(),
            
            SizedBox(
              width: double.infinity,
              child: ElevatedButton(
                style: TextButton.styleFrom(
                  backgroundColor: Color(0xFFD32F2F),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(12)
                  )),
                  onPressed: () {
                    
                    showDialog(
                      context: context, 
                      builder: (BuildContext context) {
                        
                        return AlertDialog(
                          title: Text("¿Estás seguro de que quieres cerrar sesión?", style: TextStyle(fontSize: 20)),
                          actions: <Widget> [
                            TextButton(onPressed:  () async {
                             
                              bool resultado = await peticiones.cerrarSesion(email: usuario.email, inicioSesion: false);

                              if(resultado == false){
                                Navigator.pushReplacement(
                                  context,
                                  MaterialPageRoute(builder: (context) => RegisterView())
                                );
                                
                              }
                            }, 
                            child: Text("Cerrar sesión"))
                          ],
                          
                        );
                      });
                  }, 
                  child: Text("Cerrar sesión", style: TextStyle(color: Colors.white))
              ),
            )
            
          ],
        ),
      ),
    ));
  }
}
