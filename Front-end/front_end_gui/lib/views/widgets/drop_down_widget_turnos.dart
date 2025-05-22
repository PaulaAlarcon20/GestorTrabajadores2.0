import 'package:flutter/material.dart';

// Datos de ejemplo para lista Turnos
const List<Map<int, String>> list = [
  {0: 'Seleccione turno'},
  {1: 'Turno Noche'},
  {2: 'Turno Tarde'},
  {3: 'Turno Mañana'},
];

class DropDownWidgetTurnos extends StatefulWidget {
  final ValueNotifier<String> selectedValue = ValueNotifier("Seleccione turno");

  DropDownWidgetTurnos({Key? key}) : super(key: key);

  @override
  _DropDownWidgetTurnosState createState() => _DropDownWidgetTurnosState();

  String get valor => selectedValue.value;

  /// 🔹 Nuevo método para obtener el **valor entero** del turno seleccionado
  int get valorEntero {
    final entry = list.firstWhere(
      (map) => map.containsValue(selectedValue.value),
      orElse: () => {-1: "Error"}, // Devuelve -1 si no se encuentra el valor
    );
    return entry.keys.first; // Obtiene la clave `int` correspondiente
  }
}

class _DropDownWidgetTurnosState extends State<DropDownWidgetTurnos> {
  @override
  Widget build(BuildContext context) {
    return ValueListenableBuilder<String>(
      valueListenable: widget.selectedValue,
      builder: (context, value, child) {
        return DropdownButtonFormField<String>(
          value: value,
          icon: const Icon(Icons.arrow_downward),
          elevation: 16,
          style: const TextStyle(color: Colors.black),
          decoration: InputDecoration(
            labelStyle: TextStyle(fontFamily: "Quicksand", color: Colors.black),
            errorStyle: TextStyle(color: Colors.redAccent, fontSize: 16.0),
            hintText: 'Debe seleccionar un turno',
            border:
                OutlineInputBorder(borderRadius: BorderRadius.circular(5.0)),
          ),
          validator: (value) {
            if (value == null || value.isEmpty || value == "Seleccione turno") {
              return "Debe rellenar los campos";
            }
            return null;
          },
          isExpanded: true,
          borderRadius: BorderRadius.circular(10),
          onChanged: (String? newValue) {
            widget.selectedValue.value = newValue!;
          },
          items: list.map<DropdownMenuItem<String>>((Map<int, String> turno) {
            return DropdownMenuItem<String>(
              value: turno.values.first, // Accede al valor del mapa
              child: Text(turno.values.first),
            );
          }).toList(),
        );
      },
    );
  }
}
