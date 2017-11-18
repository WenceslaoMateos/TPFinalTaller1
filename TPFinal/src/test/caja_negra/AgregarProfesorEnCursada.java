package test.caja_negra;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import modelo.Cursada;
import modelo.IndiceDoble;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Considero que el caso correcto esta horrorosamente mal excrito asi que lo retoco de acuerdo a la logica del metodo
 */
public class AgregarProfesorEnCursada
{
  SistemaConElementosFixture fixture1 = new SistemaConElementosFixture();

  public AgregarProfesorEnCursada()
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
   * @see modelo.Sistema#agregarProfesorEnCursada(modelo.Profesor,modelo.Cursada)
   */
  @Test
  public void testAgregarProfesorEnCursadaCorrecto()
  {
    try
    {
      this.fixture1
          .sistema
          .agregarProfesorEnCursada(this.fixture1.profesor, this.fixture1.cursada);
      Assert.assertTrue("El profesor no fue agregado correctamente", this.fixture1
                                                                         .cursada
                                                                         .tieneProfesor(this.fixture1.profesor));
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("La clave no deberia existir previamente");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("El dato no deberia ser invalido");
    }
  }

  /**
   * @see modelo.Sistema#agregarProfesorEnCursada(modelo.Profesor,modelo.Cursada)
   */
  @Test
  public void testAgregarProfesorEnCursadaErroneo2()
  {
    try
    {
      this.fixture1
          .sistema
          .agregarProfesorEnCursada(null, this.fixture1.cursada);
      Assert.fail("El profesor no deberia haber sido agregado a la cursada");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("La clave no deberia existir previamente");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("El dato no deberia ser invalido");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarProfesorEnCursada(modelo.Profesor,modelo.Cursada)
   */
  @Test
  public void testAgregarProfesorEnCursadaErroneo4_1()
  {
    try
    {
      this.fixture1
          .sistema
          .agregarProfesorEnCursada(this.fixture1.profesor, null);
      Assert.fail("El profesor no deberia haber sido agregado a la cursada");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("La clave no deberia existir previamente");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("El dato no deberia ser invalido");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarProfesorEnCursada(modelo.Profesor,modelo.Cursada)
   */
  @Test
  public void testAgregarProfesorEnCursadaErroneo4_2()
  {
    try
    {
      this.fixture1
          .sistema
          .agregarProfesorEnCursada(this.fixture1.profesor, this.fixture1.cursada);
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("La clave no deberia existir previamente");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("El dato no deberia ser invalido");
    }
    try
    {
      this.fixture1
          .sistema
          .agregarProfesorEnCursada(this.fixture1.profesor, this.fixture1.cursada);
      Assert.fail("El profesor no deberia haber sido agregado a la cursada");
    }
    catch (ClaveYaExistenteException e)
    {
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("El dato no deberia ser invalido");
    }
  }

  /**
   * @see modelo.Sistema#agregarProfesorEnCursada(modelo.Profesor,modelo.Cursada)
   */
  @Test
  public void testAgregarProfesorEnCursadaErroneo6()
  {
    this.fixture1
        .profesor
        .eliminarCompetencia(this.fixture1.asignatura1);
    try
    {
      this.fixture1
          .sistema
          .agregarProfesorEnCursada(this.fixture1.profesor, this.fixture1.cursada);
      Assert.fail("El profesor no deberia haber sido agregado a la cursada");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("La clave no deberia existir previamente");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarProfesorEnCursada(modelo.Profesor,modelo.Cursada)
   */
  @Test
  public void testAgregarProfesorEnCursadaErroneo8()
  {
    this.fixture1
        .sistema
        .setCalendario(null);
    try
    {
      this.fixture1
          .sistema
          .agregarProfesorEnCursada(this.fixture1.profesor, this.fixture1.cursada);
      Assert.fail("El profesor no deberia haber sido agregado a la cursada");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("La clave no deberia existir previamente");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("El dato no deberia ser invalido");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarProfesorEnCursada(modelo.Profesor,modelo.Cursada)
   */
  @Test
  public void testAgregarProfesorEnCursadaErroneo10()
  {
    this.fixture1
        .sistema
        .setCalendario(new IndiceDoble<Cursada>());
    try
    {
      this.fixture1
          .sistema
          .agregarProfesorEnCursada(this.fixture1.profesor, this.fixture1.cursada);
      Assert.fail("El profesor no deberia haber sido agregado a la cursada");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("La clave no deberia existir previamente");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("El dato no deberia ser invalido");
    }
    catch (Exception e)
    {
    }
  }
}
