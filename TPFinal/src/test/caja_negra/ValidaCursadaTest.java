package test.caja_negra;

import modelo.Asignatura;
import modelo.Cursada;
import modelo.Dia;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValidaCursadaTest
{
  private Sistema sistema;
  private Cursada cursada;
  private Asignatura asignatura;

  public ValidaCursadaTest()
  {
  }

  @Before
  public void setUp()
    throws Exception
  {
    this.asignatura = new Asignatura("Física 1");
    this.sistema = new Sistema();
    this.sistema.agregarAsignatura(this.asignatura);
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
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaCorrecta()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:00");
    Assert.assertTrue("La cursada deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaCorrectaL1()
  {
    this.cursada = new Cursada(this.asignatura, "01-2017", Dia.DOM, "00:00", "23:59");
    Assert.assertTrue("La cursada deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo2()
  {
    try
    {
      Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(null));
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_1()
  {
    this.cursada = new Cursada(this.asignatura, null, Dia.LUN, "12:00", "14:00");
    try
    {
      Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_2()
  {
    this.cursada = new Cursada(this.asignatura, "", Dia.LUN, "12:00", "14:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_3()
  {
    this.cursada = new Cursada(this.asignatura, "AA2017", Dia.LUN, "12:00", "14:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_3L1()
  {
    this.cursada = new Cursada(this.asignatura, "022017", Dia.LUN, "12:00", "14:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_4()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, null, "14:00");
    try
    {
      Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_5()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "", "14:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_6()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "**:00", "14:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_6L1()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "0/:00", "14:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_7()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "aa:00", "14:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_7L1()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "24:00", "14:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_8()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:**", "14:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_8L1()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:0/", "14:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_9()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:aa", "14:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_9L1()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:5:", "14:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_10()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", null);
    try
    {
      Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_11()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_12()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "**:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_12L1()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "0/:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_13()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "aa:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_13L1()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "24:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_14()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:**");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_14L1()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:0/");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_15()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:aa");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_15L1()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:5:");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_16()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "14:00", "12:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }

  /**
   * @see modelo.Cursada#validaCursada(modelo.Cursada)
   */
  @Test
  public void testValidaCursadaErroneo4_16L1()
  {
    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "14:00", "14:00");
    Assert.assertFalse("La cursada no deberia ser valida", Cursada.validaCursada(this.cursada));
  }
}
