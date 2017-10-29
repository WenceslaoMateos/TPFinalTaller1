package modelo;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;
import excepciones.NoEncontradoException;

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

    /**
     * Añade un elemento al índice si su clave primaria no se encuentra ya.<br>
     * <b>Pre:</b> los atributos de nuevo son correctos.<br>
     * <b>Post:</b> el índice tiene un elemento más.
     * @param nuevo elemento tipo V a agregar.
     * @throws ClaveYaExistenteException un elemento de la colección ya contaba con esa clave.
     */
    public void agregar(V nuevo)
        throws ClaveYaExistenteException
    {
        if (this.contieneClave(nuevo.getClavePrimaria()))
            throw new ClaveYaExistenteException(nuevo.getClavePrimaria());
        else
            this.elementos.put(nuevo.getClavePrimaria(), nuevo);
    }

    /**
     * Elimina del índice la referencia al objeto ingresado como parámetro.<br>
     * <b>Pre:</b> el elemento a eliminar se encuentra en el índice.<br>
     * <b>Post:</b> el índice tiene un elemento menos.
     * @param elim objeto de tipo V a eliminar del índice.
     */
    public void eliminar(V elim)
    {
        this.elementos.remove(elim.getClavePrimaria());
    }

    /**
     * Busca un objeto por su clave primaria y devuelve su referencia.<br>
     * <b>Pre:</b> el parámetro clave es del tipo del objeto otorgado por getClavePrimaria() de la clase V.
     * @param clave clave a buscar en el índice.
     * @return la referencia al objeto de tipo V asociado a la clave dada.
     * @throws NoEncontradoException no existe la clave ingresada dentro del índice.
     */
    public V buscarPorClavePrimaria(Object clave)
        throws NoEncontradoException
    {
        if (!this.contieneClave(clave))
            throw new NoEncontradoException(clave, "No se encontró la clave solicitada en el índice.");
        else
            return this.elementos.get(clave);
    }

    /**
     * Comprueba si la clave dada tiene una entrada en el índice.<br>
     * <b>Pre:</b> el parámetro clave es del tipo del objeto otorgado por getClavePrimaria() de la clase V.
     * @param clave clave a buscar en el índice.
     * @return <b>true</b> si la clave se encuentra, <b>false</b> en caso contrario.
     */
    public boolean contieneClave(Object clave)
    {
        return this.elementos.containsKey(clave);
    }

    /**
     * Comprueba si la referencia al objeto de tipo V pasado como parámetro se encuentra en el índice.
     * @param valor elemento a buscar.
     * @return <b>true</b> si se encontró el elemento, <b>false</b> en caso contrario.
     */
    public boolean contieneValor(V valor)
    {
        return this.elementos.containsValue(valor);
    }

    /**
     * Devuelve un iterador con los elementos del índice ordenados de forma ascendente, según su clave primaria.
     * @return iterador de elementos V.
     */
    public Iterator<V> elementos()
    {
        return this.elementos
                   .values()
                   .iterator();
    }

    /**
     * Permite modificar un valor del índice dada su referencia. Las modificaciones se generan mediante el método
     * modificarDatos(I_Indexable modif) implementado por la clase V. En caso de cambiar la clave primaria,
     * se reindexa el elemento.<br>
     * <b>Pre:</b> elem se encuentra en el índice y los atributos de modif ya fueron verificados.<br>
     * <b>Post:</b> elem fue modificado y mantiene una entrada válida en el índice.
     * @param elem elemento de tipo V a modificar.
     * @param modif objeto de tipo V que contiene las modificaciones.
     */
    public void modificarValor(V elem, V modif)
    {
        Object aux = elem.getClavePrimaria();
        try
        {
            elem.modificarDatos(modif);
            if (!elem.getClavePrimaria().equals(aux))
            {
                // Hay que reindexar
                this.elementos.remove(aux);
                this.elementos.put(elem.getClavePrimaria(), elem);
            }
        }
        catch (DatoInvalidoException e)
        {
            // La restricción en los parámetros no permite que elem y modif sean de distinto tipo.
        }
    }

    /**
     * Obtiene las claves del índice.
     * @return Iterator con las claves asociadas a los valores del índice.
     */
    public Iterator clavesPrimarias()
    {
        return this.elementos
                   .keySet()
                   .iterator();
    }

    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public void setElementos(TreeMap<Object, V> elementos)
    {
        this.elementos = elementos;
    }

    public TreeMap<Object, V> getElementos()
    {
        return elementos;
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}
