package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class IndiceDoble<K1, K2, V extends I_Indexable>
{
  private IndicePrimario<K1, V> indice1;
  private TreeMap<K2, ArrayList<V>> indice2;

  public IndiceDoble()
  {
    this.indice1 = new IndicePrimario<K1, V>();
    this.indice2 = new TreeMap<K2, ArrayList<V>>();
  }

  public void agregar(V nuevo)
  {
    this.indice1.agregar(nuevo);
    if (!this.contieneClaveSecundaria((K2) nuevo.getClaveSecundaria()))
      this.indice2.put((K2) nuevo.getClaveSecundaria(), new ArrayList<V>());
    this.indice2
        .get(nuevo.getClaveSecundaria())
        .add(nuevo);
  }

  public void eliminar(V elim)
  {
    ArrayList<V> aux;
    this.indice1.eliminar(elim);
    aux = this.indice2.get(elim.getClaveSecundaria());
    aux.remove(elim);
    if (aux.isEmpty())
      this.indice2.remove(elim.getClaveSecundaria());
  }

  public V buscarPorClavePrimaria(K1 clave)
  {
    return this.indice1.buscarPorClavePrimaria(clave);
  }

  public Iterator<V> buscarPorClaveSecundaria(K2 clave)
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

  public boolean contieneClavePrimaria(K1 clave)
  {
    return this.indice1.contieneClave(clave);
  }

  public boolean contieneClaveSecundaria(K2 clave)
  {
    return this.indice2.containsKey(clave);
  }

  public boolean contieneValor(V valor)
  {
    return this.indice1.contieneValor(valor);
  }
}
