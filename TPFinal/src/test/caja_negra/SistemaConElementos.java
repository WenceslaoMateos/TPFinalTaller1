package test.caja_negra;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.Dia;
import modelo.Profesor;
import modelo.Sistema;

public class SistemaConElementos
{
    protected Sistema sistema;
    protected Alumno alumno;
    protected Profesor profesor;
    protected Asignatura asignatura;
    protected Cursada cursada;
    
    public SistemaConElementos()
    {   
    }

    public void setUp()
        throws Exception
    {
        this.sistema = new Sistema();
        this.alumno = new Alumno("PablosBraulio", "Rawson 273", "braulio@gmail.com");
        this.profesor = new Profesor("PablosBraulio", "Rawson 273", "braulio@gmail.com", "155555555");
        this.asignatura = new Asignatura("Física 1");
        this.sistema.agregarAlumno(this.alumno);
        this.sistema.agregarProfesor(this.profesor);
        this.sistema.agregarAsignatura(this.asignatura);
        this.cursada = new Cursada(this.asignatura, "02-2017", Dia.LUN, "12:00", "14:00");
        this.sistema.agregarCursada(this.cursada);
        this.alumno.agregarHistoria(this.asignatura);
        this.profesor.agregarCompetencia(this.asignatura);
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
