package test.caja_negra;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import modelo.Asignatura;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AltaAsignaturaTest
{
  private Sistema sistema;

  public AltaAsignaturaTest()
  {
  }

  @Before
  public void setUp()
    throws Exception
  {
    this.sistema = new Sistema();
  }

  @After
  public void tearDown()
    throws Exception
  {
    this.sistema = null;
  }

  /**
   * @see modelo.Sistema#agregarAsignatura(modelo.Asignatura)
   */
  @Test
  public void testAgregarAsignaturaCorrecto()
  {
    Asignatura nuevo = new Asignatura("Física 1");
    try
    {
      this.sistema.agregarAsignatura(nuevo);
    }
    catch (ClaveYaExistenteException | DatoInvalidoException e)
    {
      Assert.fail("Salió por excepción cuando debería haber tenido éxito.");
    }
  }

  /**
   * @see modelo.Sistema#agregarAsignatura(modelo.Asignatura)
   */
  @Test
  public void testAgregarAsignaturaCorrectoL()
  {
    Asignatura nuevo = new Asignatura("F");
    try
    {
      this.sistema.agregarAsignatura(nuevo);
    }
    catch (ClaveYaExistenteException | DatoInvalidoException e)
    {
      Assert.fail("Salió por excepción cuando debería haber tenido éxito.");
    }
  }

  /**
   * @see modelo.Sistema#agregarAsignatura(modelo.Asignatura)
   */
  @Test
  public void testAgregarAsignaturaErroneo2()
  {
    Asignatura nuevo = null;
    try
    {
      this.sistema.agregarAsignatura(nuevo);
      Assert.fail("No debería haber agregado un elemento nulo.");
    }
    catch (ClaveYaExistenteException | DatoInvalidoException e)
    {
      Assert.fail("Debería haberse detenido por elemento nulo.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarAsignatura(modelo.Asignatura)
   */
  @Test
  public void testAgregarAsignaturaErroneo4()
  {
    Asignatura nuevo = new Asignatura(null);
    try
    {
      this.sistema.agregarAsignatura(nuevo);
      Assert.fail("No debería haber agregado un elemento incorrecto.");
    }
    catch (ClaveYaExistenteException | DatoInvalidoException e)
    {
      Assert.fail("Debería haberse detenido por contenido nulo.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarAsignatura(modelo.Asignatura)
   */
  @Test
  public void testAgregarAsignaturaErroneo6()
  {
    Asignatura nuevo = new Asignatura(null);
    this.sistema.setPlanDeEstudio(null);
    try
    {
      this.sistema.agregarAsignatura(nuevo);
      Assert.fail("Debería haber salido por nulidad de la colección.");
    }
    catch (ClaveYaExistenteException | DatoInvalidoException e)
    {
      Assert.fail("Debería haber salido por nulidad de la colección.");
    }
    catch (Exception e)
    {
    }
  }
}
