package test.caja_negra;

import excepciones.ClaveYaExistenteException;
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

/**
 * Se debieron reordenar los parametros de los constructores para que sea coherente con el programa.<br>
 * Se debió modificar el enumeration "LUNES" por "LUN" para que tenga coherencia con el programa.<br>
 */
public class AltaCursadaTest
{
  private Sistema sistema;
  private Asignatura asignatura;

  public AltaCursadaTest()
  {
  }

  @Before
  public void setUp()
    throws Exception
  {
    this.sistema = new Sistema();
    this.asignatura = new Asignatura("Física 1");
    this.sistema.agregarAsignatura(this.asignatura);
  }

  @After
  public void tearDown()
    throws Exception
  {
    this.sistema = null;
    this.asignatura = null;
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaCorrecto()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia ser invalida.");
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaCorrectoL1()
  {
    Cursada nuevo = new Cursada(this.asignatura, "01-2017", Dia.DOM, "00:00", "23:59");
    try
    {
      this.sistema.agregarCursada(nuevo);
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia ser invalida.");
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo2()
  {
    Cursada nuevo = null;
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente una cursada nula");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia ser invalida.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_1()
  {
    Cursada nuevo = new Cursada(null, "02-2017", Dia.LUN, "12:00", "14:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia ser invalida.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_2()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:00");
    this.sistema.setPlanDeEstudio(new IndiceDoble<Asignatura>());
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia ser invalida.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_3()
  {
    Cursada nuevo = new Cursada(this.asignatura, null, Dia.LUN, "12:00", "14:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia ser invalida.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_4()
  {
    Cursada nuevo = new Cursada(this.asignatura, "", Dia.LUN, "12:00", "14:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {

    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_5()
  {
    Cursada nuevo = new Cursada(this.asignatura, "0A/@)17", Dia.LUN, "12:00", "14:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }

  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_5L1()
  {
    Cursada nuevo = new Cursada(this.asignatura, "022017", Dia.LUN, "12:00", "14:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_6()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, null, "14:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_7()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "", "14:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_8()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "**:00", "13:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_8L1()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "0/:00", "13:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_9()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "27:00", "13:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_9L1()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "24:00", "13:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_10()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "13:**", "15:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_10L1()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "13:0/", "15:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_11()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "13:80", "15:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_11L1()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "13:5:", "15:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_12()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "13:00", null);
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_13()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "13:00", "");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_14()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "13:00", "**:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_14L1()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "13:00", "0/:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_15()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", null, "12:00", "30:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_15L1()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", null, "12:00", "24:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_16()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:**");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_16L1()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:0/");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_17()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:80");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_17L1()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:5:");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_18()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", null, "12:00", "14:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_19()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "14:00", "12:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo4_19L1()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "14:00", "14:00");
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarCursada(modelo.Cursada)
   */
  @Test
  public void testAgregarCursadaErroneo6()
  {
    Cursada nuevo = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:00");
    this.sistema.setCalendario(null);
    try
    {
      this.sistema.agregarCursada(nuevo);
      Assert.fail("No deberia haber agregado exitosamente");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail(e.getMessage() + " La cursada no deberia existir previamente.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail(e.getMessage() + " El dato no deberia ser invalido.");
    }
    catch (Exception e)
    {
    }
  }
}
