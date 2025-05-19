import 'package:bloc/bloc.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';
import 'package:front_end_gui/views/Home_screen.dart';
import 'package:front_end_gui/views/cubit/SignUpState2.dart';
import 'package:front_end_gui/views/infraestructure/inputs/disponibilidadHorasExtras.dart';
import 'package:front_end_gui/views/infraestructure/inputs/inputs.dart';
import 'package:front_end_gui/views/infraestructure/inputs/multiInput.dart';
import 'package:front_end_gui/views/infraestructure/inputs/dropDwonPuesto.dart';
import 'package:front_end_gui/views/infraestructure/inputs/preferenciasRadioButton.dart';
import 'package:front_end_gui/views/infraestructure/inputs/telefonoInput.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'dart:developer';

/// Clase RegisterLoginCubit, en donde vamos a desarrollar los métodos de la validaciónbool avance;
class SignUpCubit2 extends Cubit<SignUpState2> {

  SignUpCubit2() : super(const SignUpState2(
  isValid: false,
  isValid2: false,
  isValid3: false,
  avance: false
  ));

  

  void setValidState0(bool isValid) {
    emit(state.copyWith(isValid: isValid));
  }

  void setValidState(bool isValid2) {
    emit(state.copyWith(isValid2: isValid2));
  }

  void setValidState3(bool isValid3) {
    emit(state.copyWith(isValid3: isValid3));
  }

  

  void onSubmit(int parte) {
     // TODO logica para que se meta a realizar cosas por cada form

      log('---> Validación formulario realizada -> ${parte}');
      log("---- ANTES EJECUCIÓN OnSubmit-----");
      log("isValid: ${state.isValid}");
      log("isValid2: ${state.isValid2}");
      log("isValid3: ${state.isValid3}");


      final newState = state.copyWith(
        nombre: MultiInput.dirty(value: state.nombre.value),
        apellidos: MultiInput.dirty(value: state.apellidos.value),
        gmail: GmailInput.dirty(value: state.gmail.value),
        telefono: TelefonoInput.dirty(value: state.telefono.value),
        centroDeTrabajo: MultiInput.dirty(value: state.centroDeTrabajo.value),
        puesto: DropDownPuesto.dirty(value: state.puesto.value),
        localidad: MultiInput.dirty(value: state.localidad.value),
        preferenciasHorarias: PreferenciasRadioButton.dirty(value: state.preferenciasHorarias.value),
        disponibilidadHorasExtras:  DisponibilidadHorasExtras.dirty(value: state.disponibilidadHorasExtras.value),
      );

      if(parte == 1){
        print("SE METE EN 1");
          emit(
          state.copyWith(
            formStatus2: FormStatus.validating,
            nombre: MultiInput.dirty(value: state.nombre.value),
            apellidos: MultiInput.dirty(value: state.apellidos.value),
            gmail: GmailInput.dirty(value: state.gmail.value),
            telefono: TelefonoInput.dirty(value: state.telefono.value),
   
                  
            isValid: Formz.validate([
                newState.nombre,
                newState.apellidos,
                newState.gmail,
                newState.telefono
            ]),

            )
            );
      } else if(parte == 2){
          print("SE METE EN 2");
          emit(
          state.copyWith(
            formStatus2: FormStatus.validating,
            nombre: MultiInput.dirty(value: state.nombre.value),
            apellidos: MultiInput.dirty(value: state.apellidos.value),
            gmail: GmailInput.dirty(value: state.gmail.value),
            telefono: TelefonoInput.dirty(value: state.telefono.value),
            centroDeTrabajo: MultiInput.dirty(value: state.centroDeTrabajo.value),
            puesto: DropDownPuesto.dirty(value: state.puesto.value),
            localidad: MultiInput.dirty(value: state.localidad.value),
            preferenciasHorarias: PreferenciasRadioButton.dirty(value: state.preferenciasHorarias.value),
            disponibilidadHorasExtras:  DisponibilidadHorasExtras.dirty(value: state.disponibilidadHorasExtras.value),
   
                  
            isValid: Formz.validate([
                newState.nombre,
                newState.apellidos,
                newState.gmail,
                newState.telefono
            ]),


            isValid2: Formz.validate([
              newState.centroDeTrabajo,
              newState.puesto,
              newState.localidad,
              newState.preferenciasHorarias,
              newState.disponibilidadHorasExtras,
            ]),
            )
            );
      } else if(parte == 3){
        print("SE METE EN 3");
        log('---> Validación formulario 3/3 realizada');
        emit(
          state.copyWith(
            formStatus3: FormStatus3.validating,

            isValid3: Formz.validate([
              state.nombre,
              state.apellidos,
              state.gmail,
              state.telefono,
              state.centroDeTrabajo,
              state.puesto,
              state.localidad,
              state.preferenciasHorarias,
              state.disponibilidadHorasExtras,
              state.password
            ]),
          ),
        );

        sendHttpPost();

      }

      log("---- DESPUES EJECUCIÓN OnSubmit-----");
      log("isValid: ${state.isValid}");
      log("isValid2: ${state.isValid2}");
      log("isValid3: ${state.isValid3}");
      log('VALORES RECODIGOS 1º PANTALLA: nombre -> ${state.nombre.value} ${state.apellidos.value} / ${state.gmail.value} / ${state.telefono.value} - ${state.puesto.value} ');
      log('VALORES RECODIGOS 2º PANTALLA: Centro -> ${state.centroDeTrabajo.value} ${state.puesto.value} / ${state.localidad.value} / ${state.preferenciasHorarias.value} - ${state.disponibilidadHorasExtras.value} ');
    
  }

  void onSubmit3(int partOfForm) {


    
  }

  Future<void> sendHttpPost() async{ 
    log('*** Se ejecuta sendHttpPost ***');
    try {
      //1- Almacenar URL api
      final url = Uri.parse('http://10.0.2.2:8080/api/usuarios/SignUp'); //TODO va a haber que cambiar el mapping del futuro @get

      //2- Crear map que contiene datos formulario 2 y 3
      final Map<String, dynamic> formData = {
        "nombre" : state.nombre.value,
        "apellido" : state.apellidos.value,
        "email" : state.gmail.value,
        "contrasena" : state.password.value,
        "telefono" : state.telefono.value,
        "centroTrabajo" : state.centroDeTrabajo.value,
        "puesto" : state.puesto.value,
        //jornadaID PENSAR LUEGO
        "localidad" : state.localidad.value,
        "preferenciasHorarias" : state.preferenciasHorarias.value,
        "disponibilidadHorasExtras" : state.disponibilidadHorasExtras.value,
        "inicio_sesion" : false,
        'mensaje': ""
        // vemos como se comporta las barras bajas
      };

      //3- Petición
      final response = await http.post(
        url,
        headers: {"Content-Type": "application/json"} ,
        body: jsonEncode(formData) ,
      );

      
      
      log('------ JSON A ENVIAR AL BACK-END ------');
      formData.forEach((clave, valor){ 
        log('* $clave : $valor *');
      });
      log('---------------------------------------');
      
      //4- Comprobar estado de petición
      bool avance;
      if( response.statusCode == 200 || response.statusCode == 201){
        avance = false;
        final data = jsonDecode(response.body);

        log('Datos enviados correctamente: ${response.statusCode}  - valor de avance $avance');
        emit(state.copyWith(avance: true));
        log('${data.nombre} - ${data.apellidos} - ${data.puesto} - ${data.telefono} - ');


      } else {
        
        final data = jsonDecode(response.body);
        final mensaje = data['mensaje'];
        final error = data['error'];
        avance = true;

        

        emit(state.copyWith(avance: false));

        log('statusCode HTTP: ${response.statusCode} - valor de avance $avance - mensaje: $mensaje + $error');
        log('cuerpo HTTP: ${response.statusCode}');

      }
      


    } catch (e, stackTrace) {
      log('Error en HTTP POST envio parte 2/3 y 3/3 formulario de alta ->  ${e.toString()}');
      log('StackTrace: $stackTrace');
    }
  }

    // Parte 2/3 del formulario
  void centroTrabajoChanged(String centroDeTrabajo){
    final centroTrabajoNewValor = MultiInput.dirty(value: centroDeTrabajo);
    
    emit(
      state.copyWith(
        centroDeTrabajo: centroTrabajoNewValor,
        isValid2: Formz.validate([centroTrabajoNewValor, state.localidad, state.puesto, state.preferenciasHorarias, state.disponibilidadHorasExtras])
      )
    );
  }

  // PARTE 1 DEL FORMULARIO
  void nombreChanged(String  nombre){
      final nombreNewValor = MultiInput.dirty(value: nombre);
      print("EJECUCION VALOR NOMBRENEWVALOR: $nombreNewValor");
      emit(
        state.copyWith(
          nombre: nombreNewValor,
          isValid: Formz.validate([nombreNewValor, state.apellidos, state.gmail, state.telefono]) // le vamos a tener que enviar todo los campos porque necesita saber si todos los campos son validos
        )
        
      );
    }

    void apellidosChanged(String apellidos){
      final apellidosNewValor = MultiInput.dirty(value: apellidos);

      emit(
        state.copyWith(
          apellidos: apellidosNewValor,
          isValid: Formz.validate([apellidosNewValor, state.nombre, state.gmail, state.telefono])
        )
      );
    }

    void gmailChanged(String email){
      final gmailNewValor = GmailInput.dirty(value: email);

      emit(
        state.copyWith(
          gmail: gmailNewValor,
          isValid: Formz.validate([gmailNewValor, state.nombre, state.apellidos, state.telefono])
        )
      );
    }

    void telefonoChanged(String telefono){
      final telefonoNewValor = TelefonoInput.dirty(value: telefono);

      emit(
        state.copyWith(
          telefono: telefonoNewValor,
          isValid: Formz.validate([telefonoNewValor, state.nombre, state.apellidos, state.gmail])
        )
      );
    }


  void puestoChanged(String puesto){
    final puestoNewValor = DropDownPuesto.dirty(value: puesto);
    log('MARCADO RADIO BUTTON PUESTO -> $puesto');
    emit(
      state.copyWith(
        puesto: puestoNewValor,
        isValid2: Formz.validate([puestoNewValor, state.centroDeTrabajo, state.localidad, state.disponibilidadHorasExtras, state.preferenciasHorarias])
      )
    );
  }

  
  void localidadChanged(String localidad) {
    final localidadNewValor = MultiInput.dirty(value: localidad);
    log('SE EJECUTA CHANGED ${state.localidad}');
    emit(state.copyWith(
      localidad: localidadNewValor,
      isValid2: Formz.validate([localidadNewValor, state.centroDeTrabajo, state.puesto,  state.disponibilidadHorasExtras,  state.preferenciasHorarias])
    ));
 }

  void preferenciaHorariaChanged(String preferencias){
    log('PREFERENCIA HORARIA MARCADA -> $preferencias');
    final preferenciasHorariasNewValor = PreferenciasRadioButton.dirty(value: preferencias);
    
    emit(
      state.copyWith(
        preferenciasHorarias: preferenciasHorariasNewValor,
        isValid2: Formz.validate([preferenciasHorariasNewValor, state.centroDeTrabajo, state.puesto , state.localidad, state.disponibilidadHorasExtras])
      )
    );

  }

  void disponibilidadHorasExtrasChanged(int disponibilidadHorasExtras){
    final disponibilidadhorasExtrasNewValor = DisponibilidadHorasExtras.dirty(value: disponibilidadHorasExtras);
    log('SE EJECUTA EL METODO DEL READIO BUTTON');
    emit(
      state.copyWith(
        disponibilidadHorasExtras: disponibilidadhorasExtrasNewValor,
        isValid2: Formz.validate([disponibilidadhorasExtrasNewValor, state.puesto, state.localidad, state.centroDeTrabajo,  state.preferenciasHorarias])
      )
    );

  }

  void passwordChanged(String password){
    final passwordNewValor = PasswordInput.dirty(value: password);

    emit(
      state.copyWith(
        password: passwordNewValor,
        isValid3:  Formz.validate([passwordNewValor]) // TODO validador nuevo puede hacer falta, vamos a probar
      )
    );

  }

}
