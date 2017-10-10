package modelo;

import java.util.HashMap;

public class IndicePrimario<K, V extends I_Indexable>
{
  private HashMap<K, V> elementos;

  public IndicePrimario()
  {
    this.elementos = new HashMap<K, V>();
  }

  public void agregar(V nuevo)
  {
    if (this.elementos.containsKey(nuevo.getClavePrimaria()))
      ;
    // TODO
    else
      this.elementos.put((K) nuevo.getClavePrimaria(), nuevo);
  }

  public void eliminar(V elim)
  {
    if (!this.elementos.containsValue(elim.getClavePrimaria()))
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
}
