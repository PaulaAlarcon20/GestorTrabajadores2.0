import 'package:flutter/material.dart';
import 'package:front_end_gui/views/widgets/DropDownWidgetTurnos.dart';

class NuevaSolicitudWidget extends StatelessWidget {
  const NuevaSolicitudWidget({super.key});

//formulario crear nueva solicitud
  @override
  Widget build(BuildContext context) {
    //validadores
    final _formKey = GlobalKey<FormState>();

    final TextEditingController _fechaCambio = TextEditingController();

    return Padding(
        padding: EdgeInsets.all(8),
        child: Form(
          key: _formKey,
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
              SizedBox(
                height: 16,
              ),
              _buildTextField(
                  controller: _fechaCambio,
                  label: "fecha cambio",
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return "Debe rellenar los campos";
                    }
                    return null;
                  }),
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
                      if (_formKey.currentState!.validate()) {
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

  Widget _buildTextField(
      {required String label,
      required TextEditingController controller,
      required String? Function(String?) validator}) {
    return TextFormField(
      decoration: InputDecoration(
          labelText: label,
          labelStyle: TextStyle(
            fontFamily: "Quicksand",
            color: Colors.black,
          ),
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(10),
          ),
          focusedBorder: OutlineInputBorder(
              borderSide: BorderSide(color: Colors.blue, width: 1),
              borderRadius: BorderRadius.circular(10))),
      validator: validator,
    );
  }
}
