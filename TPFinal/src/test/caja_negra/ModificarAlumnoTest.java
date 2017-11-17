package test.caja_negra;

import excepciones.DatoInvalidoException;

import modelo.Alumno;
import modelo.IndiceDoble;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ModificarAlumnoTest
{
    private Sistema sistema;
    private Alumno alumno;
    
    public ModificarAlumnoTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
        this.alumno = new Alumno("PablosBraulio", "Rawson 273", "braulio@gmail.com");
        this.sistema.agregarAlumno(this.alumno);
    }

    @After
    public void tearDown()
        throws Exception
    {
        this.sistema = null;
        this.alumno = null;
    }

    /**
     * @see Sistema#modificarAlumno(Alumno,Alumno)
     */
    @Test
    public void testModificarAlumnoCorrecto()
    {
        Alumno modif = new Alumno("Pablos Braulio", "Chaco 33", "bravlinleonel@gmail.com");
        try
        {
            this.sistema.modificarAlumno(this.alumno, modif);
            Assert.assertTrue("La modificación no se realizó correctamente.",
                              this.alumno.getApellidoNombre().equals(modif.getApellidoNombre())
                              && this.alumno.getDomicilio().equals(modif.getDomicilio())
                              && this.alumno.getMail().equals(modif.getMail()));
        }
        catch (Exception e)
        {
            Assert.fail("No debería haber fallado la modificación.");
        }
    }
    
    /**
     * @see Sistema#modificarAlumno(Alumno,Alumno)
     */
    @Test
    public void testModificarAlumnoErroneo2()
    {
        Alumno modif = new Alumno("Pablos Braulio", "Chaco 33", "bravlinleonel@gmail.com");
        try
        {
            this.sistema.modificarAlumno(null, modif);
            Assert.fail("No debería tratar de modificar un elemento nulo.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("Debería haber salido por otra excepción.");
        }
        catch (Exception e)
        {
        }
    }
    
    /**
     * @see Sistema#modificarAlumno(Alumno,Alumno)
     */
    @Test
    public void testModificarAlumnoErroneo4_1()
    {
        try
        {
            this.sistema.modificarAlumno(this.alumno, null);
            Assert.fail("No debería tratar de modificar mediante un elemento nulo.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("Debería haber salido por otra excepción.");
        }
        catch (Exception e)
        {
        }
    }
    
    /**
     * @see Sistema#modificarAlumno(Alumno,Alumno)
     */
    @Test
    public void testModificarAlumnoErroneo4_2()
    {
        Alumno modif = new Alumno("Pablos Braulio", "Chaco 33", "bravlinleonelgmail.com");
        try
        {
            this.sistema.modificarAlumno(this.alumno, modif);
            Assert.fail("No debería haber seguido adelante la modificación.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("No fue el parámetro modif lo que desató la excepción.", modif, e.getDato());
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber salido por DatoInvalidoException.");
        }
    }
    
    /**
     * @see Sistema#modificarAlumno(Alumno,Alumno)
     */
    @Test
    public void testModificarAlumnoErroneo4_3()
    {
        Alumno modif = new Alumno("", "Chaco 33", "bravlinleonel@gmail.com");
        try
        {
            this.sistema.modificarAlumno(this.alumno, modif);
            Assert.fail("No debería haber seguido adelante la modificación.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.assertSame("No fue el parámetro modif lo que desató la excepción.", modif, e.getDato());
        }
        catch (Exception e)
        {
            Assert.fail("Debería haber salido por DatoInvalidoException.");
        }
    }
    
    /**
     * @see Sistema#modificarAlumno(Alumno,Alumno)
     */
    @Test
    public void testModificarAlumnoErroneo6()
    {
        this.sistema.setAlumnos(null);
        Alumno modif = new Alumno("Pablos Braulio", "Chaco 33", "bravlinleonel@gmail.com");
        try
        {
            this.sistema.modificarAlumno(this.alumno, modif);
            Assert.fail("No debería haber seguido adelante la modificación siendo la colección nula.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("No debería haber intentado la modificación siendo la colección nula.");
        }
        catch (Exception e)
        {   
        }
    }
    
    /**
     * @see Sistema#modificarAlumno(Alumno,Alumno)
     */
    @Test
    public void testModificarAlumnoErroneo8()
    {
        this.sistema.setAlumnos(new IndiceDoble<Alumno>());
        Alumno modif = new Alumno("Pablos Braulio", "Chaco 33", "bravlinleonel@gmail.com");
        try
        {
            this.sistema.modificarAlumno(this.alumno, modif);
            Assert.fail("No debería haber seguido adelante la modificación no estando el elemento.");
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("No debería haber intentado la modificación no estando el elemento.");
        }
        catch (Exception e)
        {
        }
    }
}
