import 'package:bloc/bloc.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';
import 'package:front_end_gui/views/cubit/SignUpState2.dart';
import 'package:front_end_gui/views/infraestructure/inputs/disponibilidadHorasExtras.dart';
import 'package:front_end_gui/views/infraestructure/inputs/inputs.dart';
import 'package:front_end_gui/views/infraestructure/inputs/multiInput.dart';
import 'package:front_end_gui/views/infraestructure/inputs/dropDwonPuesto.dart';
import 'package:front_end_gui/views/infraestructure/inputs/preferenciasRadioButton.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'dart:developer';

/// Clase RegisterLoginCubit, en donde vamos a desarrollar los métodos de la validación
class SignUpCubit2 extends Cubit<SignUpState2> {
  SignUpCubit2() : super(SignUpState2(isValid2: false));

  void setValidState(bool isValid2) {
    emit(state.copyWith(isValid2: isValid2));
  }

  void setValidState3(bool isValid3) {
    emit(state.copyWith(isValid3: isValid3));
  }

  void onSubmit2(int partOfForm) {
    
    if(partOfForm == 0){
      log('---> Validación formulario 2/3 realizada');
      emit(
      state.copyWith(
        formStatus2: FormStatus.validating,
        centroDeTrabajo: MultiInput.dirty(value: state.centroDeTrabajo.value),
        puesto: DropDownPuesto.dirty(value: state.puesto.value),
        localidad: MultiInput.dirty(value: state.localidad.value),
        preferenciasHorarias: PreferenciasRadioButton.dirty(value: state.preferenciasHorarias.value),
        disponibilidadHorasExtras:  DisponibilidadHorasExtras.dirty(value: state.disponibilidadHorasExtras.value),
        
        isValid2: Formz.validate([
          state.centroDeTrabajo,
          state.puesto,
          state.localidad,
          state.preferenciasHorarias,
          state.disponibilidadHorasExtras,
        ]),
      )
      );
    } 
  }

  void onSubmit3(int partOfForm) {
    log('---> Validación formulario 3/3 realizada');
    emit(
      state.copyWith(
        formStatus3: FormStatus3.validating,

        isValid3: Formz.validate([
          state.password
        ]),
      ),
    );

    sendHttpPost();
  }

  Future<void> sendHttpPost() async{ 
    log('*** Se ejecuta sendHttpPost ***');
    try {
      //1- Almacenar URL api
      final url = Uri.parse('https://tu-api.com');

      //2- Crear map que contiene datos formulario 2 y 3
      final Map<String, dynamic> formPart2and3 = {
        "CentroTrabajo" : state.centroDeTrabajo.value,
        "Puesto" : state.puesto.value,
        "Localidad" : state.localidad.value,
        "PreferenciasHorarias" : state.preferenciasHorarias.value,
        "DisponibilidadHorasExtras" : state.disponibilidadHorasExtras.value,
        "contraseña" : state.password.value
      };

      //3- Petición
      final response = await http.post(
        url,
        headers: {"Content-Type": "application/json"} ,
        body: jsonEncode(formPart2and3) ,
      );
      
      log('------ JSON A ENVIAR AL BACK-END ------');
      formPart2and3.forEach((clave, valor){ 
        log('* $clave : $valor *');
      });
      log('---------------------------------------');
      //4- Comprobar estado de petición
      if( response.statusCode == 200 || response.statusCode == 201){
        log('Datos enviados correctamente: ${response.statusCode}');
      } else{
        log('statusCode HTTP: ${response.statusCode}');
      }
      //5-


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
