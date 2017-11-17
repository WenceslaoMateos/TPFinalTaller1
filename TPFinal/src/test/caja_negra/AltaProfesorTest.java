package test.caja_negra;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import modelo.Profesor;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AltaProfesorTest
{
    private Sistema sistema;
    
    public AltaProfesorTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
    }
    
    @After
    public void tearDown()
        throws Exception
    {
        this.sistema = null;
    }

    /**
     * @see Sistema#agregarProfesor(Profesor)
     */
    @Test
    public void testAgregarProfesorCorrecto()
    {
        Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "braulio@gmail.com", "155555555");
        try
        {
            this.sistema.agregarProfesor(nuevo);
        }
        catch (Exception e)
        {
            Assert.fail("Salió por excepción cuando debería haber tenido éxito.");
        }
    }
    
    /**
     * @see Sistema#agregarProfesor(Profesor)
     */
    @Test
    public void testAgregarProfesorCorrectoL1()
    {
        Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "b@gmail.com", "155555555");
        try
        {
            this.sistema.agregarProfesor(nuevo);
        }
        catch (Exception e)
        {
            Assert.fail("Salió por excepción cuando debería haber tenido éxito.");
        }
    }
    
    /**
     * @see Sistema#agregarProfesor(Profesor)
     */
    @Test
    public void testAgregarProfesorCorrectoL2()
    {
        Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "braulio@g", "155555555");
        try
        {
            this.sistema.agregarProfesor(nuevo);
        }
        catch (Exception e)
        {
            Assert.fail("Salió por excepción cuando debería haber tenido éxito.");
        }
    }
    
    /**
     * @see Sistema#agregarProfesor(Profesor)
     */
    @Test
    public void testAgregarProfesorErroneo2()
    {
        Profesor nuevo = null;
        try
        {
            this.sistema.agregarProfesor(nuevo);
            Assert.fail("El profesor erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException | DatoInvalidoException e)
        {
            Assert.fail("Ninguna de las excepciones declaradas contempla null.");
        }
        catch(Exception e)
        {
        }
    }
    
    /**
     * @see Sistema#agregarProfesor(Profesor)
     */
    @Test
    public void testAgregarProfesorErroneo4_1()
    {
        Profesor nuevo = new Profesor(null, "Rawson 273", "braulio@gmail", "155555555");
        try
        {
            this.sistema.agregarProfesor(nuevo);
            Assert.fail("El profesor erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException | DatoInvalidoException e)
        {
            Assert.fail("Ninguna de las excepciones declaradas contempla null.");
        }
        catch(Exception e)
        {
        }
    }
    
    /**
     * @see Sistema#agregarProfesor(Profesor)
     */
    @Test
    public void testAgregarProfesorErroneo4_2()
    {
        Profesor nuevo = new Profesor("", "Rawson 273", "braulio@gmail", "155555555");
        try
        {
            this.sistema.agregarProfesor(nuevo);
            Assert.fail("El profesor erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("Debería haber sido DatoInvalidoException.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El profesor erróneo no fue el objeto que disparó la excepción.", nuevo, e.getDato());
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber sido DatoInvalidoException.");
        }
    }
    
    /**
     * @see Sistema#agregarProfesor(Profesor)
     */
    @Test
    public void testAgregarProfesorErroneo4_3()
    {
        Profesor nuevo = new Profesor("PablosBraulio", null, "braulio@gmail", "155555555");
        try
        {
            this.sistema.agregarProfesor(nuevo);
            Assert.fail("El profesor erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException | DatoInvalidoException e)
        {
            Assert.fail("Ninguna de las excepciones declaradas contempla null.");
        }
        catch(Exception e)
        {
        }
    }
    
    /**
     * @see Sistema#agregarProfesor(Profesor)
     */
    @Test
    public void testAgregarProfesorErroneo4_4()
    {
        Profesor nuevo = new Profesor("PablosBraulio", "", "braulio@gmail", "155555555");
        try
        {
            this.sistema.agregarProfesor(nuevo);
            Assert.fail("El profesor erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException | DatoInvalidoException e)
        {
            Assert.fail("Debería haber sido error en la lógica del programa.");
        }
        catch (Exception e)
        {
        }
    }
    
    /**
     * @see Sistema#agregarProfesor(Profesor)
     */
    @Test
    public void testAgregarProfesorErroneo4_5()
    {
        Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "brauliogmail", "155555555");
        try
        {
            this.sistema.agregarProfesor(nuevo);
            Assert.fail("El profesor erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("Debería haber sido DatoInvalidoException.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El profesor erróneo no fue el objeto que disparó la excepción.", nuevo, e.getDato());
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber sido DatoInvalidoException.");
        }
    }
    
    /**
     * @see Sistema#agregarProfesor(Profesor)
     */
    @Test
    public void testAgregarProfesorErroneo4_6()
    {
        Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "@gmail", "155555555");
        try
        {
            this.sistema.agregarProfesor(nuevo);
            Assert.fail("El profesor erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("Debería haber sido DatoInvalidoException.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El profesor erróneo no fue el objeto que disparó la excepción.", nuevo, e.getDato());
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber sido DatoInvalidoException.");
        }
    }
    
    /**
     * @see Sistema#agregarProfesor(Profesor)
     */
    @Test
    public void testAgregarProfesorErroneo4_7()
    {
        Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "braulio@", "155555555");
        try
        {
            this.sistema.agregarProfesor(nuevo);
            Assert.fail("El profesor erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("Debería haber sido DatoInvalidoException.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El profesor erróneo no fue el objeto que disparó la excepción.", nuevo, e.getDato());
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber sido DatoInvalidoException.");
        }
    }
    
    /**
     * @see Sistema#agregarProfesor(Profesor)
     */
    @Test
    public void testAgregarProfesorErroneo4_8()
    {
        Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "braulio@", null);
        try
        {
            this.sistema.agregarProfesor(nuevo);
            Assert.fail("El profesor erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("Debería haber sido DatoInvalidoException.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El profesor erróneo no fue el objeto que disparó la excepción.", nuevo, e.getDato());
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber sido DatoInvalidoException.");
        }
    }
    
    /**
     * @see Sistema#agregarProfesor(Profesor)
     */
    @Test
    public void testAgregarProfesorErroneo4_9()
    {
        Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "braulio@", "");
        try
        {
            this.sistema.agregarProfesor(nuevo);
            Assert.fail("El profesor erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("Debería haber sido DatoInvalidoException.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El profesor erróneo no fue el objeto que disparó la excepción.", nuevo, e.getDato());
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber sido DatoInvalidoException.");
        }
    }
}
