package test.caja_negra;

import excepciones.NoEncontradoException;

import java.util.Iterator;

import modelo.IndiceDoble;
import modelo.Profesor;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BuscarProfesorTest
{
  private Sistema sistema;
  private Profesor elemento;

  public BuscarProfesorTest()
  {
  }

  @Before
  public void setUp()
    throws Exception
  {
    this.sistema = new Sistema();
    this.elemento = new Profesor("LazzurriGuillermo", "Rawson 273", "guillermo@gmail.com", "155555555");
    this.sistema.agregarProfesor(this.elemento);
  }

  @After
  public void tearDown()
    throws Exception
  {
    this.sistema = new Sistema();
  }

  /**
   * @see modelo.Sistema#buscarProfesor(String)
   */
  @Test
  public void testBuscarProfesorCorrecto()
  {
    try
    {
      Iterator<Profesor> it = this.sistema.buscarProfesor("LazzurriGuillermo");
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.elemento.getApellidoNombre()))
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
      Iterator<Profesor> it = this.sistema.buscarProfesor(null);
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.elemento.getApellidoNombre()))
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
      Iterator<Profesor> it = this.sistema.buscarProfesor("");
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.elemento.getApellidoNombre()))
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
    this.sistema.setProfesores(null);
    try
    {
      Iterator<Profesor> it = this.sistema.buscarProfesor("LazzurriGuillermo");
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.elemento.getApellidoNombre()))
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
    this.sistema.setProfesores(new IndiceDoble<Profesor>());
    try
    {
      Iterator<Profesor> it = this.sistema.buscarProfesor("LazzurriGuillermo");
      while (it.hasNext() && it.next()
                               .getApellidoNombre()
                               .equals(this.elemento.getApellidoNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Algún Profesor de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ningún Profesor");
    }
    catch (NoEncontradoException e)
    {
    }
  }
}
