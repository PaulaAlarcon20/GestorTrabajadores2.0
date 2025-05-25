class UsuarioDTO {
  final int id;
  final String nombre;
  final String apellido;
  final String email;
  final String centroTrabajo;
  final String puesto;
  final String telefono;
  final String localidad;
  final String preferenciasHorarias;
  final bool disponibilidadHorasExtras;

  UsuarioDTO({
    required this.id,
    required this.nombre,
    required this.apellido,
    required this.email,
    required this.telefono,
    required this.centroTrabajo,
    required this.puesto,
    required this.localidad,
    required this.preferenciasHorarias,
    required this.disponibilidadHorasExtras,
  });

  factory UsuarioDTO.fromJson(Map<String, dynamic> json) {
    return UsuarioDTO(
      id: json['id'],
      nombre: json['nombre'],
      apellido: json['apellido'],
      email: json['email'],
      telefono: json['telefono'],
      centroTrabajo: json['centroTrabajo'],
      puesto: json['puesto'],
      localidad: json['localidad'],
      preferenciasHorarias: json['preferenciasHorarias'],
      disponibilidadHorasExtras: json['disponibilidadHorasExtras'],
    );
  }
}
