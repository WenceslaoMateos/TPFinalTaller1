package test.caja_negra;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Profesor;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AltasEscenarioNormal
{
    private Sistema sistema;
    
    public AltasEscenarioNormal()
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
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoCorrecto()
    {
        Alumno nuevo = new Alumno("PablosBraulio", "Rawson 273", "braulio@gmail.com");
        try
        {
            this.sistema.agregarAlumno(nuevo);
        }
        catch (Exception e)
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
        Alumno nuevo = new Alumno("P", "7", "b@gmail.com");
        try
        {
            this.sistema.agregarAlumno(nuevo);
        }
        catch (Exception e)
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
        Alumno nuevo = new Alumno("PablosBraulio", "Rawson 273", "braulio@g");
        try
        {
            this.sistema.agregarAlumno(nuevo);
        }
        catch (Exception e)
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
        Alumno nuevo = null;
        try
        {
            this.sistema.agregarAlumno(nuevo);
            Assert.fail("El alumno erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("No debería haber intentado agregar siquiera.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("No debería haber intentado agregar siquiera.");
        }
        catch (Exception e)
        {
        }
    }
    
    /**
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoErroneo4_1()
    {
        Alumno nuevo = new Alumno(null, "Rawson 273", "braulio@gmail.com");
        try
        {
            this.sistema.agregarAlumno(nuevo);
            Assert.fail("El alumno erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("No debería haber intentado agregar siquiera.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El objeto contenido no es el alumno que se intentó agregar.", nuevo, e.getDato());
        }
        catch (Exception e)
        {
        }
    }
    
    /**
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoErroneo4_2()
    {
        Alumno nuevo = new Alumno("", "Rawson 273", "braulio@gmail.com");
        try
        {
            this.sistema.agregarAlumno(nuevo);
            Assert.fail("El alumno erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("No debería haber intentado agregar siquiera.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El objeto contenido no es el alumno que se intentó agregar.", nuevo, e.getDato());
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber sido DatoInvalidoException.");
        }
    }
    
    /**
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoErroneo4_3()
    {
        Alumno nuevo = new Alumno("PablosBraulio", null, "braulio@gmail.com");
        try
        {
            this.sistema.agregarAlumno(nuevo);
            Assert.fail("El alumno erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("No debería haber intentado agregar siquiera.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("No debería haber intentado agregar siquiera.");
        }
        catch (Exception e)
        {
        }
    }
    
    /**
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoErroneo4_4()
    {
        Alumno nuevo = new Alumno("PablosBraulio", "", "braulio@gmail.com");
        try
        {
            this.sistema.agregarAlumno(nuevo);
            Assert.fail("El alumno erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("No debería haber intentado agregar siquiera.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("No debería haber intentado agregar siquiera.");
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber sido DatoInvalidoException.");
        }
    }
    
    /**
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoErroneo4_5()
    {
        Alumno nuevo = new Alumno("PablosBraulio", "Rawson 273", "brauliogmail.com");
        try
        {
            this.sistema.agregarAlumno(nuevo);
            Assert.fail("El alumno erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("No debería haber intentado agregar siquiera.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El objeto contenido no es el alumno que se intentó agregar.", nuevo, e.getDato());
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber sido DatoInvalidoException.");
        }
    }
    
    /**
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoErroneo4_6()
    {
        Alumno nuevo = new Alumno("PablosBraulio", "Rawson 273", "@gmail.com");
        try
        {
            this.sistema.agregarAlumno(nuevo);
            Assert.fail("El alumno erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("No debería haber intentado agregar siquiera.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El objeto contenido no es el alumno que se intentó agregar.", nuevo, e.getDato());
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber sido DatoInvalidoException.");
        }
    }
    
    /**
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoErroneo4_7()
    {
        Alumno nuevo = new Alumno("PablosBraulio", "Rawson 273", "braulio@");
        try
        {
            this.sistema.agregarAlumno(nuevo);
            Assert.fail("El alumno erróneo fue agregado.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("No debería haber intentado agregar siquiera.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("El objeto contenido no es el alumno que se intentó agregar.", nuevo, e.getDato());
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
    
    /**
     * @see modelo.Sistema#agregarAsignatura(modelo.Asignatura)
     */
    @Test
    public void testAgregarAsignaturaCorrecto()
    {
        Asignatura nuevo = new Asignatura("Física 1");
        try
        {
            this.sistema.agregarAsignatura(nuevo);
        }
        catch (ClaveYaExistenteException | DatoInvalidoException e)
        {
            Assert.fail("Salió por excepción cuando debería haber tenido éxito.");
        }
    }
    
    /**
     * @see modelo.Sistema#agregarAsignatura(modelo.Asignatura)
     */
    @Test
    public void testAgregarAsignaturaCorrectoL()
    {
        Asignatura nuevo = new Asignatura("F");
        try
        {
            this.sistema.agregarAsignatura(nuevo);
        }
        catch (ClaveYaExistenteException | DatoInvalidoException e)
        {
            Assert.fail("Salió por excepción cuando debería haber tenido éxito.");
        }
    }
    
    /**
     * @see modelo.Sistema#agregarAsignatura(modelo.Asignatura)
     */
    @Test
    public void testAgregarAsignaturaErroneo2()
    {
        Asignatura nuevo = null;
        try
        {
            this.sistema.agregarAsignatura(nuevo);
            Assert.fail("No debería haber agregado un elemento nulo.");
        }
        catch (ClaveYaExistenteException | DatoInvalidoException e)
        {
            Assert.fail("Debería haberse detenido por elemento nulo.");
        }
        catch (Exception e)
        {
        }
    }
    
    /**
     * @see modelo.Sistema#agregarAsignatura(modelo.Asignatura)
     */
    @Test
    public void testAgregarAsignaturaErroneo4()
    {
        Asignatura nuevo = new Asignatura(null);
        try
        {
            this.sistema.agregarAsignatura(nuevo);
            Assert.fail("No debería haber agregado un elemento incorrecto.");
        }
        catch (ClaveYaExistenteException | DatoInvalidoException e)
        {
            Assert.fail("Debería haberse detenido por contenido nulo.");
        }
        catch (Exception e)
        {
        }
    }
}
