package test;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.Dia;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * considero la colección alumno a la que se refiere la bateria de pruebas como la colección interna de cursada
 */
public class CorrelativasAprobadasTest
{
    private Sistema sistema;
    private Alumno alumno;
    private Cursada cursada;
    private Asignatura asignatura;
    private Asignatura correlativa;

    public CorrelativasAprobadasTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
        this.asignatura = new Asignatura("Programación 3");
        this.sistema.agregarAsignatura(this.asignatura);
        this.alumno = new Alumno("PablosBraulio", "Rawson 273", "braulio@gmail.com");
        this.alumno.agregarHistoria(this.asignatura);
        this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:00");
        this.sistema.agregarCursada(this.cursada);
        this.correlativa = new Asignatura("Programación 2");
        this.sistema.agregarAsignatura(this.correlativa);
    }

    @After
    public void tearDown()
        throws Exception
    {
        this.sistema = null;
        this.alumno = null;
        this.asignatura = null;
        this.cursada = null;
    }

    /**
     * @see modelo.Cursada#correlativasAprobadas(modelo.Alumno)
     */
    @Test
    public void testCorrelativasAprobadasCorrecto5_1()
    {
        Assert.assertTrue("Deberia ser posible usar el alumno en la cursada",
                          this.cursada.correlativasAprobadas(this.alumno));
    }

    /**
     * @see modelo.Cursada#correlativasAprobadas(modelo.Alumno)
     */
    @Test
    public void testCorrelativasAprobadasCorrecto5_2()
    {
        try
        {
            this.asignatura.agregarCorrelativa(this.correlativa);
            Assert.assertFalse("No deberia ser posible usar el alumno en la cursada",
                               this.cursada.correlativasAprobadas(this.alumno));
        }
        catch (ClaveYaExistenteException | DatoInvalidoException e)
        {
            Assert.fail("Este mensaje nunca deberia verse");
        }
    }

    /**
     * @see modelo.Cursada#correlativasAprobadas(modelo.Alumno)
     */
    @Test
    public void testCorrelativasAprobadasErroneas2()
    {
        try
        {
            Assert.assertFalse("No deberia ser posible usar el alumno en la cursada",
                               this.cursada.correlativasAprobadas(null));
            Assert.fail("No deberia ser posible que de falso, el dato es invalido");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Cursada#correlativasAprobadas(modelo.Alumno)
     */
    @Test
    public void testCorrelativasAprobadasErroneas4()
    {
        try
        {
            this.cursada.setAsignatura(null);
            Assert.assertFalse("No deberia ser posible usar el alumno en la cursada",
                               this.cursada.correlativasAprobadas(this.alumno));
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Cursada#correlativasAprobadas(modelo.Alumno)
     */
    @Test
    public void testCorrelativasAprobadasErroneas7()
    {
        try
        {
            this.cursada.setAsignatura(null);
            Assert.assertFalse("No deberia ser posible usar el alumno en la cursada",
                               this.cursada.correlativasAprobadas(this.alumno));
        }
        catch (Exception e)
        {
        }
    }
}
