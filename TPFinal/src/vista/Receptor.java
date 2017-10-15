package vista;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import modelo.Alumno;
import modelo.Sistema;


public class Receptor
{
  private Sistema modelo;
  private VentanaPrincipal vista;
  public static final int ALTA_ALUMNO=0;
  public static final int ALTA_PROFESOR=1;
  public static final int ALTA_ASIGNATURA=2;
  

  public Receptor(Sistema modelo, VentanaPrincipal vista)
  {
    super();
    this.modelo = modelo;
    this.vista = vista;
  }

  public void alta(Object obj, int comando)
    throws ClaveYaExistenteException, DatoInvalidoException
  {
    switch(comando)
    {
      case Receptor.ALTA_ALUMNO:
        this.modelo.agregarAlumno((Alumno)obj);
        break;
    }
  }
}
