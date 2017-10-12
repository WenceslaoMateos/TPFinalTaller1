package modelo;

import excepciones.ClaveYaExistenteException;
import excepciones.ElementoNoExisteException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class IndiceDoble<V extends I_Indexable>
{
    private IndicePrimario<V> indice1;
    private TreeMap<Object, ArrayList<V>> indice2;

    public IndiceDoble()
    {
        this.indice1 = new IndicePrimario<V>();
        this.indice2 = new TreeMap<Object, ArrayList<V>>();
    }

    public void agregar(V nuevo)
        throws ClaveYaExistenteException
    {
        this.indice1.agregar(nuevo);
        if (!this.contieneClaveSecundaria(nuevo.getClaveSecundaria()))
            this.indice2.put(nuevo.getClaveSecundaria(), new ArrayList<V>());
        this.indice2
            .get(nuevo.getClaveSecundaria())
            .add(nuevo);
    }

    public void eliminar(V elim)
        throws ElementoNoExisteException
    {
        ArrayList<V> aux;
        this.indice1.eliminar(elim);
        aux = this.indice2.get(elim.getClaveSecundaria());
        aux.remove(elim);
        if (aux.isEmpty())
            this.indice2.remove(elim.getClaveSecundaria());
    }

    public V buscarPorClavePrimaria(Object clave)
    {
        return this.indice1.buscarPorClavePrimaria(clave);
    }

    public Iterator<V> buscarPorClaveSecundaria(Object clave)
    {
        if (!this.indice2.containsKey(clave))
            ;
        // TODO
        return this.indice2
                   .get(clave)
                   .iterator();
    }

    public Iterator<ArrayList<V>> elementosPorClaveSecundaria()
    {
        return this.indice2
                   .values()
                   .iterator();
    }

    public boolean contieneClavePrimaria(Object clave)
    {
        return this.indice1.contieneClave(clave);
    }

    public boolean contieneClaveSecundaria(Object clave)
    {
        return this.indice2.containsKey(clave);
    }

    public boolean contieneValor(V valor)
    {
        return this.indice1.contieneValor(valor);
    }

    public Iterator<V> elementosPorClavePrimaria()
    {
        return this.indice1.elementos();
    }
}
