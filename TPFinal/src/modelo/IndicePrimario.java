package modelo;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * Clase que mantiene una colección de elementos indexables organizados según su clave primaria.
 * @param <V> tipo de elementos de la colección. Deben implementar I_Indexable.
 */
public class IndicePrimario<V extends I_Indexable>
{
    private TreeMap<Object, V> elementos;

    public IndicePrimario()
    {
        this.elementos = new TreeMap<Object, V>();
    }

    public void agregar(V nuevo)
    {
        if (this.contieneClave(nuevo.getClavePrimaria()))
            ;
        // TODO
        else
            this.elementos.put(nuevo.getClavePrimaria(), nuevo);
    }

    public void eliminar(V elim)
    {
        if (!this.contieneValor(elim))
            ;
        // TODO
        else
            this.elementos.remove(elim);
    }

    public V buscarPorClavePrimaria(Object clave)
    {
        if (!this.contieneClave(clave))
            ;
        // TODO
        return this.elementos.get(clave);
    }

    public boolean contieneClave(Object clave)
    {
        return this.elementos.containsKey(clave);
    }

    /**
     * Comprueba si la referencia al objeto de tipo V pasado como parámetro se encuentra en el índice.
     * @param valor elemento a buscar.
     * @return true si se encontró el elemento, false en caso contrario.
     */
    public boolean contieneValor(V valor)
    {
        return this.elementos.containsValue(valor);
    }

    /**
     * Devuelve un iterador con los elementos del indice ordenados de forma ascendente según su clave primaria.
     * @return iterador de elementos V.
     */
    public Iterator<V> elementos()
    {
        return this.elementos
                   .values()
                   .iterator();
    }
}
