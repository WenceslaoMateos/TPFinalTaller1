package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Prueba
{
    public Prueba()
    {
        super();
    }

    public static void main(String[] args)
    {
        int i;
        Iterator<ArrayList<Asignatura>> it;
        ArrayList<Asignatura> aux;
        IndiceDoble<String, String, Asignatura> asignaturas = new IndiceDoble<String, String, Asignatura>();
        asignaturas.agregar(new Asignatura("234", "Taller de Programacion"));
        asignaturas.agregar(new Asignatura("121", "Analis de Sistemas"));
        asignaturas.agregar(new Asignatura("530", "Teoria de la informacion"));
        it = (Iterator<ArrayList<Asignatura>>) asignaturas.elementosPorClaveSecundaria();
        while (it.hasNext())
        {
            aux = it.next();
            for (i = 0; i < aux.size(); i++)
                System.out.println(aux.get(i).getClaveSecundaria());
        }
    }
}
