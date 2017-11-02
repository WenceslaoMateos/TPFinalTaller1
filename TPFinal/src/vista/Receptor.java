package vista;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;
import excepciones.NoEncontradoException;

import java.util.Iterator;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.Persona;
import modelo.Profesor;
import modelo.Sistema;

import persistencia.SerializadorXML;


public class Receptor
{
    private Sistema modelo;
    public static final int ALUMNO = 0;
    public static final int PROFESOR = 1;
    public static final int ASIGNATURA = 2;
    public static final int CURSADA = 3;


    public Receptor(Sistema modelo)
    {
        super();
        this.modelo = modelo;
    }

    public void alta(Object obj, int comando)
        throws ClaveYaExistenteException, DatoInvalidoException
    {
        switch (comando)
        {
            case Receptor.ALUMNO:
                this.modelo.agregarAlumno((Alumno) obj);
                break;
            case Receptor.PROFESOR:
                this.modelo.agregarProfesor((Profesor) obj);
                break;
            case Receptor.ASIGNATURA:
                this.modelo.agregarAsignatura((Asignatura) obj);
                break;
            case Receptor.CURSADA:
                this.modelo.agregarCursada((Cursada) obj);
                break;
        }
    }

    public Iterator ubicar(Object obj, int comando)
        throws NoEncontradoException
    {
        Iterator ret = null;
        switch (comando)
        {
            case Receptor.ALUMNO:
                ret = this.modelo.buscarAlumno((String) obj);
                break;
            case Receptor.PROFESOR:
                ret = this.modelo.buscarProfesor((String) obj);
                break;
            case Receptor.ASIGNATURA:
                ret = this.modelo.buscarAsignatura((String) obj);
                break;
            case Receptor.CURSADA:
                ret = this.modelo.buscarCursada((String) obj);
                break;
        }
        return ret;
    }

    public Object buscar(Object obj, int comando)
        throws NoEncontradoException
    {
        Object ret = null;
        switch (comando)
        {
            case Receptor.ALUMNO:
                ret = this.modelo.buscarAlumnoPorLegajo((String) obj);
                break;
            case Receptor.PROFESOR:
                ret = this.modelo.buscarProfesorPorLegajo((String) obj);
                break;
            case Receptor.ASIGNATURA:
                ret = this.modelo.buscarAsignaturaPorIdentifiacion((String) obj);
                break;
            case Receptor.CURSADA:
                ret = this.modelo.buscarCursadaPorIdentificacion((String) obj);
                break;
        }
        return ret;
    }

    public void baja(Object obj, int comando)
        throws NoEncontradoException
    {
        switch (comando)
        {
            case Receptor.ALUMNO:
                this.modelo.eliminarAlumno((Alumno) this.buscar(obj, comando));
                break;
            case Receptor.PROFESOR:
                this.modelo.eliminarProfesor((Profesor) this.buscar(obj, comando));
                break;
            case Receptor.ASIGNATURA:
                this.modelo.eliminarAsignatura((Asignatura) this.buscar(obj, comando));
                break;
            case Receptor.CURSADA:
                this.modelo.eliminarCursada((Cursada) obj);
                break;
        }
    }

    public void modificacion(Object obj, int comando, Iterator eliminar, Iterator agregar)
        throws DatoInvalidoException, NoEncontradoException, ClaveYaExistenteException
    {
        switch (comando)
        {
            case Receptor.ALUMNO:
                Alumno aluViejo = (Alumno) this.buscar(((Alumno) obj).getLegajo(), comando);
                // Primero se llama a modificarAlumno para evitar manipular la colección si hay un error
                this.modelo.modificarAlumno(aluViejo, (Alumno) obj);
                while (eliminar.hasNext())
                    aluViejo.eliminarHistoria((Asignatura) eliminar.next());
                while (agregar.hasNext())
                    aluViejo.agregarHistoria((Asignatura) agregar.next());
                break;
            case Receptor.PROFESOR:
                Profesor profViejo = (Profesor) this.buscar(((Profesor) obj).getLegajo(), comando);
                // Primero se llama a modificarProfesor para evitar manipular la colección si hay un error
                this.modelo.modificarProfesor(profViejo, (Profesor) obj);
                while (eliminar.hasNext())
                    this.modelo.quitarCompetenciaAProfesor(profViejo, (Asignatura) eliminar.next());
                while (agregar.hasNext())
                    profViejo.agregarCompetencia((Asignatura) agregar.next());
                break;
            case Receptor.ASIGNATURA:
                Asignatura asigViejo = (Asignatura) this.buscar(((Asignatura) obj).getIdentificacion(), comando);
                // Primero se llama a modificarAsignatura para evitar manipular la colección si hay un error
                this.modelo.modificarAsignatura(asigViejo, (Asignatura) obj);
                while (eliminar.hasNext())
                    asigViejo.eliminarCorrelativa((Asignatura) eliminar.next());
                while (agregar.hasNext())
                    asigViejo.agregarCorrelativa((Asignatura) agregar.next());
                break;
            case Receptor.CURSADA:
                Cursada curViejo;
                Persona aux;
                if (!this.participantesProvisoriosDisponibles((Cursada) obj))
                    throw new DatoInvalidoException(obj,
                                                    "El horario de la cursada no es viable para todos los participantes.");
                else
                {
                    curViejo = (Cursada) this.buscar(((Cursada) obj).getIdentificacion(), comando);
                    // Primero se llama a modificarCursada para evitar manipular las colecciones si hay un error
                    this.modelo.modificarCursada(curViejo, (Cursada) obj);
                    while (eliminar.hasNext())
                    {
                        aux = (Persona) eliminar.next();
                        if (aux.getLegajo()
                               .substring(0, 3)
                               .equals("ALU"))
                            curViejo.bajaAlumno((Alumno) aux);
                        else if (aux.getLegajo()
                                    .substring(0, 3)
                                    .equals("PRO"))
                            curViejo.bajaProfesor((Profesor) aux);
                    }

                    while (agregar.hasNext())
                    {
                        aux = (Persona) agregar.next();
                        if (aux.getLegajo()
                               .substring(0, 3)
                               .equals("ALU"))
                            this.modelo.agregarAlumnoEnCursada((Alumno) aux, curViejo);
                        else if (aux.getLegajo()
                                    .substring(0, 3)
                                    .equals("PRO"))
                            this.modelo.agregarProfesorEnCursada((Profesor) aux, curViejo);
                    }
                    
                }
                break;
        }
    }

    private boolean participantesProvisoriosDisponibles(Cursada provisorio)
    {
        boolean res = true;
        Iterator<Alumno> alumnos = provisorio.alumnos();
        Iterator<Profesor> profesores = provisorio.profesores();
        while (alumnos.hasNext() && res)
            res = this.modelo.alumnoDisponible(alumnos.next(), provisorio);
        while (profesores.hasNext() && res)
            res = this.modelo.profesorDisponible(profesores.next(), provisorio);
        return res;
    }

    public void guardar()
    {
        SerializadorXML.guardar(this.modelo);
    }
}
