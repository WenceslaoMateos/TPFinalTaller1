package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public abstract class DialogUbicar
  extends JDialog
  implements ActionListener, ListSelectionListener
{
  protected Receptor receptor;
  protected JTextField texto;
  protected JButton aceptar;
  protected JTable tabla;
  protected JScrollPane scroll;
  protected Vector columnas;
  protected JPanel busqueda;
  protected JPanel resultado;

  public DialogUbicar(Receptor receptor)
  {
    super();
    this.setSize(200, 200);
    this.setVisible(true);
    this.receptor = receptor;
    this.busqueda = new JPanel();
    this.resultado = new JPanel();
    Container contenedor = this.getContentPane();
    contenedor.setLayout(new BorderLayout());
    contenedor.add(this.busqueda, BorderLayout.NORTH);
    contenedor.add(this.resultado, BorderLayout.CENTER);

    this.busqueda.setLayout(new GridLayout(1, 2));
    this.texto = new JTextField();
    this.busqueda.add(this.texto);
    this.aceptar = new JButton("Aceptar");
    this.busqueda.add(this.aceptar);
    this.aceptar.addActionListener(this);
    this.generaTabla(resultado);
  }

  public abstract void generaTabla(Container contenedor);

  public abstract void agregaResultadosTabla(Iterator alumnos);

  public abstract int getComando();

  @Override
  public void actionPerformed(ActionEvent e)
  {
    this.agregaResultadosTabla(this.receptor.ubicar(this.texto.getText(), this.getComando()));
    this.tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.tabla.setRowSelectionAllowed(true);
    ListSelectionModel model = this.tabla.getSelectionModel();
    model.addListSelectionListener(this);

  }

  @Override
  public void valueChanged(ListSelectionEvent e)
  {
    String identificador = (String) this.tabla.getValueAt(this.tabla.getSelectedRow(), 0);
    dispose();
  }
}
