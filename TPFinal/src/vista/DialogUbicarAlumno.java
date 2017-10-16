package vista;

import java.awt.BorderLayout;

import java.awt.Container;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DialogUbicarAlumno
  extends DialogUbicar
{

  public DialogUbicarAlumno(Receptor receptor)
  {
    super(receptor);
  }

  @Override
  public void generaTabla(Container contenedor)
  {
    this.columnas = new String[4];
    this.columnas[0]="Legajo";
    this.columnas[1]="Apellido y Nombre";
    this.columnas[2]="Domicilio";
    this.columnas[3]="Mail";
    tabla = new JTable(null, this.columnas);
    scroll = new JScrollPane(tabla);
    tabla.setFillsViewportHeight(true);
    contenedor.setLayout(new BorderLayout());
    contenedor.add(tabla.getTableHeader(), BorderLayout.PAGE_START);
    contenedor.add(tabla, BorderLayout.CENTER);
  }

  @Override
  public int getComando()
  {
    return Receptor.ALUMNO;
  }
}
