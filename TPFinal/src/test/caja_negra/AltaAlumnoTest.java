package test.caja_negra;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import modelo.Alumno;
import modelo.Sistema;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AltaAlumnoTest
{
    private Sistema sistema;
    
    public AltaAlumnoTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
    }

    /**
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoCorrecto()
    {
        Alumno alumno = new Alumno("PablosBraulio", "Rawson 273", "braulio@gmail.com");
        try
        {
            this.sistema.agregarAlumno(alumno);
        }
        catch (ClaveYaExistenteException | DatoInvalidoException e)
        {
            Assert.fail("Salió por excepción cuando debería haber tenido éxito.");
        }
    }
    
    /**
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoCorrectoL1()
    {
        Alumno alumno = new Alumno("P", "7", "b@gmail.com");
        try
        {
            this.sistema.agregarAlumno(alumno);
        }
        catch (ClaveYaExistenteException | DatoInvalidoException e)
        {
            Assert.fail("Salió por excepción cuando debería haber tenido éxito.");
        }
    }
    
    /**
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoCorrectoL2()
    {
        Alumno alumno = new Alumno("PablosBraulio", "Rawson 273", "braulio@g");
        try
        {
            this.sistema.agregarAlumno(alumno);
        }
        catch (ClaveYaExistenteException | DatoInvalidoException e)
        {
            Assert.fail("Salió por excepción cuando debería haber tenido éxito.");
        }
    }
    
    /**
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoErroneo2()
    {
        Alumno alumno = null;
        try
        {
            this.sistema.agregarAlumno(alumno);
            Assert.fail("El alumno erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("No debería haber intentado agregar siquiera.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El objeto contenido no es el alumno que se intentó agregar.", alumno, e.getDato());
        }
    }
    
    /**
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoErroneo4_1()
    {
        Alumno alumno = new Alumno(null, "Rawson 273", "braulio@gmail.com");
        try
        {
            this.sistema.agregarAlumno(alumno);
            Assert.fail("El alumno erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("No debería haber intentado agregar siquiera.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El objeto contenido no es el alumno que se intentó agregar.", alumno, e.getDato());
        }
    }
    
    /**
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoErroneo4_2()
    {
        Alumno alumno = new Alumno("", "Rawson 273", "braulio@gmail.com");
        try
        {
            this.sistema.agregarAlumno(alumno);
            Assert.fail("El alumno erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("No debería haber intentado agregar siquiera.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El objeto contenido no es el alumno que se intentó agregar.", alumno, e.getDato());
        }
    }
    
    /**
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoErroneo4_3()
    {
        Alumno alumno = new Alumno("PablosBraulio", null, "braulio@gmail.com");
        try
        {
            this.sistema.agregarAlumno(alumno);
            Assert.fail("El alumno erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("No debería haber intentado agregar siquiera.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El objeto contenido no es el alumno que se intentó agregar.", alumno, e.getDato());
        }
    }
    
    /**
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoErroneo4_4()
    {
        Alumno alumno = new Alumno("PablosBraulio", "", "braulio@gmail.com");
        try
        {
            this.sistema.agregarAlumno(alumno);
            Assert.fail("El alumno erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("No debería haber intentado agregar siquiera.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El objeto contenido no es el alumno que se intentó agregar.", alumno, e.getDato());
        }
    }
}
