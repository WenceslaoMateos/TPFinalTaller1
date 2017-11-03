package lanzador;

import modelo.Sistema;

import persistencia.SerializadorXML;

import vista.Receptor;
import vista.Ventana;

public class Lanzador
{
  public static void main(String[] args)
  {
    Sistema modelo = SerializadorXML.cargar();
    Receptor receptor = new Receptor(modelo);
    Ventana vista = new Ventana(receptor);
  }
}
