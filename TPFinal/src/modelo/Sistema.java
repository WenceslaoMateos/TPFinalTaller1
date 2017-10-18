package modelo;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;
import excepciones.NoEncontradoException;

import java.util.ArrayList;
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
    
    public void agregarAlumno(Alumno nuevo)
        throws ClaveYaExistenteException, DatoInvalidoException
    {
        if (!Persona.validaPersona(nuevo))
            throw new DatoInvalidoException(nuevo, "Se encontraron datos inválidos.");
        else
        {
            nuevo.setLegajo(Alumno.getNuevoLegajo());
            this.alumnos.agregar(nuevo);
        }
    }
    
    public void agregarProfesor(Profesor nuevo)
        throws ClaveYaExistenteException, DatoInvalidoException
    {
        if (!Persona.validaPersona(nuevo))
            throw new DatoInvalidoException(nuevo, "Se encontraron datos inválidos.");
        else
        {
            nuevo.setLegajo(Profesor.getNuevaIdentificacion());
            this.profesores.agregar(nuevo);
        }
    }
    
    public void agregarAsignatura(Asignatura nuevo)
        throws ClaveYaExistenteException, DatoInvalidoException
    {
        if (!Asignatura.validaAsignatura(nuevo))
            throw new DatoInvalidoException(nuevo, "Se encontraron datos inválidos.");
        else
        {
            nuevo.setIdentificacion(Asignatura.getNuevaIdentificacion());
            this.planDeEstudio.agregar(nuevo);
        }
    }
    
    public void agregarCursada(Cursada nuevo)
        throws ClaveYaExistenteException, DatoInvalidoException
    {
        if (!Cursada.validaCursada(nuevo))
            throw new DatoInvalidoException(nuevo, "Se encontraron datos inválidos.");
        /* 
        else if (!this.horarioCursadaDisponible(nuevo))
            throw new DatoInvalidoException(nuevo, "El horario solicitado ya está ocupado.");
        */
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
            if (aux.tieneAlumno(elim))
                aux.bajaAlumno(elim);
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
            if (aux.tieneProfesor(elim))
                aux.bajaProfesor(elim);
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
            if (aux.asignaturaAprobada(elim))
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
    
    private Iterator busquedaPorNombre(String nombre, IndiceDoble indice)
    {
        String clave;
        Iterator<I_Indexable> elementos;
        Iterator<String> claves = (Iterator<String>) indice.clavesSecundarias();
        ArrayList aux = new ArrayList();
        String nombreUpper = nombre.toUpperCase();
        while (claves.hasNext())
        {
            clave = claves.next();
            if (clave.toUpperCase().contains(nombreUpper))
            {
                try
                {
                    elementos = indice.buscarPorClaveSecundaria(clave);
                    elementos.forEachRemaining(aux::add);
                }
                catch (NoEncontradoException e)
                {
                    //Ya se verificó que la clave está
                }
            }
        }
        return aux.iterator();
    }
    
    public Iterator<Alumno> buscarAlumno(String nombre)
        throws NoEncontradoException
    {
        Iterator<Alumno> ret = this.busquedaPorNombre(nombre, this.alumnos);
        if (!ret.hasNext())
            throw new NoEncontradoException(nombre, "El nombre solicitado no fue encontrado.");
        else
            return ret;
    }
    
    public Iterator<Profesor> buscarProfesor(String nombre)
        throws NoEncontradoException
    {
        Iterator<Profesor> ret = this.busquedaPorNombre(nombre, this.profesores);
        if (!ret.hasNext())
            throw new NoEncontradoException(nombre, "El nombre solicitado no fue encontrado.");
        else
            return ret;
    }
    
    public Iterator<Asignatura> buscarAsignatura(String nombre)
        throws NoEncontradoException
    {
        Iterator<Asignatura> ret = this.busquedaPorNombre(nombre, this.planDeEstudio);
        if (!ret.hasNext())
            throw new NoEncontradoException(nombre, "El nombre solicitado no fue encontrado.");
        else
            return ret;
    }
    
    public Iterator<Cursada> buscarCursada(String nombreAsignatura)
    {
        Cursada cursada;
        Iterator<Cursada> cursadas = this.calendario.elementosPorClavePrimaria();
        ArrayList<Cursada> aux = new ArrayList<Cursada>();
        String nombreUpper = nombreAsignatura.toUpperCase();
        while (cursadas.hasNext())
        {
            cursada = cursadas.next();
            if (cursada.getAsignatura().getNombre().toUpperCase().contains(nombreUpper))
                aux.add(cursada);
        }
        return aux.iterator();
    }
    
    public Alumno buscarAlumnoPorLegajo(String legajo)
        throws NoEncontradoException
    {
        return this.alumnos.buscarPorClavePrimaria(legajo);
    }
    
    public Profesor buscarProfesorPorLegajo(String legajo)
        throws NoEncontradoException
    {
        return this.profesores.buscarPorClavePrimaria(legajo);
    }
    
    public Asignatura buscarAsignaturaPorIdentifiacion(String identificacion)
        throws NoEncontradoException
    {
        return this.planDeEstudio.buscarPorClavePrimaria(identificacion);
    }
    
    public Cursada buscarCursadaPorIdentificacion(String identificacion)
        throws NoEncontradoException
    {
        return this.calendario.buscarPorClavePrimaria(identificacion);
    }
    
    public void agregarAlumnoEnCursada(Alumno alumno, Cursada cursada)
        throws DatoInvalidoException, ClaveYaExistenteException
    {
        if (!this.alumnoDisponible(alumno, cursada))
            throw new DatoInvalidoException(alumno,
                                            "El alumno solicitado se encuentra ocupado en el horario de la cursada.");
        else
            cursada.altaAlumno(alumno);
    }
    
    public void agregarProfesorEnCursada(Profesor profesor, Cursada cursada)
        throws DatoInvalidoException, ClaveYaExistenteException
    {
        if (!this.profesorDisponible(profesor, cursada))
            throw new DatoInvalidoException(profesor,
                                            "El profesor solicitado se encuentra ocupado en el horario de la cursada.");
        else
            cursada.altaProfesor(profesor);
    }
    
    private boolean alumnoDisponible(Alumno alumno, Cursada cursada)
    {
        boolean res = true;
        Cursada aux;
        Iterator<Cursada> it = this.calendario.elementosPorClavePrimaria();
        while (it.hasNext() && res)
        {
            aux = it.next();
            // El alumno no debe estar en la cursada o la misma no debe colisionar con la solicitada
            res = !aux.tieneAlumno(alumno) || !aux.hayColision(cursada);
        }
        return res;
    }
    
    private boolean profesorDisponible(Profesor profesor, Cursada cursada)
    {
        boolean res = true;
        Cursada aux;
        Iterator<Cursada> it = this.calendario.elementosPorClavePrimaria();
        while (it.hasNext() && res)
        {
            aux = it.next();
            // El profesor no debe estar en la cursada o la misma no debe colisionar con la solicitada
            res = !aux.tieneProfesor(profesor) || !aux.hayColision(cursada);
        }
        return res;
    }
    
    public void modificarAlumno(Alumno alumno, Alumno modif)
        throws DatoInvalidoException
    {
        if (!Persona.validaPersona(modif))
            throw new DatoInvalidoException(modif, "Se detectaron parámetros inválidos al tratar de modificar.");
        this.alumnos.modificarValor(alumno, modif);
    }
    
    public void modificarProfesor(Profesor profesor, Profesor modif)
        throws DatoInvalidoException
    {
        if (!Persona.validaPersona(modif))
            throw new DatoInvalidoException(modif, "Se detectaron parámetros inválidos al tratar de modificar.");
        this.profesores.modificarValor(profesor, modif);
    }
    
    public void modificarAsignatura(Asignatura asignatura, Asignatura modif)
        throws DatoInvalidoException
    {
        if (!Asignatura.validaAsignatura(modif))
            throw new DatoInvalidoException(modif, "Se detectaron parámetros inválidos al tratar de modificar.");
        this.planDeEstudio.modificarValor(asignatura, modif);
    }
    
    public void modificarCursada(Cursada cursada, Cursada modif)
        throws DatoInvalidoException
    {
        if (!Cursada.validaCursada(modif))
            throw new DatoInvalidoException(modif, "Se detectaron parámetros inválidos al tratar de modificar.");
        this.calendario.modificarValor(cursada, modif);
    }

    /* 
    private boolean horarioCursadaDisponible(Cursada cursada)
    {
        boolean res = true;
        Iterator<Cursada> it = this.calendario.elementosPorClavePrimaria();
        while (it.hasNext() && res)
            res = !cursada.hayColision(it.next());
        return res;
    }
    */
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
}
