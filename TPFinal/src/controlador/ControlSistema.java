package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Sistema;

import vista.VentanaPrincipal;

public class ControlSistema
  implements ActionListener
{
  private Sistema modelo;
  private VentanaPrincipal vista;

  public ControlSistema(Sistema modelo, VentanaPrincipal vista)
  {
    super();
    this.modelo = modelo;
    this.vista = vista;
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // TODO Implement this methodz
  }
}
