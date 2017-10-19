package modelo;

import excepciones.ClaveYaExistenteException;

import java.util.Iterator;

/**
 * Clase que representa a los alumnos partícipes del sistema. Implementa I_Indexable siendo su legajo la clave
 * primaria y su apellido y nombre la clave secundaria.
 */
public class Alumno
    extends Persona
{
    private IndicePrimario<Asignatura> historia;
    private static int CANT_ALUMNOS = 0;

    /**
     * Constructor para crear una instancia preliminar de Alumno. No valida sus parámetros ni asigna un legajo.
     * <b>Post:</b> Se genera una nueva instancia de alumno cuyos datos podrán ser validados y, tras esto, se le podrá 
     * asignar un legajo.
     * @param apellidoNombre String con el apellido y nombre del alumno nuevo. Es clave secundaria.
     * @param domicilio dirección del alumno.
     * @param mail dirección electrónica del alumno.
     */
    public Alumno(String apellidoNombre, String domicilio, String mail)
    {
        super(apellidoNombre, domicilio, mail);
        this.historia = new IndicePrimario<Asignatura>();
    }

    /**
     * Agrega una asignatura a la historia académica del alumno.<br>
     * <b>Pre:</b> La asignatura forma parte del sistema.<br>
     * <b>Post:</b> El alumno tiene una asignatura aprobada más.
     * @param nuevo Asignatura a agregar.
     * @throws ClaveYaExistenteException El alumno ya tenía aprobada la asignatura dada.
     */
    public void agregarHistoria(Asignatura nuevo)
        throws ClaveYaExistenteException
    {
        this.historia.agregar(nuevo);
    }

    /**
     * Elimina una asignatura de la historia académica del alumno.
     * <b>Pre:</b> La asignatura ya fue ubicada previamente entre la historia del alumno.
     * <b>Post:</b> El alumno tiene una materia aprobada menos.
     * @param elim Asignatura a eliminar.
     */
    public void eliminarHistoria(Asignatura elim)
    {
        this.historia.eliminar(elim);
    }

    /**
     * Determina si un alumno tiene una asignatura aprobada.
     * @param asignatura Materia a comprobar.
     * @return <b>true</b> si la asignatura se encuentra en la historia académica del alumno,
     * <b>false</b> en caso contrario.
     */
    public boolean asignaturaAprobada(Asignatura asignatura)
    {
        return this.historia.contieneValor(asignatura);
    }

    /**
     * Genera un nuevo legajo cumpliendo con las especificaciones para un Alumno.<br>
     * <b>Post:</b> Se otorga un nuevo legajo válido y aumenta la cantidad de legajos.
     * @return String con el nuevo legajo.
     */
    public static String getNuevoLegajo()
    {
        Alumno.CANT_ALUMNOS++;
        String ret = "ALU";
        String aux = "" + Alumno.CANT_ALUMNOS;
        int i, j = aux.length();
        for (i = 1; i <= 4 - j; i++)
            ret = ret + "0";
        return ret + aux;
    }

    /**
     * Devuelve la historia acádemica del alumno.
     * @return Iterator con las asingaturas aprobadas del alumno.
     */
    public Iterator<Asignatura> historiaAcademica()
    {
        return this.historia.elementos();
    }

    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public Alumno()
    {
        super();
    }

    public void setHistoria(IndicePrimario<Asignatura> historia)
    {
        this.historia = historia;
    }

    public IndicePrimario<Asignatura> getHistoria()
    {
        return historia;
    }

    public static void setCANT_ALUMNOS(int CANT_ALUMNOS)
    {
        Alumno.CANT_ALUMNOS = CANT_ALUMNOS;
    }

    public static int getCANT_ALUMNOS()
    {
        return CANT_ALUMNOS;
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}
