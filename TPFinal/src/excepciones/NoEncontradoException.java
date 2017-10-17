package excepciones;


public class NoEncontradoException
  extends Exception
{
  private Object dato;

  public NoEncontradoException(Object dato, String mensaje)
  {
    super(mensaje);
    this.dato = dato;
  }

  public Object getDato()
  {
    return dato;
  }
}
