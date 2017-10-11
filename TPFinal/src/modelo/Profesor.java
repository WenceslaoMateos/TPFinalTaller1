package modelo;


public class Profesor
  extends Persona
{
  private String telefono;
  private IndicePrimario<String, Asignatura> competencia;

  public Profesor()
  {
    super();
  }

  public Profesor(String legajo, String apellidoNombre, String domicilio, String mail, String telefono)
  {
    super(legajo, apellidoNombre, domicilio, mail);
    this.telefono = telefono;
    this.competencia = new IndicePrimario<String, Asignatura>();
  }

  public void agregarCompetencia(Asignatura nuevo)
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

  public boolean habilitadoParaAsignatura(Asignatura asignatura)
  {
    return this.competencia.contieneValor(asignatura);
  }
}
