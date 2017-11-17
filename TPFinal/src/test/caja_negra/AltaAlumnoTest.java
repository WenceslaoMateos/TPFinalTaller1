package test.caja_negra;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import modelo.Alumno;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AltaAlumnoTest
{
  private Sistema sistema;

  public AltaAlumnoTest()
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
   * @see Sistema#agregarAlumno(Alumno)
   */
  @Test
  public void testAgregarAlumnoCorrecto()
  {
    Alumno nuevo = new Alumno("PablosBraulio", "Rawson 273", "braulio@gmail.com");
    try
    {
      this.sistema.agregarAlumno(nuevo);
    }
    catch (ClaveYaExistenteException | DatoInvalidoException e)
    {
      Assert.fail("Salió por excepción cuando debería haber tenido éxito.");
    }
  }

  /**
   * @see Sistema#agregarAlumno(Alumno)
   */
  @Test
  public void testAgregarAlumnoCorrectoL1()
  {
    Alumno nuevo = new Alumno("P", "7", "b@gmail.com");
    try
    {
      this.sistema.agregarAlumno(nuevo);
    }
    catch (ClaveYaExistenteException | DatoInvalidoException e)
    {
      Assert.fail("Salió por excepción cuando debería haber tenido éxito.");
    }
  }

  /**
   * @see Sistema#agregarAlumno(Alumno)
   */
  @Test
  public void testAgregarAlumnoCorrectoL2()
  {
    Alumno nuevo = new Alumno("PablosBraulio", "Rawson 273", "braulio@g");
    try
    {
      this.sistema.agregarAlumno(nuevo);
    }
    catch (ClaveYaExistenteException | DatoInvalidoException e)
    {
      Assert.fail("Salió por excepción cuando debería haber tenido éxito.");
    }
  }

  /**
   * @see Sistema#agregarAlumno(Alumno)
   */
  @Test
  public void testAgregarAlumnoErroneo2()
  {
    Alumno nuevo = null;
    try
    {
      this.sistema.agregarAlumno(nuevo);
      Assert.fail("El alumno erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("No debería haber intentado agregar siquiera.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.assertSame("El objeto contenido no es el alumno que se intentó agregar.", nuevo, e.getDato());
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see Sistema#agregarAlumno(Alumno)
   */
  @Test
  public void testAgregarAlumnoErroneo4_1()
  {
    Alumno nuevo = new Alumno(null, "Rawson 273", "braulio@gmail.com");
    try
    {
      this.sistema.agregarAlumno(nuevo);
      Assert.fail("El alumno erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("No debería haber intentado agregar siquiera.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.assertSame("El objeto contenido no es el alumno que se intentó agregar.", nuevo, e.getDato());
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see Sistema#agregarAlumno(Alumno)
   */
  @Test
  public void testAgregarAlumnoErroneo4_2()
  {
    Alumno nuevo = new Alumno("", "Rawson 273", "braulio@gmail.com");
    try
    {
      this.sistema.agregarAlumno(nuevo);
      Assert.fail("El alumno erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("No debería haber intentado agregar siquiera.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.assertSame("El objeto contenido no es el alumno que se intentó agregar.", nuevo, e.getDato());
    }
  }

  /**
   * @see Sistema#agregarAlumno(Alumno)
   */
  @Test
  public void testAgregarAlumnoErroneo4_3()
  {
    Alumno nuevo = new Alumno("PablosBraulio", null, "braulio@gmail.com");
    try
    {
      this.sistema.agregarAlumno(nuevo);
      Assert.fail("El alumno erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("No debería haber intentado agregar siquiera.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("No debería haber intentado agregar siquiera.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see Sistema#agregarAlumno(Alumno)
   */
  @Test
  public void testAgregarAlumnoErroneo4_4()
  {
    Alumno nuevo = new Alumno("PablosBraulio", "", "braulio@gmail.com");
    try
    {
      this.sistema.agregarAlumno(nuevo);
      Assert.fail("El alumno erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("No debería haber intentado agregar siquiera.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("No debería haber intentado agregar siquiera.");
    }
  }

  /**
   * @see Sistema#agregarAlumno(Alumno)
   */
  @Test
  public void testAgregarAlumnoErroneo4_5()
  {
    Alumno nuevo = new Alumno("PablosBraulio", "Rawson 273", "brauliogmail.com");
    try
    {
      this.sistema.agregarAlumno(nuevo);
      Assert.fail("El alumno erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("No debería haber intentado agregar siquiera.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.assertSame("El objeto contenido no es el alumno que se intentó agregar.", nuevo, e.getDato());
    }
  }

  /**
   * @see Sistema#agregarAlumno(Alumno)
   */
  @Test
  public void testAgregarAlumnoErroneo4_6()
  {
    Alumno nuevo = new Alumno("PablosBraulio", "Rawson 273", "@gmail.com");
    try
    {
      this.sistema.agregarAlumno(nuevo);
      Assert.fail("El alumno erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("No debería haber intentado agregar siquiera.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.assertSame("El objeto contenido no es el alumno que se intentó agregar.", nuevo, e.getDato());
    }
  }

  /**
   * @see Sistema#agregarAlumno(Alumno)
   */
  @Test
  public void testAgregarAlumnoErroneo4_7()
  {
    Alumno nuevo = new Alumno("PablosBraulio", "Rawson 273", "braulio@");
    try
    {
      this.sistema.agregarAlumno(nuevo);
      Assert.fail("El alumno erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("No debería haber intentado agregar siquiera.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.assertSame("El objeto contenido no es el alumno que se intentó agregar.", nuevo, e.getDato());
    }
  }

  /**
   * @see Sistema#agregarAlumno(Alumno)
   */
  @Test
  public void testAgregarAlumnoErroneo6()
  {
    Alumno nuevo = new Alumno("PablosBraulio", "Rawson 273", "braulio@gmail.com");
    this.sistema.setAlumnos(null);
    try
    {
      this.sistema.agregarAlumno(nuevo);
      Assert.fail("No debería siquiera haber colección.");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("No debería siquiera haber colección.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("No debería siquiera haber colección.");
    }
    catch (Exception e)
    {
    }
  }
}
