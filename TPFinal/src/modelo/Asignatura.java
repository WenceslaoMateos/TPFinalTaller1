package modelo;

public class Asignatura
    implements I_Indexable
{
    private String identificacion;
    private String nombre;

    @Override
    public Object getClavePrimaria()
    {
        return this.identificacion;
    }

    @Override
    public Object getClaveSecundaria()
    {
        return this.nombre;
    }
}
