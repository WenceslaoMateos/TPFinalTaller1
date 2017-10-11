package modelo;


public class Alumno
    extends Persona
{
    private IndicePrimario<String, Asignatura> historia;

    public Alumno()
    {
        super();
    }

    public Alumno(String legajo, String apellidoNombre, String domicilio, String mail)
    {
        super(legajo, apellidoNombre, domicilio, mail);
        this.historia = new IndicePrimario<String, Asignatura>();
    }

    public void agregarHistoria(Asignatura nuevo)
    {
        this.historia.agregar(nuevo);
    }

    public void eliminarHistoria(Asignatura elim)
    {
        this.historia.eliminar(elim);
    }
    
    public boolean asignaturaAprobada(Asignatura asignatura)
    {
        return this.historia.contieneValor(asignatura);
    }
}
