package test.caja_negra;

import excepciones.DatoInvalidoException;

import modelo.IndiceDoble;
import modelo.Profesor;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ModificarProfesorTest
{
    private Sistema sistema;
    private Profesor profesor;
    
    public ModificarProfesorTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
        this.profesor = new Profesor("PablosBraulio", "Rawson 273", "braulio@gmail.com", "155555555");
        this.sistema.agregarProfesor(this.profesor);
    }

    @After
    public void tearDown()
        throws Exception
    {
        this.sistema = null;
        this.profesor = null;
    }

    /**
     * @see Sistema#modificarProfesor(Profesor,Profesor)
     */
    @Test
    public void testModificarProfesorCorrecto()
    {
        Profesor modif = new Profesor("Pablos Braulio", "Chaco 33", "bravlinleonel@gmail.com", "123456789");
        try
        {
            this.sistema.modificarProfesor(this.profesor, modif);
            Assert.assertTrue("La modificación no se realizó correctamente.",
                              this.profesor.getApellidoNombre().equals(modif.getApellidoNombre())
                              && this.profesor.getDomicilio().equals(modif.getDomicilio())
                              && this.profesor.getMail().equals(modif.getMail())
                              && this.profesor.getTelefono().equals(modif.getTelefono()));
        }
        catch (Exception e)
        {
            Assert.fail("No debería haber fallado la modificación.");
        }
    }
    
    /**
     * @see Sistema#modificarProfesor(Profesor,Profesor)
     */
    @Test
    public void testModificarProfesorErroneo2()
    {
        Profesor modif = new Profesor("Pablos Braulio", "Chaco 33", "bravlinleonel@gmail.com", "123456789");
        try
        {
            this.sistema.modificarProfesor(null, modif);
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
     * @see Sistema#modificarProfesor(Profesor,Profesor)
     */
    @Test
    public void testModificarProfesorErroneo4_1()
    {
        try
        {
            this.sistema.modificarProfesor(this.profesor, null);
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
     * @see Sistema#modificarProfesor(Profesor,Profesor)
     */
    @Test
    public void testModificarProfesorErroneo4_2()
    {
        Profesor modif = new Profesor("Pablos Braulio", "Chaco 33", "bravlinleonelgmail.com", "123456789");
        try
        {
            this.sistema.modificarProfesor(this.profesor, modif);
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
     * @see Sistema#modificarProfesor(Profesor,Profesor)
     */
    @Test
    public void testModificarProfesorErroneo4_3()
    {
        Profesor modif = new Profesor("", "Chaco 33", "bravlinleonel@gmail.com", "123456789");
        try
        {
            this.sistema.modificarProfesor(this.profesor, modif);
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
     * @see Sistema#modificarProfesor(Profesor,Profesor)
     */
    @Test
    public void testModificarProfesorErroneo6()
    {
        this.sistema.setProfesores(null);
        Profesor modif = new Profesor("Pablos Braulio", "Chaco 33", "bravlinleonel@gmail.com", "123456789");
        try
        {
            this.sistema.modificarProfesor(this.profesor, modif);
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
     * @see Sistema#modificarProfesor(Profesor,Profesor)
     */
    @Test
    public void testModificarProfesorErroneo8()
    {
        this.sistema.setProfesores(new IndiceDoble<Profesor>());
        Profesor modif = new Profesor("Pablos Braulio", "Chaco 33", "bravlinleonel@gmail.com", "123456789");
        try
        {
            this.sistema.modificarProfesor(this.profesor, modif);
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
