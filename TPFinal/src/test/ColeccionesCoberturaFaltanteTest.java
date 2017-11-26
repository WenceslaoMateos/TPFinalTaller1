package test;

import excepciones.DatoInvalidoException;

import java.util.Iterator;

import modelo.Cursada;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ColeccionesCoberturaFaltanteTest
{
    CursadasConElementosFixture fixture1 = new CursadasConElementosFixture();

    public ColeccionesCoberturaFaltanteTest()
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
     * @see modelo.Sistema#eliminarAlumno(modelo.Alumno)
     */
    @Test
    public void testEliminarAlumnoCoberturaT3()
    {
        Iterator<Cursada> it;
        this.fixture1.sistema.eliminarAlumno(this.fixture1.alumno);
        Assert.assertFalse("El alumno debería haber sido eliminado.",
                           this.fixture1.sistema.getAlumnos().contieneValor(this.fixture1.alumno));
        it = this.fixture1.sistema.getCalendario().elementosPorClavePrimaria();
        while (it.hasNext())
            Assert.assertFalse("El alumno debería haber sido eliminado de todas las cursadas.",
                               it.next().tieneAlumno(this.fixture1.alumno));
    }
    
    /**
     * @see modelo.Sistema#eliminarProfesor(modelo.Profesor)
     */
    @Test
    public void testEliminarProfesorCoberturaT3()
    {
        Iterator<Cursada> it;
        this.fixture1.sistema.eliminarProfesor(this.fixture1.profesor);
        Assert.assertFalse("El profesor debería haber sido eliminado.",
                           this.fixture1.sistema.getProfesores().contieneValor(this.fixture1.profesor));
        it = this.fixture1.sistema.getCalendario().elementosPorClavePrimaria();
        while (it.hasNext())
            Assert.assertFalse("El profesor debería haber sido eliminado de todas las cursadas.",
                               it.next().tieneProfesor(this.fixture1.profesor));
    }

    /**
     * @see modelo.Sistema#quitarCompetenciaAProfesor(modelo.Profesor,modelo.Asignatura)
     */
    @Test
    public void testQuitarCompetenciaAProfesorCoberturaT2()
    {
        try
        {
            this.fixture1.sistema.quitarCompetenciaAProfesor(this.fixture1.profesor, this.fixture1.asignatura1);
            Assert.fail("No debería haber intentado eliminar una competencia de un profesor que no la tiene.");
        }
        catch (Exception e)
        {
        }
    }
    
    /**
     * @see modelo.Sistema#quitarCompetenciaAProfesor(modelo.Profesor,modelo.Asignatura)
     */
    @Test
    public void testQuitarCompetenciaAProfesorCoberturaT3()
    {
        Iterator<Cursada> it;
        Cursada aux;
        try
        {
            this.fixture1.sistema.quitarCompetenciaAProfesor(this.fixture1.profesor, this.fixture1.asignatura2);
            Assert.assertFalse("El profesor no debería seguir teniendo la asignatura.",
                              this.fixture1.profesor.habilitadoParaAsignatura(this.fixture1.asignatura2));
            it = this.fixture1.sistema.getCalendario().elementosPorClavePrimaria();
            while (it.hasNext())
            {
                aux = it.next();
                if (aux.getAsignatura().equals(this.fixture1.asignatura2))
                    Assert.assertFalse("El profesor ya no debería participar en ninguna cursada de la asignatura.",
                                       aux.tieneProfesor(this.fixture1.profesor));
            }
        }
        catch (Exception e)
        {
            Assert.fail("No debería haber fallado la ejecución del método.");
        }
    }

    /**
     * @see modelo.Sistema#agregarAlumnoEnCursada(modelo.Alumno,modelo.Cursada)
     */
    @Test
    public void testAgregarAlumnoEnCursadaCoberturaT1()
    {
        try
        {
            this.fixture1.sistema.agregarAlumnoEnCursada(this.fixture1.alumno, this.fixture1.cursada1);
            Assert.fail("Debería haber salido por DatoInvalidoException.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El alumno que se intentó agregar no fue lo que desató la excepción.",
                              this.fixture1.alumno, e.getDato());
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber salido por DatoInvalidoException.");
        }
    }

    /**
     * @see modelo.Sistema#agregarProfesorEnCursada(modelo.Profesor,modelo.Cursada)
     */
    @Test
    public void testAgregarProfesorEnCursadaCoberturaT1()
    {
        try
        {
            this.fixture1.sistema.agregarProfesorEnCursada(this.fixture1.profesor, this.fixture1.cursada1);
            Assert.fail("Debería haber salido por DatoInvalidoException.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El profesor que se intentó agregar no fue lo que desató la excepción.",
                              this.fixture1.profesor, e.getDato());
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber salido por DatoInvalidoException.");
        }
    }
}
