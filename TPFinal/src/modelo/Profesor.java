package modelo;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;

import java.util.Iterator;

/**
 * Clase que representa a los profesores del sistema.
 */
public class Profesor
    extends Persona
{
    private String telefono;

    /**
     * @aggregation composite
     */
    private IndicePrimario<Asignatura> competencia;
    private static int CANT_PROFESORES = 0;

    /**
     * Constructor para crear una instancia preliminar de Profesor. No valida sus parámetros ni asigna un legajo.<br>
     * <b>Post:</b> Se genera una nueva instancia de persona cuyos datos podrán ser validados y, tras esto, se le podrá
     * asignar un legajo.
     * @param apellidoNombre String con el apellido y nombre del profesor nuevo. Es clave secundaria.
     * @param domicilio dirección del profesor.
     * @param mail dirección electrónica del profesor.
     * @param telefono número de teléfono del profesor.
     */
    public Profesor(String apellidoNombre, String domicilio, String mail, String telefono)
    {
        super(apellidoNombre, domicilio, mail);
        this.telefono = telefono;
        this.competencia = new IndicePrimario<Asignatura>();
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

    /**
     * Especifica si el profesor está habilitado para dar la asginatura recibida como parámetro.
     * @param asignatura Materia a comprobar.
     * @return <b>true</b> si la asignatura está entre las competencias del profesor, <b>false</b> en caso contrario.
     */
    public boolean habilitadoParaAsignatura(Asignatura asignatura)
    {
        return this.competencia.contieneValor(asignatura);
    }

    /**
     * Genera un nuevo legajo cumpliendo con las especificaciones para un profesor.<br>
     * <b>Post:</b> Devuelve un legajo válido nuevo e incrementa la cantidad de legajos.
     * @return String con el nuevo legajo.
     */
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

    @Override
    /**
     * Le asigna el apellido y nombre, domicilio, mail y telefóno provenientes del parámetro al objeto invocante.
     * No permite modificar el legajo. Ver modificarDatos(I_Indexable modif) en Persona.<br>
     * <b>Pre:</b> Los atributos de modif son correctos.
     * <b>Post:</b> Los cambios fueron aplicados sobre el objeto invocante.
     * @param modif parámetro que contiene las modificaciones a aplicar sobre el objeto invocante.
     * @throws DatoInvalidoException modif no es un profesor.
     */
    public void modificarDatos(I_Indexable modif)
        throws DatoInvalidoException
    {
        super.modificarDatos(modif);
        this.setTelefono(((Profesor) modif).getTelefono());
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
