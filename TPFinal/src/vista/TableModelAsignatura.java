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

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }

    public boolean contieneElemento(String id)
    {
        int n = this.getRowCount();
        int i = 0;
        while (i < n && !this.getValueAt(i, 0).equals(id))
            i++;
        return i < n; //true  si lo contiene, por que salio antes
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
