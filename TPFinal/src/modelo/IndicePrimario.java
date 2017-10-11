package modelo;

import java.util.HashMap;
import java.util.Iterator;

public class IndicePrimario<K, V extends I_Indexable>
{
  private HashMap<K, V> elementos;

  public IndicePrimario()
  {
    this.elementos = new HashMap<K, V>();
  }

  public void agregar(V nuevo)
  {
    if (this.contieneClave((K) nuevo.getClavePrimaria()))
      ;
    // TODO
    else
      this.elementos.put((K) nuevo.getClavePrimaria(), nuevo);
  }

  public void eliminar(V elim)
  {
    if (!this.contieneValor(elim))
      ;
    // TODO
    else
      this.elementos.remove(elim);
  }

  public V buscarPorClavePrimaria(K clave)
  {
    if (!this.elementos.containsKey(clave))
      ;
    // TODO
    return this.elementos.get(clave);
  }

  public boolean contieneClave(K clave)
  {
    return this.elementos.containsKey(clave);
  }

  public boolean contieneValor(V valor)
  {
    return this.elementos.containsValue(valor);
  }

  public Iterator<V> iterator()
  {
    return this.elementos
               .values()
               .iterator();
  }
}
