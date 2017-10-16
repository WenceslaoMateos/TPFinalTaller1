package modelo;

import excepciones.ClaveYaExistenteException;

import java.util.Iterator;

/**
 * Clase que representa a los profesores del sistema.
 */
public class Profesor
    extends Persona
{
    private String telefono;
    private IndicePrimario<Asignatura> competencia;
    private static int CANT_PROFESORES = 0;

    public Profesor(String apellidoNombre, String domicilio, String mail, String telefono)
    {
        super(apellidoNombre, domicilio, mail);
        this.telefono = telefono;
        this.competencia = new IndicePrimario<Asignatura>();
    }

    /**
     * Agrega una asignatura a las competencias del profesor.<br>
     * <b>Pre:</b> La materia se encuentra en el sistema.<br>
     * <b>Post:</b> El profesor está habilitado para dar una asignatura más.
     * @param nuevo Asignatura a agregar.
     * @throws ClaveYaExistenteException El profesor ya estaba habilitado para dar la materia.
     */
    public void agregarCompetencia(Asignatura nuevo)
        throws ClaveYaExistenteException
    {
        this.competencia.agregar(nuevo);
    }

    /**
     * Elimina una asignatura de las competencias del profesor.<br>
     * <b>Pre:</b> La materia se encuentra entre las competencias del profesor.<br>
     * <b>Post:</b> El profesor está habilitado para dar una asignatura menos.
     * @param elim Asignatura a eliminar.
     */
    public void eliminarCompetencia(Asignatura elim)
    {
        this.competencia.eliminar(elim);
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public String getTelefono()
    {
        return telefono;
    }

    /**
     * Especifica si el profesor está habilitado para dar la asginatura recibida como parámetro.
     * @param asignatura materia a comprobar.
     * @return <b>true</b> si la asignatura está entre las competencias del profesor, <b>false</b> en caso contrario.
     */
    public boolean habilitadoParaAsignatura(Asignatura asignatura)
    {
        return this.competencia.contieneValor(asignatura);
    }

    public static String getNuevaIdentificacion()
    {
        Profesor.CANT_PROFESORES++;
        String ret = "PRO";
        String aux = "" + Profesor.CANT_PROFESORES;
        int i, j = aux.length();
        for (i = 1; i <= 4 - j; i++)
            ret = ret + "0";
        return ret + aux;
    }

    /**
     * Devuelve las competencias del profesor.
     * @return Iterator con las asignaturas para las cuales el profesor está habilitado para dar clases.
     */
    public Iterator<Asignatura> competencias()
    {
        return this.competencia.elementos();
    }

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public Profesor()
    {
        super();
    }

    public static int getCANT_PROFESORES()
    {
        return CANT_PROFESORES;
    }

    public static void setCANT_PROFESORES(int CANT_PROFESORES)
    {
        Profesor.CANT_PROFESORES = CANT_PROFESORES;
    }

    public void setCompetencia(IndicePrimario<Asignatura> competencia)
    {
        this.competencia = competencia;
    }

    public IndicePrimario<Asignatura> getCompetencia()
    {
        return competencia;
    }
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}
