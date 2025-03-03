import 'package:bloc/bloc.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';

import 'package:front_end_gui/views/cubit/SignUpState2.dart';
import 'package:front_end_gui/views/infraestructure/inputs/disponibilidadHorasExtras.dart';
import 'package:front_end_gui/views/infraestructure/inputs/inputs.dart';
import 'package:front_end_gui/views/infraestructure/inputs/multiInput.dart';
import 'package:front_end_gui/views/infraestructure/inputs/dropDwonPuesto.dart';
import 'package:front_end_gui/views/infraestructure/inputs/preferenciasRadioButton.dart';


/// Clase RegisterLoginCubit, en donde vamos a desarrollar los métodos de la validación
class SignUpCubit2 extends Cubit<SignUpState2> {
  SignUpCubit2() : super(SignUpState2(isValid2: false));

  void setValidState(bool isValid2) {
    emit(state.copyWith(isValid2: isValid2));
  }

  void setValidState3(bool isValid3) {
    emit(state.copyWith(isValid3: isValid3));
  }

  void onSubmit3() {



    emit(
      state.copyWith(
        formStatus3: FormStatus3.validating,

        isValid3: Formz.validate([
          state.password

          
        ]),
      ),

      
    );
  }
    
  

  void onSubmit2() {
    emit(
      state.copyWith(
        formStatus2: FormStatus.validating,
        centroDeTrabajo: MultiInput.dirty(value: state.centroDeTrabajo.value),
        puesto: DropDownPuesto.dirty(value: state.puesto.value),
        localidad: MultiInput.dirty(value: state.localidad.value),
        preferenciasHorarias: PreferenciasRadioButton.dirty(value: state.preferenciasHorarias.value),
        disponibilidadHorasExtras:  DisponibilidadHorasExtras.dirty(value: state.disponibilidadHorasExtras.value),
        
        // Cuando hagas el Submit de todo, lo incluyes
       
        isValid2: Formz.validate([
          state.centroDeTrabajo,
          state.puesto,
          state.localidad,
          state.preferenciasHorarias,
          state.disponibilidadHorasExtras,

          
        ]),

        
      )
    );
    print('Envio formulario -> centro: ${state.centroDeTrabajo.value} / puesto: ${state.puesto.value} / localidad: ${state.localidad.value} / preferencias: ${state.preferenciasHorarias.value} /disponibilidad: ${state.disponibilidadHorasExtras.value}');
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
    print('MARCADO RADIO BUTTON PUESTO -> $puesto');
    emit(
      state.copyWith(
        puesto: puestoNewValor,
        isValid2: Formz.validate([puestoNewValor, state.centroDeTrabajo, state.localidad, state.disponibilidadHorasExtras, state.preferenciasHorarias])
      )
    );
  }

  
  void localidadChanged(String localidad) {
    final localidadNewValor = MultiInput.dirty(value: localidad);
    print('SE EJECUTA CHANGED ${state.localidad}');
    emit(state.copyWith(
      localidad: localidadNewValor,
      isValid2: Formz.validate([localidadNewValor, state.centroDeTrabajo, state.puesto,  state.disponibilidadHorasExtras,  state.preferenciasHorarias])
    ));
  }

  void preferenciaHorariaChanged(String preferencias){
    print('PREFERENCIA HORARIA MARCADA -> $preferencias');
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
    print('SE EJECUTA EL METODO DEL READIO BUTTON');
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
