package test.caja_negra;

import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class QuitarCompetenciaAProfesorTest
{
  SistemaConElementos fixture1 = new SistemaConElementos();

  public QuitarCompetenciaAProfesorTest()
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
   * @see modelo.Sistema#quitarCompetenciaAProfesor(modelo.Profesor,modelo.Asignatura)
   */
  @Test
  public void testQuitarCompetenciaAProfesorCorrecto()
  {
    this.fixture1
        .sistema
        .quitarCompetenciaAProfesor(this.fixture1.profesor, this.fixture1.asignatura);
    Assert.assertFalse("No se quitó correctamente la asignatura al profesor",
                       this.fixture1
                                                                                  .profesor
                                                                                  .habilitadoParaAsignatura(this.fixture1.asignatura));
  }

  /**
   * @see modelo.Sistema#quitarCompetenciaAProfesor(modelo.Profesor,modelo.Asignatura)
   */
  @Test
  public void testQuitarCompetenciaAProfesorErroneo2()
  {
    try
    {
      this.fixture1
          .sistema
          .quitarCompetenciaAProfesor(null, this.fixture1.asignatura);
      Assert.fail("No deberia haber podido terminar la ejecución del metodo");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#quitarCompetenciaAProfesor(modelo.Profesor,modelo.Asignatura)
   */
  @Test
  public void testQuitarCompetenciaAProfesorErroneo4()
  {
    try
    {
      this.fixture1
          .sistema
          .quitarCompetenciaAProfesor(this.fixture1.profesor, null);
      Assert.fail("No deberia haber podido terminar la ejecución del metodo");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#quitarCompetenciaAProfesor(modelo.Profesor,modelo.Asignatura)
   */
  @Test
  public void testQuitarCompetenciaAProfesorErroneo6()
  {
    try
    {
      this.fixture1
          .sistema
          .setCalendario(null);
      this.fixture1
          .sistema
          .quitarCompetenciaAProfesor(this.fixture1.profesor, this.fixture1.asignatura);
      Assert.fail("No deberia haber podido terminar la ejecución del metodo");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#quitarCompetenciaAProfesor(modelo.Profesor,modelo.Asignatura)
   */
  @Test
  public void testQuitarCompetenciaAProfesorErroneo8()
  {
    try
    {
      this.fixture1
          .profesor
          .setCompetencia(null);
      this.fixture1
          .sistema
          .quitarCompetenciaAProfesor(this.fixture1.profesor, this.fixture1.asignatura);
      Assert.fail("No deberia haber podido terminar la ejecución del metodo");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#quitarCompetenciaAProfesor(modelo.Profesor,modelo.Asignatura)
   */
  @Test
  public void testQuitarCompetenciaAProfesorErroneo10()
  {
    try
    {
      this.fixture1
          .sistema
          .setProfesores(null);
      this.fixture1
          .sistema
          .quitarCompetenciaAProfesor(this.fixture1.profesor, this.fixture1.asignatura);
      Assert.fail("No deberia haber podido terminar la ejecución del metodo");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#quitarCompetenciaAProfesor(modelo.Profesor,modelo.Asignatura)
   */
  @Test
  public void testQuitarCompetenciaAProfesorErroneo12()
  {
    try
    {
      this.fixture1
          .sistema
          .quitarCompetenciaAProfesor(this.fixture1.profesor, this.fixture1.asignatura);
      this.fixture1
          .sistema
          .quitarCompetenciaAProfesor(this.fixture1.profesor, this.fixture1.asignatura);
      Assert.fail("No deberia haber podido terminar la ejecución del metodo");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#quitarCompetenciaAProfesor(modelo.Profesor,modelo.Asignatura)
   */
  @Test
  public void testQuitarCompetenciaAProfesorErroneo14()
  {
    try
    {
      this.fixture1
          .sistema
          .eliminarProfesor(this.fixture1.profesor);
      this.fixture1
          .sistema
          .quitarCompetenciaAProfesor(this.fixture1.profesor, this.fixture1.asignatura);
      Assert.fail("No deberia haber podido terminar la ejecución del metodo");
    }
    catch (Exception e)
    {
    }
  }
}