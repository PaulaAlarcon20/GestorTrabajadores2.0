class Peticiones {}

class ItemPeticion {
  late String turno;
  late String fechaSolicitada;
  late bool isChecked;

  ItemPeticion(String lTurno, String lfechaSolicitada, bool lCheck) {
    turno = lTurno;
    fechaSolicitada = lfechaSolicitada;
    isChecked = lCheck;
  }
}
