package test.caja_negra;

import excepciones.NoEncontradoException;

import java.util.Iterator;

import modelo.Asignatura;
import modelo.Cursada;
import modelo.Dia;
import modelo.IndiceDoble;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BuscarCursadaTest
{
  private Sistema sistema;
  private Asignatura asignatura;
  private Cursada elemento;

  public BuscarCursadaTest()
  {
  }

  @Before
  public void setUp()
    throws Exception
  {
    this.sistema = new Sistema();
    this.asignatura = new Asignatura("Programación 3");
    this.sistema.agregarAsignatura(this.asignatura);
    this.elemento = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:00");
    this.sistema.agregarCursada(this.elemento);
  }

  @After
  public void tearDown()
    throws Exception
  {
    this.sistema = new Sistema();
  }

  /**
   * @see modelo.Sistema#buscarCursada(String)
   */
  @Test
  public void testBuscarCursadaCorrecto()
  {
    try
    {
      Iterator<Cursada> it = this.sistema.buscarCursada("Programación 3");
      while (it.hasNext() && it.next()
                               .getAsignatura()
                               .getNombre()
                               .equals(this.elemento
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
   * @see modelo.Sistema#buscarCursada(String)
   */
  @Test
  public void testBuscarCursadaErroneo2_1()
  {
    try
    {
      Iterator<Cursada> it = this.sistema.buscarCursada(null);
      while (it.hasNext() && it.next()
                               .getAsignatura()
                               .getNombre()
                               .equals(this.elemento
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
      Iterator<Cursada> it = this.sistema.buscarCursada("");
      while (it.hasNext() && it.next()
                               .getAsignatura()
                               .getNombre()
                               .equals(this.elemento
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
    this.sistema.setCalendario(null);
    try
    {
      Iterator<Cursada> it = this.sistema.buscarCursada("Programación 3");
      while (it.hasNext() && it.next()
                               .getAsignatura()
                               .getNombre()
                               .equals(this.elemento
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
    this.sistema.setCalendario(new IndiceDoble<Cursada>());
    try
    {
      Iterator<Cursada> it = this.sistema.buscarCursada("Programación 3");
      while (it.hasNext() && it.next()
                               .getAsignatura()
                               .getNombre()
                               .equals(this.elemento
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
