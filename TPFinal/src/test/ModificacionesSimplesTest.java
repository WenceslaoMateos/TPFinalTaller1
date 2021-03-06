package test;

import excepciones.DatoInvalidoException;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.Dia;
import modelo.IndiceDoble;
import modelo.Profesor;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ModificacionesSimplesTest
{
    SistemaConElementosFixture fixture1 = new SistemaConElementosFixture();

    public ModificacionesSimplesTest()
    {
    }

    @Before
    public void setUp()
        throws Exception
    {
        fixture1.setUp();
    }

    @After
    public void tearDown()
        throws Exception
    {
        fixture1.tearDown();
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
            this.fixture1
                .sistema
                .modificarAlumno(this.fixture1.alumno, modif);
            Assert.assertTrue("La modificación no se realizó correctamente.",
                              this.fixture1
                                                                                  .alumno
                                                                                  .getApellidoNombre()
                                                                                  .equals(modif.getApellidoNombre()) &&
                                                             this.fixture1
                                                                                                                            .alumno
                                                                                                                            .getDomicilio()
                                                                                                                            .equals(modif.getDomicilio()) &&
this.fixture1
                                                                                                                                                                 .alumno
                                                                                                                                                                 .getMail()
                                                                                                                                                                 .equals(modif.getMail()));
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
            this.fixture1
                .sistema
                .modificarAlumno(null, modif);
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
            this.fixture1
                .sistema
                .modificarAlumno(this.fixture1.alumno, null);
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
            this.fixture1
                .sistema
                .modificarAlumno(this.fixture1.alumno, modif);
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
            this.fixture1
                .sistema
                .modificarAlumno(this.fixture1.alumno, modif);
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
        this.fixture1
            .sistema
            .setAlumnos(null);
        Alumno modif = new Alumno("Pablos Braulio", "Chaco 33", "bravlinleonel@gmail.com");
        try
        {
            this.fixture1
                .sistema
                .modificarAlumno(this.fixture1.alumno, modif);
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
        this.fixture1
            .sistema
            .setAlumnos(new IndiceDoble<Alumno>());
        Alumno modif = new Alumno("Pablos Braulio", "Chaco 33", "bravlinleonel@gmail.com");
        try
        {
            this.fixture1
                .sistema
                .modificarAlumno(this.fixture1.alumno, modif);
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

    /**
     * @see Sistema#modificarProfesor(Profesor,Profesor)
     */
    @Test
    public void testModificarProfesorCorrecto()
    {
        Profesor modif = new Profesor("Pablos Braulio", "Chaco 33", "bravlinleonel@gmail.com", "123456789");
        try
        {
            this.fixture1
                .sistema
                .modificarProfesor(this.fixture1.profesor, modif);
            Assert.assertTrue("La modificación no se realizó correctamente.",
                              this.fixture1
                                                                                  .profesor
                                                                                  .getApellidoNombre()
                                                                                  .equals(modif.getApellidoNombre()) &&
                                                             this.fixture1
                                                                                                                            .profesor
                                                                                                                            .getDomicilio()
                                                                                                                            .equals(modif.getDomicilio()) &&
this.fixture1
                                                                                                                                                                 .profesor
                                                                                                                                                                 .getMail()
                                                                                                                                                                 .equals(modif.getMail()) &&
 this.fixture1
                                                                                                                                                                                                 .profesor
                                                                                                                                                                                                 .getTelefono()
                                                                                                                                                                                                 .equals(modif.getTelefono()));
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
            this.fixture1
                .sistema
                .modificarProfesor(null, modif);
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
            this.fixture1
                .sistema
                .modificarProfesor(this.fixture1.profesor, null);
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
            this.fixture1
                .sistema
                .modificarProfesor(this.fixture1.profesor, modif);
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
            this.fixture1
                .sistema
                .modificarProfesor(this.fixture1.profesor, modif);
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
        this.fixture1
            .sistema
            .setProfesores(null);
        Profesor modif = new Profesor("Pablos Braulio", "Chaco 33", "bravlinleonel@gmail.com", "123456789");
        try
        {
            this.fixture1
                .sistema
                .modificarProfesor(this.fixture1.profesor, modif);
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
        this.fixture1
            .sistema
            .setProfesores(new IndiceDoble<Profesor>());
        Profesor modif = new Profesor("Pablos Braulio", "Chaco 33", "bravlinleonel@gmail.com", "123456789");
        try
        {
            this.fixture1
                .sistema
                .modificarProfesor(this.fixture1.profesor, modif);
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

    /**
     * @see modelo.Sistema#modificarAsignatura(modelo.Asignatura,modelo.Asignatura)
     */
    @Test
    public void testModificarAsignaturaCorrecto()
    {
        Asignatura modif = new Asignatura("Física I");
        try
        {
            this.fixture1
                .sistema
                .modificarAsignatura(this.fixture1.asignatura2, modif);
            Assert.assertTrue("La modificación no se realizó correctamente.", this.fixture1
                                                                                  .asignatura2
                                                                                  .getNombre()
                                                                                  .equals(modif.getNombre()));
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
            this.fixture1
                .sistema
                .modificarAsignatura(null, modif);
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
            this.fixture1
                .sistema
                .modificarAsignatura(this.fixture1.asignatura2, null);
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
            this.fixture1
                .sistema
                .modificarAsignatura(this.fixture1.asignatura2, modif);
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
        this.fixture1
            .sistema
            .setPlanDeEstudio(null);
        Asignatura modif = new Asignatura("Física I");
        try
        {
            this.fixture1
                .sistema
                .modificarAsignatura(this.fixture1.asignatura2, modif);
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
        this.fixture1
            .sistema
            .setPlanDeEstudio(new IndiceDoble<Asignatura>());
        Asignatura modif = new Asignatura("Física I");
        try
        {
            this.fixture1
                .sistema
                .modificarAsignatura(this.fixture1.asignatura2, modif);
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

    /**
     * @see Sistema#modificarCursada(Cursada,Cursada)
     */
    @Test
    public void testModificarCursadaCorrecta()
    {
        Cursada modif = new Cursada(this.fixture1.asignatura2, "01-2017", Dia.DOM, "11:00", "13:00");
        try
        {
            this.fixture1
                .sistema
                .modificarCursada(this.fixture1.cursada, modif);
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("Los datos deberian ser invalidos");
        }
        Assert.assertSame("La asignatura deberia estar modificada", this.fixture1.asignatura2, this.fixture1
                                                                                                   .cursada
                                                                                                   .getAsignatura());
        Assert.assertTrue("El periodo deberia estar modificado", this.fixture1
                                                                     .cursada
                                                                     .getPeriodo()
                                                                     .equals("01-2017"));
        Assert.assertSame("La asignatura deberia estar modificada", Dia.DOM, this.fixture1
                                                                                 .cursada
                                                                                 .getDia());
        Assert.assertTrue("La hora de inicio deberia estar modificada", this.fixture1
                                                                            .cursada
                                                                            .getHoraInicio()
                                                                            .equals("11:00"));
        Assert.assertTrue("La hora de finalización deberia estar modificada", this.fixture1
                                                                                  .cursada
                                                                                  .getHoraFinalizacion()
                                                                                  .equals("13:00"));
    }

    /**
     * @see Sistema#modificarCursada(Cursada,Cursada)
     */
    @Test
    public void testModificarCursadaErronea2()
    {
        Cursada modif = new Cursada(this.fixture1.asignatura2, "01-2017", Dia.DOM, "11:00", "13:00");
        try
        {
            this.fixture1
                .sistema
                .modificarCursada(null, modif);
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("Los datos no deberian ser invalidos");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see Sistema#modificarCursada(Cursada,Cursada)
     */
    @Test
    public void testModificarCursadaErronea4_1()
    {
        try
        {
            this.fixture1
                .sistema
                .modificarCursada(this.fixture1.cursada, null);
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("Los datos no deberian ser invalidos");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see Sistema#modificarCursada(Cursada,Cursada)
     */
    @Test
    public void testModificarCursadaErronea4_2()
    {
        Cursada modif = new Cursada(this.fixture1.asignatura2, "ab-2017", Dia.DOM, "11:00", "13:00");
        try
        {
            this.fixture1
                .sistema
                .modificarCursada(this.fixture1.cursada, modif);
        }
        catch (DatoInvalidoException e)
        {
        }
    }

    /**
     * @see Sistema#modificarCursada(Cursada,Cursada)
     */
    @Test
    public void testModificarCursadaErronea4_3()
    {
        Cursada modif = new Cursada(this.fixture1.asignatura2, "01-2017", Dia.DOM, "ab:00", "13:00");
        try
        {
            this.fixture1
                .sistema
                .modificarCursada(this.fixture1.cursada, modif);
        }
        catch (DatoInvalidoException e)
        {
        }
    }

    /**
     * @see Sistema#modificarCursada(Cursada,Cursada)
     */
    @Test
    public void testModificarCursadaErronea4_4()
    {
        Cursada modif = new Cursada(this.fixture1.asignatura2, "01-2017", Dia.DOM, "11:00", "ab:00");
        try
        {
            this.fixture1
                .sistema
                .modificarCursada(this.fixture1.cursada, modif);
        }
        catch (DatoInvalidoException e)
        {
        }
    }

    /**
     * @see Sistema#modificarCursada(Cursada,Cursada)
     */
    @Test
    public void testModificarCursadaErronea4_5()
    {
        Cursada modif = new Cursada(this.fixture1.asignatura2, "01-2017", Dia.DOM, "11:00", "10:00");
        try
        {
            this.fixture1
                .sistema
                .modificarCursada(this.fixture1.cursada, modif);
        }
        catch (DatoInvalidoException e)
        {
        }
    }

    /**
     * @see Sistema#modificarCursada(Cursada,Cursada)
     */
    @Test
    public void testModificarCursadaErronea4_6()
    {
        Cursada modif = new Cursada(new Asignatura(""), "01-2017", Dia.DOM, "11:00", "13:00");
        try
        {
            this.fixture1
                .sistema
                .modificarCursada(this.fixture1.cursada, modif);
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("Los datos no deberian ser invalidos");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see Sistema#modificarCursada(Cursada,Cursada)
     */
    @Test
    public void testModificarCursadaErronea6()
    {
        this.fixture1
            .sistema
            .setCalendario(new IndiceDoble<Cursada>());
        Cursada modif = new Cursada(this.fixture1.asignatura2, "01-2017", Dia.DOM, "11:00", "13:00");
        try
        {
            this.fixture1
                .sistema
                .modificarCursada(this.fixture1.cursada, modif);
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("Los datos no deberian ser invalidos");
        }
        catch (Exception e)
        {
        }
    }

    /**
     * @see Sistema#modificarCursada(Cursada,Cursada)
     */
    @Test
    public void testModificarCursadaErronea8()
    {
        this.fixture1
            .sistema
            .eliminarAsignatura(this.fixture1.asignatura2);
        Cursada modif = new Cursada(this.fixture1.asignatura2, "01-2017", Dia.DOM, "11:00", "13:00");
        try
        {
            this.fixture1
                .sistema
                .modificarCursada(this.fixture1.cursada, modif);
        }
        catch (DatoInvalidoException e)
        {
            Assert.fail("Los datos no deberian ser invalidos");
        }
        catch (Exception e)
        {
        }
    }
}
