package modelo;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;
import excepciones.NoEncontradoException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 * Clase que engloba el conjunto de alumnos, profesores, asignaturas y cursadas, como las acciones que se pueden
 * realizar sobre los mismos.
 */
public class Sistema
    extends Observable
{
    /**
     * @aggregation composite
     */
    private IndiceDoble<Alumno> alumnos;

    /**
     * @aggregation composite
     */
    private IndiceDoble<Profesor> profesores;

    /**
     * @aggregation composite
     */
    private IndiceDoble<Asignatura> planDeEstudio;

    /**
     * @aggregation composite
     */
    private IndiceDoble<Cursada> calendario;

    /**
     * Constructor que crea una nueva instancia de Sistema vacía.
     */
    public Sistema()
    {
        this.alumnos = new IndiceDoble<Alumno>();
        this.profesores = new IndiceDoble<Profesor>();
        this.planDeEstudio = new IndiceDoble<Asignatura>();
        this.calendario = new IndiceDoble<Cursada>();
    }

    /**
     * Agrega un alumno nuevo al sistema, dándole un legajo.<br>
     * <b>Post:</b> El sistema tiene un alumno válido más que está correctamente indexado.
     * @param nuevo Alumno a agregar.
     * @throws ClaveYaExistenteException El legajo otorgado ya existía en el sistema.
     * @throws DatoInvalidoException Los atributos del alumno son incorrectos.
     */
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

    /**
     * Agrega un profesor nuevo al sistema, dándole un legajo.<br>
     * <b>Post:</b> El sistema tiene un profesor válido más que está correctamente indexado.
     * @param nuevo Profesor a agregar.
     * @throws ClaveYaExistenteException El legajo otorgado ya existía en el sistema.
     * @throws DatoInvalidoException Los atributos del profesor son incorrectos.
     */
    public void agregarProfesor(Profesor nuevo)
        throws ClaveYaExistenteException, DatoInvalidoException
    {
        if (!Persona.validaPersona(nuevo))
            throw new DatoInvalidoException(nuevo, "Se encontraron datos inválidos.");
        else
        {
            nuevo.setLegajo(Profesor.getNuevoLegajo());
            this.profesores.agregar(nuevo);
        }
    }

    /**
     * Agrega una asignatura nueva al sistema, dándole una indentificación.<br>
     * <b>Post:</b> El sistema tiene una asignatura válida más que está correctamente indexada.
     * @param nuevo Asignatura a agregar.
     * @throws ClaveYaExistenteException La identificación otorgada ya existía en el sistema.
     * @throws DatoInvalidoException Los atributos de la asignatura son incorrectos.
     */
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

    /**
     * Agrega una cursada nueva al sistema, dándole una indentificación.<br>
     * <b>Post:</b> El sistema tiene una cursada válida más que está correctamente indexada.
     * @param nuevo Cursada a agregar.
     * @throws ClaveYaExistenteException La identificación otorgada ya existía en el sistema.
     * @throws DatoInvalidoException Los atributos de la cursada son incorrectos.
     */
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

    /**
     * Elimina a un alumno del sistema.<br>
     * <b>Pre:</b> El alumno se encuentra en el sistema.<br>
     * <b>Post:</b> El sistema tiene un alumno menos que fue dado de baja de todas las cursadas en las que participaba.
     * @param elim Alumno a remover del sistema.
     */
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

    /**
     * Elimina a un profesor del sistema.<br>
     * <b>Pre:</b> El profesor se encuentra en el sistema.<br>
     * <b>Post:</b> El sistema tiene un profesor menos que fue dado de baja de todas las cursadas en las que participaba.
     * @param elim Profesor a remover del sistema.
     */
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

    /**
     * Elimina una asignatura del sistema.<br>
     * <b>Pre:</b> La asignatura se encuentra en el sistema.<br>
     * <b>Post:</b> El sistema tiene una asignatura menos que fue excluida de la historia académica de sus alumnos, de
     * las competencias de sus profesores y de las precorrelativades de otras materias.
     * A su vez, se eliminaron todas la cursadas de esa materia.
     * @param elim Asignatura a remover del sistema.
     */
    public void eliminarAsignatura(Asignatura elim)
    {
        Cursada aux;
        Iterator<Cursada> it;
        this.planDeEstudio.eliminar(elim);
        this.eliminaAsignaturaEnAlumnos(elim);
        this.eliminaAsignaturaEnProfesores(elim);
        this.eliminaAsignaturaEnCorrelatividades(elim);
        it = this.calendario.elementosPorClavePrimaria();
        while (it.hasNext())
        {
            aux = it.next();
            if (aux.getAsignatura().equals(elim))
                this.eliminarCursada(aux);
        }
    }

    /**
     * Elimina una cursada del sistema.<br>
     * <b>Pre:</b> La cursada se encuentra en el sistema.<br>
     * <b>Post:</b> El sistema tiene una cursada menos.
     * @param elim Cursada a remover del sistema.
     */
    public void eliminarCursada(Cursada elim)
    {
        this.calendario.eliminar(elim);
    }

    /**
     * Busca entre los alumnos el parámetro y lo elimina de sus materias aprobadas.<br>
     * <b>Post:</b> Ningún alumno mantiene la asignatura en su historia académica.
     * @param elim Asignatura a eliminar de las historias académicas.
     */
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

    /**
     * Busca entre los profesores el parámetro y lo elimina de sus materias habilitadas.<br>
     * <b>Post:</b> Ningún profesor mantiene la asignatura entre sus competencias.
     * @param elim Asignatura a eliminar de las competencias.
     */
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

    /**
     * Busca entre las asignaturas el parámetro y lo elimina de sus precorrelativas.<br>
     * <b>Post:</b> Ninguna materia mantiene la asignatura entre sus precorrelativas.
     * @param elim Asignatura a eliminar de las precorrelativas.
     */
    private void eliminaAsignaturaEnCorrelatividades(Asignatura elim)
    {
        Iterator<Asignatura> it = this.planDeEstudio.elementosPorClavePrimaria();
        Asignatura aux;
        while (it.hasNext())
        {
            aux = it.next();
            if (aux.tienePrecorrelativa(elim))
                aux.eliminarCorrelativa(elim);
        }
    }

    /**
     * Búsqueda genérica en un índice mediante un nombre parcial.<br>
     * <b>Pre:</b> indice mantiene un conjunto de elementos cuyas claves secundarias son Strings.
     * @param nombre String a buscar entre las claves secundarias del índice.
     * @param indice IndiceDoble cuyas claves secundarias son Strings.
     * @return Iterator con todos los elementos cuyas claves secundarias contienen el nombre proporcionado.
     */
    private Iterator busquedaPorNombre(String nombre, IndiceDoble indice)
    {
        String clave;
        Iterator<I_Indexable> elementos;
        Iterator<String> claves = indice.clavesSecundarias();
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

    /**
     * Busca alumnos en el sistema cuyos nombres contengan el String parámetro.<br>
     * <b>Post:</b> Al menos el nombre de un alumno presentó coincidencias.
     * @param nombre String a buscar entre los nombres de los alumnos del sistema.
     * @return Iterator con todos aquellos alumnos cuyos nombres contienen la cadena proporcionada.
     * @throws NoEncontradoException No se encontró ninguna coincidencia entre el String proporcionado y los nombres de
     * los alumnos.
     */
    public Iterator<Alumno> buscarAlumno(String nombre)
        throws NoEncontradoException
    {
        Iterator<Alumno> ret = this.busquedaPorNombre(nombre, this.alumnos);
        if (!ret.hasNext())
            throw new NoEncontradoException(nombre, "El nombre solicitado no fue encontrado.");
        else
            return ret;
    }

    /**
     * Busca profesores en el sistema cuyos nombres contengan el String parámetro.<br>
     * <b>Post:</b> Al menos el nombre de un profesor presentó coincidencias.
     * @param nombre String a buscar entre los nombres de los profesores del sistema.
     * @return Iterator con todos aquellos profesores cuyos nombres contienen la cadena proporcionada.
     * @throws NoEncontradoException No se encontró ninguna coincidencia entre el String proporcionado y los nombres de
     * los profesores.
     */
    public Iterator<Profesor> buscarProfesor(String nombre)
        throws NoEncontradoException
    {
        Iterator<Profesor> ret = this.busquedaPorNombre(nombre, this.profesores);
        if (!ret.hasNext())
            throw new NoEncontradoException(nombre, "El nombre solicitado no fue encontrado.");
        else
            return ret;
    }

    /**
     * Busca asignaturas en el sistema cuyos nombres contengan el String parámetro.<br>
     * <b>Post:</b> Al menos el nombre de una asignatura presentó coincidencias.
     * @param nombre String a buscar entre los nombres de las asignaturas del sistema.
     * @return Iterator con todos aquellas asignaturas cuyos nombres contienen la cadena proporcionada.
     * @throws NoEncontradoException No se encontró ninguna coincidencia entre el String proporcionado y los nombres de
     * los asignaturas.
     */
    public Iterator<Asignatura> buscarAsignatura(String nombre)
        throws NoEncontradoException
    {
        Iterator<Asignatura> ret = this.busquedaPorNombre(nombre, this.planDeEstudio);
        if (!ret.hasNext())
            throw new NoEncontradoException(nombre, "El nombre solicitado no fue encontrado.");
        else
            return ret;
    }

    /**
     * Busca cursadas en el sistema cuyas asignaturas tengan nombres que contengan el String parámetro.<br>
     * <b>Post:</b> Al menos el nombre de la asignatura de una cursada presentó coincidencias.
     * @param nombreAsignatura String a buscar entre los nombres de las asignaturas de las cursadas del sistema.
     * @return Iterator con todos aquellas cursadas cuyas asignaturas tienen nombres que contienen la cadena proporcionada.
     * @throws NoEncontradoException No se encontró ninguna coincidencia entre el String proporcionado y los nombres de
     * los asignaturas de las cursadas.
     */
    public Iterator<Cursada> buscarCursada(String nombreAsignatura)
        throws NoEncontradoException
    {
        Cursada cursada;
        Iterator<Cursada> cursadas = this.calendario.elementosPorClavePrimaria();
        ArrayList<Cursada> aux = new ArrayList<Cursada>();
        String nombreUpper = nombreAsignatura.toUpperCase();
        while (cursadas.hasNext())
        {
            cursada = cursadas.next();
            if (cursada.getAsignatura()
                       .getNombre()
                       .toUpperCase()
                       .contains(nombreUpper))
                aux.add(cursada);
        }
        if (aux.isEmpty())
            throw new NoEncontradoException(nombreAsignatura,
                                            "No se encontró ninguna cursada cuya asignatura coincida con el nombre dado.");
        else
            return aux.iterator();
    }

    /**
     * Busca a un alumno en el sistema por su legajo.<br>
     * <b>Post:</b> Se encontró un alumno asociado al legajo dado.
     * @param legajo String a buscar entre los legajos.
     * @return Referencia al alumno asociado al legajo proporcionado.
     * @throws NoEncontradoException Ningún alumno tiene el legajo proporcionado.
     */
    public Alumno buscarAlumnoPorLegajo(String legajo)
        throws NoEncontradoException
    {
        return this.alumnos.buscarPorClavePrimaria(legajo);
    }

    /**
     * Busca a un profesor en el sistema por su legajo.<br>
     * <b>Post:</b> Se encontró un profesor asociado al legajo dado.
     * @param legajo String a buscar entre los legajos.
     * @return Referencia al profesor asociado al legajo proporcionado.
     * @throws NoEncontradoException Ningún profesor tiene el legajo proporcionado.
     */
    public Profesor buscarProfesorPorLegajo(String legajo)
        throws NoEncontradoException
    {
        return this.profesores.buscarPorClavePrimaria(legajo);
    }

    /**
     * Busca a una asignatura en el sistema por su identificación.<br>
     * <b>Post:</b> Se encontró una asignatura asociada a la identificación dada.
     * @param identificacion String a buscar entre las identificaciones.
     * @return Referencia a la asignatura asociada a la identificación proporcionada.
     * @throws NoEncontradoException Ninguna asignatura tiene la identificación proporcionada.
     */
    public Asignatura buscarAsignaturaPorIdentifiacion(String identificacion)
        throws NoEncontradoException
    {
        return this.planDeEstudio.buscarPorClavePrimaria(identificacion);
    }

    /**
     * Busca a una cursada en el sistema por su identificación.<br>
     * <b>Post:</b> Se encontró una cursada asociada a la identificación dada.
     * @param identificacion String a buscar entre las identificaciones.
     * @return Referencia a la cursada asociada a la identificación proporcionada.
     * @throws NoEncontradoException Ninguna cursada tiene la identificación proporcionada.
     */
    public Cursada buscarCursadaPorIdentificacion(String identificacion)
        throws NoEncontradoException
    {
        return this.calendario.buscarPorClavePrimaria(identificacion);
    }
    
    /**
     * Elimina la asignatura dada de entre las competencias del profesor.<br>
     * <b>Pre:</b> El profesor se encuentra en el sistema y la asignatura está entre sus competencias.<br>
     * <b>Post:</b> El profesor está habilitado para dar una asignatura menos y fue dado de baja en todas las cursadas
     * de la misma en las que participaba.
     * @param profesor Profesor a quien se le desea remover una competencia.
     * @param asignatura Competencia a ser eliminada.
     */
    public void quitarCompetenciaAProfesor(Profesor profesor, Asignatura asignatura)
    {
        Cursada aux;
        Iterator<Cursada> it = this.calendario.elementosPorClavePrimaria();
        while (it.hasNext())
        {
            aux = it.next();
            if (aux.getAsignatura().equals(asignatura) && aux.tieneProfesor(profesor))
                aux.bajaProfesor(profesor);
        }
        profesor.eliminarCompetencia(asignatura);
    }
    
    /**
     * Agrega un alumno en la cursada solicitada.<br>
     * <b>Pre:</b> alumno y cursada forman parte del sistema.<br>
     * <b>Post:</b> El alumno dado fue dado de alta en la cursada proporcionada.
     * @param alumno Alumno solicitante.
     * @param cursada Cursada solicitada.
     * @throws DatoInvalidoException El alumno se encuentra ocupado en el horario de la cursada.
     * @throws ClaveYaExistenteException El alumno ya está anotado en la cursada.
     */
    public void agregarAlumnoEnCursada(Alumno alumno, Cursada cursada)
        throws DatoInvalidoException, ClaveYaExistenteException
    {
        if (!this.alumnoDisponible(alumno, cursada))
            throw new DatoInvalidoException(alumno,
                                            "El alumno solicitado se encuentra ocupado en el horario de la cursada.");
        else
            cursada.altaAlumno(alumno);
    }

    /**
     * Agrega un profesor en la cursada solicitada.<br>
     * <b>Pre:</b> profesor y cursada forman parte del sistema.<br>
     * <b>Post:</b> El profesor dado fue dado de alta en la cursada proporcionada.
     * @param profesor Profesor solicitante.
     * @param cursada Cursada solicitada.
     * @throws DatoInvalidoException El profesor se encuentra ocupado en el horario de la cursada.
     * @throws ClaveYaExistenteException El profesor ya está anotado en la cursada.
     */
    public void agregarProfesorEnCursada(Profesor profesor, Cursada cursada)
        throws DatoInvalidoException, ClaveYaExistenteException
    {
        if (!this.profesorDisponible(profesor, cursada))
            throw new DatoInvalidoException(profesor,
                                            "El profesor solicitado se encuentra ocupado en el horario de la cursada.");
        else
            cursada.altaProfesor(profesor);
    }

    /**
     * Comprueba que un alumno esté disponible en el horario de la cursada dada.<br>
     * <b>Pre:</b> alumno y cursada forman parte del sistema.
     * @param alumno Alumno a verificar sus horarios.
     * @param cursada Cursada con los horarios a comparar.
     * @return <b>true</b> si el alumno está libre en el horario de la cursada, <b>false</b> en caso contrario.
     */
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

    /**
     * Comprueba que un profesor esté disponible en el horario de la cursada dada.<br>
     * <b>Pre:</b> profesor y cursada forman parte del sistema.
     * @param profesor Profesor a verificar sus horarios.
     * @param cursada Cursada con los horarios a comparar.
     * @return <b>true</b> si el alumno está libre en el horario de la cursada, <b>false</b> en caso contrario.
     */
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

    /**
     * Dado un alumno, modifica sus atributos a partir del estado del parámetro modif.<br>
     * Ver modificarDatos() de la clase Persona.<br>
     * <b>Pre:</b> alumno forma parte del sistema.<br>
     * <b>Post:</b> el estado del alumno se adaptó a los cambios introducidos por modif y el mismo se mantiene
     * correctamente indexado.
     * @param alumno Alumno a modificar sus datos.
     * @param modif objeto que encapsula los cambios a introducir en alumno.
     * @throws DatoInvalidoException modif presenta atributos inválidos.
     */
    public void modificarAlumno(Alumno alumno, Alumno modif)
        throws DatoInvalidoException
    {
        if (!Persona.validaPersona(modif))
            throw new DatoInvalidoException(modif, "Se detectaron parámetros inválidos al tratar de modificar.");
        else
            this.alumnos.modificarValor(alumno, modif);
    }

    /**
     * Dado un profesor, modifica sus atributos a partir del estado del parámetro modif.<br>
     * Ver modificarDatos() de la clase Profesor.<br>
     * <b>Pre:</b> profesor forma parte del sistema.<br>
     * <b>Post:</b> el estado del profesor se adaptó a los cambios introducidos por modif y el mismo se mantiene
     * correctamente indexado.
     * @param profesor Profesor a modificar sus datos.
     * @param modif objeto que encapsula los cambios a introducir en alumno.
     * @throws DatoInvalidoException modif presenta atributos inválidos.
     */
    public void modificarProfesor(Profesor profesor, Profesor modif)
        throws DatoInvalidoException
    {
        if (!Persona.validaPersona(modif))
            throw new DatoInvalidoException(modif, "Se detectaron parámetros inválidos al tratar de modificar.");
        else
            this.profesores.modificarValor(profesor, modif);
    }

    /**
     * Dada una asignatura, modifica sus atributos a partir del estado del parámetro modif.<br>
     * Ver modificarDatos() de la clase Asignatura.<br>
     * <b>Pre:</b> asignatura forma parte del sistema.<br>
     * <b>Post:</b> el estado de la asignatura se adaptó a los cambios introducidos por modif y la misma se mantiene
     * correctamente indexada.
     * @param asignatura Asignatura a modificar sus datos.
     * @param modif objeto que encapsula los cambios a introducir en alumno.
     * @throws DatoInvalidoException modif presenta atributos inválidos.
     */
    public void modificarAsignatura(Asignatura asignatura, Asignatura modif)
        throws DatoInvalidoException
    {
        if (!Asignatura.validaAsignatura(modif))
            throw new DatoInvalidoException(modif, "Se detectaron parámetros inválidos al tratar de modificar.");
        else
            this.planDeEstudio.modificarValor(asignatura, modif);
    }

    /**
     * Dada una cursada, modifica sus atributos a partir del estado del parámetro modif.<br>
     * Ver modificarDatos() de la clase Cursada.<br>
     * <b>Pre:</b> cursada forma parte del sistema y los profesores y alumnos no tienen superposiciones con el
     * nuevo horario.<br>
     * <b>Post:</b> el estado de la cursada se adaptó a los cambios introducidos por modif y la misma se mantiene
     * correctamente indexada.
     * @param cursada Cursada a modificar sus datos.
     * @param modif objeto que encapsula los cambios a introducir en alumno.
     * @throws DatoInvalidoException modif presenta atributos inválidos.
     */
    public void modificarCursada(Cursada cursada, Cursada modif)
        throws DatoInvalidoException
    {
        if (!Cursada.validaCursada(modif))
            throw new DatoInvalidoException(modif, "Se detectaron parámetros inválidos al tratar de modificar.");
        else
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
