package modelo;


public class Profesor
    extends Persona
{
    private String telefono;
    
    public Profesor()
    {
        super();
    }
    
    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public String getTelefono()
    {
        return telefono;
    }
}
