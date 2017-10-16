package modelo;


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

  @Override
  public Object getClavePrimaria()
  {
    return this.getLegajo();
  }

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

  public static boolean validaMail(String mail)
  {
    return mail.contains("@") && ((mail.charAt(mail.length() - 1)) != '@') && (mail.charAt(0) != '@');
  }
}
