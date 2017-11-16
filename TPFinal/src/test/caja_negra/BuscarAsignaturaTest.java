package Test.caja_negra;

import excepciones.NoEncontradoException;

import java.util.Iterator;

import modelo.IndiceDoble;
import modelo.Asignatura;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class BuscarAsignaturaTest
{
  private Sistema sistema;
  private Asignatura elemento;

  public BuscarAsignaturaTest()
  {
  }

  @Before
  public void setUp()
    throws Exception
  {
    this.sistema = new Sistema();
    this.elemento = new Asignatura("Programación 3");
    this.sistema.agregarAsignatura(this.elemento);
  }

  @After
  public void tearDown()
    throws Exception
  {
    this.sistema = new Sistema();
  }

  /**
   * @see modelo.Sistema#buscarAsignatura(String)
   */
  @Test
  public void testBuscarAsignaturaCorrecto()
  {
    try
    {
      Iterator<Asignatura> it = this.sistema.buscarAsignatura("Programación 3");
      while (it.hasNext() && it.next()
                               .getNombre()
                               .equals(this.elemento.getNombre()))
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
      Iterator<Asignatura> it = this.sistema.buscarAsignatura(null);
      while (it.hasNext() && it.next()
                               .getNombre()
                               .equals(this.elemento.getNombre()))
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
      Iterator<Asignatura> it = this.sistema.buscarAsignatura("");
      while (it.hasNext() && it.next()
                               .getNombre()
                               .equals(this.elemento.getNombre()))
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
    this.sistema.setPlanDeEstudio(null);
    try
    {
      Iterator<Asignatura> it = this.sistema.buscarAsignatura("Programación 3");
      while (it.hasNext() && it.next()
                               .getNombre()
                               .equals(this.elemento.getNombre()))
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
    this.sistema.setPlanDeEstudio(new IndiceDoble<Asignatura>());
    try
    {
      Iterator<Asignatura> it = this.sistema.buscarAsignatura("Programación 3");
      while (it.hasNext() && it.next()
                               .getNombre()
                               .equals(this.elemento.getNombre()))
        ;
      if (it.hasNext())
        Assert.fail("Alguna Asignatura de la colección no coincide con lo solicitado");
      Assert.fail("Deberia haber avisado que no encontró ninguna Asignatura");
    }
    catch (NoEncontradoException e)
    {
    }
  }
}
