package test.caja_negra;

import modelo.IndiceDoble;
import modelo.Profesor;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BajaProfesorTest
{
    private Sistema sistema;
    private Profesor elim;
    
    public BajaProfesorTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
        this.elim = new Profesor("PablosBraulio", "Rawson 273", "braulio@gmail.com", "155555555");
        this.sistema.agregarProfesor(this.elim);
    }

    @After
    public void tearDown()
        throws Exception
    {
        this.sistema = null;
        this.elim = null;
    }

    /**
     * @see modelo.Sistema#eliminarProfesor(modelo.Profesor)
     */
    @Test
    public void testEliminarProfesorCorrecto()
    {
        this.sistema.eliminarProfesor(elim);
    }
    
    /**
     * @see modelo.Sistema#eliminarProfesor(modelo.Profesor)
     */
    @Test
    public void testEliminarProfesorCorrectoErroneo2()
    {
        try
        {
            this.sistema.eliminarProfesor(null);
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
        this.sistema.setProfesores(null);
        try
        {
            this.sistema.eliminarProfesor(this.elim);
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
        this.sistema.setProfesores(new IndiceDoble<Profesor>());
        try
        {
            this.sistema.eliminarProfesor(this.elim);
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
    public void testEliminarProfesorErroneo8()
    {
        this.sistema.setCalendario(null);
        try
        {
            this.sistema.eliminarProfesor(this.elim);
            Assert.fail("No debería haber intentado eliminar un elemento cuando el calendario es nulo.");
        }
        catch (Exception e)
        {
        }
    }
}
