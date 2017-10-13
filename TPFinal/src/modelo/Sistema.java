package modelo;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;


public class Sistema
{
    private IndiceDoble<Alumno> alumnos;
    private IndiceDoble<Profesor> profesores;
    private IndiceDoble<Asignatura> planDeEstudio;
    private IndiceDoble<Cursada> calendario;

    public Sistema()
    {
        this.alumnos = new IndiceDoble<Alumno>();
        this.profesores = new IndiceDoble<Profesor>();
        this.planDeEstudio = new IndiceDoble<Asignatura>();
        this.calendario = new IndiceDoble<Cursada>();
    }
    
    public void setAlumnos(IndiceDoble<Alumno> alumnos)
    {
        this.alumnos = alumnos;
    }

    public IndiceDoble<Alumno> getAlumnos()
    {
        return alumnos;
    }

    public void setProfesores(IndiceDoble<Profesor> profesores)
    {
        this.profesores = profesores;
    }

    public IndiceDoble<Profesor> getProfesores()
    {
        return profesores;
    }

    public void setPlanDeEstudio(IndiceDoble<Asignatura> planDeEstudio)
    {
        this.planDeEstudio = planDeEstudio;
    }

    public IndiceDoble<Asignatura> getPlanDeEstudio()
    {
        return planDeEstudio;
    }

    public void setCalendario(IndiceDoble<Cursada> calendario)
    {
        this.calendario = calendario;
    }

    public IndiceDoble<Cursada> getCalendario()
    {
        return calendario;
    }
    
    public void agregarAlumno(Alumno nuevo)
        throws ClaveYaExistenteException, DatoInvalidoException
    {
        if (!Persona.validaMail(nuevo.getMail()))
            throw new DatoInvalidoException(nuevo.getMail(), "Mail inválido.");
        else
            this.alumnos.agregar(nuevo);
    }
    
    public void agregarProfesor(Profesor nuevo)
        throws ClaveYaExistenteException, DatoInvalidoException
    {
        if (!Persona.validaMail(nuevo.getMail()))
            throw new DatoInvalidoException(nuevo.getMail(), "Mail inválido.");
        else
            this.profesores.agregar(nuevo);
    }
    
    public void agregarAsignatura(Asignatura nuevo)
        throws ClaveYaExistenteException
    {
        this.planDeEstudio.agregar(nuevo);
    }
    
    public void agregarCursada(Cursada nuevo)
        throws ClaveYaExistenteException, DatoInvalidoException
    {
        if (!Cursada.validaHora(nuevo.getHora()))
            throw new DatoInvalidoException(nuevo.getHora(), "Hora inválida.");
        else if (!Cursada.validaPeriodo(nuevo.getPeriodo()))
            throw new DatoInvalidoException(nuevo.getPeriodo(), "Periodo inválido.");
        else
            this.calendario.agregar(nuevo);
    }
}
