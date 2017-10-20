package vista;

import excepciones.NoEncontradoException;

import java.awt.BorderLayout;

import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import modelo.Asignatura;

public class DialogAsignatura
  extends MiDialogo
{
  private TableModelAsignatura modeloAsignatura;


  public DialogAsignatura(JFrame owner,Receptor receptor, JTable fuente, DefaultTableModel modelo)
  {
    super(owner,receptor, fuente);
    
    this.setTitle("Selección de asignatura");
    this.modeloAsignatura = (TableModelAsignatura) modelo;
    this.tabla = new JTable(this.modeloAsignatura);
    resultado.setLayout(new BorderLayout());
    this.scroll = new JScrollPane(tabla);
    this.tabla.setFillsViewportHeight(true);
    this.resultado.add(this.scroll);
    resultado.add(tabla.getTableHeader(), BorderLayout.PAGE_START);
    resultado.add(tabla,BorderLayout.CENTER);
    this.setVisible(true);
  }

  @Override
  public void agregaResultadosTabla(Iterator alumnos)
  {
    this.modeloAsignatura.agregarFilas(alumnos);
  }

  @Override
  public int getComandoAccion()
  {
    return Receptor.ASIGNATURA;
  }

  ///este va a mandar el resultado a la ventana
  @Override
  public void valueChanged(ListSelectionEvent e)
  {
    try
    {
      Asignatura elemento =
        (Asignatura) this.receptor.buscar(this.tabla.getValueAt(this.tabla.getSelectedRow(), 0), Receptor.ASIGNATURA);
      DefaultTableModel model = (DefaultTableModel) this.fuente.getModel();
            
      model.addRow(new Object[] { elemento.getIdentificacion(), elemento.getNombre() });
    }
    catch (NoEncontradoException f)
    {
      JOptionPane.showMessageDialog(this, f.getMessage());
    }
    this.dispose();
  }
}
