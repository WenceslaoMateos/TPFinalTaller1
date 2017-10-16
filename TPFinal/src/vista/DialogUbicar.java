package vista;

import java.awt.BorderLayout;
import java.awt.Container;

import java.awt.FlowLayout;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public abstract class DialogUbicar
  extends JDialog
  implements ActionListener
{
  protected Receptor receptor;
  protected JTextField texto;
  protected JButton aceptar;
  protected JTable tabla;
  protected JScrollPane scroll;
  protected String[] columnas;

  public DialogUbicar(Receptor receptor)
  {
    super();
    this.setSize(200, 200);
    this.setVisible(true);
    this.receptor = receptor;
    JPanel busqueda = new JPanel();
    JPanel resultado = new JPanel();
    Container contenedor = this.getContentPane();
    contenedor.setLayout(new BorderLayout());
    contenedor.add(busqueda, BorderLayout.CENTER);
    contenedor.add(resultado, BorderLayout.SOUTH);

    busqueda.setLayout(new GridLayout(1, 2));
    this.texto = new JTextField();
    busqueda.add(this.texto);
    this.aceptar = new JButton("Aceptar");
    busqueda.add(this.aceptar);
    this.aceptar.addActionListener(this);

    this.generaTabla(resultado);
  }

  public abstract void generaTabla(Container contenedor);

  public abstract int getComando();

  @Override
  public void actionPerformed(ActionEvent e)
  {
    this.receptor.ubicar(this.texto.getText(), this.getComando());
  }
}
