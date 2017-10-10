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
    int ret = 0;
    switch (dia)
    {
      case LUN:
        ret = 1;
      case MAR:
        ret = 2;
      case MIE:
        ret = 3;
      case JUE:
        ret = 4;
      case VIE:
        ret = 5;
      case SAB:
        ret = 6;
      case DOM:
        ret = 7;
    }
    return ret;
  }
}
