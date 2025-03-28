import 'package:flutter/material.dart';
import 'package:table_calendar/table_calendar.dart';
import 'package:intl/intl.dart';
import 'package:front_end_gui/views/widgets/Turn_detail_screen.dart';

class CalendarWidget extends StatefulWidget {
  const CalendarWidget({super.key});

  @override
  State<CalendarWidget> createState() => _CalendarWidgetState();
}

class _CalendarWidgetState extends State<CalendarWidget> {
  late DateTime _selectedDay;
  late DateTime _focusedDay;
  late CalendarFormat _calendarFormat;
  late Map<DateTime, String> _turnos;

  @override
  void initState() {
    super.initState();
    _selectedDay = DateTime.now();
    _focusedDay = DateTime.now();
    _calendarFormat = CalendarFormat.month;

    // 🔹 Simulación de turnos con horarios específicos (Año 2025)
    _turnos = {
      DateTime.utc(2025, 3, 5): "Turno de mañana: 08:00 - 16:00",
      DateTime.utc(2025, 3, 6): "Turno de tarde: 16:00 - 00:00",
      DateTime.utc(2025, 3, 7): "Turno de noche: 00:00 - 08:00",
      DateTime.utc(2025, 3, 10): "Turno de mañana: 08:00 - 16:00",
      DateTime.utc(2025, 3, 15): "Día libre",
      DateTime.utc(2025, 3, 20): "Turno de tarde: 16:00 - 00:00",
      DateTime.utc(2025, 3, 25): "Turno de noche: 00:00 - 08:00",
    };
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
              String? turno = _turnos[DateTime.utc(date.year, date.month, date.day)];
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
          _turnos[DateTime.utc(_selectedDay.year, _selectedDay.month, _selectedDay.day)] ??
              "Sin turno asignado",
          style: TextStyle(
            fontSize: 16,
            fontWeight: FontWeight.bold,
            color: _turnos[DateTime.utc(_selectedDay.year, _selectedDay.month, _selectedDay.day)] ==
                    "Día libre"
                ? Colors.green
                : theme.colorScheme.onSurface,
          ),
        ),
        const SizedBox(height: 20),
        ElevatedButton(
          onPressed: () {
            String turno = _turnos[DateTime.utc(_selectedDay.year, _selectedDay.month, _selectedDay.day)] ?? "Sin turno asignado";
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
            shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
          ),
          child: const Text(
            "Ver Turno",
            style: TextStyle(fontSize: 18, color: Colors.white),
          ),
        ),
      ],
    );
  }
}
