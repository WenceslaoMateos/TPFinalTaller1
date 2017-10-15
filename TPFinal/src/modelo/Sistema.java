package modelo;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;
import excepciones.NoEncontradoException;

import java.util.Iterator;
import java.util.Observable;


public class Sistema
    extends Observable
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

    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
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
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    public void agregarAlumno(Alumno nuevo)
        throws ClaveYaExistenteException, DatoInvalidoException
    {
        if (!Persona.validaMail(nuevo.getMail()))
            throw new DatoInvalidoException(nuevo.getMail(), "Mail inválido.");
        else if (nuevo.getApellidoNombre().equals(""))
            throw new DatoInvalidoException(nuevo, "El nombre del alumno esta vacio");
        else
        {
            nuevo.setLegajo(Alumno.getNuevoLegajo());
            this.alumnos.agregar(nuevo);
        }
    }

    public void agregarProfesor(Profesor nuevo)
        throws ClaveYaExistenteException, DatoInvalidoException
    {
        if (!Persona.validaMail(nuevo.getMail()))
            throw new DatoInvalidoException(nuevo.getMail(), "Mail inválido.");
        else if (nuevo.getApellidoNombre().equals(""))
            throw new DatoInvalidoException(nuevo, "El nombre del profesor esta vacio");
        else
        {
            nuevo.setLegajo(Profesor.getNuevaIdentificacion());
            this.profesores.agregar(nuevo);
        }
    }

    public void agregarAsignatura(Asignatura nuevo)
        throws ClaveYaExistenteException, DatoInvalidoException
    {
        if (nuevo.getNombre().equals(""))
            throw new DatoInvalidoException(nuevo, "El nombre de la asignatura es invalido");
        else
        {
            nuevo.setIdentificacion(Asignatura.getNuevaIdentificacion());
            this.planDeEstudio.agregar(nuevo);
        }
    }

    public void agregarCursada(Cursada nuevo)
        throws ClaveYaExistenteException, DatoInvalidoException
    {
        if (!Cursada.validaHora(nuevo.getHora()))
            throw new DatoInvalidoException(nuevo.getHora(), "Hora inválida.");
        else if (!Cursada.validaPeriodo(nuevo.getPeriodo()))
            throw new DatoInvalidoException(nuevo.getPeriodo(), "Periodo inválido.");
        // TODO faltan verificaciones
        else
        {
            nuevo.setIdentificacion(Cursada.getNuevaIdentificacion());
            this.calendario.agregar(nuevo);
        }
    }

    public void eliminarAlumno(Alumno elim)
    {
        Cursada aux;
        Iterator<Cursada> it;
        this.alumnos.eliminar(elim);
        it = this.calendario.elementosPorClavePrimaria();
        while (it.hasNext())
        {
            aux = it.next();
            if (aux.getAlumnos().contieneValor(elim))
                aux.getAlumnos().eliminar(elim);
        }
    }

    public void eliminarProfesor(Profesor elim)
    {
        Cursada aux;
        Iterator<Cursada> it;
        this.profesores.eliminar(elim);
        it = this.calendario.elementosPorClavePrimaria();
        while (it.hasNext())
        {
            aux = it.next();
            if (aux.getProfesores().contieneValor(elim))
                aux.getProfesores().eliminar(elim);
        }
    }

    public void eliminarAsignatura(Asignatura elim)
    {
        Cursada aux;
        Iterator<Cursada> it;
        this.planDeEstudio.eliminar(elim);
        this.eliminaAsignaturaEnAlumnos(elim);
        this.eliminaAsignaturaEnProfesores(elim);
        it = this.calendario.elementosPorClavePrimaria();
        while (it.hasNext())
        {
            aux = it.next();
            if (aux.getAsignatura().equals(elim))
                this.eliminarCursada(aux);
        }
    }

    public void eliminarCursada(Cursada elim)
    {
        this.calendario.eliminar(elim);
    }

    private void eliminaAsignaturaEnAlumnos(Asignatura elim)
    {
        Iterator<Alumno> it = this.alumnos.elementosPorClavePrimaria();
        Alumno aux;
        while (it.hasNext())
        {
            aux = it.next();
            if (aux.getHistoria().contieneValor(elim))
                aux.eliminarHistoria(elim);
        }
    }

    private void eliminaAsignaturaEnProfesores(Asignatura elim)
    {
        Iterator<Profesor> it = this.profesores.elementosPorClavePrimaria();
        Profesor aux;
        while (it.hasNext())
        {
            aux = it.next();
            if (aux.habilitadoParaAsignatura(elim))
                aux.eliminarCompetencia(elim);
        }
    }

    public Iterator<Alumno> buscarAlumno(String nombre)
        throws NoEncontradoException
    {
        return this.alumnos.buscarPorClaveSecundaria(nombre);
    }

    public Iterator<Profesor> buscarProfesor(String nombre)
        throws NoEncontradoException
    {
        return this.profesores.buscarPorClaveSecundaria(nombre);
    }

    public Iterator<Asignatura> buscarAsigatura(String nombre)
        throws NoEncontradoException
    {
        return this.planDeEstudio.buscarPorClaveSecundaria(nombre);
    }
}
