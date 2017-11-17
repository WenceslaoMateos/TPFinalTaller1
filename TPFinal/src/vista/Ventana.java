
package vista;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;
import excepciones.DiaInvalidoException;
import excepciones.NoEncontradoException;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.Dia;
import modelo.Persona;
import modelo.Profesor;

/**
 *
 * @author wence
 */
public class Ventana
  extends javax.swing.JFrame
  implements ListSelectionListener
{
  private Receptor receptor;
  private int accionAceptar = 0;
  private static final int NUEVO = 0;
  private static final int MODIFICAR = 1;

  /**Creates new form Ventana
   * @param receptor Receptor de la ventana
   */
  public Ventana(Receptor receptor)
  {
    initComponents();
    this.receptor = receptor;
    this.setResizable(false);
    this.setTitle("Programa de Gestion de asignaturas");
    this.setVisible(true);

    //limpia los paneles para el comienzo de la aplicación
    this.cancelarAlumno();
    this.cancelarProfesor();
    this.cancelarAsignatura();
    this.cancelarCursada();

    //genera los listeners para las tablas de busqueda
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.addWindowListener(new WindowAdapter()
    {

      @Override
      public void windowClosing(WindowEvent e)
      {
        super.windowClosing(e);
        Ventana.this.receptor.guardar();
        Ventana.this.dispose();
      }
    });
    this.jTableAlumnoAlumno
        .getSelectionModel()
        .addListSelectionListener(this);
    this.jTableProfesorProfesor
        .getSelectionModel()
        .addListSelectionListener(this);
    this.jTableCursadaCursada
        .getSelectionModel()
        .addListSelectionListener(this);
    this.jTableAsignaturaAsignatura
        .getSelectionModel()
        .addListSelectionListener(this);

    //inician las tablas para que se pueda seleccionar una fila
    this.jTableAlumnoAlumno.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.jTableAlumnoAlumno.setRowSelectionAllowed(true);

    this.jTableHistoria.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.jTableHistoria.setRowSelectionAllowed(true);

    this.jTableProfesorProfesor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.jTableProfesorProfesor.setRowSelectionAllowed(true);

    this.jTableCompetencia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.jTableCompetencia.setRowSelectionAllowed(true);

    this.jTableAsignaturaAsignatura.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.jTableAsignaturaAsignatura.setRowSelectionAllowed(true);

    this.jTableCorrelativas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.jTableCorrelativas.setRowSelectionAllowed(true);

    this.jTableCursadaCursada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.jTableCursadaCursada.setRowSelectionAllowed(true);

    this.jTableAlumnosCursada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.jTableAlumnosCursada.setRowSelectionAllowed(true);

    this.jTableProfesoresCursada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.jTableProfesoresCursada.setRowSelectionAllowed(true);
  }

  @Override
  public void valueChanged(ListSelectionEvent e)
  {
    if (e.getValueIsAdjusting())
    {
      if (this.jPanelAlumno.isShowing())
      {
        ((DefaultTableModel) this.jTableHistoria.getModel()).setRowCount(0);
        Alumno elemento;
        try
        {
          elemento =
            (Alumno) this.receptor.buscar(this.jTableAlumnoAlumno.getValueAt(this.jTableAlumnoAlumno.getSelectedRow(),
                                                                             0), Receptor.ALUMNO);
          this.jTextFieldLegajoAlumno.setText(elemento.getLegajo());
          this.jTextFieldNombreAlumno.setText(elemento.getApellidoNombre());
          this.jTextFieldDomicilioAlumno.setText(elemento.getDomicilio());
          this.jTextFieldMailAlumno.setText(elemento.getMail());

          Iterator<Asignatura> asignaturas = elemento.historiaAcademica();
          Asignatura aux;
          DefaultTableModel model = (DefaultTableModel) this.jTableHistoria.getModel();
          while (asignaturas.hasNext())
          {
            aux = asignaturas.next();
            model.addRow(new Object[] { aux.getIdentificacion(), aux.getNombre() });
          }
        }
        catch (NoEncontradoException f)
        {
          //esto nunca se va a ejecutar, ya que si está en la tabla, es por que existe
        }
      }
      else if (this.jPanelProfesor.isShowing())
      {
        Profesor elemento;
        try
        {
          ((DefaultTableModel) this.jTableCompetencia.getModel()).setRowCount(0);
          elemento =
            (Profesor) this.receptor.buscar(this.jTableProfesorProfesor.getValueAt(this.jTableProfesorProfesor.getSelectedRow(),
                                                                                   0), Receptor.PROFESOR);
          this.jTextFieldLegajoProfesor.setText(elemento.getLegajo());
          this.jTextFieldNombreProfesor.setText(elemento.getApellidoNombre());
          this.jTextFieldDomicilioProfesor.setText(elemento.getDomicilio());
          this.jTextFieldMailProfesor.setText(elemento.getMail());
          this.jTextFieldTelefonoProfesor.setText(elemento.getTelefono());

          Iterator<Asignatura> asignaturas = elemento.competencias();
          Asignatura aux;
          DefaultTableModel model = (DefaultTableModel) this.jTableCompetencia.getModel();
          while (asignaturas.hasNext())
          {
            aux = asignaturas.next();
            model.addRow(new Object[] { aux.getIdentificacion(), aux.getNombre() });
          }
        }
        catch (NoEncontradoException f)
        {
          //esto nunca se va a ejecutar, ya que si está en la tabla, es por que existe
        }
      }
      else if (this.jPanelAsignatura.isShowing())
      {
        Asignatura elemento;
        try
        {
          ((DefaultTableModel) this.jTableCorrelativas.getModel()).setRowCount(0);
          elemento =
            (Asignatura) this.receptor.buscar(this.jTableAsignaturaAsignatura.getValueAt(this.jTableAsignaturaAsignatura.getSelectedRow(),
                                                                                         0), Receptor.ASIGNATURA);
          this.jTextFieldIdentificadorAsignatura.setText(elemento.getIdentificacion());
          this.jTextFieldNombreAsignatura.setText(elemento.getNombre());
          Iterator<Asignatura> asignaturas = elemento.precorrelativas();
          Asignatura aux;
          DefaultTableModel model = (DefaultTableModel) this.jTableCorrelativas.getModel();
          while (asignaturas.hasNext())
          {
            aux = asignaturas.next();
            model.addRow(new Object[] { aux.getIdentificacion(), aux.getNombre() });
          }
        }
        catch (NoEncontradoException f)
        {
          //esto nunca se va a ejecutar, ya que si está en la tabla, es por que existe
        }
      }
      else if (this.jPanelCursada.isShowing())
      {
        Cursada elemento;
        try
        {
          ((DefaultTableModel) this.jTableProfesoresCursada.getModel()).setRowCount(0);
          ((DefaultTableModel) this.jTableAlumnosCursada.getModel()).setRowCount(0);
          elemento =
            (Cursada) this.receptor.buscar(this.jTableCursadaCursada.getValueAt(this.jTableCursadaCursada.getSelectedRow(),
                                                                                0), Receptor.CURSADA);
          this.jTextFieldIdentificadorCursada.setText(elemento.getIdentificacion());
          this.jTextFieldPeriodoCursada.setText(elemento.getPeriodo());
          this.jComboBoxDia.setSelectedIndex(Dia.parseInt(elemento.getDia()));
          this.jTextFieldInicioCursada.setText(elemento.getHoraInicio());
          this.jTextFieldFinCursada.setText(elemento.getHoraFinalizacion());

          Asignatura asig = elemento.getAsignatura();
          this.jTextFieldIDAsignaturaCursada.setText(asig.getIdentificacion());
          this.jTextFieldNombreAsignaturaCursada.setText(asig.getNombre());

          Iterator<Profesor> profesores = elemento.profesores();
          Profesor prof;
          DefaultTableModel modelprof = (DefaultTableModel) this.jTableProfesoresCursada.getModel();
          while (profesores.hasNext())
          {
            prof = profesores.next();
            modelprof.addRow(new Object[] { prof.getLegajo(), prof.getApellidoNombre(), prof.getDomicilio(),
                                            prof.getMail(), prof.getTelefono() });
          }

          Iterator<Alumno> alumnos = elemento.alumnos();
          Alumno alu;
          DefaultTableModel modelalu = (DefaultTableModel) this.jTableAlumnosCursada.getModel();
          while (alumnos.hasNext())
          {
            alu = alumnos.next();
            modelalu.addRow(new Object[] { alu.getLegajo(), alu.getApellidoNombre(), alu.getDomicilio(),
                                           alu.getMail() });
          }
        }
        catch (NoEncontradoException | DiaInvalidoException f)
        {
          //esto nunca se va a ejecutar, ya que si está en la tabla, es por que existe
          //el dia tampoco puede ser invalido por que si está almacenado en la tabla es por que salió de las colecciones
          //y entonces existe
        }
      }
    }
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  private void initComponents()//GEN-BEGIN:initComponents
  {

    jTabbedPane = new javax.swing.JTabbedPane();
    jPanelAlumno = new javax.swing.JPanel();
    jPanelBuscarAlumno = new javax.swing.JPanel();
    jButtonBuscarAlumno = new javax.swing.JButton();
    jTextFieldBuscarAlumno = new javax.swing.JTextField();
    jPanelTableBusquedaAlumno = new javax.swing.JPanel();
    jScrollPaneBusquedaAlumno = new javax.swing.JScrollPane();
    jTableAlumnoAlumno = new javax.swing.JTable();
    jPanelResultadosAlumno = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jTextFieldLegajoAlumno = new javax.swing.JTextField();
    jTextFieldNombreAlumno = new javax.swing.JTextField();
    jTextFieldDomicilioAlumno = new javax.swing.JTextField();
    jTextFieldMailAlumno = new javax.swing.JTextField();
    jScrollPaneHistoria = new javax.swing.JScrollPane();
    jTableHistoria = new javax.swing.JTable();
    jButtonNuevoAlumno = new javax.swing.JButton();
    jButtonEliminarAlumno = new javax.swing.JButton();
    jButtonAgregarHistoria = new javax.swing.JButton();
    jButtonAceptarAlumno = new javax.swing.JButton();
    jButtonCancelarAlumno = new javax.swing.JButton();
    jButtonModificarAlumno = new javax.swing.JButton();
    jButtonEliminarHistoria = new javax.swing.JButton();
    jPanelProfesor = new javax.swing.JPanel();
    jPanelResultadosProfesor = new javax.swing.JPanel();
    jLabel2 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    jLabel9 = new javax.swing.JLabel();
    jLabel10 = new javax.swing.JLabel();
    jLabel11 = new javax.swing.JLabel();
    jTextFieldLegajoProfesor = new javax.swing.JTextField();
    jTextFieldNombreProfesor = new javax.swing.JTextField();
    jTextFieldDomicilioProfesor = new javax.swing.JTextField();
    jTextFieldMailProfesor = new javax.swing.JTextField();
    jTextFieldTelefonoProfesor = new javax.swing.JTextField();
    jScrollPaneCompetencias = new javax.swing.JScrollPane();
    jTableCompetencia = new javax.swing.JTable();
    jButtonNuevoProfesor = new javax.swing.JButton();
    jButtonEliminarProfesor = new javax.swing.JButton();
    jButtonAgregarCompetencia = new javax.swing.JButton();
    jButtonAceptarProfesor = new javax.swing.JButton();
    jButtonCancelarProfesor = new javax.swing.JButton();
    jButtonModificarProfesor = new javax.swing.JButton();
    jButtonEliminarCompetencia = new javax.swing.JButton();
    jPanelBusquedaProfesor = new javax.swing.JPanel();
    jScrollPaneBusquedaProfesor = new javax.swing.JScrollPane();
    jTableProfesorProfesor = new javax.swing.JTable();
    jPanelBuscarProfesor = new javax.swing.JPanel();
    jButtonBuscarProfesor = new javax.swing.JButton();
    jTextFieldBuscarProfesor = new javax.swing.JTextField();
    jPanelAsignatura = new javax.swing.JPanel();
    jPanelBuscarAsignatura = new javax.swing.JPanel();
    jButtonBuscarAsignatura = new javax.swing.JButton();
    jTextFieldBuscarAsignatura = new javax.swing.JTextField();
    jPanelResultadosAsignatura = new javax.swing.JPanel();
    jLabel12 = new javax.swing.JLabel();
    jLabel13 = new javax.swing.JLabel();
    jLabel16 = new javax.swing.JLabel();
    jTextFieldIdentificadorAsignatura = new javax.swing.JTextField();
    jTextFieldNombreAsignatura = new javax.swing.JTextField();
    jScrollPane7 = new javax.swing.JScrollPane();
    jTableCorrelativas = new javax.swing.JTable();
    jButtonNuevoAsignatura = new javax.swing.JButton();
    jButtonEliminarAsignatura = new javax.swing.JButton();
    jButtonAgregarCorrelativa = new javax.swing.JButton();
    jButtonAceptarAsignatura = new javax.swing.JButton();
    jButtonCancelarAsignatura = new javax.swing.JButton();
    jButtonModificarAsignatura = new javax.swing.JButton();
    jButtonEliminarCorrelativa = new javax.swing.JButton();
    jPanel3 = new javax.swing.JPanel();
    jScrollPane8 = new javax.swing.JScrollPane();
    jTableAsignaturaAsignatura = new javax.swing.JTable();
    jPanelCursada = new javax.swing.JPanel();
    jPanelBuscarCursada = new javax.swing.JPanel();
    jButtonBuscarCursada = new javax.swing.JButton();
    jTextFieldBuscarCursada = new javax.swing.JTextField();
    jPanel4 = new javax.swing.JPanel();
    jScrollPane9 = new javax.swing.JScrollPane();
    jTableCursadaCursada = new javax.swing.JTable();
    jPanelResultadosCursada = new javax.swing.JPanel();
    jLabel14 = new javax.swing.JLabel();
    jLabel17 = new javax.swing.JLabel();
    jLabel18 = new javax.swing.JLabel();
    jLabel19 = new javax.swing.JLabel();
    jLabel20 = new javax.swing.JLabel();
    jLabel21 = new javax.swing.JLabel();
    jLabel22 = new javax.swing.JLabel();
    jLabel23 = new javax.swing.JLabel();
    jTextFieldIdentificadorCursada = new javax.swing.JTextField();
    jTextFieldIDAsignaturaCursada = new javax.swing.JTextField();
    jTextFieldNombreAsignaturaCursada = new javax.swing.JTextField();
    jTextFieldPeriodoCursada = new javax.swing.JTextField();
    jTextFieldInicioCursada = new javax.swing.JTextField();
    jTextFieldFinCursada = new javax.swing.JTextField();
    jComboBoxDia = new javax.swing.JComboBox<>();
    jScrollPane10 = new javax.swing.JScrollPane();
    jTableProfesoresCursada = new javax.swing.JTable();
    jButtonNuevoCursada = new javax.swing.JButton();
    jButtonEliminarCursada = new javax.swing.JButton();
    jButtonAgregarProfesorCursada = new javax.swing.JButton();
    jButtonAceptarCursada = new javax.swing.JButton();
    jButtonCancelarCursada = new javax.swing.JButton();
    jButtonModificarCursada = new javax.swing.JButton();
    jScrollPane11 = new javax.swing.JScrollPane();
    jTableAlumnosCursada = new javax.swing.JTable();
    jButtonAgregarAlumnoCursada = new javax.swing.JButton();
    jButtonCambiarAsignaturaCursada = new javax.swing.JButton();
    jButtonEliminarProfesorCursada = new javax.swing.JButton();
    jButtonEliminarAlumnoCursada = new javax.swing.JButton();
    jButtonAgradecimientos = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jPanelAlumno.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jPanelBuscarAlumno.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jButtonBuscarAlumno.setText("Buscar");
    jButtonBuscarAlumno.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonBuscarAlumnoActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanelBuscarAlumnoLayout = new javax.swing.GroupLayout(jPanelBuscarAlumno);
    jPanelBuscarAlumno.setLayout(jPanelBuscarAlumnoLayout);
    jPanelBuscarAlumnoLayout.setHorizontalGroup(
      jPanelBuscarAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelBuscarAlumnoLayout.createSequentialGroup()
        .addComponent(jTextFieldBuscarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonBuscarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanelBuscarAlumnoLayout.setVerticalGroup(
      jPanelBuscarAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelBuscarAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        .addComponent(jTextFieldBuscarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(jButtonBuscarAlumno))
    );

    jPanelTableBusquedaAlumno.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTableAlumnoAlumno.setModel(new TableModelAlumno());
    jScrollPaneBusquedaAlumno.setViewportView(jTableAlumnoAlumno);

    javax.swing.GroupLayout jPanelTableBusquedaAlumnoLayout = new javax.swing.GroupLayout(jPanelTableBusquedaAlumno);
    jPanelTableBusquedaAlumno.setLayout(jPanelTableBusquedaAlumnoLayout);
    jPanelTableBusquedaAlumnoLayout.setHorizontalGroup(
      jPanelTableBusquedaAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPaneBusquedaAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
    );
    jPanelTableBusquedaAlumnoLayout.setVerticalGroup(
      jPanelTableBusquedaAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPaneBusquedaAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
    );

    jPanelResultadosAlumno.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jLabel1.setText("Legajo");

    jLabel3.setText("Apellido y Nombre");

    jLabel4.setText("Domicilio");

    jLabel5.setText("Mail");

    jLabel6.setText("Historia:");

    jTextFieldLegajoAlumno.setEditable(false);
    jTextFieldLegajoAlumno.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldNombreAlumno.setEditable(false);
    jTextFieldNombreAlumno.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldDomicilioAlumno.setEditable(false);
    jTextFieldDomicilioAlumno.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldMailAlumno.setEditable(false);
    jTextFieldMailAlumno.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTableHistoria.setModel(new TableModelAsignatura());
    jScrollPaneHistoria.setViewportView(jTableHistoria);

    jButtonNuevoAlumno.setText("Nuevo");
    jButtonNuevoAlumno.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonNuevoAlumnoActionPerformed(evt);
      }
    });

    jButtonEliminarAlumno.setText("Eliminar");
    jButtonEliminarAlumno.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonEliminarAlumnoActionPerformed(evt);
      }
    });

    jButtonAgregarHistoria.setText("Agregar...");
    jButtonAgregarHistoria.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAgregarHistoriaActionPerformed(evt);
      }
    });

    jButtonAceptarAlumno.setText("Aceptar");
    jButtonAceptarAlumno.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAceptarAlumnoActionPerformed(evt);
      }
    });

    jButtonCancelarAlumno.setText("Cancelar");
    jButtonCancelarAlumno.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonCancelarAlumnoActionPerformed(evt);
      }
    });

    jButtonModificarAlumno.setText("Modificar");
    jButtonModificarAlumno.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonModificarAlumnoActionPerformed(evt);
      }
    });

    jButtonEliminarHistoria.setText("Eliminar...");
    jButtonEliminarHistoria.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonEliminarHistoriaActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanelResultadosAlumnoLayout = new javax.swing.GroupLayout(jPanelResultadosAlumno);
    jPanelResultadosAlumno.setLayout(jPanelResultadosAlumnoLayout);
    jPanelResultadosAlumnoLayout.setHorizontalGroup(
      jPanelResultadosAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelResultadosAlumnoLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelResultadosAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPaneHistoria)
          .addGroup(jPanelResultadosAlumnoLayout.createSequentialGroup()
            .addGroup(jPanelResultadosAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(jLabel1)
              .addComponent(jLabel4)
              .addComponent(jLabel5)
              .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelResultadosAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jTextFieldMailAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
              .addComponent(jTextFieldLegajoAlumno, javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jTextFieldNombreAlumno, javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jTextFieldDomicilioAlumno)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelResultadosAlumnoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonAgregarHistoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEliminarHistoria)
                .addGap(5, 5, 5))))
          .addGroup(jPanelResultadosAlumnoLayout.createSequentialGroup()
            .addGroup(jPanelResultadosAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(jPanelResultadosAlumnoLayout.createSequentialGroup()
                .addComponent(jButtonAceptarAlumno)
                .addGap(18, 18, 18)
                .addComponent(jButtonCancelarAlumno))
              .addGroup(jPanelResultadosAlumnoLayout.createSequentialGroup()
                .addComponent(jButtonNuevoAlumno)
                .addGap(18, 18, 18)
                .addComponent(jButtonEliminarAlumno)
                .addGap(18, 18, 18)
                .addComponent(jButtonModificarAlumno)))
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    jPanelResultadosAlumnoLayout.setVerticalGroup(
      jPanelResultadosAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelResultadosAlumnoLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelResultadosAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButtonNuevoAlumno)
          .addComponent(jButtonEliminarAlumno)
          .addComponent(jButtonModificarAlumno))
        .addGap(18, 18, 18)
        .addGroup(jPanelResultadosAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(jTextFieldLegajoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanelResultadosAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(jTextFieldNombreAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanelResultadosAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4)
          .addComponent(jTextFieldDomicilioAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanelResultadosAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel5)
          .addComponent(jTextFieldMailAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanelResultadosAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel6)
          .addComponent(jButtonAgregarHistoria)
          .addComponent(jButtonEliminarHistoria))
        .addGap(5, 5, 5)
        .addComponent(jScrollPaneHistoria, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        .addGap(18, 18, 18)
        .addGroup(jPanelResultadosAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButtonAceptarAlumno)
          .addComponent(jButtonCancelarAlumno))
        .addContainerGap())
    );

    javax.swing.GroupLayout jPanelAlumnoLayout = new javax.swing.GroupLayout(jPanelAlumno);
    jPanelAlumno.setLayout(jPanelAlumnoLayout);
    jPanelAlumnoLayout.setHorizontalGroup(
      jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelAlumnoLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanelResultadosAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanelTableBusquedaAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanelBuscarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanelAlumnoLayout.setVerticalGroup(
      jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelAlumnoLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanelResultadosAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(jPanelAlumnoLayout.createSequentialGroup()
            .addComponent(jPanelBuscarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanelTableBusquedaAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addContainerGap())
    );

    jTabbedPane.addTab("Alumno", jPanelAlumno);

    jPanelResultadosProfesor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jLabel2.setText("Legajo");

    jLabel7.setText("Apellido y Nombre");

    jLabel8.setText("Domicilio");

    jLabel9.setText("Mail");

    jLabel10.setText("Competencias:");

    jLabel11.setText("Telefono");

    jTextFieldLegajoProfesor.setEditable(false);
    jTextFieldLegajoProfesor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldNombreProfesor.setEditable(false);
    jTextFieldNombreProfesor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldDomicilioProfesor.setEditable(false);
    jTextFieldDomicilioProfesor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldMailProfesor.setEditable(false);
    jTextFieldMailProfesor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldTelefonoProfesor.setEditable(false);
    jTextFieldTelefonoProfesor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTableCompetencia.setModel(new TableModelAsignatura());
    jScrollPaneCompetencias.setViewportView(jTableCompetencia);

    jButtonNuevoProfesor.setText("Nuevo");
    jButtonNuevoProfesor.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonNuevoProfesorActionPerformed(evt);
      }
    });

    jButtonEliminarProfesor.setText("Eliminar");
    jButtonEliminarProfesor.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonEliminarProfesorActionPerformed(evt);
      }
    });

    jButtonAgregarCompetencia.setText("Agregar...");
    jButtonAgregarCompetencia.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAgregarCompetenciaActionPerformed(evt);
      }
    });

    jButtonAceptarProfesor.setText("Aceptar");
    jButtonAceptarProfesor.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAceptarProfesorActionPerformed(evt);
      }
    });

    jButtonCancelarProfesor.setText("Cancelar");
    jButtonCancelarProfesor.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonCancelarProfesorActionPerformed(evt);
      }
    });

    jButtonModificarProfesor.setText("Modificar");
    jButtonModificarProfesor.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonModificarProfesorActionPerformed(evt);
      }
    });

    jButtonEliminarCompetencia.setText("Eliminar...");
    jButtonEliminarCompetencia.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonEliminarCompetenciaActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanelResultadosProfesorLayout = new javax.swing.GroupLayout(jPanelResultadosProfesor);
    jPanelResultadosProfesor.setLayout(jPanelResultadosProfesorLayout);
    jPanelResultadosProfesorLayout.setHorizontalGroup(
      jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelResultadosProfesorLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanelResultadosProfesorLayout.createSequentialGroup()
            .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(jPanelResultadosProfesorLayout.createSequentialGroup()
                .addComponent(jButtonAceptarProfesor)
                .addGap(18, 18, 18)
                .addComponent(jButtonCancelarProfesor))
              .addGroup(jPanelResultadosProfesorLayout.createSequentialGroup()
                .addComponent(jButtonNuevoProfesor)
                .addGap(18, 18, 18)
                .addComponent(jButtonEliminarProfesor)
                .addGap(18, 18, 18)
                .addComponent(jButtonModificarProfesor)))
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(jPanelResultadosProfesorLayout.createSequentialGroup()
            .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel7)
              .addComponent(jLabel2)
              .addComponent(jLabel8)
              .addComponent(jLabel9)
              .addComponent(jLabel11))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jTextFieldMailProfesor)
              .addComponent(jTextFieldLegajoProfesor, javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jTextFieldNombreProfesor, javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jTextFieldDomicilioProfesor)
              .addComponent(jTextFieldTelefonoProfesor)))
          .addComponent(jScrollPaneCompetencias, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
          .addGroup(jPanelResultadosProfesorLayout.createSequentialGroup()
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(186, 186, 186)
            .addComponent(jButtonAgregarCompetencia)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButtonEliminarCompetencia)))
        .addContainerGap())
    );
    jPanelResultadosProfesorLayout.setVerticalGroup(
      jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelResultadosProfesorLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButtonNuevoProfesor)
          .addComponent(jButtonEliminarProfesor)
          .addComponent(jButtonModificarProfesor))
        .addGap(18, 18, 18)
        .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(jTextFieldLegajoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel7)
          .addComponent(jTextFieldNombreProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel8)
          .addComponent(jTextFieldDomicilioProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel9)
          .addComponent(jTextFieldMailProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel11)
          .addComponent(jTextFieldTelefonoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(38, 38, 38)
        .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel10)
          .addComponent(jButtonEliminarCompetencia)
          .addComponent(jButtonAgregarCompetencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(18, 18, 18)
        .addComponent(jScrollPaneCompetencias, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jButtonCancelarProfesor)
          .addComponent(jButtonAceptarProfesor))
        .addContainerGap())
    );

    jPanelBusquedaProfesor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTableProfesorProfesor.setModel(new TableModelProfesor());
    jScrollPaneBusquedaProfesor.setViewportView(jTableProfesorProfesor);

    javax.swing.GroupLayout jPanelBusquedaProfesorLayout = new javax.swing.GroupLayout(jPanelBusquedaProfesor);
    jPanelBusquedaProfesor.setLayout(jPanelBusquedaProfesorLayout);
    jPanelBusquedaProfesorLayout.setHorizontalGroup(
      jPanelBusquedaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPaneBusquedaProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
    );
    jPanelBusquedaProfesorLayout.setVerticalGroup(
      jPanelBusquedaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPaneBusquedaProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
    );

    jPanelBuscarProfesor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jButtonBuscarProfesor.setText("Buscar");
    jButtonBuscarProfesor.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonBuscarProfesorActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanelBuscarProfesorLayout = new javax.swing.GroupLayout(jPanelBuscarProfesor);
    jPanelBuscarProfesor.setLayout(jPanelBuscarProfesorLayout);
    jPanelBuscarProfesorLayout.setHorizontalGroup(
      jPanelBuscarProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelBuscarProfesorLayout.createSequentialGroup()
        .addComponent(jTextFieldBuscarProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonBuscarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanelBuscarProfesorLayout.setVerticalGroup(
      jPanelBuscarProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelBuscarProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        .addComponent(jTextFieldBuscarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(jButtonBuscarProfesor))
    );

    javax.swing.GroupLayout jPanelProfesorLayout = new javax.swing.GroupLayout(jPanelProfesor);
    jPanelProfesor.setLayout(jPanelProfesorLayout);
    jPanelProfesorLayout.setHorizontalGroup(
      jPanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 1069, Short.MAX_VALUE)
      .addGroup(jPanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelProfesorLayout.createSequentialGroup()
          .addGap(14, 14, 14)
          .addComponent(jPanelResultadosProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGap(18, 18, 18)
          .addGroup(jPanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBusquedaProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelBuscarProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGap(14, 14, 14)))
    );
    jPanelProfesorLayout.setVerticalGroup(
      jPanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 566, Short.MAX_VALUE)
      .addGroup(jPanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelProfesorLayout.createSequentialGroup()
          .addGap(8, 8, 8)
          .addGroup(jPanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProfesorLayout.createSequentialGroup()
              .addComponent(jPanelBuscarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(jPanelBusquedaProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanelResultadosProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGap(8, 8, 8)))
    );

    jTabbedPane.addTab("Profesor", jPanelProfesor);

    jPanelBuscarAsignatura.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jButtonBuscarAsignatura.setText("Buscar");
    jButtonBuscarAsignatura.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonBuscarAsignaturaActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanelBuscarAsignaturaLayout = new javax.swing.GroupLayout(jPanelBuscarAsignatura);
    jPanelBuscarAsignatura.setLayout(jPanelBuscarAsignaturaLayout);
    jPanelBuscarAsignaturaLayout.setHorizontalGroup(
      jPanelBuscarAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelBuscarAsignaturaLayout.createSequentialGroup()
        .addComponent(jTextFieldBuscarAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonBuscarAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanelBuscarAsignaturaLayout.setVerticalGroup(
      jPanelBuscarAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelBuscarAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        .addComponent(jTextFieldBuscarAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(jButtonBuscarAsignatura))
    );

    jPanelResultadosAsignatura.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jLabel12.setText("Identificador");

    jLabel13.setText("Nombre");

    jLabel16.setText("Correlatividades:");

    jTextFieldIdentificadorAsignatura.setEditable(false);
    jTextFieldIdentificadorAsignatura.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldNombreAsignatura.setEditable(false);
    jTextFieldNombreAsignatura.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTableCorrelativas.setModel(new TableModelAsignatura());
    jScrollPane7.setViewportView(jTableCorrelativas);

    jButtonNuevoAsignatura.setText("Nuevo");
    jButtonNuevoAsignatura.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonNuevoAsignaturaActionPerformed(evt);
      }
    });

    jButtonEliminarAsignatura.setText("Eliminar");
    jButtonEliminarAsignatura.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonEliminarAsignaturaActionPerformed(evt);
      }
    });

    jButtonAgregarCorrelativa.setText("Agregar...");
    jButtonAgregarCorrelativa.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAgregarCorrelativaActionPerformed(evt);
      }
    });

    jButtonAceptarAsignatura.setText("Aceptar");
    jButtonAceptarAsignatura.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAceptarAsignaturaActionPerformed(evt);
      }
    });

    jButtonCancelarAsignatura.setText("Cancelar");
    jButtonCancelarAsignatura.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonCancelarAsignaturaActionPerformed(evt);
      }
    });

    jButtonModificarAsignatura.setText("Modificar");
    jButtonModificarAsignatura.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonModificarAsignaturaActionPerformed(evt);
      }
    });

    jButtonEliminarCorrelativa.setText("Eliminar...");
    jButtonEliminarCorrelativa.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonEliminarCorrelativaActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanelResultadosAsignaturaLayout = new javax.swing.GroupLayout(jPanelResultadosAsignatura);
    jPanelResultadosAsignatura.setLayout(jPanelResultadosAsignaturaLayout);
    jPanelResultadosAsignaturaLayout.setHorizontalGroup(
      jPanelResultadosAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelResultadosAsignaturaLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelResultadosAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanelResultadosAsignaturaLayout.createSequentialGroup()
            .addGroup(jPanelResultadosAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel13)
              .addComponent(jLabel12))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelResultadosAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jTextFieldIdentificadorAsignatura, javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jTextFieldNombreAsignatura, javax.swing.GroupLayout.Alignment.TRAILING)))
          .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
          .addGroup(jPanelResultadosAsignaturaLayout.createSequentialGroup()
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(152, 152, 152)
            .addComponent(jButtonAgregarCorrelativa)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButtonEliminarCorrelativa))
          .addGroup(jPanelResultadosAsignaturaLayout.createSequentialGroup()
            .addGroup(jPanelResultadosAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(jPanelResultadosAsignaturaLayout.createSequentialGroup()
                .addComponent(jButtonNuevoAsignatura)
                .addGap(18, 18, 18)
                .addComponent(jButtonEliminarAsignatura)
                .addGap(18, 18, 18)
                .addComponent(jButtonModificarAsignatura))
              .addGroup(jPanelResultadosAsignaturaLayout.createSequentialGroup()
                .addComponent(jButtonAceptarAsignatura)
                .addGap(18, 18, 18)
                .addComponent(jButtonCancelarAsignatura)))
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    jPanelResultadosAsignaturaLayout.setVerticalGroup(
      jPanelResultadosAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelResultadosAsignaturaLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelResultadosAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButtonNuevoAsignatura)
          .addComponent(jButtonEliminarAsignatura)
          .addComponent(jButtonModificarAsignatura))
        .addGap(18, 18, 18)
        .addGroup(jPanelResultadosAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel12)
          .addComponent(jTextFieldIdentificadorAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanelResultadosAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel13)
          .addComponent(jTextFieldNombreAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addGroup(jPanelResultadosAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel16)
          .addComponent(jButtonAgregarCorrelativa)
          .addComponent(jButtonEliminarCorrelativa))
        .addGap(18, 18, 18)
        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        .addGap(18, 18, 18)
        .addGroup(jPanelResultadosAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jButtonCancelarAsignatura)
          .addComponent(jButtonAceptarAsignatura))
        .addContainerGap())
    );

    jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTableAsignaturaAsignatura.setModel(new TableModelAsignatura());
    jScrollPane8.setViewportView(jTableAsignaturaAsignatura);

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
    );
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout jPanelAsignaturaLayout = new javax.swing.GroupLayout(jPanelAsignatura);
    jPanelAsignatura.setLayout(jPanelAsignaturaLayout);
    jPanelAsignaturaLayout.setHorizontalGroup(
      jPanelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelAsignaturaLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanelResultadosAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addGroup(jPanelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanelBuscarAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanelAsignaturaLayout.setVerticalGroup(
      jPanelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelAsignaturaLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanelResultadosAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(jPanelAsignaturaLayout.createSequentialGroup()
            .addComponent(jPanelBuscarAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addContainerGap())
    );

    jTabbedPane.addTab("Asignatura", jPanelAsignatura);

    jPanelBuscarCursada.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jButtonBuscarCursada.setText("Buscar");
    jButtonBuscarCursada.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonBuscarCursadaActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanelBuscarCursadaLayout = new javax.swing.GroupLayout(jPanelBuscarCursada);
    jPanelBuscarCursada.setLayout(jPanelBuscarCursadaLayout);
    jPanelBuscarCursadaLayout.setHorizontalGroup(
      jPanelBuscarCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelBuscarCursadaLayout.createSequentialGroup()
        .addComponent(jTextFieldBuscarCursada, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonBuscarCursada, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanelBuscarCursadaLayout.setVerticalGroup(
      jPanelBuscarCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelBuscarCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        .addComponent(jTextFieldBuscarCursada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(jButtonBuscarCursada))
    );

    jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTableCursadaCursada.setModel(new TableModelCursada());
    jScrollPane9.setViewportView(jTableCursadaCursada);

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
    );
    jPanel4Layout.setVerticalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
    );

    jPanelResultadosCursada.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jLabel14.setText("Identificador");

    jLabel17.setText("Profesores");

    jLabel18.setText("Periodo");

    jLabel19.setText("Asignatura");

    jLabel20.setText("Dia");

    jLabel21.setText("Hora Inicio");

    jLabel22.setText("Hora Finalizacion");

    jLabel23.setText("Alumnos");

    jTextFieldIdentificadorCursada.setEditable(false);
    jTextFieldIdentificadorCursada.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldIDAsignaturaCursada.setEditable(false);
    jTextFieldIDAsignaturaCursada.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldNombreAsignaturaCursada.setEditable(false);
    jTextFieldNombreAsignaturaCursada.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldPeriodoCursada.setEditable(false);
    jTextFieldPeriodoCursada.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldInicioCursada.setEditable(false);
    jTextFieldInicioCursada.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldFinCursada.setEditable(false);
    jTextFieldFinCursada.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jComboBoxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" }));

    jTableProfesoresCursada.setModel(new TableModelProfesor());
    jScrollPane10.setViewportView(jTableProfesoresCursada);

    jButtonNuevoCursada.setText("Nuevo");
    jButtonNuevoCursada.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonNuevoCursadaActionPerformed(evt);
      }
    });

    jButtonEliminarCursada.setText("Eliminar");
    jButtonEliminarCursada.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonEliminarCursadaActionPerformed(evt);
      }
    });

    jButtonAgregarProfesorCursada.setText("Agregar...");
    jButtonAgregarProfesorCursada.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAgregarProfesorCursadaActionPerformed(evt);
      }
    });

    jButtonAceptarCursada.setText("Aceptar");
    jButtonAceptarCursada.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAceptarCursadaActionPerformed(evt);
      }
    });

    jButtonCancelarCursada.setText("Cancelar");
    jButtonCancelarCursada.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonCancelarCursadaActionPerformed(evt);
      }
    });

    jButtonModificarCursada.setText("Modificar");
    jButtonModificarCursada.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonModificarCursadaActionPerformed(evt);
      }
    });

    jTableAlumnosCursada.setModel(new TableModelAlumno());
    jScrollPane11.setViewportView(jTableAlumnosCursada);

    jButtonAgregarAlumnoCursada.setText("Agregar...");
    jButtonAgregarAlumnoCursada.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAgregarAlumnoCursadaActionPerformed(evt);
      }
    });

    jButtonCambiarAsignaturaCursada.setText("Asignatura...");
    jButtonCambiarAsignaturaCursada.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonCambiarAsignaturaCursadaActionPerformed(evt);
      }
    });

    jButtonEliminarProfesorCursada.setText("Eliminar...");
    jButtonEliminarProfesorCursada.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonEliminarProfesorCursadaActionPerformed(evt);
      }
    });

    jButtonEliminarAlumnoCursada.setText("Eliminar...");
    jButtonEliminarAlumnoCursada.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonEliminarAlumnoCursadaActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanelResultadosCursadaLayout = new javax.swing.GroupLayout(jPanelResultadosCursada);
    jPanelResultadosCursada.setLayout(jPanelResultadosCursadaLayout);
    jPanelResultadosCursadaLayout.setHorizontalGroup(
      jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
          .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
            .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel14)
              .addComponent(jLabel19)
              .addComponent(jLabel18))
            .addGap(17, 17, 17)
            .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jTextFieldIdentificadorCursada)
              .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
                .addComponent(jTextFieldPeriodoCursada, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldInicioCursada, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButtonAgregarProfesorCursada)
                    .addGap(18, 18, 18)
                    .addComponent(jButtonEliminarProfesorCursada))
                  .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
                    .addComponent(jLabel22)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextFieldFinCursada))))
              .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
                .addComponent(jTextFieldIDAsignaturaCursada)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldNombreAsignaturaCursada, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonCambiarAsignaturaCursada))))
          .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
          .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(237, 237, 237)
            .addComponent(jButtonAgregarAlumnoCursada)
            .addGap(18, 18, 18)
            .addComponent(jButtonEliminarAlumnoCursada))
          .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
            .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
                .addComponent(jButtonAceptarCursada)
                .addGap(18, 18, 18)
                .addComponent(jButtonCancelarCursada))
              .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
                .addComponent(jButtonNuevoCursada)
                .addGap(18, 18, 18)
                .addComponent(jButtonEliminarCursada)
                .addGap(18, 18, 18)
                .addComponent(jButtonModificarCursada))
              .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    jPanelResultadosCursadaLayout.setVerticalGroup(
      jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButtonNuevoCursada)
          .addComponent(jButtonEliminarCursada)
          .addComponent(jButtonModificarCursada))
        .addGap(18, 18, 18)
        .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel14)
          .addComponent(jTextFieldIdentificadorCursada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel19)
          .addComponent(jTextFieldNombreAsignaturaCursada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jTextFieldIDAsignaturaCursada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jButtonCambiarAsignaturaCursada))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jTextFieldPeriodoCursada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel18)
          .addComponent(jTextFieldFinCursada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel22)
          .addComponent(jTextFieldInicioCursada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel21)
          .addComponent(jLabel20)
          .addComponent(jComboBoxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(42, 42, 42)
        .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel17)
          .addComponent(jButtonAgregarProfesorCursada)
          .addComponent(jButtonEliminarProfesorCursada))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel23)
          .addComponent(jButtonAgregarAlumnoCursada)
          .addComponent(jButtonEliminarAlumnoCursada))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jButtonCancelarCursada)
          .addComponent(jButtonAceptarCursada))
        .addContainerGap())
    );

    javax.swing.GroupLayout jPanelCursadaLayout = new javax.swing.GroupLayout(jPanelCursada);
    jPanelCursada.setLayout(jPanelCursadaLayout);
    jPanelCursadaLayout.setHorizontalGroup(
      jPanelCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelCursadaLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanelResultadosCursada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addGroup(jPanelCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanelBuscarCursada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanelCursadaLayout.setVerticalGroup(
      jPanelCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCursadaLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(jPanelResultadosCursada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(jPanelCursadaLayout.createSequentialGroup()
            .addComponent(jPanelBuscarCursada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addContainerGap())
    );

    jTabbedPane.addTab("Cursada", jPanelCursada);

    jButtonAgradecimientos.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
    jButtonAgradecimientos.setText("Agradecimientos");
    jButtonAgradecimientos.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAgradecimientosActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jTabbedPane)
        .addContainerGap())
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGap(0, 0, Short.MAX_VALUE)
        .addComponent(jButtonAgradecimientos))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jButtonAgradecimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(jTabbedPane)
        .addContainerGap())
    );

    pack();
  }//GEN-END:initComponents

  private void jButtonBuscarAlumnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBuscarAlumnoActionPerformed
  {//GEN-HEADEREND:event_jButtonBuscarAlumnoActionPerformed
    try
    {
      Iterator<Alumno> alumnos = this.receptor.ubicar(this.jTextFieldBuscarAlumno.getText(), Receptor.ALUMNO);
      Alumno aux;
      DefaultTableModel model = (DefaultTableModel) this.jTableAlumnoAlumno.getModel();
      model.setRowCount(0);
      while (alumnos.hasNext())
      {
        aux = alumnos.next();
        model.addRow(new Object[] { aux.getLegajo(), aux.getApellidoNombre(), aux.getDomicilio(), aux.getMail() });
      }
    }
    catch (NoEncontradoException e)
    {
      JOptionPane.showMessageDialog(this, e.getMessage() + " Por favor vuelva a ingresarlo");
    }
  }//GEN-LAST:event_jButtonBuscarAlumnoActionPerformed

  private void jButtonNuevoAlumnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonNuevoAlumnoActionPerformed
  {//GEN-HEADEREND:event_jButtonNuevoAlumnoActionPerformed
    this.accionAceptar = Ventana.NUEVO;

    ((DefaultTableModel) this.jTableAlumnoAlumno.getModel()).setRowCount(0);
    ((DefaultTableModel) this.jTableHistoria.getModel()).setRowCount(0);

    this.jButtonBuscarAlumno.setEnabled(false);
    this.jTextFieldBuscarAlumno.setText("");
    this.jTextFieldBuscarAlumno.setEditable(false);

    this.jButtonNuevoAlumno.setEnabled(false);
    this.jButtonEliminarAlumno.setEnabled(false);
    this.jButtonModificarAlumno.setEnabled(false);

    this.jButtonCancelarAlumno.setEnabled(true);
    this.jButtonAceptarAlumno.setEnabled(true);

    this.jTextFieldLegajoAlumno.setText("");

    this.jTextFieldNombreAlumno.setEditable(true);
    this.jTextFieldNombreAlumno.setText("");

    this.jTextFieldDomicilioAlumno.setEditable(true);
    this.jTextFieldDomicilioAlumno.setText("");

    this.jTextFieldMailAlumno.setEditable(true);
    this.jTextFieldMailAlumno.setText("");
  }//GEN-LAST:event_jButtonNuevoAlumnoActionPerformed

  private void jButtonAgregarHistoriaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAgregarHistoriaActionPerformed
  {//GEN-HEADEREND:event_jButtonAgregarHistoriaActionPerformed
    new DialogAsignatura(this, this.receptor, this.jTableHistoria, new TableModelAsignatura());  
  }//GEN-LAST:event_jButtonAgregarHistoriaActionPerformed

  private void jButtonModificarAlumnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonModificarAlumnoActionPerformed
  {//GEN-HEADEREND:event_jButtonModificarAlumnoActionPerformed
    if (!this.jTextFieldLegajoAlumno
             .getText()
             .equals(""))
    {
      this.accionAceptar = Ventana.MODIFICAR;

      this.jButtonBuscarAlumno.setEnabled(false);
      this.jTextFieldBuscarAlumno.setText("");
      this.jTextFieldBuscarAlumno.setEditable(false);

      this.jButtonNuevoAlumno.setEnabled(false);
      this.jButtonEliminarAlumno.setEnabled(false);
      this.jButtonModificarAlumno.setEnabled(false);

      this.jButtonCancelarAlumno.setEnabled(true);
      this.jButtonAceptarAlumno.setEnabled(true);

      this.jTextFieldNombreAlumno.setEditable(true);

      this.jTextFieldDomicilioAlumno.setEditable(true);

      this.jTextFieldMailAlumno.setEditable(true);

      this.jButtonAgregarHistoria.setEnabled(true);
      this.jButtonEliminarHistoria.setEnabled(true);
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione un alumno para poder modificarlo");
   
  }//GEN-LAST:event_jButtonModificarAlumnoActionPerformed

  private void jButtonEliminarAlumnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarAlumnoActionPerformed
  {//GEN-HEADEREND:event_jButtonEliminarAlumnoActionPerformed
    if (!this.jTextFieldLegajoAlumno
             .getText()
             .equals(""))
    {
      try
      {
        if (JOptionPane.showConfirmDialog(this, "¿Esta usted seguro que desea eliminar?", "Eliminar Alumno",
                                          JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
          this.receptor.baja(this.jTextFieldLegajoAlumno.getText(), Receptor.ALUMNO);

          ((DefaultTableModel) this.jTableAlumnoAlumno.getModel()).setRowCount(0);
          this.cancelarAlumno();
        }
      }
      catch (NoEncontradoException e)
      {
        JOptionPane.showMessageDialog(this, e.getMessage());
      }
      JOptionPane.showMessageDialog(this, "Alumno eliminado exitosamente.");
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione un alumno para poder eliminarlo");
  }//GEN-LAST:event_jButtonEliminarAlumnoActionPerformed

  private void jButtonCancelarAlumnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelarAlumnoActionPerformed
  {//GEN-HEADEREND:event_jButtonCancelarAlumnoActionPerformed
    this.cancelarAlumno();
  }//GEN-LAST:event_jButtonCancelarAlumnoActionPerformed

  private void jButtonAceptarAlumnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAceptarAlumnoActionPerformed
  {//GEN-HEADEREND:event_jButtonAceptarAlumnoActionPerformed
    //a un alumno no se le agregan materias a la historia cuando se crea nuevo
    Alumno provisorio =
      new Alumno(this.jTextFieldNombreAlumno.getText(), this.jTextFieldDomicilioAlumno.getText(),
                 this.jTextFieldMailAlumno.getText());

    try
    {
      if (this.accionAceptar == Ventana.NUEVO)
        this.receptor.alta(provisorio, Receptor.ALUMNO);
      else if (this.accionAceptar == Ventana.MODIFICAR)
      {
        provisorio.setLegajo(this.jTextFieldLegajoAlumno.getText());

        ArrayList<Asignatura> agregar = new ArrayList<Asignatura>();
        ArrayList<Asignatura> eliminar = new ArrayList<Asignatura>();

        this.modificarHistoria(provisorio, eliminar, agregar);
        this.receptor.modificacion(provisorio, Receptor.ALUMNO, eliminar.iterator(), agregar.iterator());
      }
      else
        JOptionPane.showMessageDialog(this, "La operación deseada no existe.");
      this.cancelarAlumno();
      JOptionPane.showMessageDialog(this, "Operación realizada exitosamente.");
    }
    catch (NoEncontradoException | ClaveYaExistenteException | DatoInvalidoException e)
    {
      JOptionPane.showMessageDialog(this, e.getMessage());
    }
  }//GEN-LAST:event_jButtonAceptarAlumnoActionPerformed

  private void jButtonBuscarProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBuscarProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonBuscarProfesorActionPerformed

    try
    {
      Iterator<Profesor> profesores = this.receptor.ubicar(this.jTextFieldBuscarProfesor.getText(), Receptor.PROFESOR);
      Profesor aux;
      DefaultTableModel model = (DefaultTableModel) this.jTableProfesorProfesor.getModel();
      model.setRowCount(0);
      while (profesores.hasNext())
      {
        aux = profesores.next();
        model.addRow(new Object[] { aux.getLegajo(), aux.getApellidoNombre(), aux.getDomicilio(), aux.getMail(),
                                    aux.getTelefono() });
      }
    }
    catch (NoEncontradoException e)
    {
      JOptionPane.showMessageDialog(this, e.getMessage() + " Por favor vuelva a ingresarlo");
    }

  }//GEN-LAST:event_jButtonBuscarProfesorActionPerformed

  private void jButtonNuevoProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonNuevoProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonNuevoProfesorActionPerformed
    this.accionAceptar = Ventana.NUEVO;

    ((DefaultTableModel) this.jTableProfesorProfesor.getModel()).setRowCount(0);
    ((DefaultTableModel) this.jTableCompetencia.getModel()).setRowCount(0);

    this.jTextFieldLegajoProfesor.setText("");

    this.jTextFieldNombreProfesor.setText("");
    this.jTextFieldNombreProfesor.setEditable(true);

    this.jTextFieldDomicilioProfesor.setText("");
    this.jTextFieldDomicilioProfesor.setEditable(true);

    this.jTextFieldMailProfesor.setText("");
    this.jTextFieldMailProfesor.setEditable(true);

    this.jTextFieldTelefonoProfesor.setText("");
    this.jTextFieldTelefonoProfesor.setEditable(true);

    this.jTextFieldBuscarProfesor.setText("");
    this.jTextFieldBuscarProfesor.setEditable(false);
    this.jButtonBuscarProfesor.setEnabled(false);

    this.jButtonNuevoProfesor.setEnabled(false);
    this.jButtonEliminarProfesor.setEnabled(false);
    this.jButtonModificarProfesor.setEnabled(false);

    this.jButtonAgregarCompetencia.setEnabled(true);
    this.jButtonEliminarCompetencia.setEnabled(true);

    this.jButtonCancelarProfesor.setEnabled(true);
    this.jButtonAceptarProfesor.setEnabled(true);
  }//GEN-LAST:event_jButtonNuevoProfesorActionPerformed

  private void jButtonEliminarProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonEliminarProfesorActionPerformed
    try
    {
      if (!this.jTextFieldLegajoProfesor
               .getText()
               .equals(""))
      {
        if (JOptionPane.showConfirmDialog(this, "¿Esta usted seguro que desea eliminar?", "Eliminar Profesor",
                                          JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
          this.receptor.baja(this.jTextFieldLegajoProfesor.getText(), Receptor.PROFESOR);

          ((DefaultTableModel) this.jTableProfesorProfesor.getModel()).setRowCount(0);
          this.cancelarProfesor();
        }
      }
      else
        JOptionPane.showMessageDialog(this, "Seleccione un profesor para poder eliminarlo");
    }
    catch (NoEncontradoException e)
    {
      JOptionPane.showMessageDialog(this, e.getMessage());
    }
    JOptionPane.showMessageDialog(this, "Profesor eliminado exitosamente.");
  }//GEN-LAST:event_jButtonEliminarProfesorActionPerformed

  private void jButtonAgregarCompetenciaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAgregarCompetenciaActionPerformed
  {//GEN-HEADEREND:event_jButtonAgregarCompetenciaActionPerformed
    new DialogAsignatura(this, this.receptor, this.jTableCompetencia, new TableModelAsignatura());  
  }//GEN-LAST:event_jButtonAgregarCompetenciaActionPerformed

  private void jButtonAceptarProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAceptarProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonAceptarProfesorActionPerformed
    Profesor provisorio =
      new Profesor(this.jTextFieldNombreProfesor.getText(), this.jTextFieldDomicilioProfesor.getText(),
                   this.jTextFieldMailProfesor.getText(), this.jTextFieldTelefonoProfesor.getText());
    try
    {
      if (this.accionAceptar == Ventana.NUEVO)
      {
        this.receptor.alta(provisorio, Receptor.PROFESOR);
        this.jTextFieldLegajoProfesor.setText(provisorio.getLegajo());

        ArrayList<Asignatura> agregar = new ArrayList<Asignatura>();
        ArrayList<Asignatura> eliminar = new ArrayList<Asignatura>();

        this.modificarCompetencia(provisorio, eliminar, agregar);

        this.receptor.modificacion(provisorio, Receptor.PROFESOR, eliminar.iterator(), agregar.iterator());
      }
      else if (this.accionAceptar == Ventana.MODIFICAR)
      {
        provisorio.setLegajo(this.jTextFieldLegajoProfesor.getText());

        ArrayList<Asignatura> agregar = new ArrayList<Asignatura>();
        ArrayList<Asignatura> eliminar = new ArrayList<Asignatura>();

        this.modificarCompetencia(provisorio, eliminar, agregar);

        this.receptor.modificacion(provisorio, Receptor.PROFESOR, eliminar.iterator(), agregar.iterator());
      }
      else
        JOptionPane.showMessageDialog(this, "La operación deseada no existe.");
      this.cancelarProfesor();
      JOptionPane.showMessageDialog(this, "Operación realizada exitosamente.");
    }
    catch (NoEncontradoException | ClaveYaExistenteException | DatoInvalidoException e)
    {
      JOptionPane.showMessageDialog(this, e.getMessage());
    }
  }//GEN-LAST:event_jButtonAceptarProfesorActionPerformed

  private void jButtonCancelarProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelarProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonCancelarProfesorActionPerformed
    this.cancelarProfesor();
  }//GEN-LAST:event_jButtonCancelarProfesorActionPerformed

  private void jButtonModificarProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonModificarProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonModificarProfesorActionPerformed
    if (!this.jTextFieldLegajoProfesor
             .getText()
             .equals(""))
    {
      this.accionAceptar = Ventana.MODIFICAR;

      ((DefaultTableModel) this.jTableProfesorProfesor.getModel()).setRowCount(0);

      this.jTextFieldNombreProfesor.setEditable(true);
      this.jTextFieldDomicilioProfesor.setEditable(true);
      this.jTextFieldMailProfesor.setEditable(true);
      this.jTextFieldTelefonoProfesor.setEditable(true);

      this.jButtonEliminarProfesor.setEnabled(false);
      this.jButtonNuevoProfesor.setEnabled(false);
      this.jButtonModificarProfesor.setEnabled(false);

      this.jButtonAgregarCompetencia.setEnabled(true);
      this.jButtonEliminarCompetencia.setEnabled(true);
      this.jButtonAceptarProfesor.setEnabled(true);
      this.jButtonCancelarProfesor.setEnabled(true);
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione un profesor para poder modificarlo");
  }//GEN-LAST:event_jButtonModificarProfesorActionPerformed

  private void jButtonBuscarAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBuscarAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonBuscarAsignaturaActionPerformed
    try
    {
      Iterator<Asignatura> asignaturas =
        this.receptor.ubicar(this.jTextFieldBuscarAsignatura.getText(), Receptor.ASIGNATURA);
      Asignatura aux;
      DefaultTableModel model = (DefaultTableModel) this.jTableAsignaturaAsignatura.getModel();
      model.setRowCount(0);
      while (asignaturas.hasNext())
      {
        aux = asignaturas.next();
        model.addRow(new Object[] { aux.getIdentificacion(), aux.getNombre() });
      }
    }
    catch (NoEncontradoException e)
    {
      JOptionPane.showMessageDialog(this, e.getMessage() + " Por favor vuelva a ingresarlo");
    }
  }//GEN-LAST:event_jButtonBuscarAsignaturaActionPerformed

  private void jButtonNuevoAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonNuevoAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonNuevoAsignaturaActionPerformed
    this.accionAceptar = Ventana.NUEVO;

    ((DefaultTableModel) this.jTableAsignaturaAsignatura.getModel()).setRowCount(0);
    ((DefaultTableModel) this.jTableCorrelativas.getModel()).setRowCount(0);

    this.jTextFieldIdentificadorAsignatura.setText("");

    this.jTextFieldNombreAsignatura.setText("");
    this.jTextFieldNombreAsignatura.setEditable(true);

    this.jButtonEliminarAsignatura.setEnabled(false);
    this.jButtonModificarAsignatura.setEnabled(false);
    this.jButtonNuevoAsignatura.setEnabled(false);

    this.jButtonAgregarCorrelativa.setEnabled(true);
    this.jButtonEliminarCorrelativa.setEnabled(true);

    this.jButtonAceptarAsignatura.setEnabled(true);
    this.jButtonCancelarAsignatura.setEnabled(true);

    this.jTextFieldBuscarAsignatura.setText("");
    this.jTextFieldBuscarAsignatura.setEditable(false);
    this.jButtonBuscarAsignatura.setEnabled(false);
  }//GEN-LAST:event_jButtonNuevoAsignaturaActionPerformed

  private void jButtonEliminarAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonEliminarAsignaturaActionPerformed
    try
    {
      if (!this.jTextFieldIdentificadorAsignatura
               .getText()
               .equals(""))
      {
        if (JOptionPane.showConfirmDialog(this, "¿Esta usted seguro que desea eliminar?", "Eliminar Asignatura",
                                          JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
          this.receptor.baja(this.jTextFieldIdentificadorAsignatura.getText(), Receptor.ASIGNATURA);

          ((DefaultTableModel) this.jTableAsignaturaAsignatura.getModel()).setRowCount(0);
          this.cancelarAsignatura();
        }
        else
          JOptionPane.showMessageDialog(this, "Seleccione una asignatura para poder eliminarla");
      }
    }
    catch (NoEncontradoException e)
    {
      JOptionPane.showMessageDialog(this, e.getMessage());
    }
    JOptionPane.showMessageDialog(this, "Asignatura eliminada exitosamente.");
  }//GEN-LAST:event_jButtonEliminarAsignaturaActionPerformed

  private void jButtonAgregarCorrelativaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAgregarCorrelativaActionPerformed
  {//GEN-HEADEREND:event_jButtonAgregarCorrelativaActionPerformed
    new DialogAsignatura(this, this.receptor, this.jTableCorrelativas, new TableModelAsignatura());
  }//GEN-LAST:event_jButtonAgregarCorrelativaActionPerformed

  private void jButtonAceptarAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAceptarAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonAceptarAsignaturaActionPerformed
    Asignatura provisorio = new Asignatura(this.jTextFieldNombreAsignatura.getText());
    try
    {
      ArrayList<Asignatura> agregar = new ArrayList<Asignatura>();
      ArrayList<Asignatura> eliminar = new ArrayList<Asignatura>();
      if (this.accionAceptar == Ventana.NUEVO)
      {
        this.receptor.alta(provisorio, Receptor.ASIGNATURA);
        this.jTextFieldIdentificadorAsignatura.setText(provisorio.getIdentificacion());
        this.modificarCorrelativa(provisorio, eliminar, agregar);
        this.receptor.modificacion(provisorio, Receptor.ASIGNATURA, eliminar.iterator(), agregar.iterator());
      }
      else if (this.accionAceptar == Ventana.MODIFICAR)
      {
        provisorio.setIdentificacion(this.jTextFieldIdentificadorAsignatura.getText());
        this.modificarCorrelativa(provisorio, eliminar, agregar);
        this.receptor.modificacion(provisorio, Receptor.ASIGNATURA, eliminar.iterator(), agregar.iterator());
      }
      else
        JOptionPane.showMessageDialog(this, "La operación deseada no existe.");
      this.cancelarAsignatura();
      JOptionPane.showMessageDialog(this, "Operación realizada exitosamente.");
    }
    catch (NoEncontradoException | ClaveYaExistenteException | DatoInvalidoException e)
    {
      JOptionPane.showMessageDialog(this, e.getMessage());
    }
  }//GEN-LAST:event_jButtonAceptarAsignaturaActionPerformed

  private void jButtonCancelarAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelarAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonCancelarAsignaturaActionPerformed
    this.cancelarAsignatura();    
  }//GEN-LAST:event_jButtonCancelarAsignaturaActionPerformed

  private void jButtonModificarAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonModificarAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonModificarAsignaturaActionPerformed
    if (!this.jTextFieldIdentificadorAsignatura
             .getText()
             .equals(""))
    {
      this.accionAceptar = Ventana.MODIFICAR;

      this.jTextFieldNombreAsignatura.setEditable(true);

      this.jButtonEliminarAsignatura.setEnabled(false);
      this.jButtonNuevoAsignatura.setEnabled(false);
      this.jButtonModificarAsignatura.setEnabled(false);

      this.jButtonAgregarCorrelativa.setEnabled(true);
      this.jButtonEliminarCorrelativa.setEnabled(true);

      this.jButtonAceptarAsignatura.setEnabled(true);
      this.jButtonCancelarAsignatura.setEnabled(true);

      ((DefaultTableModel) this.jTableAsignaturaAsignatura.getModel()).setRowCount(0);

      this.jTextFieldBuscarAsignatura.setText("");
      this.jTextFieldBuscarAsignatura.setEditable(false);
      this.jButtonBuscarAsignatura.setEnabled(false);
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione una asignatura para poder modificarla");
  }//GEN-LAST:event_jButtonModificarAsignaturaActionPerformed

  private void jButtonBuscarCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBuscarCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonBuscarCursadaActionPerformed

    try
    {
      Iterator<Cursada> cursadas = this.receptor.ubicar(this.jTextFieldBuscarCursada.getText(), Receptor.CURSADA);
      Cursada aux;
      DefaultTableModel model = (DefaultTableModel) this.jTableCursadaCursada.getModel();
      model.setRowCount(0);
      while (cursadas.hasNext())
      {
        aux = cursadas.next();
        model.addRow(new Object[] { aux.getIdentificacion(), aux.getAsignatura().getIdentificacion(),
                                    aux.getAsignatura().getNombre(), aux.getPeriodo(), aux.getDia(),
                                    aux.getHoraInicio(), aux.getHoraFinalizacion() });
      }
    }
    catch (NoEncontradoException e)
    {
      JOptionPane.showMessageDialog(this, e.getMessage() + " Por favor vuelva a ingresarlo");
    }
  }//GEN-LAST:event_jButtonBuscarCursadaActionPerformed

  private void jButtonNuevoCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonNuevoCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonNuevoCursadaActionPerformed
    this.accionAceptar = Ventana.NUEVO;

    this.jTextFieldIdentificadorCursada.setText("");

    this.jTextFieldIDAsignaturaCursada.setText("");
    this.jButtonCambiarAsignaturaCursada.setEnabled(true);

    this.jTextFieldNombreAsignaturaCursada.setText("");

    this.jComboBoxDia.setEnabled(true);

    this.jTextFieldPeriodoCursada.setText("");
    this.jTextFieldPeriodoCursada.setEditable(true);

    this.jTextFieldInicioCursada.setText("");
    this.jTextFieldInicioCursada.setEditable(true);

    this.jTextFieldFinCursada.setText("");
    this.jTextFieldFinCursada.setEditable(true);

    ((DefaultTableModel) this.jTableCursadaCursada.getModel()).setRowCount(0);
    ((DefaultTableModel) this.jTableAlumnosCursada.getModel()).setRowCount(0);
    ((DefaultTableModel) this.jTableProfesoresCursada.getModel()).setRowCount(0);

    this.jTextFieldBuscarCursada.setText("");
    this.jTextFieldBuscarCursada.setEditable(false);
    this.jButtonBuscarCursada.setEnabled(false);

    this.jButtonEliminarCursada.setEnabled(false);
    this.jButtonModificarCursada.setEnabled(false);
    this.jButtonNuevoAlumno.setEnabled(false);

    this.jButtonAgregarAlumnoCursada.setEnabled(true);
    this.jButtonEliminarAlumnoCursada.setEnabled(true);

    this.jButtonAgregarProfesorCursada.setEnabled(true);
    this.jButtonEliminarProfesorCursada.setEnabled(true);

    this.jButtonCancelarCursada.setEnabled(true);
    this.jButtonAceptarCursada.setEnabled(true);
  }//GEN-LAST:event_jButtonNuevoCursadaActionPerformed

  private void jButtonEliminarCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonEliminarCursadaActionPerformed
    if (!this.jTextFieldIdentificadorCursada
             .getText()
             .equals(""))
    {
      try
      {
        if (JOptionPane.showConfirmDialog(this, "¿Esta usted seguro que desea eliminar?", "Eliminar Cursada",
                                          JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
          this.receptor.baja(this.receptor.buscar(this.jTextFieldIdentificadorCursada.getText(), Receptor.CURSADA),
                             Receptor.CURSADA);

          ((DefaultTableModel) this.jTableCursadaCursada.getModel()).setRowCount(0);
          this.cancelarCursada();
        }
      }
      catch (NoEncontradoException e)
      {
        JOptionPane.showMessageDialog(this, e.getMessage());
      }
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione una cursada para poder eliminarla");
    JOptionPane.showMessageDialog(this, "Cursada eliminada exitosamente.");
  }//GEN-LAST:event_jButtonEliminarCursadaActionPerformed

  private void jButtonAgregarProfesorCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAgregarProfesorCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonAgregarProfesorCursadaActionPerformed
    new DialogProfesor(this, this.receptor, this.jTableProfesoresCursada, new TableModelProfesor());
  }//GEN-LAST:event_jButtonAgregarProfesorCursadaActionPerformed

  private void jButtonAceptarCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAceptarCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonAceptarCursadaActionPerformed
    try
    {
      Cursada provisorio =
        new Cursada((Asignatura) this.receptor.buscar(this.jTextFieldIDAsignaturaCursada.getText(),
                                                      Receptor.ASIGNATURA), this.jTextFieldPeriodoCursada.getText(),
                    Dia.parseDia(this.jComboBoxDia.getItemAt(this.jComboBoxDia.getSelectedIndex())),
                    this.jTextFieldInicioCursada.getText(), this.jTextFieldFinCursada.getText());

      ArrayList<Persona> agregar = new ArrayList<Persona>();
      ArrayList<Persona> eliminar = new ArrayList<Persona>();

      if (this.accionAceptar == Ventana.NUEVO)
      {
        this.receptor.alta(provisorio, Receptor.CURSADA);
        this.jTextFieldIdentificadorCursada.setText(provisorio.getIdentificacion());

        this.modificarCursada(provisorio, eliminar, agregar);
        this.receptor.modificacion(provisorio, Receptor.CURSADA, eliminar.iterator(), agregar.iterator());
      }
      else if (this.accionAceptar == Ventana.MODIFICAR)
      {
        provisorio.setIdentificacion(this.jTextFieldIdentificadorCursada.getText());
        this.modificarCursada(provisorio, eliminar, agregar);
        this.receptor.modificacion(provisorio, Receptor.CURSADA, eliminar.iterator(), agregar.iterator());
      }
      else
        JOptionPane.showMessageDialog(this, "La operación deseada no existe.");
      this.cancelarCursada();
      JOptionPane.showMessageDialog(this, "Operación realizada exitosamente.");
    }
    catch (DatoInvalidoException | NoEncontradoException | ClaveYaExistenteException | DiaInvalidoException e)
    {
      JOptionPane.showMessageDialog(this, e.getMessage());
    }
  }//GEN-LAST:event_jButtonAceptarCursadaActionPerformed

  private void jButtonCancelarCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelarCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonCancelarCursadaActionPerformed
    this.cancelarCursada();
  }//GEN-LAST:event_jButtonCancelarCursadaActionPerformed

  private void jButtonModificarCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonModificarCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonModificarCursadaActionPerformed
    if (!this.jTextFieldIdentificadorCursada
             .getText()
             .equals(""))
    {
      this.accionAceptar = Ventana.MODIFICAR;

      ((DefaultTableModel) this.jTableCursadaCursada.getModel()).setRowCount(0);

      this.jComboBoxDia.setEnabled(true);
      this.jTextFieldPeriodoCursada.setEditable(true);
      this.jTextFieldInicioCursada.setEditable(true);
      this.jTextFieldFinCursada.setEditable(true);

      this.jButtonAgregarAlumnoCursada.setEnabled(true);
      this.jButtonEliminarAlumnoCursada.setEnabled(true);
      this.jButtonAgregarProfesorCursada.setEnabled(true);
      this.jButtonEliminarProfesorCursada.setEnabled(true);
      this.jButtonCambiarAsignaturaCursada.setEnabled(true);

      this.jButtonCancelarCursada.setEnabled(true);
      this.jButtonAceptarCursada.setEnabled(true);

      this.jButtonEliminarCursada.setEnabled(false);
      this.jButtonNuevoCursada.setEnabled(false);
      this.jButtonModificarCursada.setEnabled(false);

      this.jTextFieldBuscarCursada.setText("");
      this.jTextFieldBuscarCursada.setEditable(false);
      this.jButtonBuscarCursada.setEnabled(false);
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione una cursada para poder modificarla");
  }//GEN-LAST:event_jButtonModificarCursadaActionPerformed

  private void jButtonAgregarAlumnoCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAgregarAlumnoCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonAgregarAlumnoCursadaActionPerformed
    new DialogAlumno(this, this.receptor, this.jTableAlumnosCursada, new TableModelAlumno());
  }//GEN-LAST:event_jButtonAgregarAlumnoCursadaActionPerformed

  private void jButtonCambiarAsignaturaCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCambiarAsignaturaCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonCambiarAsignaturaCursadaActionPerformed
    new DialogAsignaturaCursada(this, this.receptor, this.jTextFieldIDAsignaturaCursada,
                                this.jTextFieldNombreAsignaturaCursada);
  }//GEN-LAST:event_jButtonCambiarAsignaturaCursadaActionPerformed

  private void jButtonEliminarHistoriaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarHistoriaActionPerformed
  {//GEN-HEADEREND:event_jButtonEliminarHistoriaActionPerformed
    if (this.jTableHistoria.getSelectedRow() != -1)
    {
      TableModelAsignatura aux = (TableModelAsignatura) this.jTableHistoria.getModel();
      aux.removeRow(this.jTableHistoria.getSelectedRow());
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione una asignatura para poder eliminarla de la historia");
  }//GEN-LAST:event_jButtonEliminarHistoriaActionPerformed

  private void jButtonEliminarCompetenciaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarCompetenciaActionPerformed
  {//GEN-HEADEREND:event_jButtonEliminarCompetenciaActionPerformed
    if (this.jTableCompetencia.getSelectedRow() != -1)
    {
      TableModelAsignatura aux = (TableModelAsignatura) this.jTableCompetencia.getModel();
      aux.removeRow(this.jTableCompetencia.getSelectedRow());
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione una asignatura para poder eliminarla de la competencia");
  }//GEN-LAST:event_jButtonEliminarCompetenciaActionPerformed

  private void jButtonEliminarCorrelativaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarCorrelativaActionPerformed
  {//GEN-HEADEREND:event_jButtonEliminarCorrelativaActionPerformed
    if (this.jTableCorrelativas.getSelectedRow() != -1)
    {
      TableModelAsignatura aux = (TableModelAsignatura) this.jTableCorrelativas.getModel();
      aux.removeRow(this.jTableCorrelativas.getSelectedRow());
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione una asignatura para poder eliminarla de las correlativas");
  }//GEN-LAST:event_jButtonEliminarCorrelativaActionPerformed

  private void jButtonEliminarProfesorCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarProfesorCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonEliminarProfesorCursadaActionPerformed
    if (this.jTableProfesoresCursada.getSelectedRow() != -1)
    {
      TableModelProfesor aux = (TableModelProfesor) this.jTableProfesoresCursada.getModel();
      aux.removeRow(this.jTableProfesoresCursada.getSelectedRow());
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione un profesor para poder eliminarlo de la cursada");
  }//GEN-LAST:event_jButtonEliminarProfesorCursadaActionPerformed

  private void jButtonEliminarAlumnoCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarAlumnoCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonEliminarAlumnoCursadaActionPerformed
    if (this.jTableAlumnosCursada.getSelectedRow() != -1)
    {
      TableModelAlumno aux = (TableModelAlumno) this.jTableAlumnosCursada.getModel();
      aux.removeRow(this.jTableAlumnosCursada.getSelectedRow());
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione un alumno para poder eliminarlo de la cursada");
  }//GEN-LAST:event_jButtonEliminarAlumnoCursadaActionPerformed

  private void jButtonAgradecimientosActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAgradecimientosActionPerformed
  {//GEN-HEADEREND:event_jButtonAgradecimientosActionPerformed
    JOptionPane.showMessageDialog(this, "Gracias DarioFF");
  }//GEN-LAST:event_jButtonAgradecimientosActionPerformed

  /**
   * Este metodo setea todos los jTextField en vacio y los inhabilita, exceptuando el de buscar que lo habilita.
   * A su ves, limpia la tabla y habilita los botones de nuevo, eliminar, modificar y buscar.
   * También ihnabilita los botones de cancelar y aceptar junto con los de agregar... y eliminar...
   */
  private void cancelarAlumno()
  {
    this.jTextFieldLegajoAlumno.setText("");

    this.jTextFieldNombreAlumno.setText("");
    this.jTextFieldNombreAlumno.setEditable(false);

    this.jTextFieldDomicilioAlumno.setText("");
    this.jTextFieldDomicilioAlumno.setEditable(false);

    this.jTextFieldMailAlumno.setText("");
    this.jTextFieldMailAlumno.setEditable(false);

    this.jTextFieldBuscarAlumno.setText("");
    this.jTextFieldBuscarAlumno.setEditable(true);
    this.jButtonBuscarAlumno.setEnabled(true);

    ((DefaultTableModel) this.jTableHistoria.getModel()).setRowCount(0);

    this.jButtonEliminarAlumno.setEnabled(true);
    this.jButtonModificarAlumno.setEnabled(true);
    this.jButtonNuevoAlumno.setEnabled(true);

    this.jButtonAgregarHistoria.setEnabled(false);
    this.jButtonEliminarHistoria.setEnabled(false);

    this.jButtonAceptarAlumno.setEnabled(false);
    this.jButtonCancelarAlumno.setEnabled(false);
  }

  /**
   * Este metodo setea todos los jTextField en vacio y los inhabilita, exceptuando el de buscar que lo habilita.
   * A su ves, limpia la tabla y habilita los botones de nuevo, eliminar, modificar y buscar.
   * También ihnabilita los botones de cancelar y aceptar junto con los de agregar... y eliminar...
   */
  private void cancelarProfesor()
  {
    this.jTextFieldLegajoProfesor.setText("");

    this.jTextFieldNombreProfesor.setText("");
    this.jTextFieldNombreProfesor.setEditable(false);

    this.jTextFieldDomicilioProfesor.setText("");
    this.jTextFieldDomicilioProfesor.setEditable(false);

    this.jTextFieldMailProfesor.setText("");
    this.jTextFieldMailProfesor.setEditable(false);

    this.jTextFieldTelefonoProfesor.setText("");
    this.jTextFieldTelefonoProfesor.setEditable(false);

    this.jTextFieldBuscarProfesor.setText("");
    this.jTextFieldBuscarProfesor.setEditable(true);
    this.jButtonBuscarProfesor.setEnabled(true);

    ((DefaultTableModel) this.jTableCompetencia.getModel()).setRowCount(0);

    this.jButtonEliminarProfesor.setEnabled(true);
    this.jButtonModificarProfesor.setEnabled(true);
    this.jButtonNuevoProfesor.setEnabled(true);

    this.jButtonEliminarCompetencia.setEnabled(false);
    this.jButtonAgregarCompetencia.setEnabled(false);

    this.jButtonAceptarProfesor.setEnabled(false);
    this.jButtonCancelarProfesor.setEnabled(false);
  }

  /**
   * Este metodo setea todos los jTextField en vacio y los inhabilita, exceptuando el de buscar que lo habilita.
   * A su ves, limpia la tabla y habilita los botones de nuevo, eliminar, modificar y buscar.
   * También ihnabilita los botones de cancelar y aceptar junto con los de agregar... y eliminar...
   */
  private void cancelarAsignatura()
  {
    this.jTextFieldIdentificadorAsignatura.setText("");

    this.jTextFieldNombreAsignatura.setText("");
    this.jTextFieldNombreAsignatura.setEditable(false);

    this.jTextFieldBuscarAsignatura.setText("");
    this.jTextFieldBuscarAsignatura.setEditable(true);
    this.jButtonBuscarAsignatura.setEnabled(true);

    ((DefaultTableModel) this.jTableCorrelativas.getModel()).setRowCount(0);

    this.jButtonEliminarAsignatura.setEnabled(true);
    this.jButtonModificarAsignatura.setEnabled(true);
    this.jButtonNuevoAsignatura.setEnabled(true);

    this.jButtonAgregarCorrelativa.setEnabled(false);
    this.jButtonEliminarCorrelativa.setEnabled(false);

    this.jButtonCancelarAsignatura.setEnabled(false);
    this.jButtonAceptarAsignatura.setEnabled(false);
  }

  /**
   * Este metodo setea todos los jTextField en vacio y los inhabilita, exceptuando el de buscar que lo habilita.
   * A su ves, limpia las tablas y habilita los botones de nuevo, eliminar, modificar y buscar.
   * También ihnabilita los botones de cancelar y aceptar junto con los de agregar... y eliminar...
   */
  private void cancelarCursada()
  {
    ((DefaultTableModel) this.jTableAlumnosCursada.getModel()).setRowCount(0);
    ((DefaultTableModel) this.jTableProfesoresCursada.getModel()).setRowCount(0);

    this.jTextFieldIdentificadorCursada.setText("");

    this.jTextFieldIDAsignaturaCursada.setText("");

    this.jTextFieldNombreAsignaturaCursada.setText("");

    this.jTextFieldPeriodoCursada.setText("");
    this.jTextFieldPeriodoCursada.setEditable(false);

    this.jTextFieldInicioCursada.setText("");
    this.jTextFieldInicioCursada.setEditable(false);

    this.jTextFieldFinCursada.setText("");
    this.jTextFieldFinCursada.setEditable(false);

    this.jComboBoxDia.setEnabled(false);

    this.jTextFieldBuscarCursada.setText("");
    this.jTextFieldBuscarCursada.setEditable(true);
    this.jButtonBuscarCursada.setEnabled(true);

    this.jButtonEliminarCursada.setEnabled(true);
    this.jButtonModificarCursada.setEnabled(true);
    this.jButtonNuevoCursada.setEnabled(true);

    this.jButtonAgregarAlumnoCursada.setEnabled(false);
    this.jButtonEliminarAlumnoCursada.setEnabled(false);

    this.jButtonCambiarAsignaturaCursada.setEnabled(false);

    this.jButtonAgregarProfesorCursada.setEnabled(false);
    this.jButtonEliminarProfesorCursada.setEnabled(false);

    this.jButtonAceptarCursada.setEnabled(false);
    this.jButtonCancelarCursada.setEnabled(false);
  }

  private void modificarHistoria(Alumno provisorio, ArrayList<Asignatura> eliminar, ArrayList<Asignatura> agregar)
    throws NoEncontradoException, ClaveYaExistenteException
  {
    int i, n = this.jTableHistoria
                   .getModel()
                   .getRowCount();
    Alumno viejo = (Alumno) this.receptor.buscar(provisorio.getLegajo(), Receptor.ALUMNO);

    for (i = 0; i < n; i++)
    {
      provisorio.agregarHistoria((Asignatura) this.receptor.buscar(this.jTableHistoria.getValueAt(i, 0),
                                                                   Receptor.ASIGNATURA));
    }
    //si el for funciona, entonces es que el alumno es valido


    Iterator<Asignatura> asignaturasViejas = viejo.historiaAcademica();
    while (asignaturasViejas.hasNext())
    {
      Asignatura auxiliar = asignaturasViejas.next();
      if (!provisorio.asignaturaAprobada(auxiliar))
        eliminar.add(auxiliar);
    }

    asignaturasViejas = provisorio.historiaAcademica();
    while (asignaturasViejas.hasNext())
    {
      Asignatura auxiliar = asignaturasViejas.next();
      if (!viejo.asignaturaAprobada(auxiliar))
        agregar.add(auxiliar);
    }

  }

  private void modificarCompetencia(Profesor provisorio, ArrayList<Asignatura> eliminar, ArrayList<Asignatura> agregar)
    throws NoEncontradoException, ClaveYaExistenteException
  {
    int i, n = this.jTableCompetencia
                   .getModel()
                   .getRowCount();
    Profesor viejo = (Profesor) this.receptor.buscar(provisorio.getLegajo(), Receptor.PROFESOR);

    for (i = 0; i < n; i++)
    {
      provisorio.agregarCompetencia((Asignatura) this.receptor.buscar(this.jTableCompetencia.getValueAt(i, 0),
                                                                      Receptor.ASIGNATURA));
    }
    //si el for funciona, entonces es que el alumno es valido

    Iterator<Asignatura> asignaturasViejas = viejo.competencias();
    while (asignaturasViejas.hasNext())
    {
      Asignatura auxiliar = asignaturasViejas.next();
      if (!provisorio.habilitadoParaAsignatura(auxiliar))
        eliminar.add(auxiliar);
    }

    asignaturasViejas = provisorio.competencias();
    while (asignaturasViejas.hasNext())
    {
      Asignatura auxiliar = asignaturasViejas.next();
      if (!viejo.habilitadoParaAsignatura(auxiliar))
        agregar.add(auxiliar);
    }
  }

  private void modificarCorrelativa(Asignatura provisorio, ArrayList<Asignatura> eliminar,
                                    ArrayList<Asignatura> agregar)
    throws NoEncontradoException, ClaveYaExistenteException, DatoInvalidoException
  {
    int i, n = this.jTableCorrelativas
                   .getModel()
                   .getRowCount();
    Asignatura viejo = (Asignatura) this.receptor.buscar(provisorio.getIdentificacion(), Receptor.ASIGNATURA);

    for (i = 0; i < n; i++)
    {
      provisorio.agregarCorrelativa((Asignatura) this.receptor.buscar(this.jTableCorrelativas.getValueAt(i, 0),
                                                                      Receptor.ASIGNATURA));
    }
    //si el for funciona, entonces es que el alumno es valido

    Iterator<Asignatura> asignaturasViejas = viejo.precorrelativas();
    while (asignaturasViejas.hasNext())
    {
      Asignatura auxiliar = asignaturasViejas.next();
      if (!provisorio.tienePrecorrelativa(auxiliar))
        eliminar.add(auxiliar);
    }

    asignaturasViejas = provisorio.precorrelativas();
    while (asignaturasViejas.hasNext())
    {
      Asignatura auxiliar = asignaturasViejas.next();
      if (!viejo.tienePrecorrelativa(auxiliar))
        agregar.add(auxiliar);
    }
  }

  private void modificarCursada(Cursada provisorio, ArrayList<Persona> eliminar, ArrayList<Persona> agregar)
    throws NoEncontradoException, ClaveYaExistenteException, DatoInvalidoException
  {

    //***************************ALUMNO*************************//
    int i, n = this.jTableAlumnosCursada
                   .getModel()
                   .getRowCount();
    Cursada viejo = (Cursada) this.receptor.buscar(provisorio.getIdentificacion(), Receptor.CURSADA);
    for (i = 0; i < n; i++)
    {
      provisorio.altaAlumno((Alumno) this.receptor.buscar(this.jTableAlumnosCursada.getValueAt(i, 0), Receptor.ALUMNO));
    }
    //si el for funciona, entonces es que el alumno es valido

    Iterator<Alumno> alumnoViejas = viejo.alumnos();
    while (alumnoViejas.hasNext())
    {
      Alumno auxiliar = alumnoViejas.next();
      if (!provisorio.tieneAlumno(auxiliar))
        eliminar.add(auxiliar);
    }

    alumnoViejas = provisorio.alumnos();
    while (alumnoViejas.hasNext())
    {
      Alumno auxiliar = alumnoViejas.next();
      if (!viejo.tieneAlumno(auxiliar))
        agregar.add(auxiliar);
    }

    //***************************PROFESOR*************************//
    n = this.jTableProfesoresCursada
            .getModel()
            .getRowCount();
    for (i = 0; i < n; i++)
    {
      provisorio.altaProfesor((Profesor) this.receptor.buscar(this.jTableProfesoresCursada.getValueAt(i, 0),
                                                              Receptor.PROFESOR));
    }

    Iterator<Profesor> profesorViejas = viejo.profesores();
    while (profesorViejas.hasNext())
    {
      Profesor auxiliar = profesorViejas.next();
      if (!provisorio.tieneProfesor(auxiliar))
        eliminar.add(auxiliar);
    }

    profesorViejas = provisorio.profesores();
    while (profesorViejas.hasNext())
    {
      Profesor auxiliar = profesorViejas.next();
      if (!viejo.tieneProfesor(auxiliar))
        agregar.add(auxiliar);
    }

  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonAceptarAlumno;
  private javax.swing.JButton jButtonAceptarAsignatura;
  private javax.swing.JButton jButtonAceptarCursada;
  private javax.swing.JButton jButtonAceptarProfesor;
  private javax.swing.JButton jButtonAgradecimientos;
  private javax.swing.JButton jButtonAgregarAlumnoCursada;
  private javax.swing.JButton jButtonAgregarCompetencia;
  private javax.swing.JButton jButtonAgregarCorrelativa;
  private javax.swing.JButton jButtonAgregarHistoria;
  private javax.swing.JButton jButtonAgregarProfesorCursada;
  private javax.swing.JButton jButtonBuscarAlumno;
  private javax.swing.JButton jButtonBuscarAsignatura;
  private javax.swing.JButton jButtonBuscarCursada;
  private javax.swing.JButton jButtonBuscarProfesor;
  private javax.swing.JButton jButtonCambiarAsignaturaCursada;
  private javax.swing.JButton jButtonCancelarAlumno;
  private javax.swing.JButton jButtonCancelarAsignatura;
  private javax.swing.JButton jButtonCancelarCursada;
  private javax.swing.JButton jButtonCancelarProfesor;
  private javax.swing.JButton jButtonEliminarAlumno;
  private javax.swing.JButton jButtonEliminarAlumnoCursada;
  private javax.swing.JButton jButtonEliminarAsignatura;
  private javax.swing.JButton jButtonEliminarCompetencia;
  private javax.swing.JButton jButtonEliminarCorrelativa;
  private javax.swing.JButton jButtonEliminarCursada;
  private javax.swing.JButton jButtonEliminarHistoria;
  private javax.swing.JButton jButtonEliminarProfesor;
  private javax.swing.JButton jButtonEliminarProfesorCursada;
  private javax.swing.JButton jButtonModificarAlumno;
  private javax.swing.JButton jButtonModificarAsignatura;
  private javax.swing.JButton jButtonModificarCursada;
  private javax.swing.JButton jButtonModificarProfesor;
  private javax.swing.JButton jButtonNuevoAlumno;
  private javax.swing.JButton jButtonNuevoAsignatura;
  private javax.swing.JButton jButtonNuevoCursada;
  private javax.swing.JButton jButtonNuevoProfesor;
  private javax.swing.JComboBox<String> jComboBoxDia;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel12;
  private javax.swing.JLabel jLabel13;
  private javax.swing.JLabel jLabel14;
  private javax.swing.JLabel jLabel16;
  private javax.swing.JLabel jLabel17;
  private javax.swing.JLabel jLabel18;
  private javax.swing.JLabel jLabel19;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel20;
  private javax.swing.JLabel jLabel21;
  private javax.swing.JLabel jLabel22;
  private javax.swing.JLabel jLabel23;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JPanel jPanelAlumno;
  private javax.swing.JPanel jPanelAsignatura;
  private javax.swing.JPanel jPanelBuscarAlumno;
  private javax.swing.JPanel jPanelBuscarAsignatura;
  private javax.swing.JPanel jPanelBuscarCursada;
  private javax.swing.JPanel jPanelBuscarProfesor;
  private javax.swing.JPanel jPanelBusquedaProfesor;
  private javax.swing.JPanel jPanelCursada;
  private javax.swing.JPanel jPanelProfesor;
  private javax.swing.JPanel jPanelResultadosAlumno;
  private javax.swing.JPanel jPanelResultadosAsignatura;
  private javax.swing.JPanel jPanelResultadosCursada;
  private javax.swing.JPanel jPanelResultadosProfesor;
  private javax.swing.JPanel jPanelTableBusquedaAlumno;
  private javax.swing.JScrollPane jScrollPane10;
  private javax.swing.JScrollPane jScrollPane11;
  private javax.swing.JScrollPane jScrollPane7;
  private javax.swing.JScrollPane jScrollPane8;
  private javax.swing.JScrollPane jScrollPane9;
  private javax.swing.JScrollPane jScrollPaneBusquedaAlumno;
  private javax.swing.JScrollPane jScrollPaneBusquedaProfesor;
  private javax.swing.JScrollPane jScrollPaneCompetencias;
  private javax.swing.JScrollPane jScrollPaneHistoria;
  private javax.swing.JTabbedPane jTabbedPane;
  private javax.swing.JTable jTableAlumnoAlumno;
  private javax.swing.JTable jTableAlumnosCursada;
  private javax.swing.JTable jTableAsignaturaAsignatura;
  private javax.swing.JTable jTableCompetencia;
  private javax.swing.JTable jTableCorrelativas;
  private javax.swing.JTable jTableCursadaCursada;
  private javax.swing.JTable jTableHistoria;
  private javax.swing.JTable jTableProfesorProfesor;
  private javax.swing.JTable jTableProfesoresCursada;
  private javax.swing.JTextField jTextFieldBuscarAlumno;
  private javax.swing.JTextField jTextFieldBuscarAsignatura;
  private javax.swing.JTextField jTextFieldBuscarCursada;
  private javax.swing.JTextField jTextFieldBuscarProfesor;
  private javax.swing.JTextField jTextFieldDomicilioAlumno;
  private javax.swing.JTextField jTextFieldDomicilioProfesor;
  private javax.swing.JTextField jTextFieldFinCursada;
  private javax.swing.JTextField jTextFieldIDAsignaturaCursada;
  private javax.swing.JTextField jTextFieldIdentificadorAsignatura;
  private javax.swing.JTextField jTextFieldIdentificadorCursada;
  private javax.swing.JTextField jTextFieldInicioCursada;
  private javax.swing.JTextField jTextFieldLegajoAlumno;
  private javax.swing.JTextField jTextFieldLegajoProfesor;
  private javax.swing.JTextField jTextFieldMailAlumno;
  private javax.swing.JTextField jTextFieldMailProfesor;
  private javax.swing.JTextField jTextFieldNombreAlumno;
  private javax.swing.JTextField jTextFieldNombreAsignatura;
  private javax.swing.JTextField jTextFieldNombreAsignaturaCursada;
  private javax.swing.JTextField jTextFieldNombreProfesor;
  private javax.swing.JTextField jTextFieldPeriodoCursada;
  private javax.swing.JTextField jTextFieldTelefonoProfesor;
  // End of variables declaration//GEN-END:variables
}
