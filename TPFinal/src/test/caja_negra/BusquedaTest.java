package test.caja_negra;

import excepciones.NoEncontradoException;

import java.util.Iterator;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.IndiceDoble;
import modelo.Profesor;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BusquedaTest
{
  SistemaConElementosFixture fixture1 = new SistemaConElementosFixture();

  public BusquedaTest()
  {
  }


  @Before
  public void setUp()
    throws Exception
  {
    fixture1.setUp();
  }

  @After
  public void tearDown()
    throws Exception
  {
    fixture1.tearDown();
  }

  /**
   * @see modelo.Sistema#buscarAlumno(String)
   */
  @Test
  public void testBuscarAlumnoCorrecto()
  {
    try
    {
      Iterator<Alumno> it = this.fixture1.sistema.buscarAlumno("PablosBraulio");
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.fixture1.alumno.getApellidoNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Algún alumno de la colección no coincide con lo solicitado");
    }
    catch (NoEncontradoException e)
    {
      Assert.fail("Error, el alumno deberia encontrarse en la colección.");
    }
  }

  /**
   * @see modelo.Sistema#buscarAlumno(String)
   */
  @Test
  public void testBuscarAlumnoErroneo2_1()
  {
    try
    {
      Iterator<Alumno> it = this.fixture1.sistema.buscarAlumno(null);
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.fixture1.alumno.getApellidoNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Algún alumno de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ningún alumno");
    }
    catch (NoEncontradoException e)
    {
      Assert.fail("Error, el alumno deberia encontrarse en la colección.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#buscarAlumno(String)
   */
  @Test
  public void testBuscarAlumnoErroneo2_2()
  {
    try
    {
      Iterator<Alumno> it = this.fixture1.sistema.buscarAlumno("");
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.fixture1.alumno.getApellidoNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Algún alumno de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ningún alumno");
    }
    catch (NoEncontradoException e)
    {
      Assert.fail("Error, el alumno deberia encontrarse en la colección.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#buscarAlumno(String)
   */
  @Test
  public void testBuscarAlumnoErroneo4()
  {
    this.fixture1.sistema.setAlumnos(null);
    try
    {
      Iterator<Alumno> it = this.fixture1.sistema.buscarAlumno("PablosBraulio");
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.fixture1.alumno.getApellidoNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Algún alumno de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ningún alumno");
    }
    catch (NoEncontradoException e)
    {
      Assert.fail("Error, el alumno deberia encontrarse en la colección.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#buscarAlumno(String)
   */
  @Test
  public void testBuscarAlumnoErroneo6()
  {
    this.fixture1.sistema.setAlumnos(new IndiceDoble<Alumno>());
    try
    {
      Iterator<Alumno> it = this.fixture1.sistema.buscarAlumno("PablosBraulio");
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.fixture1.alumno.getApellidoNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Algún alumno de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ningún alumno");
    }
    catch (NoEncontradoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#buscarProfesor(String)
   */
  @Test
  public void testBuscarProfesorCorrecto()
  {
    try
    {
      Iterator<Profesor> it = this.fixture1.sistema.buscarProfesor("PablosBraulio");
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.fixture1.profesor.getApellidoNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Algún Profesor de la colección no coincide con lo solicitado");
    }
    catch (NoEncontradoException e)
    {
      Assert.fail("Error, el Profesor deberia encontrarse en la colección.");
    }
  }

  /**
   * @see modelo.Sistema#buscarProfesor(String)
   */
  @Test
  public void testBuscarProfesorErroneo2_1()
  {
    try
    {
      Iterator<Profesor> it = this.fixture1.sistema.buscarProfesor(null);
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.fixture1.profesor.getApellidoNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Algún Profesor de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ningún Profesor");
    }
    catch (NoEncontradoException e)
    {
      Assert.fail("Error, el Profesor deberia encontrarse en la colección.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#buscarProfesor(String)
   */
  @Test
  public void testBuscarProfesorErroneo2_2()
  {
    try
    {
      Iterator<Profesor> it = this.fixture1.sistema.buscarProfesor("");
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.fixture1.profesor.getApellidoNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Algún Profesor de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ningún Profesor");
    }
    catch (NoEncontradoException e)
    {
      Assert.fail("Error, el Profesor deberia encontrarse en la colección.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#buscarProfesor(String)
   */
  @Test
  public void testBuscarProfesorErroneo4()
  {
    this.fixture1.sistema.setProfesores(null);
    try
    {
      Iterator<Profesor> it = this.fixture1.sistema.buscarProfesor("LazzurriGuillermo");
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.fixture1.profesor.getApellidoNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Algún Profesor de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ningún Profesor");
    }
    catch (NoEncontradoException e)
    {
      Assert.fail("Error, el Profesor deberia encontrarse en la colección.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#buscarProfesor(String)
   */
  @Test
  public void testBuscarProfesorErroneo6()
  {
    this.fixture1.sistema.setProfesores(new IndiceDoble<Profesor>());
    try
    {
      Iterator<Profesor> it = this.fixture1.sistema.buscarProfesor("LazzurriGuillermo");
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.fixture1.profesor.getApellidoNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Algún Profesor de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ningún Profesor");
    }
    catch (NoEncontradoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#buscarAsignatura(String)
   */
  @Test
  public void testBuscarAsignaturaCorrecto()
  {
    try
    {
      Iterator<Asignatura> it = this.fixture1.sistema.buscarAsignatura("Física 1");
      while (it.hasNext() && it.next()
                               .getNombre()
                               .equals(this.fixture1.asignatura1.getNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Alguna Asignatura de la colección no coincide con lo solicitado");
    }
    catch (NoEncontradoException e)
    {
      Assert.fail("Error, la Asignatura deberia encontrarse en la colección.");
    }
  }

  /**
   * @see modelo.Sistema#buscarAsignatura(String)
   */
  @Test
  public void testBuscarAsignaturaErroneo2_1()
  {
    try
    {
      Iterator<Asignatura> it = this.fixture1.sistema.buscarAsignatura(null);
      while (it.hasNext() && it.next()
                               .getNombre()
                               .equals(this.fixture1.asignatura1.getNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Alguna Asignatura de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ninguna Asignatura");
    }
    catch (NoEncontradoException e)
    {
      Assert.fail("Error, la Asignatura deberia encontrarse en la colección.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#buscarAsignatura(String)
   */
  @Test
  public void testBuscarAsignaturaErroneo2_2()
  {
    try
    {
      Iterator<Asignatura> it = this.fixture1.sistema.buscarAsignatura("");
      while (it.hasNext() && it.next()
                               .getNombre()
                               .equals(this.fixture1.asignatura1.getNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Alguna Asignatura de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ninguna Asignatura");
    }
    catch (NoEncontradoException e)
    {
      Assert.fail("Error, la Asignatura deberia encontrarse en la colección.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#buscarAsignatura(String)
   */
  @Test
  public void testBuscarAsignaturaErroneo4()
  {
    this.fixture1.sistema.setPlanDeEstudio(null);
    try
    {
      Iterator<Asignatura> it = this.fixture1.sistema.buscarAsignatura("Programación 3");
      while (it.hasNext() && it.next()
                               .getNombre()
                               .equals(this.fixture1.asignatura1.getNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Alguna Asignatura de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ninguna Asignatura");
    }
    catch (NoEncontradoException e)
    {
      Assert.fail("Error, la Asignatura deberia encontrarse en la colección.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#buscarAsignatura(String)
   */
  @Test
  public void testBuscarAsignaturaErroneo6()
  {
    this.fixture1.sistema.setPlanDeEstudio(new IndiceDoble<Asignatura>());
    try
    {
      Iterator<Asignatura> it = this.fixture1.sistema.buscarAsignatura("Programación 3");
      while (it.hasNext() && it.next()
                               .getNombre()
                               .equals(this.fixture1.asignatura1.getNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Alguna Asignatura de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ninguna Asignatura");
    }
    catch (NoEncontradoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#buscarCursada(String)
   */
  @Test
  public void testBuscarCursadaCorrecto()
  {
    try
    {
      Iterator<Cursada> it = this.fixture1.sistema.buscarCursada("Física 1");
      while (it.hasNext() && it.next()
                               .getAsignatura()
                               .getNombre()
                               .equals(this.fixture1.cursada
                                           .getAsignatura()
                                           .getNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Alguna Cursada de la colección no coincide con lo solicitado");
    }
    catch (NoEncontradoException e)
    {
      Assert.fail("Error, la Cursada deberia encontrarse en la colección.");
    }
  }

  /**
   * @see modelo.sistema#buscarCursada(String)
   */
  @Test
  public void testBuscarCursadaErroneo2_1()
  {
    try
    {
      Iterator<Cursada> it = this.fixture1.sistema.buscarCursada(null);
      while (it.hasNext() && it.next()
                               .getAsignatura()
                               .getNombre()
                               .equals(this.fixture1.cursada
                                           .getAsignatura()
                                           .getNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Alguna Cursada de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ninguna Cursada");
    }
    catch (NoEncontradoException e)
    {
      Assert.fail("Error, la Cursada deberia encontrarse en la colección.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#buscarCursada(String)
   */
  @Test
  public void testBuscarCursadaErroneo2_2()
  {
    try
    {
      Iterator<Cursada> it = this.fixture1.sistema.buscarCursada("");
      while (it.hasNext() && it.next()
                               .getAsignatura()
                               .getNombre()
                               .equals(this.fixture1.cursada
                                           .getAsignatura()
                                           .getNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Alguna Cursada de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ninguna Cursada");
    }
    catch (NoEncontradoException e)
    {
      Assert.fail("Error, la Cursada deberia encontrarse en la colección.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#buscarCursada(String)
   */
  @Test
  public void testBuscarCursadaErroneo4()
  {
    this.fixture1.sistema.setCalendario(null);
    try
    {
      Iterator<Cursada> it = this.fixture1.sistema.buscarCursada("Programación 3");
      while (it.hasNext() && it.next()
                               .getAsignatura()
                               .getNombre()
                               .equals(this.fixture1.cursada
                                           .getAsignatura()
                                           .getNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Alguna Cursada de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ninguna Cursada");
    }
    catch (NoEncontradoException e)
    {
      Assert.fail("Error, la Cursada deberia encontrarse en la colección.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#buscarCursada(String)
   */
  @Test
  public void testBuscarCursadaErroneo6()
  {
    this.fixture1.sistema.setCalendario(new IndiceDoble<Cursada>());
    try
    {
      Iterator<Cursada> it = this.fixture1.sistema.buscarCursada("Programación 3");
      while (it.hasNext() && it.next()
                               .getAsignatura()
                               .getNombre()
                               .equals(this.fixture1.cursada
                                           .getAsignatura()
                                           .getNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Alguna Cursada de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ninguna Cursada");
    }
    catch (NoEncontradoException e)
    {
    }
  }
}
