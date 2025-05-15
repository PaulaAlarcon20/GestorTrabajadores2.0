import 'package:flutter/material.dart';
import 'package:front_end_gui/config/theme/app_theme.dart';
import 'package:front_end_gui/views/RegisterView_screen.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:front_end_gui/views/cubit/SignUpCubit2.dart';
import 'package:intl/date_symbol_data_local.dart';

/// Fichero principal de la aplicación que contiene nuestra aplicación


Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized(); // Asegura la inicialización correcta
  await initializeDateFormatting('es_ES', null); // Inicializa la localización en español

runApp(
  MultiBlocProvider(
      providers: [
        BlocProvider(create: (context) => SignUpCubit2())
      ],
      
      child: const MyApp(),
    ));
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: AppTheme(selectionColor: 0).theme(),
      title: 'Turn App',
      home: Scaffold(

          ///appBar: AppBar(//title: const Text('Nuevo usuario'),),
          body: RegisterView()
          // añadir vista

          ),
    );
  }
}
