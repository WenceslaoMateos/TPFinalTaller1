package modelo;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import java.util.Iterator;


public class Cursada
    implements I_Indexable
{
    private Asignatura asignatura;
    private String identificacion;
    private String periodo;
    private Dia dia;
    private String horaInicio;
    private String horaFinalizacion;
    private IndicePrimario<Profesor> profesores; //POR QUÉ ESTO WEN??????????????????????
    private IndicePrimario<Alumno> alumnos; //POR QUÉ ESTO WEN??????????????????????
    private static int CANT_CURSADAS = 0;

    public Cursada()
    {
        super();
    }

    public Cursada(Asignatura asignatura, String periodo, Dia dia, String horaInicio, String horaFinalizacion)
    {
        this.asignatura = asignatura;
        this.periodo = periodo;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFinalizacion = horaFinalizacion;
        this.profesores = new IndicePrimario<Profesor>();
        this.alumnos = new IndicePrimario<Alumno>();
    }

    public boolean correlativasAprobadas(Alumno alumno)
    {
        return this.asignatura.compruebaCorrelativas(alumno);
    }

    public boolean aceptaCompetencia(Profesor profesor)
    {
        return profesor.habilitadoParaAsignatura(this.asignatura);
    }

    @Override
    public Object getClavePrimaria()
    {
        return this.getIdentificacion();
    }

    @Override
    public Object getClaveSecundaria()
    {
        return Dia.parseInt(this.dia) * 10000 + this.parseInt(this.horaInicio);
    }

    private int parseInt(String hora)
    { //Parsea el string de la hora de acuerdo a la mascara para que trabaje con un numero entero directo
        return Integer.parseInt(hora.substring(0, 2)) * 100 + Integer.parseInt(hora.substring(3, 5));
    }

    public String getIdentificacion()
    {
        return identificacion;
    }

    public void setAsignatura(Asignatura asignatura)
    {
        this.asignatura = asignatura;
    }

    public Asignatura getAsignatura()
    {
        return asignatura;
    }

    public void setIdentificacion(String identificacion)
    {
        this.identificacion = identificacion;
    }

    public void setPeriodo(String periodo)
    {
        this.periodo = periodo;
    }

    public String getPeriodo()
    {
        return periodo;
    }

    public void setDia(Dia dia)
    {
        this.dia = dia;
    }

    public Dia getDia()
    {
        return dia;
    }

    public void setHoraInicio(String horaInicio)
    {
        this.horaInicio = horaInicio;
    }

    public String getHoraInicio()
    {
        return horaInicio;
    }

    public void setHoraFinalizacion(String horaFinalizacion)
    {
        this.horaFinalizacion = horaFinalizacion;
    }

    public String getHoraFinalizacion()
    {
        return horaFinalizacion;
    }

    public void setProfesores(IndicePrimario<Profesor> profesores)
    {
        this.profesores = profesores;
    }

    public IndicePrimario<Profesor> getProfesores()
    {
        return profesores;
    }

    public void setAlumnos(IndicePrimario<Alumno> alumnos)
    {
        this.alumnos = alumnos;
    }

    public IndicePrimario<Alumno> getAlumnos()
    {
        return alumnos;
    }

    public static void setCANT_CURSADAS(int CANT_CURSADAS)
    {
        Cursada.CANT_CURSADAS = CANT_CURSADAS;
    }

    public static int getCANT_CURSADAS()
    {
        return CANT_CURSADAS;
    }

    public static boolean validaPeriodo(String periodo)
    {
        boolean ret;
    if ((periodo.length() == 7) && (periodo.substring(0, 3).equals("01-") || periodo.substring(0, 3).equals("02-")) &&
            Integer.parseInt(periodo.substring(3, periodo.length())) > 2000 &&
            Integer.parseInt(periodo.substring(3, periodo.length())) < 2500)
            ret = true;
        else
            ret = false;
        return ret;
    }

    public static boolean validaHora(String hora)
    {
        boolean ret;
        if ((hora.length() == 5) && (Integer.parseInt(hora.substring(0, 2)) <= 99) &&
            (Integer.parseInt(hora.substring(0, 2)) >= 0) && (Integer.parseInt(hora.substring(3, 4)) <= 99) &&
            (Integer.parseInt(hora.substring(3, 4)) >= 0) && hora.charAt(2) == ':')
            ret = true;
        else
            ret = false;
        return ret;
    }

    public static String getNuevaIdentificacion()
    {
        Cursada.CANT_CURSADAS++;
        String ret = "CUR";
        String aux = "" + Cursada.CANT_CURSADAS;
        int i, j = aux.length();
        for (i = 1; i <= 4 - j; i++)
            ret = ret + "0";
        return ret + aux;
    }
    
    /**
     * Agrega un nuevo alumno a la cursada.<br>
     * <b>Pre:</b> nuevo es un Aumno ya existente en el sistema y tiene disponibilidad horaria.<br>
     * <b>Post:</b> La cursada cuenta con un alumno más.
     * @param nuevo Alumno a agregar.
     * @throws DatoInvalidoException El alumno no cumple con las correlatividades o ya hizo la materia.
     * @throws ClaveYaExistenteException El alumno ya forma parte de la cursada.
     */
    public void altaAlumno(Alumno nuevo)
        throws DatoInvalidoException, ClaveYaExistenteException
    {
        if (!this.correlativasAprobadas(nuevo))
            throw new DatoInvalidoException(nuevo, "El alumno no tiene todas las precorrelatividades aprobadas.");
        else if (nuevo.asignaturaAprobada(this.asignatura))
            throw new DatoInvalidoException(nuevo, "El alumno ya tiene aprobada la asignatura.");
        else
            this.alumnos.agregar(nuevo);
}
    
    /**
     * Da de baja en la cursada al alumno parámetro.<br>
     * <b>Pre:</b> El alumno fue ubicado previamente entre los participantes de la cursada.<br>
     * <b>Post:</b> La cursada cuenta con un alumno menos.
     * @param elim Alumno a dar de baja.
     */
    public void bajaAlumno(Alumno elim)
    {
        this.alumnos.eliminar(elim);
    }
    
    /**
     * Agrega un nuevo profesor a la cursada.<br>
     * <b>Pre:</b> nuevo es un Profesor ya existente en el sistema y tiene disponibilidad horaria.<br>
     * <b>Post:</b> La cursada cuente con un profesor más.
     * @param nuevo Profesor a agregar.
     * @throws DatoInvalidoException El profesor no tiene a la asignatura entre sus competencias.
     * @throws ClaveYaExistenteException El profesor ya forma parte de la cursada.
     */
    public void altaProfesor(Profesor nuevo)
        throws DatoInvalidoException, ClaveYaExistenteException
    {
        if (!nuevo.habilitadoParaAsignatura(this.asignatura))
            throw new DatoInvalidoException(nuevo, "El profesor no está habilitado para la asignatura.");
        else
            this.profesores.agregar(nuevo);
    }
    
    public void bajaProfesor(Profesor elim)
    {
        // Es precondición que el profesor exista.
        this.profesores.eliminar(elim);
    }
    
    public boolean hayColision(Cursada otro)
    {
    return this.getPeriodo().equals(otro.getPeriodo()) && this.getDia() == otro.getDia() &&
           !(this.getHoraInicio().compareTo(otro.getHoraFinalizacion()) > 0 ||
             this.getHoraFinalizacion().compareTo(otro.getHoraInicio()) < 0);
        // Las cursadas deben estar en el mismo periodo, en el mismo día y deben superponerse los horarios.
    }
    
    public boolean tieneAlumno(Alumno alumno)
    {
        return this.alumnos.contieneValor(alumno);
    }
    
    public boolean tieneProfesor(Profesor profesor)
    {
        return this.profesores.contieneValor(profesor);
    }
    
    public Iterator<Alumno> alumnos()
    {
        return this.alumnos.elementos();
    }
    
    public Iterator<Profesor> profesores()
    {
        return this.profesores.elementos();
    }

    @Override
    public void modificarDatos(I_Indexable modif)
        throws DatoInvalidoException
    {
        Cursada aux;
        if (this.getClass() != modif.getClass())
            throw new DatoInvalidoException(modif, "Tipo de dato inválido.");
        else
        {
            aux = (Cursada) modif;
            if (!this.getPeriodo().equals(aux.getPeriodo()))
                if (!Cursada.validaPeriodo(aux.getPeriodo()))
                    throw new DatoInvalidoException(aux.getPeriodo(), "Periodo inválido.");
                else
                    this.setPeriodo(aux.getPeriodo());
            if (this.getDia() != aux.getDia())
                this.setDia(aux.getDia());
            if (!this.getHoraInicio().equals(aux.getHoraInicio())
                || !this.getHoraFinalizacion().equals(aux.getHoraFinalizacion()))
                if (!Cursada.validaHora(aux.getHoraInicio()) || !Cursada.validaHora(aux.getHoraFinalizacion())
                    || aux.getHoraInicio().compareTo(aux.getHoraFinalizacion()) > 0)
                    throw new DatoInvalidoException(aux.getHoraInicio(), "Horario inválido.");
                else
                {
                    this.setHoraInicio(aux.getHoraInicio());
                    this.setHoraFinalizacion(aux.getHoraFinalizacion());
}
        }
    }
}
