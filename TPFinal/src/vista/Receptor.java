package vista;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;
import excepciones.NoEncontradoException;

import java.util.Iterator;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Profesor;
import modelo.Sistema;

import persistencia.SerializadorXML;


public class Receptor
{
  private Sistema modelo;
  private Ventana vista;
  public static final int ALUMNO = 0;
  public static final int PROFESOR = 1;
  public static final int ASIGNATURA = 2;


  public Receptor(Sistema modelo, Ventana vista)
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

  public Iterator ubicar(Object obj, int comando)
    throws NoEncontradoException
  {
    Iterator ret = null;
    switch (comando)
    {
      case Receptor.ALUMNO:
        ret = this.modelo.buscarAlumno((String) obj);
        break;
      case Receptor.PROFESOR:
        ret = this.modelo.buscarProfesor((String) obj);
        break;
      case Receptor.ASIGNATURA:
        ret = this.modelo.buscarAsignatura((String) obj);
        break;
    }
    return ret;
  }

  public Object buscar(Object obj, int comando)
    throws NoEncontradoException
  {
    Object ret = null;
    switch (comando)
    {
      case Receptor.ALUMNO:
        ret = this.modelo.buscarAlumnoPorLegajo((String) obj);
        break;
      case Receptor.PROFESOR:
        ret = this.modelo.buscarProfesorPorLegajo((String) obj);
        break;
      case Receptor.ASIGNATURA:
        ret = this.modelo.buscarAsignaturaPorIdentifiacion((String) obj);
        break;
    }
    return ret;
  }

  public Object baja(Object obj, int comando)
    throws NoEncontradoException
  {
    Object ret = null;
    switch (comando)
    {
      case Receptor.ALUMNO:
        this.modelo.eliminarAlumno((Alumno) this.buscar(obj, comando));
        break;
      case Receptor.PROFESOR:
        this.modelo.eliminarProfesor((Profesor) this.buscar(obj, comando));
        break;
      case Receptor.ASIGNATURA:
        this.modelo.eliminarAsignatura((Asignatura) this.buscar(obj, comando));
        break;
    }
    return ret;
  }

  public void guardar()
  {
    SerializadorXML.guardar(this.modelo);
  }
}
