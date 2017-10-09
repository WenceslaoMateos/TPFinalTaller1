package modelo;

public class Cursada
    implements I_Indexable
{
    private Asignatura asignatura;
    private String identificacion;
    private String periodo;
    private Dia dia;
    private String hora;

    @Override
    public Object getClavePrimaria()
    {
        return this.identificacion;
    }

    @Override
    public Object getClaveSecundaria()
    {
        // TODO Implement this method
        return null;
    }
}
