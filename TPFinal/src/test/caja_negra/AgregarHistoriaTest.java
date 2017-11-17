package test.caja_negra;

import excepciones.ClaveYaExistenteException;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AgregarHistoriaTest
{
    private Sistema sistema;
    private Asignatura asignatura;
    private Alumno alumno;
    
    public AgregarHistoriaTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
        this.alumno = new Alumno("PablosBraulio", "Rawson 273", "braulio@gmail.com");
        this.sistema.agregarAlumno(this.alumno);
        this.asignatura = new Asignatura("Fisica 1");
        this.sistema.agregarAsignatura(this.asignatura);
    }

    @After
    public void tearDown()
        throws Exception
    {
        this.sistema = null;
        this.alumno = null;
        this.asignatura = null;
    }

    /**
     * @see Alumno#agregarHistoria(Asignatura)
     */
    @Test
    public void testAgregarHistoriaCorrecto()
    {
        try
        {
            this.alumno.agregarHistoria(this.asignatura);
            Assert.assertTrue("El elemento no se agregó correctamente.",
                              this.alumno.asignaturaAprobada(this.asignatura));
        }
        catch (Exception e)
        {
            Assert.fail("Debería haberse agregado sin problemas.");
        }
    }
    
    /**
     * @see Alumno#agregarHistoria(Asignatura)
     */
    @Test
    public void testAgregarHistoriaErroneo2()
    {
        try
        {
            this.alumno.agregarHistoria(null);
            Assert.fail("No debería haber tenido éxito.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("Debería haber tirado otra excepción.");
        }
        catch (Exception e)
        {
        }
    }
    
    /**
     * @see Alumno#agregarHistoria(Asignatura)
     */
    @Test
    public void testAgregarHistoriaErroneo4()
    {
        this.alumno.setHistoria(null);
        try
        {
            this.alumno.agregarHistoria(this.asignatura);
            Assert.fail("No debería haber tenido éxito.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("Debería haber tirado otra excepción.");
        }
        catch (Exception e)
        {
        }
    }
}
