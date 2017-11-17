package test.caja_negra;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.Dia;
import modelo.IndiceDoble;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AlumnoDisponibleTest
{
  private Sistema sistema;
  private Cursada cursada;
  private Cursada superpuesta;
  private Asignatura asignatura;
  private Alumno alumno;

  public AlumnoDisponibleTest()
  {
  }

  @Before
  public void setUp()
    throws Exception
  {
    this.sistema = new Sistema();

    this.asignatura = new Asignatura("Programación 3");
    this.sistema.agregarAsignatura(this.asignatura);

    this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:00");
    this.superpuesta = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:00");
    this.sistema.agregarCursada(this.cursada);
    this.sistema.agregarCursada(this.superpuesta);
    this.sistema.agregarCursada(this.cursada);
    this.sistema.agregarCursada(this.superpuesta);

    this.alumno = new Alumno("PablosBraulio", "Rawson 273", "braulio@gmail.com");
    this.sistema.agregarAlumno(this.alumno);
  }

  @After
  public void tearDown()
    throws Exception
  {
    this.sistema = null;
    this.asignatura = null;
    this.cursada = null;
    this.superpuesta = null;
  }

  /**
   * @see modelo.Sistema#alumnoDisponible(modelo.Alumno,modelo.Cursada)
   */
  @Test
  public void testAlumnoDisponibleCorrectoTrue()
  {
    Assert.assertTrue("El alumno deberia estar disponible.", this.sistema.alumnoDisponible(this.alumno, this.cursada));
  }

  /**
   * @see modelo.Sistema#alumnoDisponible(modelo.Alumno,modelo.Cursada)
   */
  @Test
  public void testAlumnoDisponibleCorrectoFalse()
  {
    try
    {
      this.sistema.agregarAlumnoEnCursada(this.alumno, this.superpuesta);
    }
    catch (ClaveYaExistenteException | DatoInvalidoException e)
    {
      Assert.fail("No deberia existir ningún error en el agregado.");
    }
    Assert.assertFalse("El alumno no deberia estar disponible.",
                       this.sistema.alumnoDisponible(this.alumno, this.cursada));
  }

  /**
   * @see modelo.Sistema#alumnoDisponible(modelo.Alumno,modelo.Cursada)
   */
  @Test
  public void testAlumnoDisponibleErroneo2()
  {
    Assert.assertFalse("El alumno no deberia estar disponible.", this.sistema.alumnoDisponible(null, this.cursada));
  }

  /**
   * @see modelo.Sistema#alumnoDisponible(modelo.Alumno,modelo.Cursada)
   */
  @Test
  public void testAlumnoDisponibleErroneo4()
  {
    Assert.assertFalse("El alumno no deberia estar disponible.", this.sistema.alumnoDisponible(this.alumno, null));
  }

  /**
   * @see modelo.Sistema#alumnoDisponible(modelo.Alumno,modelo.Cursada)
   */
  @Test
  public void testAlumnoDisponibleErroneo6()
  {
    this.sistema.setCalendario(new IndiceDoble<Cursada>());
    Assert.assertFalse("El alumno no deberia estar disponible.",
                       this.sistema.alumnoDisponible(this.alumno, this.cursada));
  }
}
