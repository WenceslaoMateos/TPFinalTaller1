package test;

import modelo.IndicePrimario;
import modelo.Profesor;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BajaProfesorEnCursadaTest
{
    SistemaConElementosFixture fixture1 = new SistemaConElementosFixture();

    public BajaProfesorEnCursadaTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        fixture1.setUp();
        this.fixture1
            .sistema
            .agregarProfesorEnCursada(this.fixture1.profesor, this.fixture1.cursada);
    }

    @After
    public void tearDown()
        throws Exception
    {
        fixture1.tearDown();
    }

    /**
     * @see modelo.Cursada#bajaProfesor(modelo.Profesor)
     */
    @Test
    public void testBajaProfesorCorrecta()
    {
        this.fixture1
            .cursada
            .bajaProfesor(this.fixture1.profesor);
        Assert.assertFalse("El profesor no deberia estar en la cursada", this.fixture1
                                                                             .cursada
                                                                             .tieneProfesor(this.fixture1.profesor));
    }

    /**
     * @see modelo.Cursada#bajaProfesor(modelo.Profesor)
     */
    @Test
    public void testBajaProfesorErronea2()
    {
        try
        {
            this.fixture1
                .cursada
                .bajaProfesor(null);
            Assert.fail("El profesor no deberia poder ser eliminado");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Cursada#bajaProfesor(modelo.Profesor)
     */
    @Test
    public void testBajaProfesorErronea4()
    {
        try
        {
            this.fixture1
                .cursada
                .setProfesores(null);
            this.fixture1
                .cursada
                .bajaProfesor(this.fixture1.profesor);
            Assert.fail("El profesor no deberia poder ser eliminado");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see modelo.Cursada#bajaProfesor(modelo.Profesor)
     */
    @Test
    public void testBajaProfesorErronea6()
    {
        try
        {
            this.fixture1
                .cursada
                .setProfesores(new IndicePrimario<Profesor>());
            this.fixture1
                .cursada
                .bajaProfesor(this.fixture1.profesor);
            Assert.fail("El profesor no deberia poder ser eliminado");
        }
        catch (Exception e)
        {
        }
    }
}
