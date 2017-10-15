package vista;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Profesor;
import modelo.Sistema;


public class Receptor
{
  private Sistema modelo;
  private VentanaPrincipal vista;
  public static final int ALUMNO = 0;
  public static final int PROFESOR = 1;
  public static final int ASIGNATURA = 2;


  public Receptor(Sistema modelo, VentanaPrincipal vista)
  {
    super();
    this.modelo = modelo;
    this.vista = vista;
  }

  public void alta(Object obj, int comando)
    throws ClaveYaExistenteException, DatoInvalidoException
  {
    switch (comando)
    {
      case Receptor.ALUMNO:
        this.modelo.agregarAlumno((Alumno) obj);
        break;
      case Receptor.PROFESOR:
        this.modelo.agregarProfesor((Profesor) obj);
        break;
      case Receptor.ASIGNATURA:
        this.modelo.agregarAsignatura((Asignatura) obj);
        break;
    }
  }
}
