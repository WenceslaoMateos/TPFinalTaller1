package test;

import modelo.Asignatura;
import modelo.Cursada;
import modelo.Dia;
import modelo.IndicePrimario;
import modelo.Profesor;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * En public void testAceptaCompetenciaErronea4()se consideró que la asignatura correspondia a cursada
 */
public class AceptaCompetenciaTest
{
    private Sistema sistema;
    private Cursada cursada;
    private Profesor profesor;
    private Asignatura asignatura;

    public AceptaCompetenciaTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
        this.profesor = new Profesor("LazzurriGuillermo", "Rawson 273", "guillermo@gmail.com", "155555555");
        this.asignatura = new Asignatura("Programación 3");
        this.sistema.agregarAsignatura(this.asignatura);
        this.profesor.agregarCompetencia(this.asignatura);
        this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:00");
        this.sistema.agregarCursada(this.cursada);
    }

    @After
    public void tearDown()
        throws Exception
    {
        this.sistema = null;
        this.profesor = null;
        this.asignatura = null;
        this.cursada = null;
    }

    /**
     * @see modelo.Cursada#aceptaCompetencia(modelo.Profesor)
     */
    @Test
    public void testAceptaCompetenciaCorrecta5_1()
    {
        Assert.assertTrue("Deberia ser posible usar el profesor en la cursada",
                          this.cursada.aceptaCompetencia(this.profesor));
    }

    /**
     * @see modelo.Cursada#aceptaCompetencia(modelo.Profesor)
     */
    @Test
    public void testAceptaCompetenciaCorrecta5_2()
    {
        this.profesor.setCompetencia(new IndicePrimario<Asignatura>());
        Assert.assertFalse("No deberia ser posible usar el profesor en la cursada",
                           this.cursada.aceptaCompetencia(this.profesor));
    }

    /**
     * @see modelo.Cursada#aceptaCompetencia(modelo.Profesor)
     */
    @Test
    public void testAceptaCompetenciaErronea2()
    {
        try
        {
            Assert.assertFalse("No deberia ser posible usar el profesor en la cursada",
                               this.cursada.aceptaCompetencia(null));
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Cursada#aceptaCompetencia(modelo.Profesor)
     */
    @Test
    public void testAceptaCompetenciaErronea4()
    {
        try
        {
            this.cursada.setAsignatura(null);
            Assert.assertFalse("No deberia ser posible usar el profesor en la cursada",
                               this.cursada.aceptaCompetencia(this.profesor));
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Cursada#aceptaCompetencia(modelo.Profesor)
     */
    @Test
    public void testAceptaCompetenciaErronea7()
    {
        try
        {
            this.profesor.setCompetencia(null);
            Assert.assertFalse("No deberia ser posible usar el profesor en la cursada",
                               this.cursada.aceptaCompetencia(this.profesor));
        }
        catch (Exception e)
        {
        }
    }
}
