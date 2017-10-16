package modelo;

import excepciones.ClaveYaExistenteException;


public class Profesor
  extends Persona
{
  private String telefono;
  private IndicePrimario<Asignatura> competencia;
  private static int CANT_PROFESORES = 0;

  public Profesor()
  {
    super();
  }

  public Profesor(String apellidoNombre, String domicilio, String mail, String telefono)
  {
    super(apellidoNombre, domicilio, mail);
    this.telefono = telefono;
    this.competencia = new IndicePrimario<Asignatura>();
  }

  public void agregarCompetencia(Asignatura nuevo)
    throws ClaveYaExistenteException
  {
    this.competencia.agregar(nuevo);
  }

  public void eliminarCompetencia(Asignatura elim)
  {
    this.competencia.eliminar(elim);
  }

  public void setTelefono(String telefono)
  {
    this.telefono = telefono;
  }

  public String getTelefono()
  {
    return telefono;
  }

  public static void setCANT_PROFESORES(int CANT_PROFESORES)
  {
    Profesor.CANT_PROFESORES = CANT_PROFESORES;
  }

  public static int getCANT_PROFESORES()
  {
    return CANT_PROFESORES;
  }

  public void setCompetencia(IndicePrimario<Asignatura> competencia)
  {
    this.competencia = competencia;
  }

  public IndicePrimario<Asignatura> getCompetencia()
  {
    return competencia;
  }

  /**
   * Especifica si el profesor está habilitado para dar la asginatura recibida como parámetro.
   * @param asignatura materia a comprobar.
   * @return true si la asignatura está entre las competencias del profesor, false en caso contrario.
   */
  public boolean habilitadoParaAsignatura(Asignatura asignatura)
  {
    return this.competencia.contieneValor(asignatura);
  }

  public static String getNuevaIdentificacion()
  {
    Profesor.CANT_PROFESORES++;
    String ret = "PRO";
    String aux = "" + Profesor.CANT_PROFESORES;
    int i, j = aux.length();
    for (i = 1; i <= 4 - j; i++)
      ret = ret + "0";
    return ret + aux;
  }
}
