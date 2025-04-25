class Solicitudes {}

class ItemSolicitud {
  late String text;
  late bool isChecked;

  ItemSolicitud(String lTurno, bool lCheck) {
    text = lTurno;
    isChecked = lCheck;
  }
}
