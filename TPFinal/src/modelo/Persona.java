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

    public void setLegajo(String legajo)
    {
        this.legajo = legajo;
    }

    public void setApellidoNombre(String apellidoNombre)
    {
        this.apellidoNombre = apellidoNombre;
    }

    public void setDomicilio(String domicilio)
    {
        this.domicilio = domicilio;
    }

    public String getDomicilio()
    {
        return domicilio;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getMail()
    {
        return mail;
    }
}
