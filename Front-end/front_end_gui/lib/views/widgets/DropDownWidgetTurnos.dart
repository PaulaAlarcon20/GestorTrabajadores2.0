import 'package:flutter/material.dart';

// Datos de ejemplo para lista Turnos
const List<String> list = <String>[
  'Seleccione turno',
  'Turno Mañana',
  'Turno Tarde',
  'Turno Noche',
  'Turno Especial'
];

class DropDownWidgetTurnos extends StatefulWidget {
  @override
  _DropDownWidgetState createState() => _DropDownWidgetState();
}

class _DropDownWidgetState extends State<DropDownWidgetTurnos> {
  String dropdownValue = list.first;

  @override
  Widget build(BuildContext context) {
    return DropdownButtonFormField<String>(
      value: dropdownValue,
      icon: const Icon(Icons.arrow_downward),
      elevation: 16,
      style: const TextStyle(color: Colors.black),
      decoration: InputDecoration(
          labelStyle: TextStyle(
            fontFamily: "Quicksand",
            color: Colors.black,
          ),
          errorStyle: TextStyle(color: Colors.redAccent, fontSize: 16.0),
          hintText: 'Debe seleccionar un turno',
          border: OutlineInputBorder(borderRadius: BorderRadius.circular(5.0))),
      isExpanded: true,
      borderRadius: BorderRadius.circular(10),
      onChanged: (String? value) {
        if (value == "Seleccione turno") {}
        setState(() {
          dropdownValue = value!;
        });
      },
      items: list.map<DropdownMenuItem<String>>((String value) {
        return DropdownMenuItem<String>(value: value, child: Text(value));
      }).toList(),
    );
  }
}
