package modelo;

import java.util.Iterator;


public class Asignatura
    implements I_Indexable
{
    private String identificacion;
    private String nombre;
    private IndicePrimario<String, Asignatura> correlatividades;

    public Asignatura(String identificacion, String nombre)
    {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correlatividades = new IndicePrimario<String, Asignatura>();
    }

    public void agregarCorrelativa(Asignatura correlativa)
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

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
}
