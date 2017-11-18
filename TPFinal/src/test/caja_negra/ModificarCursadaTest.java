package test.caja_negra;

import excepciones.DatoInvalidoException;

import modelo.Asignatura;
import modelo.Cursada;
import modelo.Dia;
import modelo.IndiceDoble;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ModificarCursadaTest
{
  private Sistema sistema;
  private Cursada cursada;
  private Asignatura asignatura;
  private Asignatura otraAsignatura;

  public ModificarCursadaTest()
  {
  }

  @Before
  public void setUp()
    throws Exception
  {
    this.sistema = new Sistema();
    this.asignatura = new Asignatura("Física 1");
    this.sistema.agregarAsignatura(this.asignatura);
    this.otraAsignatura = new Asignatura("Otra");
    this.sistema.agregarAsignatura(this.otraAsignatura);
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:00");
    this.sistema.agregarCursada(this.cursada);
  }

  @After
  public void tearDown()
    throws Exception
  {
    this.sistema = null;
    this.asignatura = null;
    this.cursada = null;
  }

  /**
   * @see Sistema#modificarCursada(Cursada,Cursada)
   */
  @Test
  public void testModificarCursadaCorrecta()
  {
    Cursada modif = new Cursada(this.otraAsignatura, "01-2017", Dia.DOM, "11:00", "13:00");
    try
    {
      this.sistema.modificarCursada(this.cursada, modif);
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("Los datos deberian ser invalidos");
    }
    Assert.assertSame("La asignatura deberia estar modificada", this.otraAsignatura, this.cursada.getAsignatura());
    Assert.assertTrue("El periodo deberia estar modificado", this.cursada
                                                                 .getPeriodo()
                                                                 .equals("01-2017"));
    Assert.assertSame("La asignatura deberia estar modificada", Dia.DOM, this.cursada.getDia());
    Assert.assertTrue("La hora de inicio deberia estar modificada", this.cursada
                                                                        .getHoraInicio()
                                                                        .equals("11:00"));
    Assert.assertTrue("La hora de finalización deberia estar modificada", this.cursada
                                                                              .getHoraFinalizacion()
                                                                              .equals("13:00"));
  }

  /**
   * @see Sistema#modificarCursada(Cursada,Cursada)
   */
  @Test
  public void testModificarCursadaErronea2()
  {
    Cursada modif = new Cursada(this.otraAsignatura, "01-2017", Dia.DOM, "11:00", "13:00");
    try
    {
      this.sistema.modificarCursada(null, modif);
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("Los datos no deberian ser invalidos");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see Sistema#modificarCursada(Cursada,Cursada)
   */
  @Test
  public void testModificarCursadaErronea4_1()
  {
    try
    {
      this.sistema.modificarCursada(this.cursada, null);
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("Los datos no deberian ser invalidos");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see Sistema#modificarCursada(Cursada,Cursada)
   */
  @Test
  public void testModificarCursadaErronea4_2()
  {
    Cursada modif = new Cursada(this.otraAsignatura, "ab-2017", Dia.DOM, "11:00", "13:00");
    try
    {
      this.sistema.modificarCursada(this.cursada, modif);
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see Sistema#modificarCursada(Cursada,Cursada)
   */
  @Test
  public void testModificarCursadaErronea4_3()
  {
    Cursada modif = new Cursada(this.otraAsignatura, "01-2017", Dia.DOM, "ab:00", "13:00");
    try
    {
      this.sistema.modificarCursada(this.cursada, modif);
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see Sistema#modificarCursada(Cursada,Cursada)
   */
  @Test
  public void testModificarCursadaErronea4_4()
  {
    Cursada modif = new Cursada(this.otraAsignatura, "01-2017", Dia.DOM, "11:00", "ab:00");
    try
    {
      this.sistema.modificarCursada(this.cursada, modif);
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see Sistema#modificarCursada(Cursada,Cursada)
   */
  @Test
  public void testModificarCursadaErronea4_5()
  {
    Cursada modif = new Cursada(this.otraAsignatura, "01-2017", Dia.DOM, "11:00", "10:00");
    try
    {
      this.sistema.modificarCursada(this.cursada, modif);
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see Sistema#modificarCursada(Cursada,Cursada)
   */
  @Test
  public void testModificarCursadaErronea4_6()
  {
    Cursada modif = new Cursada(new Asignatura(""), "01-2017", Dia.DOM, "11:00", "13:00");
    try
    {
      this.sistema.modificarCursada(this.cursada, modif);
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("Los datos no deberian ser invalidos");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see Sistema#modificarCursada(Cursada,Cursada)
   */
  @Test
  public void testModificarCursadaErronea6()
  {
    this.sistema.setCalendario(new IndiceDoble<Cursada>());
    Cursada modif = new Cursada(this.otraAsignatura, "01-2017", Dia.DOM, "11:00", "13:00");
    try
    {
      this.sistema.modificarCursada(this.cursada, modif);
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("Los datos no deberian ser invalidos");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see Sistema#modificarCursada(Cursada,Cursada)
   */
  @Test
  public void testModificarCursadaErronea8()
  {
    this.sistema.eliminarAsignatura(this.otraAsignatura);
    Cursada modif = new Cursada(this.otraAsignatura, "01-2017", Dia.DOM, "11:00", "13:00");
    try
    {
      this.sistema.modificarCursada(this.cursada, modif);
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("Los datos no deberian ser invalidos");
    }
    catch (Exception e)
    {
    }
  }
}
