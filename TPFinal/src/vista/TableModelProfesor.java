package vista;

import java.util.Iterator;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import modelo.Profesor;

public class TableModelProfesor
  extends DefaultTableModel
{
  public TableModelProfesor()
  {
    super(new Object[] { "Legajo", "Apellido y Nombre", "Domicilio", "Mail", "Telefono" }, 0);
  }

  @Override
  public boolean isCellEditable(int row, int column)
  {
    return false;
  }
  public void eliminarFila(int fila)
  {
    Vector aux=this.getDataVector();
    aux.remove(fila);
  }
  public void agregarFilas(Iterator profesores)
  {
    Profesor aux;
    this.setRowCount(0);
    while (profesores.hasNext())
    {
      aux = (Profesor) profesores.next();
      this.addRow(new Object[] { aux.getLegajo(), aux.getApellidoNombre(), aux.getDomicilio(), aux.getMail(),
                                 aux.getTelefono() });
    }
  }
}
