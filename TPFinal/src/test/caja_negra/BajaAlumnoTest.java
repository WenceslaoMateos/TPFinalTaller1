package test.caja_negra;

import modelo.Alumno;
import modelo.IndiceDoble;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BajaAlumnoTest
{
    private Sistema sistema;
    private Alumno elim;
    
    public BajaAlumnoTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
        this.elim = new Alumno("PablosBraulio", "Rawson 273", "braulio@gmail.com");
        this.sistema.agregarAlumno(this.elim);
    }

    @After
    public void tearDown()
        throws Exception
    {
        this.sistema = null;
        this.elim = null;
    }

    /**
     * @see modelo.Sistema#eliminarAlumno(modelo.Alumno)
     */
    @Test
    public void testEliminarAlumnoCorrecto()
    {
        this.sistema.eliminarAlumno(this.elim);
    }
    
    /**
     * @see modelo.Sistema#eliminarAlumno(modelo.Alumno)
     */
    @Test
    public void testEliminarAlumnoErroneo2()
    {
        try
        {
            this.sistema.eliminarAlumno(null);
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
        this.sistema.setAlumnos(null);
        try
        {
            this.sistema.eliminarAlumno(this.elim);
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
        this.sistema.setAlumnos(new IndiceDoble<Alumno>());
        try
        {
            this.sistema.eliminarAlumno(this.elim);
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
    public void testEliminarAlumnoErroneo8()
    {
        this.sistema.setCalendario(null);
        try
        {
            this.sistema.eliminarAlumno(this.elim);
            Assert.fail("No debería haber intentado eliminar un elemento nulo.");
        }
        catch (Exception e)
        {
        }
    }
}
