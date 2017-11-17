package test.caja_negra;

import excepciones.ClaveYaExistenteException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ColeccionesInternasConElementosTest
{
    SistemaConElementos fixture1 = new SistemaConElementos();

    public ColeccionesInternasConElementosTest()
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
     * @see Alumno#agregarHistoria(Asignatura)
     */
    @Test
    public void testAgregarHistoriaErroneo6()
    {
        try
        {
            this.fixture1.alumno.agregarHistoria(this.fixture1.asignatura);
            Assert.fail("Debería haber tirado ClaveYaExistenteException.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.assertSame("El elemento que se intentó agregar no fue lo que disparó la excepción.",
                              this.fixture1.asignatura.getClavePrimaria(), e.getClave());
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber tirado ClaveYaExistenteException.");
        }
    }
    
    /**
     * @see Alumno#eliminarHistoria(Asignatura)
     */
    @Test
    public void testEliminarHistoriaCorrecto()
    {
        try
        {
            this.fixture1.alumno.eliminarHistoria(this.fixture1.asignatura);
            Assert.assertFalse("El elemento no se eliminó correctamente",
                               this.fixture1.alumno.asignaturaAprobada(this.fixture1.asignatura));
        }
        catch (Exception e)
        {
            Assert.fail("El elemento debería haberse eliminado exitosamente.");
        }
    }
    
    /**
     * @see Alumno#eliminarHistoria(Asignatura)
     */
    @Test
    public void testEliminarHistoriaErroneo2()
    {
        try
        {
            this.fixture1.alumno.eliminarHistoria(null);
            Assert.fail("No debería haber intentado eliminar un elemento nulo.");
        }
        catch (Exception e)
        {
        }
    }
    
    /**
     * @see Alumno#eliminarHistoria(Asignatura)
     */
    @Test
    public void testEliminarHistoriaErroneo4()
    {
        this.fixture1.alumno.setHistoria(null);
        try
        {
            this.fixture1.alumno.eliminarHistoria(this.fixture1.asignatura);
            Assert.fail("No debería haber intentado eliminar cuando la colección es nula.");
        }
        catch (Exception e)
        {
        }
    }
    
    /**
     * @see Alumno#eliminarHistoria(Asignatura)
     */
    @Test
    public void testEliminarHistoriaErroneo6()
    {
        this.fixture1.alumno.setHistoria(null);
        try
        {
            this.fixture1.alumno.eliminarHistoria(this.fixture1.asignatura);
            Assert.fail("No debería haber intentado eliminar cuando la colección es nula.");
        }
        catch (Exception e)
        {
        }
    }
}
