package excepciones;


public class ClaveYaExistenteException
    extends Exception
{
    private Object clave;

    public ClaveYaExistenteException(Object clave)
    {
        super("La clave del elemento a agregar ya se encuentra en la colección.");
        this.clave = clave;
    }

    public Object getClave()
    {
        return clave;
    }
}
