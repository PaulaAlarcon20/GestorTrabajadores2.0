import 'package:flutter/material.dart';
import 'package:front_end_gui/views/widgets/Calendar_widget.dart'; // Importar el widget del calendario

class CalendarScreen extends StatelessWidget {
  const CalendarScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return Scaffold(
      appBar: AppBar(
        title: const Text('Calendario'),
        centerTitle: true,
        backgroundColor: theme.colorScheme.primary,
        iconTheme: theme.iconTheme,
      ),
      body: const Padding(
        padding: EdgeInsets.all(16.0),
        child: CalendarWidget(), // Usar el widget del calendario
      ),
    );
  }
}