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
}
