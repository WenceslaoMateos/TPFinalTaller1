package modelo;


public class Alumno
  extends Persona
{
  private IndicePrimario<String, Asignatura> historia;

  public Alumno()
  {
    this.historia = new IndicePrimario<String, Asignatura>();
  }
  
  public boolean isAprobada(Asignatura verif)
  {
  }

  public void agregarHistoria(Asignatura nuevo)
  {
    this.historia.agregar(nuevo);
  }

  public void eliminarHistoria(Asignatura elim)
  {
    this.historia.eliminar(elim);
  }
  
  
}
