package test.caja_negra;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import modelo.Asignatura;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AgregarCorrelativaTest
{
    private Sistema sistema;
    private Asignatura asignatura;
    private Asignatura correlativa;

    public AgregarCorrelativaTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
        this.asignatura = new Asignatura("Física 1");
        this.correlativa = new Asignatura("Análisis Matemático A");
        this.sistema.agregarAsignatura(this.asignatura);
        this.sistema.agregarAsignatura(this.correlativa);
    }

    @After
    public void tearDown()
        throws Exception
    {
        this.sistema = null;
        this.asignatura = null;
        this.correlativa = null;
    }

    /**
     * @see modelo.Asignatura#agregarCorrelativa(modelo.Asignatura)
     */
    @Test
    public void testAgregarCorrelativaCorrecto()
    {
        try
        {
            this.asignatura.agregarCorrelativa(this.correlativa);
            Assert.assertTrue("El elemento no se agregó correctamente.",
                              this.asignatura.tienePrecorrelativa(this.correlativa));
        }
        catch (Exception e)
        {
            Assert.fail("Debería haberse agregado sin problemas.");
        }
    }

    /**
     * @see modelo.Asignatura#agregarCorrelativa(modelo.Asignatura)
     */
    @Test
    public void testAgregarCorrelativaErroneo2_1()
    {
        try
        {
            this.asignatura.agregarCorrelativa(null);
            Assert.fail("No debería haber tenido éxito.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("Debería haber tirado otra excepción.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("Debería haber tirado otra excepción.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Asignatura#agregarCorrelativa(modelo.Asignatura)
     */
    @Test
    public void testAgregarCorrelativaErroneo2_2()
    {
        try
        {
            this.asignatura.agregarCorrelativa(this.asignatura);
            Assert.fail("No debería haber tenido éxito.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("La asignatura invocante no fue la que generó la excepción.", this.asignatura,
                              e.getDato());
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber tirado DatoInvalidoException.");
        }
    }

    /**
     * @see modelo.Asignatura#agregarCorrelativa(modelo.Asignatura)
     */
    @Test
    public void testAgregarCorrelativaErroneo4()
    {
        this.asignatura.setCorrelatividades(null);
        try
        {
            this.asignatura.agregarCorrelativa(this.correlativa);
            Assert.fail("No debería haber tenido éxito.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("Debería haber tirado otra excepción.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("Debería haber tirado otra excepción.");
        }
        catch (Exception e)
        {
        }
    }
}
