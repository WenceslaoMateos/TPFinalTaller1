package vista;

import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import modelo.Asignatura;

public class TableModelAsignatura
  extends DefaultTableModel
{
  public TableModelAsignatura()
  {
    super(new Object[] { "Identificacion", "Nombre" }, 0);
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
