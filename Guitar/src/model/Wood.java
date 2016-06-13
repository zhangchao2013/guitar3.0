package model;
public enum Wood { 

 MAHOGANY, MAPLE,COCOBOLO, CEDAR, ADIRONDACK, ALDER, SITKA;

  public String toString() {
    switch(this) {
      case MAHOGANY:           return "Mahogany";
      case MAPLE:              return "Maple";
      case COCOBOLO:           return "Cocobolo";
      case CEDAR:              return "Cedar";
      case ADIRONDACK:         return "Adirondack";
      case ALDER:              return "Alder";
      case SITKA:              return "Sitka";
      default:  return "unspecified";
    }
  }
}
