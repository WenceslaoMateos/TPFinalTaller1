package vista;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import modelo.Asignatura;

public class TableModelAsignatura
  extends DefaultTableModel
{
  public TableModelAsignatura()
  {
    super(new Object[] { "Identificacion", "Nombre" }, 0);
  }

  @Override
  public boolean isCellEditable(int row, int column)
  {
    return false;
  }

  public void agregarFilas(Iterator asignaturas)
  {
    Asignatura aux;
    this.setRowCount(0);
    while (asignaturas.hasNext())
    {
      aux = (Asignatura) asignaturas.next();
      this.addRow(new Object[] { aux.getIdentificacion(), aux.getNombre() });
    }
  }
}
