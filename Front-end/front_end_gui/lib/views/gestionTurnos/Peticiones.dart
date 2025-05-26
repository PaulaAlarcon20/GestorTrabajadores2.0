class Peticiones {}

class ItemPeticion {
  late String turno;
  late String fechaSolicitada;
  late bool isChecked;
  late int cambioTurnoId;

  ItemPeticion(
      String lTurno, String lfechaSolicitada, bool lCheck, int cmTurnoId) {
    turno = lTurno;
    fechaSolicitada = lfechaSolicitada;
    isChecked = lCheck;
    cambioTurnoId = cmTurnoId;
  }
}
