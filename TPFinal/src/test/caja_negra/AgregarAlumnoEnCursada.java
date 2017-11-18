package test.caja_negra;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import modelo.Cursada;
import modelo.IndiceDoble;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AgregarAlumnoEnCursada
{
  SistemaConElementosFixture fixture1 = new SistemaConElementosFixture();

  public AgregarAlumnoEnCursada()
  {
  }

  @Before
  public void setUp()
    throws Exception
  {
    fixture1.setUp();
    this.fixture1
        .alumno
        .eliminarHistoria(this.fixture1.asignatura2);
  }

  @After
  public void tearDown()
    throws Exception
  {
    fixture1.tearDown();
  }

  /**
   * @see modelo.Sistema#agregarAlumnoEnCursada(modelo.Alumno,modelo.Cursada)
   */
  @Test
  public void testAgregarAlumnoEnCursadaCorrecto()
  {
    try
    {
      this.fixture1
          .sistema
          .agregarAlumnoEnCursada(this.fixture1.alumno, this.fixture1.cursada);
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("La clave no deberia existir");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("El dato no deberia ser invalido");
    }
  }

  /**
   * @see modelo.Sistema#agregarAlumnoEnCursada(modelo.Alumno,modelo.Cursada)
   */
  @Test
  public void testAgregarAlumnoEnCursadaErroneo2()
  {
    try
    {
      this.fixture1
          .sistema
          .agregarAlumnoEnCursada(null, this.fixture1.cursada);
      Assert.fail("El alumno no deberia ser agregado a la cursada");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("La clave no deberia existir");
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
   * @see modelo.Sistema#agregarAlumnoEnCursada(modelo.Alumno,modelo.Cursada)
   */
  @Test
  public void testAgregarAlumnoEnCursadaErroneo4_1()
  {
    try
    {
      this.fixture1
          .sistema
          .agregarAlumnoEnCursada(this.fixture1.alumno, null);
      Assert.fail("El alumno no deberia ser agregado a la cursada");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("La clave no deberia existir");
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
   * @see modelo.Sistema#agregarAlumnoEnCursada(modelo.Alumno,modelo.Cursada)
   */
  @Test
  public void testAgregarAlumnoEnCursadaErroneo4_2()
  {
    try
    {
      this.fixture1
          .sistema
          .agregarAlumnoEnCursada(this.fixture1.alumno, this.fixture1.cursada);
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("La clave no deberia existir");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("El dato no deberia ser invalido");
    }
    catch (Exception e)
    {
      Assert.fail("Deberia agregarlo correctamente.");
    }
    try
    {
      this.fixture1
          .sistema
          .agregarAlumnoEnCursada(this.fixture1.alumno, this.fixture1.cursada);
      Assert.fail("No deberia poder agregar al alumno");
    }
    catch (ClaveYaExistenteException e)
    {
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("El dato no deberia ser invalido");
    }
    catch (Exception e)
    {
      Assert.fail("No deberia terminar por este error");
    }
  }

  /**
   * @see modelo.Sistema#agregarAlumnoEnCursada(modelo.Alumno,modelo.Cursada)
   */
  @Test
  public void testAgregarAlumnoEnCursadaErroneo6()
  {
    try
    {
      this.fixture1
          .alumno
          .agregarHistoria(this.fixture1.asignatura2);
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("No deberia existir la asignatura previamente.");
    }
    try
    {
      this.fixture1
          .sistema
          .agregarAlumnoEnCursada(this.fixture1.alumno, this.fixture1.cursada);
      Assert.fail("No deberia poder agregar el alumno a la cursada");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("La clave no deberia existir");
    }
    catch (DatoInvalidoException e)
    {
    }
  }

  /**
   * @see modelo.Sistema#agregarAlumnoEnCursada(modelo.Alumno,modelo.Cursada)
   */
  @Test
  public void testAgregarAlumnoEnCursadaErroneo8()
  {
    this.fixture1
        .sistema
        .setCalendario(null);
    try
    {
      this.fixture1
          .sistema
          .agregarAlumnoEnCursada(this.fixture1.alumno, this.fixture1.cursada);
      Assert.fail("No deberia poder agregar el alumno a la cursada");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("La clave no deberia existir");
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
   * @see modelo.Sistema#agregarAlumnoEnCursada(modelo.Alumno,modelo.Cursada)
   */
  @Test
  public void testAgregarAlumnoEnCursadaErroneo10()
  {
    this.fixture1
        .sistema
        .setCalendario(new IndiceDoble<Cursada>());
    try
    {
      this.fixture1
          .sistema
          .agregarAlumnoEnCursada(this.fixture1.alumno, this.fixture1.cursada);
      Assert.fail("No deberia poder agregar el alumno a la cursada");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("La clave no deberia existir");
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
