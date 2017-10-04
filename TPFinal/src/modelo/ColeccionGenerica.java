package modelo;

import java.util.TreeMap;

public class ColeccionGenerica<K, V>
{
    private TreeMap<K, V> elementos;
    
    public ColeccionGenerica()
    {
        this.elementos = new TreeMap<K, V>();
    }
}
