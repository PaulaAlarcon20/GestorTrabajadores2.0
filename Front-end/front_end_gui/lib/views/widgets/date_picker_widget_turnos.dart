import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class Datepickerwidgetturnos extends StatefulWidget {
  final ValueNotifier<String> fechaNotifier = ValueNotifier("Seleccione fecha");

  @override
  _DatePickerWidgetState createState() => _DatePickerWidgetState();

  String get valor => fechaNotifier.value;
}

class _DatePickerWidgetState extends State<Datepickerwidgetturnos> {
  final TextEditingController _fechaCambioController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return ValueListenableBuilder<String>(
      valueListenable: widget.fechaNotifier,
      builder: (context, value, child) {
        return TextFormField(
          readOnly: true,
          controller: _fechaCambioController,
          decoration: InputDecoration(
            border: OutlineInputBorder(borderRadius: BorderRadius.circular(20)),
            hintText: "Seleccione la fecha deseada",
            suffixIcon: Icon(Icons.calendar_today),
          ),
          validator: (value) =>
              value == null || value.isEmpty || value == "Seleccione fecha"
                  ? "Debe seleccionar una fecha"
                  : null,
          onTap: () => _selectDate(context),
        );
      },
    );
  }

  Future<void> _selectDate(BuildContext context) async {
    DateTime? fechaSeleccionada = await showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime(2025),
      lastDate: DateTime(2030),
    );

    if (fechaSeleccionada != null) {
      String fechaFormateada =
          DateFormat('dd/MM/yyyy').format(fechaSeleccionada);
      setState(() {
        _fechaCambioController.text = fechaFormateada;
        widget.fechaNotifier.value =
            fechaFormateada; // Actualiza el valor accesible desde fuera
      });
    }
  }
}
