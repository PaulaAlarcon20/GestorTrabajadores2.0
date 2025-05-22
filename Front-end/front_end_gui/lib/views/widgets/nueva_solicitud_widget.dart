import 'package:flutter/material.dart';
import 'package:front_end_gui/views/widgets/date_picker_widget_turnos.dart';
import 'package:front_end_gui/views/widgets/drop_down_widget_turnos.dart';
import 'package:http/http.dart' as http;
import 'package:intl/intl.dart';

class NuevaSolicitudWidget extends StatelessWidget {
  const NuevaSolicitudWidget({super.key});

//formulario crear nueva solicitud
  @override
  Widget build(BuildContext context) {
    final formKey = GlobalKey<FormState>();
    final Datepickerwidgetturnos selectorFechaController =
        Datepickerwidgetturnos();
    final DropDownWidgetTurnos listaTurnosController = DropDownWidgetTurnos();

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
            selectorFechaController,
            SizedBox(
              height: 16,
            ),
            listaTurnosController,
            SizedBox(
              height: 16,
            ),
            SizedBox(height: 16),
            Center(
              child: ElevatedButton(
                onPressed: () async {
                  if (formKey.currentState!.validate()) {
                    await sendHttpPostSolicitud(
                        listaTurnosController.valorEntero,
                        selectorFechaController.valor);
                    Navigator.pop(context);
                  }
                },
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.blue,
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(10)),
                ),
                child: Text(
                  "Enviar",
                  style: TextStyle(
                      color: Colors.white,
                      fontSize: 16,
                      fontWeight: FontWeight.bold),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  Future<void> sendHttpPostSolicitud(int jornadaId, String fecha) async {
    final usuarioId = 1; //Obtener el Valor de Usuario de Sesión
    DateTime fechaDT = DateFormat("dd/MM/yyyy").parse(fecha);
    String fechaISO = DateFormat("yyyy-MM-dd")
        .format(fechaDT); // Formatear la fecha en "yyyy-MM-dd"

    final url = Uri.parse(
        'http://localhost:8080/api/new_sol?usuarioId=$usuarioId&jornadaId=$jornadaId&fechaSolicitada=${Uri.encodeComponent(fechaISO)}');

    final response = await http.post(
      url,
      headers: {
        'Content-Type': 'application/json',
      },
    );

    if (response.statusCode == 200) {
      print("Solicitud enviada exitosamente: ${response.body}");
    } else {
      throw Exception('Error al enviar la solicitud: ${response.statusCode}');
    }
  }
}
