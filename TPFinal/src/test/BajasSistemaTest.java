package test;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.IndiceDoble;
import modelo.Profesor;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BajasSistemaTest
{
    SistemaConElementosFixture fixture1 = new SistemaConElementosFixture();

    public BajasSistemaTest()
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
    public void testEliminarAlumnoCorrecto()
    {
        try
        {
            this.fixture1
                .sistema
                .eliminarAlumno(this.fixture1.alumno);
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber tenido éxito.");
        }
    }

    /**
     * @see modelo.Sistema#eliminarAlumno(modelo.Alumno)
     */
    @Test
    public void testEliminarAlumnoErroneo2()
    {
        try
        {
            this.fixture1
                .sistema
                .eliminarAlumno(null);
            Assert.fail("No debería haber intentado eliminar un elemento nulo.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Sistema#eliminarAlumno(modelo.Alumno)
     */
    @Test
    public void testEliminarAlumnoErroneo4()
    {
        this.fixture1
            .sistema
            .setAlumnos(null);
        try
        {
            this.fixture1
                .sistema
                .eliminarAlumno(this.fixture1.alumno);
            Assert.fail("La colección debería haber sido nula.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Sistema#eliminarAlumno(modelo.Alumno)
     */
    @Test
    public void testEliminarAlumnoErroneo6()
    {
        this.fixture1
            .sistema
            .setAlumnos(new IndiceDoble<Alumno>());
        try
        {
            this.fixture1
                .sistema
                .eliminarAlumno(this.fixture1.alumno);
            Assert.fail("No debería haber intentado eliminar un elemento inexistente.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Sistema#eliminarAlumno(modelo.Alumno)
     */
    @Test
    public void testEliminarAlumnoErroneo8()
    {
        this.fixture1
            .sistema
            .setCalendario(null);
        try
        {
            this.fixture1
                .sistema
                .eliminarAlumno(this.fixture1.alumno);
            Assert.fail("No debería haber intentado eliminar un elemento cuando el calendario es nulo.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Sistema#eliminarAsignatura(modelo.Asignatura)
     */
    @Test
    public void testEliminarAsignaturaCorrecto()
    {
        try
        {
            this.fixture1
                .sistema
                .eliminarAsignatura(this.fixture1.asignatura1);
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber tenido éxito.");
        }
    }

    /**
     * @see modelo.Sistema#eliminarAsignatura(modelo.Asignatura)
     */
    @Test
    public void testEliminarAsignaturaErroneo2()
    {
        try
        {
            this.fixture1
                .sistema
                .eliminarAsignatura(null);
            Assert.fail("No debería haber intentado eliminar un elemento nulo.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Sistema#eliminarAsignatura(modelo.Asignatura)
     */
    @Test
    public void testEliminarAsignaturaErroneo4()
    {
        this.fixture1
            .sistema
            .setPlanDeEstudio(null);
        try
        {
            this.fixture1
                .sistema
                .eliminarAsignatura(this.fixture1.asignatura1);
            Assert.fail("La colección debería haber sido nula.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Sistema#eliminarAsignatura(modelo.Asignatura)
     */
    @Test
    public void testEliminarAsignaturaErroneo6()
    {
        this.fixture1
            .sistema
            .setPlanDeEstudio(new IndiceDoble<Asignatura>());
        try
        {
            this.fixture1
                .sistema
                .eliminarAsignatura(this.fixture1.asignatura1);
            Assert.fail("No debería haber intentado eliminar un elemento inexistente.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Sistema#eliminarAsignatura(modelo.Asignatura)
     */
    @Test
    public void testEliminarAsignaturaErroneo8()
    {
        this.fixture1
            .sistema
            .setCalendario(null);
        try
        {
            this.fixture1
                .sistema
                .eliminarAsignatura(this.fixture1.asignatura1);
            Assert.fail("No debería haber intentado eliminar un elemento cuando el calendario es nulo.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Sistema#eliminarProfesor(modelo.Profesor)
     */
    @Test
    public void testEliminarProfesorCorrecto()
    {
        try
        {
            this.fixture1
                .sistema
                .eliminarProfesor(this.fixture1.profesor);
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber tenido éxito.");
        }
    }

    /**
     * @see modelo.Sistema#eliminarProfesor(modelo.Profesor)
     */
    @Test
    public void testEliminarProfesorCorrectoErroneo2()
    {
        try
        {
            this.fixture1
                .sistema
                .eliminarProfesor(null);
            Assert.fail("No debería haber intentado eliminar un elemento nulo.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Sistema#eliminarProfesor(modelo.Profesor)
     */
    @Test
    public void testEliminarProfesorCorrectoErroneo4()
    {
        this.fixture1
            .sistema
            .setProfesores(null);
        try
        {
            this.fixture1
                .sistema
                .eliminarProfesor(this.fixture1.profesor);
            Assert.fail("La colección debería haber sido nula.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Sistema#eliminarProfesor(modelo.Profesor)
     */
    @Test
    public void testEliminarProfesorCorrectoErroneo6()
    {
        this.fixture1
            .sistema
            .setProfesores(new IndiceDoble<Profesor>());
        try
        {
            this.fixture1
                .sistema
                .eliminarProfesor(this.fixture1.profesor);
            Assert.fail("No debería haber intentado eliminar un elemento inexistente.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Sistema#eliminarProfesor(modelo.Profesor)
     */
    @Test
    public void testEliminarProfesorErroneo8()
    {
        this.fixture1
            .sistema
            .setCalendario(null);
        try
        {
            this.fixture1
                .sistema
                .eliminarProfesor(this.fixture1.profesor);
            Assert.fail("No debería haber intentado eliminar un elemento cuando el calendario es nulo.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Sistema#eliminarCursada(modelo.Cursada)
     */
    @Test
    public void testEliminarCursadaCorrecto()
    {
        try
        {
            this.fixture1
                .sistema
                .eliminarCursada(this.fixture1.cursada);
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber tenido éxito.");
        }
    }

    /**
     * @see modelo.Sistema#eliminarCursada(modelo.Cursada)
     */
    @Test
    public void testEliminarCursadaErroneo2()
    {
        try
        {
            this.fixture1
                .sistema
                .eliminarCursada(null);
            Assert.fail("No debería haber intentado eliminar un elemento nulo.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Sistema#eliminarCursada(modelo.Cursada)
     */
    @Test
    public void testEliminarCursadaErroneo4()
    {
        this.fixture1
            .sistema
            .setCalendario(null);
        try
        {
            this.fixture1
                .sistema
                .eliminarCursada(this.fixture1.cursada);
            Assert.fail("La colección debería haber sido nula.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Sistema#eliminarCursada(modelo.Cursada)
     */
    @Test
    public void testEliminarCursadaErroneo6()
    {
        this.fixture1
            .sistema
            .setCalendario(new IndiceDoble<Cursada>());
        try
        {
            this.fixture1
                .sistema
                .eliminarCursada(this.fixture1.cursada);
            Assert.fail("No debería haber intentado eliminar un elemento inexistente.");
        }
        catch (Exception e)
        {
        }
    }
}
