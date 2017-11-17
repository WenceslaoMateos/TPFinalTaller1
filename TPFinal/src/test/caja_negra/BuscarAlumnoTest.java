package test.caja_negra;

import excepciones.NoEncontradoException;

import java.util.Iterator;

import modelo.Alumno;
import modelo.IndiceDoble;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BuscarAlumnoTest
{
  private Sistema sistema;
  private Alumno elemento;

  public BuscarAlumnoTest()
  {
  }

  @Before
  public void setUp()
    throws Exception
  {
    this.sistema = new Sistema();
    this.elemento = new Alumno("PablosBraulio", "Rawson 273", "braulio@gmail.com");
    this.sistema.agregarAlumno(this.elemento);
  }

  @After
  public void tearDown()
    throws Exception
  {
    this.sistema = new Sistema();
  }

  /**
   * @see modelo.Sistema#buscarAlumno(String)
   */
  @Test
  public void testBuscarAlumnoCorrecto()
  {
    try
    {
      Iterator<Alumno> it = this.sistema.buscarAlumno("PablosBraulio");
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.elemento.getApellidoNombre()))
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
      Iterator<Alumno> it = this.sistema.buscarAlumno(null);
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.elemento.getApellidoNombre()))
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
      Iterator<Alumno> it = this.sistema.buscarAlumno("");
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.elemento.getApellidoNombre()))
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
    this.sistema.setAlumnos(null);
    try
    {
      Iterator<Alumno> it = this.sistema.buscarAlumno("PablosBraulio");
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.elemento.getApellidoNombre()))
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
    this.sistema.setAlumnos(new IndiceDoble<Alumno>());
    try
    {
      Iterator<Alumno> it = this.sistema.buscarAlumno("PablosBraulio");
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.elemento.getApellidoNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Algún alumno de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ningún alumno");
    }
    catch (NoEncontradoException e)
    {
    }
  }
}
