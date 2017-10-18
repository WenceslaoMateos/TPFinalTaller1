package modelo;


public enum Dia
{
  LUN,
  MAR,
  MIE,
  JUE,
  VIE,
  SAB,
  DOM;

  public static int parseInt(Dia dia)
  {
    int ret;
    switch (dia)
    {
      case LUN:
        ret = 1;
        break;
      case MAR:
        ret = 2;
        break;
      case MIE:
        ret = 3;
        break;
      case JUE:
        ret = 4;
        break;
      case VIE:
        ret = 5;
        break;
      case SAB:
        ret = 6;
        break;
      case DOM:
        ret = 7;
        break;
      default:
        ret = 0;
    }
    return ret;
  }

  public static Dia parseDia(String dia)
  {
    Dia ret=DOM;
    if (dia.equals("Lunes"))
      ret=LUN;
    else if (dia.equals("Martes"))
      ret=MAR;
    else if (dia.equals("Miercoles"))
      ret=MIE;
    else if (dia.equals("Jueves"))
      ret=JUE;
    else if (dia.equals("Viernes"))
      ret=VIE;
    else if (dia.equals("Sabado"))
      ret=SAB;
    else if (dia.equals("Domingo"))
      ret=DOM;
    return ret;
  }
}
