package modelo;


public class Cursada
  implements I_Indexable
{
  private Asignatura asignatura;
  private String identificacion;
  private String periodo;
  private Dia dia;
  private String hora;
  private IndiceDoble<Profesor> profesores; //POR QUÉ ESTO WEN??????????????????????
  private IndiceDoble<Alumno> alumnos; //POR QUÉ ESTO WEN??????????????????????
  private static int CANT_CURSADAS = 0;

    public Cursada()
    {
        super();
    }

  public Cursada(Asignatura asignatura, String periodo, Dia dia, String hora)
  {
    this.asignatura = asignatura;
    this.periodo = periodo;
    this.dia = dia;
    this.hora = hora;
    this.profesores = new IndiceDoble<Profesor>();
    this.alumnos = new IndiceDoble<Alumno>();
  }

  public boolean correlativasAprobadas(Alumno alumno)
  {
    return this.asignatura.compruebaCorrelativas(alumno);
  }

  public boolean aceptaCompetencia(Profesor profesor)
  {
    return profesor.habilitadoParaAsignatura(this.asignatura);
  }

  @Override
  public Object getClavePrimaria()
  {
        return this.getIdentificacion();
  }

  @Override
  public Object getClaveSecundaria()
  {
    return Dia.parseInt(this.dia) * 10000 + this.parseInt(this.hora);
  }

  private int parseInt(String hora)
  { //Parsea el string de la hora de acuerdo a la mascara para que trabaje con un numero entero directo
    return Integer.parseInt(hora.substring(0, 2)) * 100 + Integer.parseInt(hora.substring(3, 5));
  }

  public String getIdentificacion()
  {
    return identificacion;
  }

  public void setAsignatura(Asignatura asignatura)
  {
    this.asignatura = asignatura;
  }

  public Asignatura getAsignatura()
  {
    return asignatura;
  }

  public void setIdentificacion(String identificacion)
  {
    this.identificacion = identificacion;
  }

  public void setPeriodo(String periodo)
  {
    this.periodo = periodo;
  }

  public String getPeriodo()
  {
    return periodo;
  }

  public void setDia(Dia dia)
  {
    this.dia = dia;
  }

  public Dia getDia()
  {
    return dia;
  }

  public void setHora(String hora)
  {
    this.hora = hora;
  }

  public String getHora()
  {
    return hora;
  }

  public void setProfesores(IndiceDoble<Profesor> profesores)
  {
    this.profesores = profesores;
  }

  public IndiceDoble<Profesor> getProfesores()
  {
    return profesores;
  }

  public void setAlumnos(IndiceDoble<Alumno> alumnos)
  {
    this.alumnos = alumnos;
  }

  public IndiceDoble<Alumno> getAlumnos()
  {
    return alumnos;
  }

  public static void setCANT_CURSADAS(int CANT_CURSADAS)
  {
    Cursada.CANT_CURSADAS = CANT_CURSADAS;
  }

  public static int getCANT_CURSADAS()
  {
    return CANT_CURSADAS;
  }

  public static boolean validaPeriodo(String periodo)
  {
    boolean ret;
        if ((periodo.length() == 7) &&
            (periodo.substring(0, 3).equals("01-") || periodo.substring(0, 3).equals("02-")) &&
        Integer.parseInt(periodo.substring(3, periodo.length())) > 2000 &&
        Integer.parseInt(periodo.substring(3, periodo.length())) < 2500)
      ret = true;
    else
      ret = false;
    return ret;
  }

  public static boolean validaHora(String hora)
  {
    boolean ret;
        if ((hora.length() == 5) && (Integer.parseInt(hora.substring(0, 2)) <= 99) &&
            (Integer.parseInt(hora.substring(0, 2)) >= 0) && (Integer.parseInt(hora.substring(3, 4)) <= 99) &&
            (Integer.parseInt(hora.substring(3, 4)) >= 0) && hora.charAt(2) == ':')
      ret = true;
    else
      ret = false;
    return ret;
  }

  public static String getNuevaIdentificacion()
  {
    Cursada.CANT_CURSADAS++;
        String ret = "CUR";
    String aux = "" + Cursada.CANT_CURSADAS;
    int i, j = aux.length();
        for (i = 1; i <= 4 - j; i++)
      ret = ret + "0";
    return ret + aux;
  }
}
