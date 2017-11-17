package test.caja_negra;

import excepciones.DatoInvalidoException;

import modelo.Asignatura;
import modelo.IndiceDoble;
import modelo.Sistema;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ModificarAsignaturaTest
{
    private Sistema sistema;
    private Asignatura asignatura;
    
    public ModificarAsignaturaTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
        this.asignatura = new Asignatura("Física 1");
        this.sistema.agregarAsignatura(this.asignatura);
    }

    @After
    public void tearDown()
        throws Exception
    {
        this.sistema = null;
        this.asignatura = null;
    }

    /**
     * @see modelo.Sistema#modificarAsignatura(modelo.Asignatura,modelo.Asignatura)
     */
    @Test
    public void testModificarAsignaturaCorrecto()
    {
        Asignatura modif = new Asignatura("Física I");
        try
        {
            this.sistema.modificarAsignatura(this.asignatura, modif);
            Assert.assertTrue("La modificación no se realizó correctamente.",
                              this.asignatura.getNombre().equals(modif.getNombre()));
        }
        catch (Exception e)
        {
            Assert.fail("No debería haber fallado la modificación.");
        }
    }
    
    /**
     * @see modelo.Sistema#modificarAsignatura(modelo.Asignatura,modelo.Asignatura)
     */
    @Test
    public void testModificarAsignaturaErroneo_2()
    {
        Asignatura modif = new Asignatura("Física I");
        try
        {
            this.sistema.modificarAsignatura(null, modif);
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
     * @see modelo.Sistema#modificarAsignatura(modelo.Asignatura,modelo.Asignatura)
     */
    @Test
    public void testModificarAsignaturaErroneo4_1()
    {
        try
        {
            this.sistema.modificarAsignatura(this.asignatura, null);
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
     * @see modelo.Sistema#modificarAsignatura(modelo.Asignatura,modelo.Asignatura)
     */
    @Test
    public void testModificarAsignaturaErroneo4_2()
    {
        Asignatura modif = new Asignatura("");
        try
        {
            this.sistema.modificarAsignatura(this.asignatura, modif);
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
     * @see modelo.Sistema#modificarAsignatura(modelo.Asignatura,modelo.Asignatura)
     */
    @Test
    public void testModificarAsignaturaErroneo6()
    {
        this.sistema.setPlanDeEstudio(null);
        Asignatura modif = new Asignatura("Física I");
        try
        {
            this.sistema.modificarAsignatura(this.asignatura, modif);
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
     * @see modelo.Sistema#modificarAsignatura(modelo.Asignatura,modelo.Asignatura)
     */
    @Test
    public void testModificarAsignaturaErroneo8()
    {
        this.sistema.setPlanDeEstudio(new IndiceDoble<Asignatura>());
        Asignatura modif = new Asignatura("Física I");
        try
        {
            this.sistema.modificarAsignatura(this.asignatura, modif);
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
