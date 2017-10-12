package modelo;


public class Alumno
  extends Persona
{
  private IndicePrimario<String, Asignatura> historia;
  private static int CANT_ALUMNOS = 0;

  public Alumno()
  {
    super();
  }

  public Alumno(String legajo, String apellidoNombre, String domicilio, String mail)
  {
    super(Alumno.getNuevoLegajo(), apellidoNombre, domicilio, mail);
    this.historia = new IndicePrimario<String, Asignatura>();
  }

  public void agregarHistoria(Asignatura nuevo)
  {
    this.historia.agregar(nuevo);
  }

  public void eliminarHistoria(Asignatura elim)
  {
    this.historia.eliminar(elim);
  }

  public boolean asignaturaAprobada(Asignatura asignatura)
  {
    return this.historia.contieneValor(asignatura);
  }

  public void setHistoria(IndicePrimario<String, Asignatura> historia)
  {
    this.historia = historia;
  }

  public IndicePrimario<String, Asignatura> getHistoria()
  {
    return historia;
  }

  public static void setCANT_ALUMNOS(int CANT_ALUMNOS)
  {
    Alumno.CANT_ALUMNOS = CANT_ALUMNOS;
  }

  public static int getCANT_ALUMNOS()
  {
    return CANT_ALUMNOS;
  }

  public static String getNuevoLegajo()
  {
    Alumno.CANT_ALUMNOS++;
    String ret = "ALU";
    String aux = "" + Alumno.CANT_ALUMNOS;
    int i, j = aux.length();
    for (i = 1; i < 4 - j; i++)
      ret = ret + "0";
    return ret + aux;
  }
}
