package vista;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;
import excepciones.NoEncontradoException;

import java.util.Iterator;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.Profesor;
import modelo.Sistema;

import persistencia.SerializadorXML;


public class Receptor
{
  private Sistema modelo;
  public static final int ALUMNO = 0;
  public static final int PROFESOR = 1;
  public static final int ASIGNATURA = 2;
  public static final int CURSADA = 3;


  public Receptor(Sistema modelo)
  {
    super();
    this.modelo = modelo;
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
      case Receptor.CURSADA:
        this.modelo.agregarCursada((Cursada) obj);
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
      case Receptor.CURSADA:
        ret = this.modelo.buscarCursada((String) obj);
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
      case Receptor.CURSADA:
        ret = this.modelo.buscarCursadaPorIdentificacion((String) obj);
        break;
    }
    return ret;
  }

  public void baja(Object obj, int comando)
    throws NoEncontradoException
  {
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
      case Receptor.CURSADA:
        this.modelo.eliminarCursada((Cursada) obj);
        break;
    }
  }

  public void modificacion(Object obj, int comando)
    throws DatoInvalidoException, NoEncontradoException
  {
    switch (comando)
    {
      case Receptor.ALUMNO:
        this.modelo.modificarAlumno((Alumno) this.buscar(((Alumno) obj).getLegajo(), comando), (Alumno) obj);
        break;
      case Receptor.PROFESOR:
        this.modelo.modificarProfesor((Profesor) this.buscar(((Profesor) obj).getLegajo(), comando), (Profesor) obj);
        break;
      case Receptor.ASIGNATURA:
        this.modelo.modificarAsignatura((Asignatura) this.buscar(((Asignatura) obj).getIdentificacion(), comando),
                                        (Asignatura) obj);
        break;
      case Receptor.CURSADA:
        this.modelo.modificarCursada((Cursada) this.buscar(((Cursada) obj).getIdentificacion(), comando),
                                     (Cursada) obj);
        break;
    }
  }

  public void agregaAlumnoEnCursada(Alumno alumno, Cursada cursada)
    throws DatoInvalidoException, ClaveYaExistenteException
  {
    this.modelo.agregarAlumnoEnCursada(alumno, cursada);
  }

  public void agregaProfesorEnCursada(Profesor profesor, Cursada cursada)
    throws DatoInvalidoException, ClaveYaExistenteException
  {
    this.modelo.agregarProfesorEnCursada(profesor, cursada);
  }

  public void guardar()
  {
    SerializadorXML.guardar(this.modelo);
  }
}
