package modelo;

import java.util.Iterator;


public class Cursada
  implements I_Indexable
{
  private Asignatura asignatura;
  private String identificacion;
  private String periodo;
  private Dia dia;
  private String hora;
  private IndiceDoble<String, String, Profesor> profesores;
  private IndiceDoble<String, String, Alumno> alumnos;

  public void agregarAlumno(Alumno nuevo)
  {
    if (this.correlativasAprobadas(nuevo))
      this.alumnos.agregar(nuevo);
    else
      ;
    //TODO tirar excepcion?
  }

  public boolean correlativasAprobadas(Alumno aux)
  {

    return true;
  }

  @Override
  public Object getClavePrimaria()
  {
    return this.identificacion;
  }

  @Override
  public Object getClaveSecundaria()
  {
    return Dia.parseInt(this.dia) * 10000 + this.parseInt(this.hora);
  }

  /**
   * Parsea el string de la hora de acuerdo a la mascara para que trabaje con un numero entero directo
   * @param hora
   * @return
   */
  private int parseInt(String hora)
  {
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
}
