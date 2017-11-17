package test.caja_negra;

import modelo.Asignatura;
import modelo.Cursada;
import modelo.Dia;
import modelo.IndiceDoble;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BajaCursadaTest
{
    private Sistema sistema;
    private Cursada elim;
    
    public BajaCursadaTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        Asignatura asignatura = new Asignatura("Física 1");
        this.sistema = new Sistema();
        this.elim = new Cursada(asignatura, "02-2017", Dia.LUN, "12:00", "14:00");
        this.sistema.agregarCursada(this.elim);
    }

    @After
    public void tearDown()
        throws Exception
    {
    }

    /**
     * @see modelo.Sistema#eliminarCursada(modelo.Cursada)
     */
    @Test
    public void testEliminarCursadaCorrecto()
    {
        this.sistema.eliminarCursada(this.elim);
    }
    
    /**
     * @see modelo.Sistema#eliminarCursada(modelo.Cursada)
     */
    @Test
    public void testEliminarCursadaErroneo2()
    {
        try
        {
            this.sistema.eliminarCursada(null);
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
        this.sistema.setPlanDeEstudio(null);
        try
        {
            this.sistema.eliminarCursada(this.elim);
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
        this.sistema.setPlanDeEstudio(new IndiceDoble<Asignatura>());
        try
        {
            this.sistema.eliminarCursada(this.elim);
            Assert.fail("No debería haber intentado eliminar un elemento inexistente.");
        }
        catch (Exception e)
        {
        }
    }
}
