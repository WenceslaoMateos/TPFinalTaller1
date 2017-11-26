package test;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.Dia;
import modelo.Profesor;
import modelo.Sistema;

public class CursadasConElementosFixture
{
    protected Sistema sistema;
    protected Alumno alumno;
    protected Profesor profesor;
    protected Asignatura asignatura1;
    protected Asignatura asignatura2;
    protected Cursada cursada1;
    protected Cursada cursada2;
    
    public CursadasConElementosFixture()
    {
    }

    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
        this.alumno = new Alumno("Juan Pico","Falucho 34333","aaaaa@gmail.com");
        this.sistema.agregarAlumno(this.alumno);
        this.profesor = new Profesor("Juan Pico", "Falucho 34333", "aaaaa@gmail.com", "2235257481");
        this.sistema.agregarProfesor(this.profesor);
        this.asignatura1 = new Asignatura("Álgebra A");
        this.asignatura2 = new Asignatura("Análisis Matemático A");
        this.sistema.agregarAsignatura(this.asignatura1);
        this.sistema.agregarAsignatura(this.asignatura2);
        this.profesor.agregarCompetencia(this.asignatura2);
        this.cursada1 = new Cursada(this.asignatura1, "01-2017", Dia.LUN, "10:00", "12:00");
        this.cursada2 = new Cursada(this.asignatura2, "01-2017", Dia.LUN, "09:00", "11:00");
        this.sistema.agregarCursada(this.cursada1);
        this.sistema.agregarCursada(this.cursada2);
        this.sistema.agregarAlumnoEnCursada(this.alumno, this.cursada2);
        this.sistema.agregarProfesorEnCursada(this.profesor, this.cursada2);
    }

    public void tearDown()
    throws Exception
    {
        this.sistema = null;
        this.alumno = null;
        this.profesor = null;
        this.asignatura1 = null;
        this.asignatura2 = null;
        this.cursada1 = null;
        this.cursada2 = null;
    }
}
