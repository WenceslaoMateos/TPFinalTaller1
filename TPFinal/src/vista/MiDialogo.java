package vista;

import excepciones.NoEncontradoException;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

public abstract class MiDialogo
  extends JDialog
  implements ActionListener, ListSelectionListener
{
  protected Receptor receptor;
  protected JTextField texto;
  protected JButton aceptar;
  protected JTable tabla;
  protected JScrollPane scroll;
  protected JPanel busqueda;
  protected JPanel resultado;
  protected JPanel campos;

  public MiDialogo(Receptor receptor)
  {
    super();
    this.setSize(200, 200);
    this.setVisible(true);
    this.receptor = receptor;
    this.busqueda = new JPanel();
    this.resultado = new JPanel();
    this.campos = new JPanel();

    Container contenedor = this.getContentPane();
    contenedor.setLayout(new BorderLayout());
    contenedor.add(this.busqueda, BorderLayout.NORTH);
    contenedor.add(this.resultado, BorderLayout.WEST);
    contenedor.add(this.campos, BorderLayout.CENTER);

    this.busqueda.setLayout(new GridLayout(1, 2));
    this.texto = new JTextField();
    this.busqueda.add(this.texto);
    this.aceptar = new JButton("Aceptar");
    this.busqueda.add(this.aceptar);
    this.aceptar.addActionListener(this);

    this.generaTablaYCampos(this.resultado, this.campos);
  }

  public abstract void generaTablaYCampos(Container tabla, Container campos);

  public abstract void agregaResultadosTabla(Iterator alumnos);

  public abstract int getComandoAccion();

  @Override
  public void actionPerformed(ActionEvent e)
  {
    try
    {
      this.agregaResultadosTabla(this.receptor.ubicar(this.texto.getText(), this.getComandoAccion()));
      this.tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      this.tabla.setRowSelectionAllowed(true);
      ListSelectionModel model = this.tabla.getSelectionModel();
      model.addListSelectionListener(this);
    }
    catch (NoEncontradoException f)
    {
      JOptionPane.showMessageDialog(this, f.getMessage());
    }
  }
}
