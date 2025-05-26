class ItemPeticionAceptada {
  late String turnoDesc;
  late String fecha;
  late bool isChecked;
  late int cambioTurnoId;

  ItemPeticionAceptada(String lTurnoDesc, String lfecha, int cmTurnoId) {
    turnoDesc = lTurnoDesc;
    fecha = lfecha;
    cambioTurnoId = cmTurnoId;
  }
}
