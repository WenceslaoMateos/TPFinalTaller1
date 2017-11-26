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
    protected Asignatura asignatura;
    protected Cursada cursada;
    
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
        this.asignatura = new Asignatura("Álgebra A");
        this.sistema.agregarAsignatura(this.asignatura);
        this.profesor.agregarCompetencia(this.asignatura);
        this.cursada = new Cursada(this.asignatura, "01-2017", Dia.LUN, "08:00", "10:00");
        this.sistema.agregarCursada(this.cursada);
        this.sistema.agregarAlumnoEnCursada(this.alumno, this.cursada);
        this.sistema.agregarProfesorEnCursada(this.profesor, this.cursada);
    }

    public void tearDown()
    throws Exception
    {
        this.sistema = null;
        this.alumno = null;
        this.profesor = null;
        this.asignatura = null;
        this.cursada = null;
    }
}
