package modelo;


/**
 * Clase abstracta que define los atributos comunes de las personas. Implementa I_Indexable.
 */
public abstract class Persona
    implements I_Indexable
{
    private String legajo;
    private String apellidoNombre;
    private String domicilio;
    private String mail;

    public Persona()
    {
        super();
    }

    public Persona(String apellidoNombre, String domicilio, String mail)
    {
        this.apellidoNombre = apellidoNombre;
        this.domicilio = domicilio;
        this.mail = mail;
    }

    /**
     * Clave primaria de una persona.
     * @return Ver getLegajo()
     */
    @Override
    public Object getClavePrimaria()
    {
        return this.getLegajo();
    }

    /**
     * Clave secundaria de una persona.
     * @return Ver getApellidoNombre()
     */
    @Override
    public Object getClaveSecundaria()
    {
        return this.getApellidoNombre();
    }

    public String getLegajo()
    {
        return legajo;
    }

    public String getApellidoNombre()
    {
        return apellidoNombre;
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

    /**
     * Verifica que el String pasado como parámetro cumpla con las especificaciones de un mail.<br>
     * Las mismas imponen que contenga un símbolo @ y que haya al menos un caracter antes y después de dicho símbolo.
     * @param mail String a verificar
     * @return <b>true</b> si el mail cumple con las especifiaciones, <b>false</b> en caso contrario
     */
    public static boolean validaMail(String mail)
    {
        return mail.contains("@") && ((mail.charAt(mail.length() - 1)) != '@') && (mail.charAt(0) != '@');
    }
}
