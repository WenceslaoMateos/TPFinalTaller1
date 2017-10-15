package modelo;

import excepciones.ClaveYaExistenteException;

import java.util.Iterator;


public class Asignatura
    implements I_Indexable
{
    private String identificacion;
    private String nombre;
    private IndicePrimario<Asignatura> correlatividades;
    private static int CANT_ASIGNATURAS = 0;

    public Asignatura(String identificacion, String nombre)
    {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correlatividades = new IndicePrimario<Asignatura>();
        this.identificacion = Asignatura.getNuevaIdentificacion();
    }

    public void agregarCorrelativa(Asignatura correlativa)
        throws ClaveYaExistenteException
    {
        this.correlatividades.agregar(correlativa);
    }

    public void eliminarCorrelativa(Asignatura elim)
    {
        this.correlatividades.eliminar(elim);
    }

    public boolean compruebaCorrelativas(Alumno alumno)
    {
        Iterator<Asignatura> it = this.correlatividades.elementos();
        boolean ret = true;
        while (it.hasNext() && ret)
            ret = alumno.asignaturaAprobada(it.next());
        return ret;
    }

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

    public String getIdentificacion()
    {
        return identificacion;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setIdentificacion(String identificacion)
    {
        this.identificacion = identificacion;
    }

    public static void setCANT_ASIGNATURAS(int CANT_ASIGNATURAS)
    {
        Asignatura.CANT_ASIGNATURAS = CANT_ASIGNATURAS;
    }

    public static int getCANT_ASIGNATURAS()
    {
        return CANT_ASIGNATURAS;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setCorrelatividades(IndicePrimario<Asignatura> correlatividades)
    {
        this.correlatividades = correlatividades;
    }

    public IndicePrimario<Asignatura> getCorrelatividades()
    {
        return correlatividades;
    }

    public static String getNuevaIdentificacion()
    {
        Asignatura.CANT_ASIGNATURAS++;
        String ret = "ASI";
        String aux = "" + Asignatura.CANT_ASIGNATURAS;
        int i, j = aux.length();
        for (i = 1; i < 4 - j; i++)
            ret = ret + "0";
        return ret + aux;
    }

}
