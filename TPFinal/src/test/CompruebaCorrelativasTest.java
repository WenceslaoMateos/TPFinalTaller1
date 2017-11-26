package test;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompruebaCorrelativasTest
{
    private Sistema sistema;
    private Alumno alumno;
    private Asignatura asignatura1;
    private Asignatura asignatura2;
    private Asignatura asignatura3;
    
    public CompruebaCorrelativasTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
        this.alumno = new Alumno("Mateos Wenceslao", "Alberti 1234", "wen@gmail.com");
        this.asignatura1 = new Asignatura("Programación 1");
        this.asignatura2 = new Asignatura("Matemática Discreta");
        this.asignatura3 = new Asignatura("Programación 2");
        this.sistema.agregarAlumno(this.alumno);
        this.sistema.agregarAsignatura(this.asignatura1);
        this.sistema.agregarAsignatura(this.asignatura2);
        this.sistema.agregarAsignatura(this.asignatura3);
        this.asignatura3.agregarCorrelativa(this.asignatura1);
        this.asignatura3.agregarCorrelativa(this.asignatura2);
    }

    @After
    public void tearDown()
        throws Exception
    {
        this.sistema = null;
        this.alumno = null;
        this.asignatura1 = null;
        this.asignatura2 = null;
        this.asignatura3 = null;
    }

    /**
     * @see modelo.Asignatura#compruebaCorrelativas(modelo.Alumno)
     */
    @Test
    public void testCompruebaCorrelativasCoberturaT1()
    {
        Assert.assertTrue("Debería haber dado true.", this.asignatura1.compruebaCorrelativas(this.alumno));
    }
    
    /**
     * @see modelo.Asignatura#compruebaCorrelativas(modelo.Alumno)
     */
    @Test
    public void testCompruebaCorrelativasCoberturaT2()
    {
        Assert.assertFalse("Debería haber dado false.", this.asignatura3.compruebaCorrelativas(this.alumno));
    }
}
