package modelo;

import excepciones.DatoInvalidoException;


/**
 * Clase abstracta que define los atributos comunes de las personas. Implementa I_Indexable siendo su legajo la clave
 * primaria y su apellido y nombre la clave secundaria.
 */
public abstract class Persona
  implements I_Indexable
{
  private String legajo;
  private String apellidoNombre;
  private String domicilio;
  private String mail;

  /**
   * Genera una instancia preliminar de Persona con apellido y nombre, domicilio y mail.<br>
   * <b>Post:</b> Se genera una nueva instancia de Persona cuyos datos aún no están validados.
   * @param apellidoNombre nombre completo de la persona. Será clave secundaria.
   * @param domicilio dirección de la persona.
   * @param mail dirección electrónica de la persona.
   */
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
   * @param mail String a verificar.
   * @return <b>true</b> si el mail cumple con las especifiaciones, <b>false</b> en caso contrario.
   */
  public static boolean validaMail(String mail)
  {
    return mail.contains("@") && ((mail.charAt(mail.length() - 1)) != '@') && (mail.charAt(0) != '@');
  }

  /**
   * Verifica que el nombre pasado como parámetro no sea nulo.
   * @param nombre String a verificar.
   * @return <b>true</b> si el nombre no es nulo, <b>false</b> en caso contrario.
   */
  public static boolean validaNombre(String nombre)
  {
    return !nombre.equals("");
  }

  /**
   * Verifica que los atributos de una persona sean correctos.
   * @param persona Persona a verificar.
   * @return <b>true</b> si los atributos son correctos, <b>false</b> en caso contrario.
   */
  public static boolean validaPersona(Persona persona)
  {
    return Persona.validaMail(persona.getMail()) && Persona.validaNombre(persona.getApellidoNombre());
  }

  /**
   * Le asigna el apellido y nombre, domicilio y mail provenientes del parámetro al objeto invocante. No permite
   * modificar el legajo.<br>
   * <b>Pre:</b> Los atributos de modif son correctos.
   * <b>Post:</b> Los cambios fueron aplicados sobre el objeto invocante.
   * @param modif parámetro que contiene las modificaciones a aplicar sobre el objeto invocante.
   * @throws DatoInvalidoException Las clases del parámetro y del objeto invocante son distintas.
   */
  @Override
  public void modificarDatos(I_Indexable modif)
    throws DatoInvalidoException
  {
    Persona aux;
    if (this.getClass() != modif.getClass())
      throw new DatoInvalidoException(modif, "Tipo de dato incompatible.");
    else
    {
      aux = (Persona) modif;
      this.setApellidoNombre(aux.getApellidoNombre());
      this.setDomicilio(aux.getDomicilio());
      this.setMail(aux.getMail());
    }
  }

  //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
  public Persona()
  {
    super();
  }
  //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}
