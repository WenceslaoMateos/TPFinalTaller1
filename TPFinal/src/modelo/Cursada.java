package modelo;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import java.util.Iterator;

/**
 * Clase que representa a las cursadas del sistema. Implementa I_Indexable siendo la identificación de la cursada la
 * clave primaria y la clave secundaria una combinación del periodo, día y hora de inicio.
 */
public class Cursada
    implements I_Indexable
{
    private Asignatura asignatura;
    private String identificacion;
    private String periodo;
    private Dia dia;
    private String horaInicio;
    private String horaFinalizacion;
    private IndicePrimario<Profesor> profesores;
    private IndicePrimario<Alumno> alumnos;
    private static int CANT_CURSADAS = 0;

    /**
     * Constructor para crear una instancia preliminar de cursada. No valida sus parámetros ni asigna un legajo.<br>
     * <b>Post:</b> Se genera una nueva instancia de persona cuyos datos podrán ser validados y, tras esto, se le podrá
     * asignar una identificación.
     * @param asignatura Asignatura a la que se aplica la cursada.
     * @param periodo Año y cuatrimestre de la cursada.
     * @param dia Día de la semana en que se desenvolverá la cursada.
     * @param horaInicio Hora de comienzo de la clase.
     * @param horaFinalizacion Hora de finalización de la clase.
     */
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

    /**
     * Clave primaria de una cursada.
     * @return Ver getIdentificacion().
     */
    @Override
    public Object getClavePrimaria()
    {
        return this.getIdentificacion();
    }

    /**
     * Clave secundaria de una cursada.
     * @return
     */
    @Override
    public Object getClaveSecundaria()
    {
        return Dia.parseInt(this.dia) * 10000 + this.parseInt(this.horaInicio);
    }

    private int parseInt(String hora)
    {
        //Parsea el string de la hora de acuerdo a la mascara para que trabaje con un numero entero directo
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

    /**
     * Verifica que el alumno tenga las precorrelativas de la asignatura de la cursada aprobadas.
     * @param alumno Alumno a verificar su historia académica.
     * @return <b>true</b> si el alumno cumple con las correlatividades, <b>false</b> en caso contrario.
     */
    public boolean correlativasAprobadas(Alumno alumno)
    {
        return this.asignatura.compruebaCorrelativas(alumno);
    }

    /**
     * Verifica que el profesor esté habilitado para dar la asignatura de la cursada.
     * @param profesor Profesor a verificar sus competencias.
     * @return <b>true</b> si el profesor está habilitado a dar la asignatura, <b>false</b> en caso contrario.
     */
    public boolean aceptaCompetencia(Profesor profesor)
    {
        return profesor.habilitadoParaAsignatura(this.asignatura);
    }

    /**
     * Verifica que el periodo sea correcto, según las especificaciones.<br>
     * Las mismas imponen que el periodo debe tener la forma CC-AAAA, siendo CC 01 o 02 según que cuatrimeste sea
     * y AAAA el año.
     * @param periodo String a verificar.
     * @return <b>true</b> si el parámetro cumple con las especificaciones, <b>false</b> en caso contrario.
     */
    public static boolean validaPeriodo(String periodo)
    {
        return ((periodo.length() == 7) &&
                (periodo.substring(0, 3).equals("01-") || periodo.substring(0, 3).equals("02-")) &&
                Integer.parseInt(periodo.substring(3, periodo.length())) > 2000 &&
                Integer.parseInt(periodo.substring(3, periodo.length())) < 2500);
    }
    
    /**
     * Verifica que la hora sea correcta, según las especificaciones.<br>
     * Las mismas imponen que debe tomar el formato xx:xx, siendo x un número del 0 al 9. A su vez, debe ser una hora
     * posible.
     * @param hora String a verificar.
     * @return <b>true</b> si el parámetro cumple con las especificaciones, <b>false</b> en caso contrario.
     */
    public static boolean validaHora(String hora)
    {
        return ((hora.length() == 5) && (Integer.parseInt(hora.substring(0, 2)) <= 12) &&
                (Integer.parseInt(hora.substring(0, 2)) >= 0) && (Integer.parseInt(hora.substring(3, 4)) <= 59) &&
                (Integer.parseInt(hora.substring(3, 4)) >= 0) && hora.charAt(2) == ':');
    }

    /**
     * Verifica que el horario proporcionado sea posible.
     * @param horaInicio Hora de comienzo de la cursada.
     * @param horaFinalizacion Hora de finalización de la cursada.
     * @return <b>true</b> si la hora de inicio es previa a la de finalización, <b>false</b> en caso contrario.
     */
    public static boolean validaHorario(String horaInicio, String horaFinalizacion)
    {
        return horaInicio.compareTo(horaFinalizacion) < 0;
    }

    /**
     * Verifica que los atributos de una cursada sean correctos.
     * @param cursada Cursada a verificar.
     * @return <b>true</b> si todos los atributos son correctos, <b>false</b> en caso contrario.
     */
    public static boolean validaCursada(Cursada cursada)
    {
        return Cursada.validaPeriodo(cursada.getPeriodo()) && Cursada.validaHora(cursada.getHoraInicio()) &&
               Cursada.validaHora(cursada.getHoraFinalizacion()) &&
               Cursada.validaHorario(cursada.getHoraInicio(), cursada.getHoraFinalizacion());
    }
    
    /**
     * Genera una nueva identificación cumpliendo con las especificaciones para una cursada.<br>
     * <b>Post:</b> Se otorga una identificación válida y aumenta en uno la cantidad de identificaciones.
     * @return String que representa la nueva identificación.
     */
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
    
    /**
     * Da de baja en la cursada al profesor parámetro.<br>
     * <b>Pre:</b> El profesor fue ubicado previamente entre los participantes de la cursada.<br>
     * <b>Post:</b> La cursada cuenta con un profesor menos.
     * @param elim Profesor a dar de baja.
     */
    public void bajaProfesor(Profesor elim)
    {
        this.profesores.eliminar(elim);
    }
    
    /**
     * Verifica si la cursada parámetro se superpone con la invocante.<br>
     * Esto se produce si ambas cursadas ocurren en el mismo periodo, durante el mismo día y hay una superposición
     * en sus franjas horarias.
     * @param otro Cursada a comparar con la invocante.
     * @return <b>true</b> si existe superposición, <b>false</b> en caso contrario.
     */
    public boolean hayColision(Cursada otro)
    {
        return this.getPeriodo().equals(otro.getPeriodo()) && this.getDia() == otro.getDia() &&
               !(this.getHoraInicio().compareTo(otro.getHoraFinalizacion()) > 0 ||
                 this.getHoraFinalizacion().compareTo(otro.getHoraInicio()) < 0);
    }
    
    /**
     * Verifica si el alumno parámetro se encuentra entre los participantes de la cursada.
     * @param alumno Alumno a buscar.
     * @return <b>true</b> si se encontró al alumno, <b>false</b> en caso contrario.
     */
    public boolean tieneAlumno(Alumno alumno)
    {
        return this.alumnos.contieneValor(alumno);
    }

    /**
     * Verifica si el profesor parámetro se encuentra entre los participantes de la cursada.
     * @param profesor Profesor a buscar.
     * @return <b>true</b> si se encontró al profesor, <b>false</b> en caso contrario.
     */
    public boolean tieneProfesor(Profesor profesor)
    {
        return this.profesores.contieneValor(profesor);
    }

    /**
     * Otorga a los alumnos anotados en la cursada.
     * @return Iterator con los alumnos de la cursada.
     */
    public Iterator<Alumno> alumnos()
    {
        return this.alumnos.elementos();
    }

    /**
     * Otorga a los profesores de la cursada.
     * @return Iterator con los profesores dictando clases en la cursada.
     */
    public Iterator<Profesor> profesores()
    {
        return this.profesores.elementos();
    }
    
    /**
     * Le asigna la asignatura, periodo, día, hora de inicio y hora de finalización de modif al objeto invocante. No
     * permite modificar la identificación.<br>
     * <b>Pre:</b> Los atributos de modif son correctos.
     * <b>Post:</b> Los cambios fueron aplicados sobre el objeto invocante.
     * @param modif parámetro que contiene las modificaciones a aplicar sobre el objeto invocante.
     * @throws DatoInvalidoException modif no es una cursada.
     */
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
            this.setAsignatura(aux.getAsignatura());
            this.setPeriodo(aux.getPeriodo());
            this.setDia(aux.getDia());
            this.setHoraInicio(aux.getHoraInicio());
            this.setHoraFinalizacion(aux.getHoraFinalizacion());
        }
    }

    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public Cursada()
    {
        super();
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
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}
