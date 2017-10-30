package persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.Profesor;
import modelo.Sistema;

/**
 * Guarda y recupera la información de un sistema desde un archivo llamado "Sistema.xml".
 */
public abstract class SerializadorXML
{
  private static final String ARCHIVO = "Sistema.xml";

  /**
   * Almacena la información del sistema en un archivo XML.
   * @param sistema El sistema a persistir.
   */
  public static void guardar(Sistema sistema)
  {
    XMLEncoder encoder;
    try
    {
      encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SerializadorXML.ARCHIVO)));
      encoder.writeObject(sistema);
      encoder.writeObject(Alumno.getCANT_ALUMNOS());
      encoder.writeObject(Profesor.getCANT_PROFESORES());
      encoder.writeObject(Asignatura.getCANT_ASIGNATURAS());
      encoder.writeObject(Cursada.getCANT_CURSADAS());
      encoder.close();
    }
    catch (FileNotFoundException e)
    {
      throw new InternalError("Error fatal al tratar de guardar el sistema");
    }
  }

  /**
   * Carga el sistema desde el archivo XML por defecto. Si dicho archivo no existe, se crea una instancia nueva.
   * @return Si se pudo leer el archivo, instancia de Sistema con la información almacenada. En caso contrario se
   * devuelve una instancia nueva.
   */
  public static Sistema cargar()
  {
    Sistema res;
    XMLDecoder decoder;
    try
    {
      decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(SerializadorXML.ARCHIVO)));
      res = (Sistema) decoder.readObject();
      Alumno.setCANT_ALUMNOS((Integer) decoder.readObject());
      Profesor.setCANT_PROFESORES((Integer) decoder.readObject());
      Asignatura.setCANT_ASIGNATURAS((Integer) decoder.readObject());
      Cursada.setCANT_CURSADAS((Integer) decoder.readObject());
      decoder.close();
    }
    catch (FileNotFoundException e)
    {
      res = new Sistema();
    }
    return res;
  }
}
