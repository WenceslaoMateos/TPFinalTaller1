package test.caja_negra;

import modelo.Asignatura;
import modelo.IndiceDoble;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BajaAsignaturaTest
{
    private Sistema sistema;
    private Asignatura elim;
    
    public BajaAsignaturaTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
        this.elim = new Asignatura("Física 1");
        this.sistema.agregarAsignatura(this.elim);
    }

    @After
    public void tearDown()
        throws Exception
    {
    }

    /**
     * @see modelo.Sistema#eliminarAsignatura(modelo.Asignatura)
     */
    @Test
    public void testEliminarAsignaturaCorrecto()
    {
        this.sistema.eliminarAsignatura(this.elim);
    }
    
    /**
     * @see modelo.Sistema#eliminarAsignatura(modelo.Asignatura)
     */
    @Test
    public void testEliminarAsignaturaErroneo2()
    {
        try
        {
            this.sistema.eliminarAsignatura(null);
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
        this.sistema.setPlanDeEstudio(null);
        try
        {
            this.sistema.eliminarAsignatura(this.elim);
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
        this.sistema.setPlanDeEstudio(new IndiceDoble<Asignatura>());
        try
        {
            this.sistema.eliminarAsignatura(this.elim);
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
        this.sistema.setCalendario(null);
        try
        {
            this.sistema.eliminarAsignatura(this.elim);
            Assert.fail("No debería haber intentado eliminar un elemento cuando el calendario es nulo.");
        }
        catch (Exception e)
        {
        }
    }
}
