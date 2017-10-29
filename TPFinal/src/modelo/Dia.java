package modelo;


/**
 * Enumerador con los días de la semana.
 */
public enum Dia
{
    LUN,
    MAR,
    MIE,
    JUE,
    VIE,
    SAB,
    DOM;

    /**
     * Devuelve el numero asociado a cada día.
     * @param dia Dia válido.
     * @return lunes = 0<br>
     * martes = 1<br>
     * miércoles = 2<br>
     * jueves = 3<br>
     * viernes = 4<br>
     * sábado = 5<br>
     * domingo = 6
     */
    public static int parseInt(Dia dia)
    {
        int ret = 7;
        switch (dia)
        {
            case LUN:
                ret = 0;
                break;
            case MAR:
                ret = 1;
                break;
            case MIE:
                ret = 2;
                break;
            case JUE:
                ret = 3;
                break;
            case VIE:
                ret = 4;
                break;
            case SAB:
                ret = 5;
                break;
            case DOM:
                ret = 6;
                break;
        }
        return ret;
    }

    public static Dia parseDia(String dia)
    {
        Dia ret = DOM;
        if (dia.equals("Lunes"))
            ret = LUN;
        else if (dia.equals("Martes"))
            ret = MAR;
        else if (dia.equals("Miercoles"))
            ret = MIE;
        else if (dia.equals("Jueves"))
            ret = JUE;
        else if (dia.equals("Viernes"))
            ret = VIE;
        else if (dia.equals("Sabado"))
            ret = SAB;
        else if (dia.equals("Domingo"))
            ret = DOM;
        return ret;
    }
}
