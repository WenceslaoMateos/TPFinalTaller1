package vista;

import excepciones.NoEncontradoException;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
  public static final Dimension JTEXTFIEDL_DIMEN = new Dimension(200, 20);
  public static final Dimension JBUTTON_DIMEN = new Dimension(100, 30);
  protected JTable fuente;

  public MiDialogo(JFrame owner,Receptor receptor, JTable fuente)
  {
    super(owner,true);
    this.fuente = fuente;
    this.setSize(750, 500);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    this.receptor = receptor;
    this.busqueda = new JPanel(new FlowLayout());
    this.resultado = new JPanel();

    Container contenedor = this.getContentPane();
    contenedor.setLayout(new BorderLayout());
    contenedor.add(this.resultado,BorderLayout.CENTER);
    contenedor.add(this.busqueda, BorderLayout.NORTH);
  
    this.texto = new JTextField();
    this.aceptar = new JButton("Aceptar");

    this.busqueda.add(this.texto);
    this.busqueda.add(this.aceptar);
    
    this.texto.setPreferredSize(MiDialogo.JTEXTFIEDL_DIMEN);
    this.aceptar.addActionListener(this);

  }


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
