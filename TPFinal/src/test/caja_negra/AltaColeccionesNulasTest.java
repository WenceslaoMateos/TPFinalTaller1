package test.caja_negra;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Profesor;
import modelo.Sistema;

import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class AltaColeccionesNulasTest
{
    private Sistema sistema;
    
    public AltaColeccionesNulasTest()
    {
        this.sistema = new Sistema();
    }

    @Before
    public void setUp()
        throws Exception
    {
        this.sistema.setAlumnos(null);
        this.sistema.setCalendario(null);
        this.sistema.setPlanDeEstudio(null);
        this.sistema.setProfesores(null);
    }

    /**
     * @see Sistema#agregarAlumno(Alumno)
     */
    @Test
    public void testAgregarAlumnoErroneo6()
    {
        Alumno nuevo = new Alumno("PablosBraulio", "Rawson 273", "braulio@gmail.com");
        try
        {
            this.sistema.agregarAlumno(nuevo);
            Assert.fail("No debería siquiera haber colección.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("No debería siquiera haber colección.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("No debería siquiera haber colección.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see Sistema#agregarProfesor(Profesor)
     */
    @Test
    public void testAgregarProfesorErroneo6()
    {
        Profesor nuevo = new Profesor("PablosBraulio", "Rawson 273", "braulio@gmail.com", "155555555");
        try
        {
            this.sistema.agregarProfesor(nuevo);
            Assert.fail("No debería siquiera haber colección.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("No debería siquiera haber colección.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("No debería siquiera haber colección.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see Sistema#agregarAsignatura(Asignatura)
     */
    @Test
    public void testAgregarAsignaturaErroneo6()
    {
        Asignatura nuevo = new Asignatura(null);
        try
        {
            this.sistema.agregarAsignatura(nuevo);
            Assert.fail("Debería haber salido por nulidad de la colección.");
        }
        catch (ClaveYaExistenteException e)
        {
            Assert.fail("Debería haber salido por nulidad de la colección.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("Debería haber salido por nulidad de la colección.");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see Sistema#agregarCursada(Cursada)
     */
    @Test
    public void testAgregarCursada()
    {
        fail("Unimplemented");
    }
}
