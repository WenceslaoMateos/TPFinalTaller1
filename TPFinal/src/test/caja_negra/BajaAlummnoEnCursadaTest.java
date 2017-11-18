package test.caja_negra;

import modelo.Alumno;
import modelo.IndicePrimario;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BajaAlummnoEnCursadaTest
{
  SistemaConElementosFixture fixture1 = new SistemaConElementosFixture();

  public BajaAlummnoEnCursadaTest()
  {
  }

  @Before
  public void setUp()
    throws Exception
  {
    fixture1.setUp();
    this.fixture1
        .sistema
        .agregarAlumnoEnCursada(this.fixture1.alumno, this.fixture1.cursada);
  }

  @After
  public void tearDown()
    throws Exception
  {
    fixture1.tearDown();
  }

  /**
   * @see modelo.Cursada#bajaAlumno(modelo.Alumno)
   */
  @Test
  public void testBajaAlumnoCorrecto()
  {
    this.fixture1
        .cursada
        .bajaAlumno(this.fixture1.alumno);
    Assert.assertFalse("El alumno deberia dejar de estar en la cursada", this.fixture1
                                                                             .cursada
                                                                             .tieneAlumno(this.fixture1.alumno));
  }

  /**
   * @see modelo.Cursada#bajaAlumno(modelo.Alumno)
   */
  @Test
  public void testBajaAlumnoErroneo2()
  {
    try
    {
      this.fixture1
          .cursada
          .bajaAlumno(null);
      Assert.fail("El alumno no deberia poder ser eliminado");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Cursada#bajaAlumno(modelo.Alumno)
   */
  @Test
  public void testBajaAlumnoErroneo4()
  {
    try
    {
      this.fixture1
          .cursada
          .setAlumnos(null);
      this.fixture1
          .cursada
          .bajaAlumno(this.fixture1.alumno);
      Assert.fail("El alumno no deberia poder ser eliminado");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see modelo.Cursada#bajaAlumno(modelo.Alumno)
   */
  @Test
  public void testBajaAlumnoErroneo6()
  {
    this.fixture1.cursada.setAlumnos(new IndicePrimario<Alumno>());
    try
    {
      this.fixture1
          .cursada
          .bajaAlumno(this.fixture1.alumno);
      Assert.fail("El alumno no deberia poder ser eliminado");
    }
    catch (Exception e)
    {
    }
  }
}
