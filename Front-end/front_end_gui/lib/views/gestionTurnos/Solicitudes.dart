class Solicitudes {}

class ItemSolicitud {
  late String turno;
  late String fechaSolicitada;
  late bool isChecked;
  late int cambioTurnoId;

  ItemSolicitud(
      String lTurno, String lfechaSolicitada, bool lCheck, int cmTurnoId) {
    turno = lTurno;
    fechaSolicitada = lfechaSolicitada;
    isChecked = lCheck;
    cambioTurnoId = cmTurnoId;
  }
}
