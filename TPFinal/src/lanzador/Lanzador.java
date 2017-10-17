package lanzador;

import modelo.Sistema;

import persistencia.SerializadorXML;

import vista.Receptor;
import vista.Ventana;

public class Lanzador
{
  public Lanzador()
  {
    super();
  }

  public static void main(String[] args)
  {
    Ventana vista = new Ventana();
    Sistema modelo = SerializadorXML.cargar();
    Receptor receptor = new Receptor(modelo, vista);
    vista.setReceptor(receptor);
  }
}
