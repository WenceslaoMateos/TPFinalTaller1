package test.caja_negra;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.Dia;
import modelo.IndiceDoble;
import modelo.Profesor;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AltasEscenarioNormal
{
  private Sistema sistema;
  private Asignatura asignatura;

  public AltasEscenarioNormal()
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
    catch (Exception e)
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
    catch (Exception e)
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
    catch (Exception e)
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
    catch (Exception e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
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
    catch (Exception e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
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
    catch (Exception e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
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
    catch (Exception e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
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
    catch (Exception e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
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

  /**
   * @see Sistema#agregarProfesor(Profesor)
   */
  @Test
  public void testAgregarProfesorCorrecto()
  {
    Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "braulio@gmail.com", "155555555");
    try
    {
      this.sistema.agregarProfesor(nuevo);
    }
    catch (Exception e)
    {
      Assert.fail("Salió por excepción cuando debería haber tenido éxito.");
    }
  }

  /**
   * @see Sistema#agregarProfesor(Profesor)
   */
  @Test
  public void testAgregarProfesorCorrectoL1()
  {
    Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "b@gmail.com", "155555555");
    try
    {
      this.sistema.agregarProfesor(nuevo);
    }
    catch (Exception e)
    {
      Assert.fail("Salió por excepción cuando debería haber tenido éxito.");
    }
  }

  /**
   * @see Sistema#agregarProfesor(Profesor)
   */
  @Test
  public void testAgregarProfesorCorrectoL2()
  {
    Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "braulio@g", "155555555");
    try
    {
      this.sistema.agregarProfesor(nuevo);
    }
    catch (Exception e)
    {
      Assert.fail("Salió por excepción cuando debería haber tenido éxito.");
    }
  }

  /**
   * @see Sistema#agregarProfesor(Profesor)
   */
  @Test
  public void testAgregarProfesorErroneo2()
  {
    Profesor nuevo = null;
    try
    {
      this.sistema.agregarProfesor(nuevo);
      Assert.fail("El profesor erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException | DatoInvalidoException e)
    {
      Assert.fail("Ninguna de las excepciones declaradas contempla null.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see Sistema#agregarProfesor(Profesor)
   */
  @Test
  public void testAgregarProfesorErroneo4_1()
  {
    Profesor nuevo = new Profesor(null, "Rawson 273", "braulio@gmail", "155555555");
    try
    {
      this.sistema.agregarProfesor(nuevo);
      Assert.fail("El profesor erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException | DatoInvalidoException e)
    {
      Assert.fail("Ninguna de las excepciones declaradas contempla null.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see Sistema#agregarProfesor(Profesor)
   */
  @Test
  public void testAgregarProfesorErroneo4_2()
  {
    Profesor nuevo = new Profesor("", "Rawson 273", "braulio@gmail", "155555555");
    try
    {
      this.sistema.agregarProfesor(nuevo);
      Assert.fail("El profesor erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.assertSame("El profesor erróneo no fue el objeto que disparó la excepción.", nuevo, e.getDato());
    }
    catch (Exception e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
    }
  }

  /**
   * @see Sistema#agregarProfesor(Profesor)
   */
  @Test
  public void testAgregarProfesorErroneo4_3()
  {
    Profesor nuevo = new Profesor("PablosBraulio", null, "braulio@gmail", "155555555");
    try
    {
      this.sistema.agregarProfesor(nuevo);
      Assert.fail("El profesor erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException | DatoInvalidoException e)
    {
      Assert.fail("Ninguna de las excepciones declaradas contempla null.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see Sistema#agregarProfesor(Profesor)
   */
  @Test
  public void testAgregarProfesorErroneo4_4()
  {
    Profesor nuevo = new Profesor("PablosBraulio", "", "braulio@gmail", "155555555");
    try
    {
      this.sistema.agregarProfesor(nuevo);
      Assert.fail("El profesor erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException | DatoInvalidoException e)
    {
      Assert.fail("Debería haber sido error en la lógica del programa.");
    }
    catch (Exception e)
    {
    }
  }

  /**
   * @see Sistema#agregarProfesor(Profesor)
   */
  @Test
  public void testAgregarProfesorErroneo4_5()
  {
    Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "brauliogmail", "155555555");
    try
    {
      this.sistema.agregarProfesor(nuevo);
      Assert.fail("El profesor erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.assertSame("El profesor erróneo no fue el objeto que disparó la excepción.", nuevo, e.getDato());
    }
    catch (Exception e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
    }
  }

  /**
   * @see Sistema#agregarProfesor(Profesor)
   */
  @Test
  public void testAgregarProfesorErroneo4_6()
  {
    Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "@gmail", "155555555");
    try
    {
      this.sistema.agregarProfesor(nuevo);
      Assert.fail("El profesor erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.assertSame("El profesor erróneo no fue el objeto que disparó la excepción.", nuevo, e.getDato());
    }
    catch (Exception e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
    }
  }

  /**
   * @see Sistema#agregarProfesor(Profesor)
   */
  @Test
  public void testAgregarProfesorErroneo4_7()
  {
    Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "braulio@", "155555555");
    try
    {
      this.sistema.agregarProfesor(nuevo);
      Assert.fail("El profesor erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.assertSame("El profesor erróneo no fue el objeto que disparó la excepción.", nuevo, e.getDato());
    }
    catch (Exception e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
    }
  }

  /**
   * @see Sistema#agregarProfesor(Profesor)
   */
  @Test
  public void testAgregarProfesorErroneo4_8()
  {
    Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "braulio@", null);
    try
    {
      this.sistema.agregarProfesor(nuevo);
      Assert.fail("El profesor erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.assertSame("El profesor erróneo no fue el objeto que disparó la excepción.", nuevo, e.getDato());
    }
    catch (Exception e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
    }
  }

  /**
   * @see Sistema#agregarProfesor(Profesor)
   */
  @Test
  public void testAgregarProfesorErroneo4_9()
  {
    Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "braulio@", "");
    try
    {
      this.sistema.agregarProfesor(nuevo);
      Assert.fail("El profesor erróneo fue agregado.");
    }
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.assertSame("El profesor erróneo no fue el objeto que disparó la excepción.", nuevo, e.getDato());
    }
    catch (Exception e)
    {
      Assert.fail("Debería haber sido DatoInvalidoException.");
    }
  }

  /**
   * @see Sistema#agregarProfesor(Profesor)
   */
  @Test
  public void testAgregarProfesorErroneo6()
  {
    Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "braulio@gmail.com", "155555555");
    this.sistema.setProfesores(null);
    try
    {
      this.sistema.agregarProfesor(nuevo);
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
   * @see Sistema#agregarAsignatura(Asignatura)
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
    catch (ClaveYaExistenteException e)
    {
      Assert.fail("Debería haber salido por nulidad de la colección.");
    }
    catch (DatoInvalidoException e)
    {
      Assert.fail("Debería haber salido por nulidad de la colección.");
    }
    catch (Exception e)
    {
    }
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
