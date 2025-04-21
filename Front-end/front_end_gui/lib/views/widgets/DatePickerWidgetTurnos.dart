import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class Datepickerwidgetturnos extends StatefulWidget {
  @override
  _DatePickerWidgetState createState() => _DatePickerWidgetState();
}

class _DatePickerWidgetState extends State<Datepickerwidgetturnos> {
  FormFieldValidator<String>? validator;

  @override
  final TextEditingController _fechaCambioController = TextEditingController();
  String _fecha = "";

  Widget build(BuildContext context) {
    // TODO: implement build
    return TextFormField(
      enableInteractiveSelection: false,
      controller: _fechaCambioController,
      decoration: InputDecoration(
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(20),
        ),
        hintText: "Seleccione la fecha deseada",
        suffixIcon: Icon(Icons.calendar_today),
      ),
      validator: (value) {
        if (value == null || value.isEmpty) {
          return "Debe rellenar los campos";
        }
        return null;
      },
      onTap: () {
        FocusScope.of(context).requestFocus(new FocusNode());
        _selectDate(context);

        if (_fechaCambioController.text.isEmpty) {
          _fechaCambioController.text = "La fecha es obligatoria";
        }
      },
    );
  }

  _selectDate(BuildContext context) async {
    DateTime? fechaSeleccionada = await showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime(2025),
      lastDate: DateTime(2030),
    );

    if (fechaSeleccionada != null) {
      setState(() {
        _fecha = DateFormat('dd/MM/yyyy').format(fechaSeleccionada);
        _fechaCambioController.text = _fecha;
      });
    }
  }
}
