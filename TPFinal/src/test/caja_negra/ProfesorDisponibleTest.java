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

public class ProfesorDisponibleTest
{
  private Sistema sistema;
  private Cursada cursada;
  private Cursada superpuesta;
  private Profesor profesor;
  private Asignatura asignatura;
  private Alumno alumno;

  public ProfesorDisponibleTest()
  {
  }

  @Before
  public void setUp()
    throws Exception
  {
    this.sistema = new Sistema();

    this.asignatura = new Asignatura("Programación 3");
    this.sistema.agregarAsignatura(this.asignatura);

    this.profesor = new Profesor("LazzurriGuillermo", "Rawson 273", "guillermo@gmail.com", "155555555");
    this.sistema.agregarProfesor(this.profesor);
    this.profesor.agregarCompetencia(this.asignatura);

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
    this.profesor = null;
    this.asignatura = null;
    this.cursada = null;
    this.superpuesta = null;
  }

  /**
   * @see modelo.Sistema#profesorDisponible(modelo.Profesor,modelo.Cursada)
   */
  @Test
  public void testProfesorDisponibleCorrectoTrue()
  {
    Assert.assertTrue("El profesor deberia estar disponible.",
                      this.sistema.profesorDisponible(this.profesor, this.cursada));
  }

  /**
   * @see modelo.Sistema#profesorDisponible(modelo.Profesor,modelo.Cursada)
   */
  @Test
  public void testProfesorDisponibleCorrectoFalse()
  {
    try
    {
      this.sistema.agregarProfesorEnCursada(this.profesor, this.superpuesta);
    }
    catch (ClaveYaExistenteException | DatoInvalidoException e)
    {
      Assert.fail("No deberia existir ningún error en el agregado.");
    }
    Assert.assertFalse("El profesor no deberia estar disponible.",
                       this.sistema.profesorDisponible(this.profesor, this.cursada));
  }

  /**
   * @see modelo.Sistema#profesorDisponible(modelo.Profesor,modelo.Cursada)
   */
  @Test
  public void testProfesorDisponibleErroneo2()
  {
    Assert.assertFalse("El profesor no deberia estar disponible", this.sistema.profesorDisponible(null, this.cursada));
  }

  /**
   * @see modelo.Sistema#profesorDisponible(modelo.Profesor,modelo.Cursada)
   */
  @Test
  public void testProfesorDisponibleErroneo4()
  {
    Assert.assertFalse("El profesor no deberia estar disponible", this.sistema.profesorDisponible(this.profesor, null));
  }

  /**
   * @see modelo.Sistema#profesorDisponible(modelo.Profesor,modelo.Cursada)
   */
  @Test
  public void testProfesorDisponibleErroneo6()
  {
    this.sistema.setCalendario(new IndiceDoble<Cursada>());
    Assert.assertFalse("El profesor no deberia estar disponible",
                       this.sistema.profesorDisponible(this.profesor, this.cursada));
  }
}
