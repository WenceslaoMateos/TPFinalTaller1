package lanzador;

import controlador.ControlSistema;

import modelo.Sistema;

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
    ControlSistema controlador;
    Sistema modelo;
  }
}
