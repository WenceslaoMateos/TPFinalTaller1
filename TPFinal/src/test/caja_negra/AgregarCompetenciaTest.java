package test.caja_negra;

import excepciones.ClaveYaExistenteException;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.Dia;
import modelo.IndicePrimario;
import modelo.Profesor;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AgregarCompetenciaTest
{
    private Sistema sistema;
    private Profesor profesor;
    private Asignatura asignatura;

    public AgregarCompetenciaTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
        this.profesor = new Profesor("PablosBraulio", "Rawson 273", "braulio@gmail.com", "155555555");
        this.asignatura = new Asignatura("Física 1");
        this.sistema.agregarProfesor(this.profesor);
        this.sistema.agregarAsignatura(this.asignatura);
    }

    @After
    public void tearDown()
        throws Exception
    {
        this.sistema = null;
        this.profesor = null;
        this.asignatura = null;
    }

    /**
     * @see modelo.Profesor#agregarCompetencia(modelo.Asignatura)
     */
    @Test
    public void testAgregarCompetenciaCorrecta()
    {
        try
        {
            this.profesor.agregarCompetencia(this.asignatura);
            Assert.assertTrue("No se agregó correctamente la asignatura al profesor",
                              this.profesor.habilitadoParaAsignatura(this.asignatura));
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("La clave no deberia existir previamente");
        }
    }

    /**
     * @see modelo.Profesor#agregarCompetencia(modelo.Asignatura)
     */
    @Test
    public void testAgregarCompetenciaErronea2()
    {
        try
        {
            this.profesor.agregarCompetencia(null);
            Assert.fail("No deberia agregar la asignatura a las competencias");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("La clave no deberia existir previamente");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Profesor#agregarCompetencia(modelo.Asignatura)
     */
    @Test
    public void testAgregarCompetenciaErronea4()
    {
        this.profesor.setCompetencia(null);
        try
        {
            this.profesor.agregarCompetencia(this.asignatura);
            Assert.fail("No deberia agregar la asignatura a las competencias");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("La clave no deberia existir previamente");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Profesor#agregarCompetencia(modelo.Asignatura)
     */
    @Test
    public void testAgregarCompetenciaErronea6()
    {
        try
        {
            this.profesor.agregarCompetencia(this.asignatura);
            this.profesor.agregarCompetencia(this.asignatura);
            Assert.fail("No deberia agregar la asignatura a las competencias");
        }
        catch (ClaveYaExistenteException e)
        {
        }
    }
}
