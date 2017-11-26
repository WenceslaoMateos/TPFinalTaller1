package test;

import java.util.Iterator;

import modelo.Cursada;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BajasCoberturaFaltanteTest
{
    CursadasConElementosFixture fixture1 = new CursadasConElementosFixture();

    public BajasCoberturaFaltanteTest()
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
}
