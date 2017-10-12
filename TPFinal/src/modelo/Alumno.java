package modelo;

import excepciones.ClaveYaExistenteException;
import excepciones.ElementoNoExisteException;


public class Alumno
    extends Persona
{
    private IndicePrimario<Asignatura> historia;

    public Alumno()
    {
        super();
    }

    public Alumno(String legajo, String apellidoNombre, String domicilio, String mail)
    {
        super(legajo, apellidoNombre, domicilio, mail);
        this.historia = new IndicePrimario<Asignatura>();
    }

    public void agregarHistoria(Asignatura nuevo)
        throws ClaveYaExistenteException
    {
        this.historia.agregar(nuevo);
    }

    public void eliminarHistoria(Asignatura elim)
        throws ElementoNoExisteException
    {
        this.historia.eliminar(elim);
    }

    public boolean asignaturaAprobada(Asignatura asignatura)
    {
        return this.historia.contieneValor(asignatura);
    }
}
