package vista;

import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import modelo.Alumno;

public class TableModelAlumno
    extends DefaultTableModel
{
    public TableModelAlumno()
    {
        super(new Object[] { "Legajo", "Apellido y Nombre", "Domicilio", "Mail" }, 0);
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

    public void agregarFilas(Iterator alumnos)
    {
        Alumno aux;
        this.setRowCount(0);
        while (alumnos.hasNext())
        {
            aux = (Alumno) alumnos.next();
            this.addRow(new Object[] { aux.getLegajo(), aux.getApellidoNombre(), aux.getDomicilio(), aux.getMail() });
        }
    }
}
