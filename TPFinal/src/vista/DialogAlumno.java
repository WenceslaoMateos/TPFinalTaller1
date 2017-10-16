package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;

import modelo.Alumno;
import modelo.Asignatura;

public class DialogAlumno
  extends MiDialogo
{
  private JTextField legajo;
  private JTextField nombre;
  private JTextField domicilio;
  private JTextField mail;
  private Alumno elemento;
  private TableModelAlumno modeloAlumno;
  private TableModelAsignatura modeloHistoria;
  private JTable historia;
  private JScrollPane scrollHistoria;


  public DialogAlumno(Receptor receptor)
  {
    super(receptor);
    this.elemento = null;
  }

  @Override
  public void generaTablaYCampos(Container resultado, Container campos)
  {


    this.modeloAlumno = new TableModelAlumno();
    this.tabla = new JTable(this.modeloAlumno);

    this.scroll = new JScrollPane(tabla);
    this.tabla.setFillsViewportHeight(true);
    resultado.setLayout(new BorderLayout());
    resultado.add(tabla.getTableHeader(), BorderLayout.PAGE_START);
    resultado.add(tabla, BorderLayout.CENTER);

    //---------------------------------------------------
    campos.setLayout(new BorderLayout());
    JPanel etiquetas = new JPanel();
    campos.add(etiquetas, BorderLayout.WEST);
    etiquetas.setLayout(new GridLayout(4, 1));
    etiquetas.add(new JLabel("Legajo"));
    etiquetas.add(new JLabel("Nombre y Apellido"));
    etiquetas.add(new JLabel("Domicilio"));
    etiquetas.add(new JLabel("Mail"));

    JPanel labels = new JPanel();
    campos.add(labels, BorderLayout.CENTER);
    labels.setLayout(new GridLayout(4, 1));

    this.legajo = new JTextField();
    this.legajo.setEditable(false);
    this.nombre = new JTextField();
    this.domicilio = new JTextField();
    this.mail = new JTextField();

    labels.add(this.legajo);
    labels.add(this.nombre);
    labels.add(this.domicilio);
    labels.add(this.mail);


    //----------------------------------------------------

    this.modeloHistoria = new TableModelAsignatura();
    this.historia = new JTable(modeloHistoria);

    this.scrollHistoria = new JScrollPane(this.historia);
    this.historia.setFillsViewportHeight(true);
    campos.add(this.historia.getTableHeader(), BorderLayout.SOUTH);
    campos.add(this.historia, BorderLayout.SOUTH);
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

  @Override
  public void valueChanged(ListSelectionEvent e)
  {
    this.elemento = (Alumno) this.receptor.buscar(this.tabla.getValueAt(this.tabla.getSelectedRow(), 0),Receptor.ALUMNO);
    this.legajo.setText(this.elemento.getLegajo());
    this.nombre.setText(this.elemento.getApellidoNombre());
    this.domicilio.setText(this.elemento.getDomicilio());
    this.mail.setText(this.elemento.getMail());

    this.modeloHistoria.agregarFilas(this.elemento.historiaAcademica());
  }
}
