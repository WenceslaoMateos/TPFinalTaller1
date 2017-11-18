package test.caja_negra;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.Dia;
import modelo.Profesor;
import modelo.Sistema;

public class SistemaConElementosFixture
{
    protected Sistema sistema;
    protected Alumno alumno;
    protected Profesor profesor;
    protected Asignatura asignatura1;
    protected Asignatura asignatura2;
    protected Asignatura asignatura3;
    protected Cursada cursada;
    
    public SistemaConElementosFixture()
    {   
    }

    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
        this.alumno = new Alumno("PablosBraulio", "Rawson 273", "braulio@gmail.com");
        this.profesor = new Profesor("PablosBraulio", "Rawson 273", "braulio@gmail.com", "155555555");
        this.asignatura1 = new Asignatura("Análisis Matemático A");
        this.asignatura2 = new Asignatura("Física 1");
        this.asignatura3 = new Asignatura("Fundamentos de la Informática");
        this.sistema.agregarAlumno(this.alumno);
        this.sistema.agregarProfesor(this.profesor);
        this.sistema.agregarAsignatura(this.asignatura1);
        this.sistema.agregarAsignatura(this.asignatura2);
        this.sistema.agregarAsignatura(this.asignatura3);
        this.cursada = new Cursada(this.asignatura2, "02-2017", Dia.LUN, "12:00", "14:00");
        this.sistema.agregarCursada(this.cursada);
        this.alumno.agregarHistoria(this.asignatura1);
        this.profesor.agregarCompetencia(this.asignatura1);
        this.asignatura2.agregarCorrelativa(asignatura1);
    }

    public void tearDown()
        throws Exception
    {
        this.sistema = null;
        this.alumno = null;
        this.profesor = null;
        this.asignatura1 = null;
        this.asignatura2 = null;
        this.asignatura3 = null;
        this.cursada = null;
    }
}
