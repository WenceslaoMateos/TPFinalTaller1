package excepciones;


public class DatoInvalidoException
  extends Exception
{
  private Object dato;

  public DatoInvalidoException(Object dato, String mensaje)
  {
    super(mensaje);
    this.dato = dato;
  }

  public Object getDato()
  {
    return dato;
  }
}
