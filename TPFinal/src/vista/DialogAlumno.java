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

import modelo.Alumno;

public class DialogAlumno
  extends MiDialogo
{
  private TableModelAlumno modeloAlumno;

  public DialogAlumno(JFrame owner,Receptor receptor, JTable fuente, DefaultTableModel modelo)
  {
    super(owner,receptor, fuente);
    this.setTitle("Selección de alumno");
    this.modeloAlumno = (TableModelAlumno) modelo;
    this.tabla = new JTable(this.modeloAlumno);
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
    this.modeloAlumno.agregarFilas(alumnos);
  }

  @Override
  public int getComandoAccion()
  {
    return Receptor.ALUMNO;
  }

  ///este va a mandar el resultado a la ventana
  @Override
  public void valueChanged(ListSelectionEvent e)
  {
    try
    {
      Alumno elemento =
        (Alumno) this.receptor.buscar(this.tabla.getValueAt(this.tabla.getSelectedRow(), 0), Receptor.ALUMNO);
      DefaultTableModel model = (DefaultTableModel) this.fuente.getModel();
      model.addRow(new Object[] { elemento.getLegajo(), elemento.getApellidoNombre(), elemento.getDomicilio(),
                                  elemento.getMail() });
    }
    catch (NoEncontradoException f)
    {
      JOptionPane.showMessageDialog(this, f.getMessage());
    }
    this.dispose();
  }
}
