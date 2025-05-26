import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:front_end_gui/services/UsuarioDTO.dart';
import 'package:front_end_gui/views/calendario/ItemPeticionAceptada.dart';
import 'package:front_end_gui/views/cubit/RegisterCubit.dart';
import 'package:front_end_gui/views/widgets/Turn_detail_screen.dart';
import 'package:intl/intl.dart';
import 'package:table_calendar/table_calendar.dart';
import 'package:http/http.dart' as http;

class CalendarWidget extends StatefulWidget {
  const CalendarWidget({super.key});

  @override
  State<CalendarWidget> createState() => _CalendarWidgetState();
}

class _CalendarWidgetState extends State<CalendarWidget> {
  late DateTime _selectedDay;
  late DateTime _focusedDay;
  late CalendarFormat _calendarFormat;
  late Map<DateTime, String> _turnos = {}; // Ahora se llenará dinámicamente
  late UsuarioDTO usuario;

  @override
  void initState() {
    super.initState();

    _selectedDay = DateTime.now();
    _focusedDay = DateTime.now();
    _calendarFormat = CalendarFormat.month;

    usuario = context.read<RegisterCubit>().state.usuarioDTO!;

    // Cargar turnos desde el servidor
    cargarTurnos();
  }

  Future<void> cargarTurnos() async {
    List<ItemPeticionAceptada> peticiones =
        await convertirLista(sendHttpGetSolicitudes(usuario.id));

    setState(() {
      // _turnos = {
      //   for (var peticion in peticiones)
      //     DateTime.parse(peticion.fecha): peticion.turnoDesc
      // };

      // 🔹 Simulación de turnos con horarios específicos (Año 2025)
      _turnos = {
        DateTime.utc(2025, 5, 22): "Turno de mañana: 08:00 - 16:00",
        DateTime.utc(2025, 5, 29): "Turno de tarde: 16:00 - 00:00",
        DateTime.utc(2025, 5, 1): "Turno de noche: 00:00 - 08:00",
        DateTime.utc(2025, 5, 10): "Turno de mañana: 08:00 - 16:00",
        DateTime.utc(2025, 6, 3): "Día libre",
        DateTime.utc(2025, 6, 8): "Turno de tarde: 16:00 - 00:00",
        DateTime.utc(2025, 6, 7): "Turno de noche: 00:00 - 08:00",
      };
    });
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return Column(
      children: [
        TableCalendar(
          locale: 'es_ES',
          firstDay: DateTime.utc(2020, 1, 1),
          lastDay: DateTime.utc(2100, 12, 31),
          focusedDay: _focusedDay,
          calendarFormat: _calendarFormat,
          selectedDayPredicate: (day) => isSameDay(_selectedDay, day),
          onDaySelected: (selectedDay, focusedDay) {
            setState(() {
              _selectedDay = selectedDay;
              _focusedDay = focusedDay;
            });
          },
          headerStyle: HeaderStyle(
            formatButtonVisible: false,
            titleCentered: true,
            titleTextStyle: TextStyle(
              color: theme.colorScheme.onSurface,
              fontWeight: FontWeight.bold,
              fontSize: 18,
            ),
          ),
          calendarStyle: CalendarStyle(
            todayDecoration: BoxDecoration(
              color: theme.colorScheme.secondary,
              shape: BoxShape.circle,
            ),
            selectedDecoration: BoxDecoration(
              color: Colors.red,
              shape: BoxShape.circle,
            ),
            defaultTextStyle: TextStyle(color: theme.colorScheme.onSurface),
            weekendTextStyle: TextStyle(color: theme.colorScheme.error),
          ),
          calendarBuilders: CalendarBuilders(
            dowBuilder: (context, day) {
              final text = DateFormat.EEEE('es_ES').format(day);
              return Center(
                child: Text(
                  text.substring(0, 3).toUpperCase(),
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                    color: theme.colorScheme.onSurface,
                  ),
                ),
              );
            },
            markerBuilder: (context, date, events) {
              String? turno = _turnos[date];
              if (turno != null) {
                return Positioned(
                  bottom: 5,
                  child: Container(
                    width: 8,
                    height: 8,
                    decoration: BoxDecoration(
                      color: turno.contains("mañana")
                          ? Colors.blue // 🔵 Turno de mañana
                          : turno.contains("tarde")
                              ? Colors.orange // 🟠 Turno de tarde
                              : turno.contains("noche")
                                  ? Colors.purple // 🟣 Turno de noche
                                  : Colors.green, // 🟢 Día libre
                      shape: BoxShape.circle,
                    ),
                  ),
                );
              }
              return null;
            },
          ),
        ),
        const SizedBox(height: 20),
        Text(
          _turnos[_selectedDay] ?? "Sin turno asignado",
          style: TextStyle(
            fontSize: 16,
            fontWeight: FontWeight.bold,
            color: _turnos[_selectedDay] == "Día libre"
                ? Colors.green
                : theme.colorScheme.onSurface,
          ),
        ),
        const SizedBox(height: 20),
        ElevatedButton(
          onPressed: () {
            String turno = _turnos[_selectedDay] ?? "Sin turno asignado";
            Navigator.push(
              context,
              MaterialPageRoute(
                builder: (context) => TurnoDetallesScreen(
                  selectedDay: _selectedDay,
                  turno: turno,
                ),
              ),
            );
          },
          style: ElevatedButton.styleFrom(
            backgroundColor: theme.colorScheme.primary,
            padding: const EdgeInsets.symmetric(vertical: 15, horizontal: 30),
            shape:
                RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
          ),
          child: const Text(
            "Ver Turno",
            style: TextStyle(fontSize: 18, color: Colors.white),
          ),
        ),
      ],
    );
  }

  Future<List<ItemPeticionAceptada>> convertirLista(
      Future<List<dynamic>> futureLista) async {
    List<dynamic> lista = await futureLista;
    return lista.map((elemento) {
      if (elemento is Map<String, dynamic>) {
        String descripcion = 'N/A';
        if (elemento['jornadaID'] != null &&
            elemento['jornadaID'] is Map<String, dynamic>) {
          descripcion = elemento['jornadaID']['descripcion'] != null
              ? utf8.decode(elemento['jornadaID']['descripcion']
                  .toString()
                  .runes
                  .toList())
              : 'N/A';
        }
        int cambioTurnoId = elemento['id'] is int
            ? elemento['id']
            : int.tryParse(elemento['id'].toString()) ?? 0;
        return ItemPeticionAceptada(
          descripcion,
          elemento['fechaSolicitada']?.toString() ?? 'N/A',
          cambioTurnoId,
        );
      } else {
        return ItemPeticionAceptada(elemento.toString(), '', 0);
      }
    }).toList();
  }

  Future<List<dynamic>> sendHttpGetSolicitudes(int userId) async {
    final response = await http.get(Uri.parse(
        'http://localhost:8080/api/solicitudesAceptadas?userId=$userId'));
    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception('Error al cargar los datos');
    }
  }
}
