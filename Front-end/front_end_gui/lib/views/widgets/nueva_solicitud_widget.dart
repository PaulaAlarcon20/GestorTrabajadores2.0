import 'package:flutter/material.dart';
import 'package:front_end_gui/views/widgets/date_picker_widget_turnos.dart';
import 'package:front_end_gui/views/widgets/drop_down_widget_turnos.dart';

class NuevaSolicitudWidget extends StatelessWidget {
  const NuevaSolicitudWidget({super.key});

//formulario crear nueva solicitud
  @override
  Widget build(BuildContext context) {
    //validadores
    final formKey = GlobalKey<FormState>();

    return Padding(
        padding: EdgeInsets.all(8),
        child: Form(
          key: formKey,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                "Nueva solicitud",
                style: TextStyle(
                  color: Colors.black,
                  fontSize: 24,
                ),
              ),
              Datepickerwidgetturnos(),
              SizedBox(
                height: 16,
              ),
              DropDownWidgetTurnos(),
              SizedBox(
                height: 16,
              ),
              Center(
                child: ElevatedButton(
                    onPressed: () {
                      if (formKey.currentState!.validate()) {
                        Navigator.pop(context);
                      }
                    },
                    style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.blue,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(10))),
                    child: Text(
                      "Enviar",
                      style: TextStyle(
                          color: Colors.white,
                          fontSize: 16,
                          fontWeight: FontWeight.bold),
                    )),
              )
            ],
          ),
        ));
  }
}
