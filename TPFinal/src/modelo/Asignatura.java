package modelo;


public class Asignatura
  implements I_Indexable
{
  private String identificacion;
  private String nombre;

  @Override
  public Object getClavePrimaria()
  {
    return this.identificacion;
  }

  @Override
  public Object getClaveSecundaria()
  {
    return this.nombre;
  }

  public void setIdentificacion(String identificacion)
  {
    this.identificacion = identificacion;
  }

  public void setNombre(String nombre)
  {
    this.nombre = nombre;
  }
}
