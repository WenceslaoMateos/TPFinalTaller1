package vista;

import java.awt.BorderLayout;
import java.awt.Container;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Alumno;

public class DialogUbicarAlumno
  extends DialogUbicar
{

  public DialogUbicarAlumno(Receptor receptor)
  {
    super(receptor);
  }

  @Override
  public int getComando()
  {
    return Receptor.ALUMNO;
  }

  @Override
  public void generaTabla(Container contenedor)
  {
    this.columnas = new Vector();
    this.columnas.add("Legajo");
    this.columnas.add("Apellido y Nombre");
    this.columnas.add("Domicilio");
    this.columnas.add("Mail");

    tabla = new JTable();
    tabla.setModel(new DefaultTableModel(this.columnas, 0));

    scroll = new JScrollPane(tabla);
    tabla.setFillsViewportHeight(true);
    contenedor.setLayout(new BorderLayout());
    contenedor.add(tabla.getTableHeader(), BorderLayout.PAGE_START);
    contenedor.add(tabla, BorderLayout.CENTER);
  }

  @Override
  public void agregaResultadosTabla(Iterator alumnos)
  {
    Alumno aux;
    DefaultTableModel model = (DefaultTableModel) this.tabla.getModel();
    model.setRowCount(0);
    while (alumnos.hasNext())
    {
      aux = (Alumno) alumnos.next();
      model.addRow(new Object[] { aux.getLegajo(), aux.getApellidoNombre(), aux.getDomicilio(), aux.getMail() });
    }
  }
}
