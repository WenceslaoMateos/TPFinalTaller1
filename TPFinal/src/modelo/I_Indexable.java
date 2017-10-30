package modelo;

import excepciones.DatoInvalidoException;

/**
 * Interfaz que determina los métodos básicos para que una clase pueda ser indexada.
 */
public interface I_Indexable
{
  /**
   * Obtiene la clave primaria de un elemento, que debiera ser única.
   * @return objeto que puede variar según la clase.
   */
  public abstract Object getClavePrimaria();

  /**
   * Obtiene la clave secundaria de un elemento, que puede ser compartida con otros.
   * @return objeto que puede variar según la clase.
   */
  public abstract Object getClaveSecundaria();

  /**
   * Determina el comportamiento a seguir por la clase que lo implemente en cuanto a modificaciones respecta.
   * @param modif parámetro que contiene las modificaciones a realizarse sobre el objeto invocante.
   * @throws DatoInvalidoException el objeto modif no es de la misma clase que el invocante.
   */
  public abstract void modificarDatos(I_Indexable modif)
    throws DatoInvalidoException;
}
