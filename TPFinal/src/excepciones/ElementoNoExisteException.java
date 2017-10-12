package excepciones;


public class ElementoNoExisteException
    extends Exception
{
    private Object dato;

    public ElementoNoExisteException(Object dato)
    {
        super("No se encontró el objeto entre los elementos de la colección.");
        this.dato = dato;
    }

    public Object getDato()
    {
        return dato;
    }
}
