package modelo;

public abstract class Persona
    implements I_Indexable
{
    private String legajo;
    private String apellidoNombre;
    private String domicilio;
    private String mail;


    @Override
    public Object getClavePrimaria()
    {
        return this.legajo;
    }

    @Override
    public Object getClaveSecundaria()
    {
        return this.apellidoNombre;
    }
}
