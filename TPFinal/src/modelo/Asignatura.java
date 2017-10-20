package modelo;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import java.util.Iterator;

/**
 * Clase que representa a las asignaturas del sistema. Implementa I_Indexable siendo la identificación la clave primaria
 * y el nombre de la materia la clave secundaria.
 */
public class Asignatura
  implements I_Indexable
{
  private String identificacion;
  private String nombre;
  private IndicePrimario<Asignatura> correlatividades;
  private static int CANT_ASIGNATURAS = 0;

  /**
   * Constructor para crear una instancia preliminar de asignatura. No valida el nombre ni asigna un legajo.<br>
   * <b>Post:</b> Se genera una nueva instancia de asignatura cuyos datos podrán ser validados y, tras esto, se le
   * podrá asignar una identificación.
   * @param nombre nombre de la materia.
   */
  public Asignatura(String nombre)
  {
    this.nombre = nombre;
    this.correlatividades = new IndicePrimario<Asignatura>();
  }

  /**
   * Clave primaria de una asignatura.
   * @return Ver getIdentificacion().
   */
  @Override
  public Object getClavePrimaria()
  {
    return this.getIdentificacion();
  }

  /**
   * Clave secundaria de una asignatura.
   * @return Ver getNombre().
   */
  @Override
  public Object getClaveSecundaria()
  {
    return this.getNombre();
  }

  public String getIdentificacion()
  {
    return identificacion;
  }

  public String getNombre()
  {
    return nombre;
  }

  public void setIdentificacion(String identificacion)
  {
    this.identificacion = identificacion;
  }

  public void setNombre(String nombre)
  {
    this.nombre = nombre;
  }

  /**
   * Agrega una asignatura a las precorrelativas de la materia.<br>
   * <b>Pre:</b> correlativa se encuentra entre las asignaturas del sistema.<br>
   * <b>Post:</b> La asignatura que ejecuta el método tiene una precorrelativa más distinta de sí misma.
   * @param correlativa Asignatura a agregar como requisito para la materia invocante.
   * @throws ClaveYaExistenteException La asignatura dada ya se encontraba entre las correlatividades.
   * @throws DatoInvalidoException La asignatura dada es la misma que la invocante.
   */
  public void agregarCorrelativa(Asignatura correlativa)
    throws ClaveYaExistenteException, DatoInvalidoException
  {
    if (this.equals(correlativa))
      throw new DatoInvalidoException(correlativa, "La asignatura no puede ser correlativa de si misma.");
    else
      this.correlatividades.agregar(correlativa);
  }

  /**
   * Elimina una asignatura de entre las precorrelativas del objeto invocante.<br>
   * <b>Pre:</b> La materia se encuentra entre las precorrelativas del la asignatura invocante.<br>
   * <b>Post:</b> La asignatura tiene una precorrelativa menos.
   * @param elim Asignatura a eliminar.
   */
  public void eliminarCorrelativa(Asignatura elim)
  {
    this.correlatividades.eliminar(elim);
  }

  /**
   * Verifica que el alumno proporcionado como parámetro tenga todas las precorrelativas de la asignatura aprobadas.
   * @param alumno Alumno a verificar su historia académica.
   * @return <b>true</b> si el alumno tiene aprobadas todas las materias previas, <b>false</b> en caso contrario.
   */
  public boolean compruebaCorrelativas(Alumno alumno)
  {
    Iterator<Asignatura> it = this.correlatividades.elementos();
    boolean ret = true;
    while (it.hasNext() && ret)
      ret = alumno.asignaturaAprobada(it.next());
    return ret;
  }

  /**
   * Genera una nueva identificación cumpliendo con las especificaciones para una asignatura.<br>
   * <b>Post:</b> Se otorga una identificación válida y aumenta en uno la cantidad de identificaciones.
   * @return String que representa la nueva identificación.
   */
  public static String getNuevaIdentificacion()
  {
    Asignatura.CANT_ASIGNATURAS++;
    String ret = "ASI";
    String aux = "" + Asignatura.CANT_ASIGNATURAS;
    int i, j = aux.length();
    for (i = 1; i <= 4 - j; i++)
      ret = ret + "0";
    return ret + aux;
  }

  /**
   * Otorga las precorrelativas de la materia.
   * @return Iterator con las asignaturas necesarias para cursar la invocante.
   */
  public Iterator<Asignatura> precorrelativas()
  {
    return this.correlatividades.elementos();
  }

  /**
   * Comprueba que el parámetro sea una precorrelativa del objeto invocante.<br>
   * <b>Pre:</b> asignatura forma parte del sistema.
   * @param asignatura Asignatura a verificar.
   * @return <b>true</b> si la asignatura se encontró entre las correlatividades, <b>false</b> en caso contrario.
   */
  public boolean tienePrecorrelativa(Asignatura asignatura)
  {
    return this.correlatividades.contieneValor(asignatura);
  }

  /**
   * Le asigna el nombre proveniente de modif al objeto invocante. No permite modificar la identificación.<br>
   * <b>Pre:</b> Los atributos de modif son correctos.
   * <b>Post:</b> Los cambios fueron aplicados sobre el objeto invocante.
   * @param modif parámetro que contiene las modificaciones a aplicar sobre el objeto invocante.
   * @throws DatoInvalidoException modif no es una asignatura.
   */
  @Override
  public void modificarDatos(I_Indexable modif)
    throws DatoInvalidoException
  {
    if (this.getClass() != modif.getClass())
      throw new DatoInvalidoException(modif, "Tipo de dato inválido.");
    else
      this.setNombre(((Asignatura) modif).getNombre());
  }

  /**
   * Verifica que el nombre de la asignatura no está vacío.
   * @param asignatura Objeto a ser verificado.
   * @return <b>true</b> si el nombre no es vacío, <b>false</b> en caso contrario.
   */
  public static boolean validaAsignatura(Asignatura asignatura)
  {
    return !asignatura.getNombre().equals("");
  }

  //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
  public Asignatura()
  {
    super();
  }

  public void setCorrelatividades(IndicePrimario<Asignatura> correlatividades)
  {
    this.correlatividades = correlatividades;
  }

  public IndicePrimario<Asignatura> getCorrelatividades()
  {
    return correlatividades;
  }

  public static void setCANT_ASIGNATURAS(int CANT_ASIGNATURAS)
  {
    Asignatura.CANT_ASIGNATURAS = CANT_ASIGNATURAS;
  }

  public static int getCANT_ASIGNATURAS()
  {
    return CANT_ASIGNATURAS;
  }
  //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}
