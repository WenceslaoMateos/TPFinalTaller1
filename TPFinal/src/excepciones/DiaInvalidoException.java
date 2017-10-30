package excepciones;

public class DiaInvalidoException
  extends Exception
{
  private Object dato;

  public DiaInvalidoException(Object dato)
  {
    super("El día ingresado es inválido");
    this.dato = dato;
  }

  public Object getDato()
  {
    return dato;
  }
}
