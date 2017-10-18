package modelo;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;
import excepciones.NoEncontradoException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Clase que mantiene dos índices de elementos indexables: uno por clave primaria (no acepta claves repetidas)
 * y otro por clave secundaria (permite que las claves se repitan agrupando los valores para dicha clave).
 * @param <V> debe implementar I_Indexable
 */
public class IndiceDoble<V extends I_Indexable>
{
    private IndicePrimario<V> indice1;
    private TreeMap<Object, ArrayList<V>> indice2;

    public IndiceDoble()
    {
        this.indice1 = new IndicePrimario<V>();
        this.indice2 = new TreeMap<Object, ArrayList<V>>();
    }

    /**
     * Añade un elemento al índice si su clave primaria no se encuentra ya.<br>
     * <b>Pre:</b> los atributos de nuevo distintos a la clave primaria son correctos.<br>
     * <b>Post:</b> el índice tiene un elemento más.
     * @param nuevo elemento tipo V a agregar.
     * @throws ClaveYaExistenteException un elemento de la colección ya contaba con esa clave primaria.
     */
    public void agregar(V nuevo)
        throws ClaveYaExistenteException
    {
        this.indice1.agregar(nuevo); // Agrega al índice primario.
        if (!this.contieneClaveSecundaria(nuevo.getClaveSecundaria()))
            // Si la clave no existe aún, se crea una entrada para la misma.
            this.indice2.put(nuevo.getClaveSecundaria(), new ArrayList<V>());
        this.indice2
            .get(nuevo.getClaveSecundaria())
            .add(nuevo); // Agrega al índice secundario.
    }

    /**
     * Elimina del índice la referencia al objeto ingresado como parámetro.<br>
     * <b>Pre:</b> el elemento a eliminar se encuentra en el índice.<br>
     * <b>Post:</b> el índice tiene un elemento menos.
     * @param elim objeto de tipo V a eliminar del índice.
     */
    public void eliminar(V elim)
    {
        ArrayList<V> aux;
        this.indice1.eliminar(elim); // Elimina del índice primario.
        aux = this.indice2.get(elim.getClaveSecundaria());
        aux.remove(elim); // Elimina de la cubeta en el índice secundario.
        if (aux.isEmpty())
            // Elimina la entrada asociada a la clave si la cubeta se encuentra vacía.
            this.indice2.remove(elim.getClaveSecundaria());
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
        return this.indice1.buscarPorClavePrimaria(clave);
    }

    /**
     * Busca en el índice secundario el conjunto de valores asociados a la clave de entrada.<br>
     * <b>Pre:</b> el parámetro clave es del tipo del objeto otorgado por getClaveSecundaria() de la clase V.
     * @param clave clave a buscar en el índice secundario.
     * @return iterador con los elementos asociados a dicha clave secundaria.
     * @throws NoEncontradoException no existe la clave ingresada dentro del índice.
     */
    public Iterator<V> buscarPorClaveSecundaria(Object clave)
        throws NoEncontradoException
    {
        if (!this.indice2.containsKey(clave))
            throw new NoEncontradoException(clave, "Clave no encontrada en el índice.");
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

    /**
     * Comprueba si la clave dada tiene una entrada en el índice primario.<br>
     * <b>Pre:</b> el parámetro clave es del tipo del objeto otorgado por getClavePrimaria() de la clase V.
     * @param clave clave primaria a buscar en el índice.
     * @return <b>true</b> si la clave se encuentra, <b>false</b> en caso contrario.
     */
    public boolean contieneClavePrimaria(Object clave)
    {
        return this.indice1.contieneClave(clave);
    }

    /**
     * Comprueba si la clave dada tiene una entrada en el índice secundario.<br>
     * <b>Pre:</b> el parámetro clave es del tipo del objeto otorgado por getClaveSecundaria() de la clase V.
     * @param clave clave secundaria a buscar en el índice.
     * @return <b>true</b> si la clave se encuentra, <b>false</b> en caso contrario.
     */
    public boolean contieneClaveSecundaria(Object clave)
    {
        return this.indice2.containsKey(clave);
    }

    /**
     * Comprueba si la referencia al objeto de tipo V pasado como parámetro se encuentra en el índice.
     * @param valor elemento a buscar.
     * @return <b>true</b> si se encontró el elemento, <b>false</b> en caso contrario.
     */
    public boolean contieneValor(V valor)
    {
        return this.indice1.contieneValor(valor);
    }

    /**
     * Devuelve un iterador con los elementos del índice ordenados de forma ascendente, según su clave primaria.
     * @return iterador de elementos V.
     */
    public Iterator<V> elementosPorClavePrimaria()
    {
        return this.indice1.elementos();
    }

    public void modificarValor(V elem, V modif)
        throws DatoInvalidoException
    {
        ArrayList<V> cubeta;
        Object claveAux = elem.getClaveSecundaria();
        this.indice1.modificarValor(elem, modif);
        if (!elem.getClaveSecundaria().equals(claveAux))
        {
            cubeta = this.indice2.get(claveAux);
            cubeta.remove(elem);
            if (cubeta.isEmpty())
                this.indice2.remove(claveAux);
            if (!this.contieneClaveSecundaria(elem.getClaveSecundaria()))
                this.indice2.put(elem.getClaveSecundaria(), new ArrayList<V>());
            this.indice2.get(elem.getClaveSecundaria()).add(elem);
        }
    }

    public Iterator clavesPrimarias()
    {
        return this.indice1.clavesPrimarias();
    }
    
    public Iterator clavesSecundarias()
    {
        return this.indice2.keySet().iterator();
    }

    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public void setIndice1(IndicePrimario<V> indice1)
    {
        this.indice1 = indice1;
    }

    public IndicePrimario<V> getIndice1()
    {
        return indice1;
    }

    public void setIndice2(TreeMap<Object, ArrayList<V>> indice2)
    {
        this.indice2 = indice2;
    }

    public TreeMap<Object, ArrayList<V>> getIndice2()
    {
        return indice2;
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}
