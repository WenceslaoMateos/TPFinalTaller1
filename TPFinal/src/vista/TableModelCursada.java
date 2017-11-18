package vista;

import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import modelo.Cursada;

public class TableModelCursada
    extends DefaultTableModel
{
    public TableModelCursada()
    {
        super(new Object[] { "Identificacion", "ID Asignatura", "Nombre Asignatura", "Periodo", "Dia", "Hora Inicio",
                             "Hora Finalizacion" }, 0);
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }

    public void agregarFilas(Iterator cursadas)
    {
        Cursada aux;
        this.setRowCount(0);
        while (cursadas.hasNext())
        {
            aux = (Cursada) cursadas.next();
            this.addRow(new Object[] { aux.getIdentificacion(), aux.getAsignatura().getIdentificacion(),
                                       aux.getAsignatura().getNombre(), aux.getPeriodo(), aux.getDia(),
                                       aux.getHoraInicio(), aux.getHoraFinalizacion() });
        }
    }
}
