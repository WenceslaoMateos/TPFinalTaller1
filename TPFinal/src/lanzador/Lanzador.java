package lanzador;

import modelo.Sistema;

import vista.Receptor;
import vista.VentanaPrincipal;

public class Lanzador
{
  public Lanzador()
  {
    super();
  }

  public static void main(String[] args)
  {
    VentanaPrincipal vista = new VentanaPrincipal();
    Sistema modelo = new Sistema();
    Receptor receptor = new Receptor(modelo, vista);
    vista.setReceptor(receptor);
  }
}
