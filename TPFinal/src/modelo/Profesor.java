package modelo;

import excepciones.ClaveYaExistenteException;
import excepciones.ElementoNoExisteException;


public class Profesor
    extends Persona
{
    private String telefono;
    private IndicePrimario<Asignatura> competencia;

    public Profesor()
    {
        super();
    }

    public Profesor(String legajo, String apellidoNombre, String domicilio, String mail, String telefono)
    {
        super(legajo, apellidoNombre, domicilio, mail);
        this.telefono = telefono;
        this.competencia = new IndicePrimario<Asignatura>();
    }

    public void agregarCompetencia(Asignatura nuevo)
        throws ClaveYaExistenteException
    {
        this.competencia.agregar(nuevo);
    }

    public void eliminarCompetencia(Asignatura elim)
        throws ElementoNoExisteException
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
     * @return true si la asignatura está entre las competencias del profesor, false en caso contrario.
     */
    public boolean habilitadoParaAsignatura(Asignatura asignatura)
    {
        return this.competencia.contieneValor(asignatura);
    }
}
