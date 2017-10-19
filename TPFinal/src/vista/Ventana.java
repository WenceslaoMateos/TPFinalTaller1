
package vista;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;
import excepciones.NoEncontradoException;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.HeadlessException;

import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.Dia;
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
  private static final String ALUMNO = "Alumno";
  private static final String PROFESOR = "Profesor";
  private static final String ASIGNATURA = "Asignatura";
  private static final String CURSADA = "Cursada";
  private String focus = "Alumno";

  /** Creates new form Ventana */
  public Ventana()
  {
    initComponents();
    this.setResizable(false);
    this.setTitle("Programa de Gestion de asignaturas");
    this.setVisible(true);
    this.jButtonAgregarCorrelativa.setEnabled(false);
    this.jButtonEliminarCorrelativa.setEnabled(false);
    this.jButtonAceptarAsignatura.setEnabled(false);
    this.jButtonCancelarAsignatura.setEnabled(false);
    this.jButtonEliminarHistoria.setEnabled(false);
    this.jButtonEliminarCompetencia.setEnabled(false);
    this.jButtonAgregarHistoria.setEnabled(false);
    this.jButtonAceptarAlumno.setEnabled(false);
    this.jButtonCancelarAlumno.setEnabled(false);
    this.jButtonAgregarCompetencia.setEnabled(false);
    this.jButtonAceptarProfesor.setEnabled(false);
    this.jButtonCancelarProfesor.setEnabled(false);
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
  }

  public void setReceptor(Receptor receptor)
  {
    this.receptor = receptor;
  }

  @Override
  public void valueChanged(ListSelectionEvent e)
  {
    if (e.getValueIsAdjusting())
    {
      DefaultTableModel aux2;
      aux2 = (DefaultTableModel) this.jTableHistoria.getModel();
      aux2.setRowCount(0);
      aux2 = (DefaultTableModel) this.jTableCompetencia.getModel();
      aux2.setRowCount(0);
      aux2 = (DefaultTableModel) this.jTableCorrelativas.getModel();
      aux2.setRowCount(0);
      if (this.focus.equals(Ventana.ALUMNO))
      {
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
          this.jTableHistoria.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          this.jTableHistoria.setRowSelectionAllowed(true);
        }
        catch (NoEncontradoException f)
        {
        }
        catch (ArrayIndexOutOfBoundsException g)
        {
        }
      }
      else if (this.focus.equals(Ventana.PROFESOR))
      {
        Profesor elemento;
        try
        {
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
          this.jTableCompetencia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          this.jTableCompetencia.setRowSelectionAllowed(true);
        }
        catch (NoEncontradoException f)
        {
        }
        catch (ArrayIndexOutOfBoundsException g)
        {
        }
      }
      else if (this.focus.equals(Ventana.ASIGNATURA))
      {
        Asignatura elemento;
        try
        {
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
          this.jTableCorrelativas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          this.jTableCorrelativas.setRowSelectionAllowed(true);
        }
        catch (NoEncontradoException f)
        {
        }
        catch (ArrayIndexOutOfBoundsException g)
        {
        }
      }
      else if (this.focus.equals(Ventana.CURSADA))
      {
        Cursada elemento;
        try
        {
          elemento =
            (Cursada) this.receptor.buscar(this.jTableCursadaCursada.getValueAt(this.jTableCursadaCursada.getSelectedRow(),
                                                                                0), Receptor.CURSADA);
          this.jTextFieldIdentificadorAsignatura.setText(elemento.getIdentificacion());
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
          this.jTableProfesoresCursada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          this.jTableProfesoresCursada.setRowSelectionAllowed(true);

          Iterator<Alumno> alumnos = elemento.alumnos();
          Alumno alu;
          DefaultTableModel modelalu = (DefaultTableModel) this.jTableAlumnosCursada.getModel();
          while (alumnos.hasNext())
          {
            alu = alumnos.next();
            modelalu.addRow(new Object[] { alu.getLegajo(), alu.getApellidoNombre(), alu.getDomicilio(),
                                           alu.getMail() });
          }
          this.jTableAlumnosCursada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          this.jTableAlumnosCursada.setRowSelectionAllowed(true);
        }
        catch (NoEncontradoException f)
        {
        }
        catch (ArrayIndexOutOfBoundsException g)
        {
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

    jDialog1 = new javax.swing.JDialog();
    jDialog2 = new javax.swing.JDialog();
    jDialog3 = new javax.swing.JDialog();
    jDialog4 = new javax.swing.JDialog();
    jTabbedPane = new javax.swing.JTabbedPane();
    jPanelAlumno = new javax.swing.JPanel();
    jPanelBuscarAlumno = new javax.swing.JPanel();
    jButtonBuscarAlumno = new javax.swing.JButton();
    jTextFieldBuscarAlumno = new javax.swing.JTextField();
    jPanel1 = new javax.swing.JPanel();
    jScrollPane3 = new javax.swing.JScrollPane();
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
    jScrollPane4 = new javax.swing.JScrollPane();
    jTableHistoria = new javax.swing.JTable();
    jButtonNuevoAlumno = new javax.swing.JButton();
    jButtonEliminarAlumno = new javax.swing.JButton();
    jButtonAgregarHistoria = new javax.swing.JButton();
    jButtonAceptarAlumno = new javax.swing.JButton();
    jButtonCancelarAlumno = new javax.swing.JButton();
    jButtonModificarAlumno = new javax.swing.JButton();
    jButtonEliminarHistoria = new javax.swing.JButton();
    jPanelProfesor = new javax.swing.JPanel();
    jPanelAlumno1 = new javax.swing.JPanel();
    jPanelBuscarProfesor = new javax.swing.JPanel();
    jButtonBuscarProfesor = new javax.swing.JButton();
    jTextFieldBuscarProfesor = new javax.swing.JTextField();
    jPanel2 = new javax.swing.JPanel();
    jScrollPane5 = new javax.swing.JScrollPane();
    jTableProfesorProfesor = new javax.swing.JTable();
    jPanelResultadosProfesor = new javax.swing.JPanel();
    jLabel2 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    jLabel9 = new javax.swing.JLabel();
    jLabel10 = new javax.swing.JLabel();
    jTextFieldLegajoProfesor = new javax.swing.JTextField();
    jTextFieldNombreProfesor = new javax.swing.JTextField();
    jTextFieldDomicilioProfesor = new javax.swing.JTextField();
    jTextFieldMailProfesor = new javax.swing.JTextField();
    jScrollPane6 = new javax.swing.JScrollPane();
    jTableCompetencia = new javax.swing.JTable();
    jButtonNuevoProfesor = new javax.swing.JButton();
    jButtonEliminarProfesor = new javax.swing.JButton();
    jButtonAgregarCompetencia = new javax.swing.JButton();
    jButtonAceptarProfesor = new javax.swing.JButton();
    jButtonCancelarProfesor = new javax.swing.JButton();
    jButtonModificarProfesor = new javax.swing.JButton();
    jLabel11 = new javax.swing.JLabel();
    jTextFieldTelefonoProfesor = new javax.swing.JTextField();
    jButtonEliminarCompetencia = new javax.swing.JButton();
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
    jTextFieldIdentificadorCursada = new javax.swing.JTextField();
    jScrollPane10 = new javax.swing.JScrollPane();
    jTableProfesoresCursada = new javax.swing.JTable();
    jButtonNuevoCursada = new javax.swing.JButton();
    jButtonEliminarCursada = new javax.swing.JButton();
    jButtonAgregarProfesorCursada = new javax.swing.JButton();
    jButtonAceptarCursada = new javax.swing.JButton();
    jButtonCancelarCursada = new javax.swing.JButton();
    jButtonModificarCursada = new javax.swing.JButton();
    jLabel18 = new javax.swing.JLabel();
    jTextFieldPeriodoCursada = new javax.swing.JTextField();
    jLabel19 = new javax.swing.JLabel();
    jTextFieldNombreAsignaturaCursada = new javax.swing.JTextField();
    jTextFieldIDAsignaturaCursada = new javax.swing.JTextField();
    jLabel20 = new javax.swing.JLabel();
    jTextFieldInicioCursada = new javax.swing.JTextField();
    jLabel21 = new javax.swing.JLabel();
    jLabel22 = new javax.swing.JLabel();
    jTextFieldFinCursada = new javax.swing.JTextField();
    jScrollPane11 = new javax.swing.JScrollPane();
    jTableAlumnosCursada = new javax.swing.JTable();
    jLabel23 = new javax.swing.JLabel();
    jButtonAgregarAlumnoCursada = new javax.swing.JButton();
    jButtonCambiarAsignaturaCursada = new javax.swing.JButton();
    jComboBoxDia = new javax.swing.JComboBox<>();
    jButtonAgradecimientos = new javax.swing.JButton();

    javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
    jDialog1.getContentPane().setLayout(jDialog1Layout);
    jDialog1Layout.setHorizontalGroup(
      jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 400, Short.MAX_VALUE)
    );
    jDialog1Layout.setVerticalGroup(
      jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 300, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
    jDialog2.getContentPane().setLayout(jDialog2Layout);
    jDialog2Layout.setHorizontalGroup(
      jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 400, Short.MAX_VALUE)
    );
    jDialog2Layout.setVerticalGroup(
      jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 300, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
    jDialog3.getContentPane().setLayout(jDialog3Layout);
    jDialog3Layout.setHorizontalGroup(
      jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 400, Short.MAX_VALUE)
    );
    jDialog3Layout.setVerticalGroup(
      jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 300, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
    jDialog4.getContentPane().setLayout(jDialog4Layout);
    jDialog4Layout.setHorizontalGroup(
      jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 400, Short.MAX_VALUE)
    );
    jDialog4Layout.setVerticalGroup(
      jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 300, Short.MAX_VALUE)
    );

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jTabbedPane.addChangeListener(new javax.swing.event.ChangeListener()
    {
      public void stateChanged(javax.swing.event.ChangeEvent evt)
      {
        jTabbedPaneStateChanged(evt);
      }
    });

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
        .addComponent(jTextFieldBuscarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonBuscarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanelBuscarAlumnoLayout.setVerticalGroup(
      jPanelBuscarAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelBuscarAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        .addComponent(jTextFieldBuscarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(jButtonBuscarAlumno))
    );

    jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTableAlumnoAlumno.setModel(new TableModelAlumno());
    jScrollPane3.setViewportView(jTableAlumnoAlumno);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
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
    jScrollPane4.setViewportView(jTableHistoria);

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
          .addComponent(jScrollPane4)
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
        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
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
        .addGroup(jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanelBuscarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(18, 18, 18)
        .addComponent(jPanelResultadosAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addContainerGap())
    );

    jTabbedPane.addTab("Alumno", jPanelAlumno);

    jPanelAlumno1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
        .addComponent(jTextFieldBuscarProfesor)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonBuscarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanelBuscarProfesorLayout.setVerticalGroup(
      jPanelBuscarProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelBuscarProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        .addComponent(jTextFieldBuscarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(jButtonBuscarProfesor))
    );

    jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTableProfesorProfesor.setModel(new TableModelProfesor());
    jScrollPane5.setViewportView(jTableProfesorProfesor);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
    );

    jPanelResultadosProfesor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jLabel2.setText("Legajo");

    jLabel7.setText("Apellido y Nombre");

    jLabel8.setText("Domicilio");

    jLabel9.setText("Mail");

    jLabel10.setText("Competencias:");

    jTextFieldLegajoProfesor.setEditable(false);
    jTextFieldLegajoProfesor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldNombreProfesor.setEditable(false);
    jTextFieldNombreProfesor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldDomicilioProfesor.setEditable(false);
    jTextFieldDomicilioProfesor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldMailProfesor.setEditable(false);
    jTextFieldMailProfesor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTableCompetencia.setModel(new TableModelAsignatura());
    jScrollPane6.setViewportView(jTableCompetencia);

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

    jLabel11.setText("Telefono");

    jTextFieldTelefonoProfesor.setEditable(false);
    jTextFieldTelefonoProfesor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
          .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
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
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jButtonAgregarCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jLabel10)
            .addComponent(jButtonEliminarCompetencia)))
        .addGap(18, 18, 18)
        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jButtonCancelarProfesor)
          .addComponent(jButtonAceptarProfesor))
        .addContainerGap())
    );

    javax.swing.GroupLayout jPanelAlumno1Layout = new javax.swing.GroupLayout(jPanelAlumno1);
    jPanelAlumno1.setLayout(jPanelAlumno1Layout);
    jPanelAlumno1Layout.setHorizontalGroup(
      jPanelAlumno1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelAlumno1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelAlumno1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanelBuscarProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(18, 18, 18)
        .addComponent(jPanelResultadosProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );
    jPanelAlumno1Layout.setVerticalGroup(
      jPanelAlumno1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelAlumno1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelAlumno1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanelResultadosProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(jPanelAlumno1Layout.createSequentialGroup()
            .addComponent(jPanelBuscarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addContainerGap())
    );

    javax.swing.GroupLayout jPanelProfesorLayout = new javax.swing.GroupLayout(jPanelProfesor);
    jPanelProfesor.setLayout(jPanelProfesorLayout);
    jPanelProfesorLayout.setHorizontalGroup(
      jPanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 1135, Short.MAX_VALUE)
      .addGroup(jPanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanelAlumno1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanelProfesorLayout.setVerticalGroup(
      jPanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 566, Short.MAX_VALUE)
      .addGroup(jPanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanelAlumno1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        .addComponent(jTextFieldBuscarAsignatura)
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
      .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
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
        .addGroup(jPanelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanelBuscarAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(18, 18, 18)
        .addComponent(jPanelResultadosAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        .addComponent(jTextFieldBuscarCursada)
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
      .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
    );
    jPanel4Layout.setVerticalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
    );

    jPanelResultadosCursada.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jLabel14.setText("Identificador");

    jLabel17.setText("Profesores");

    jTextFieldIdentificadorCursada.setEditable(false);
    jTextFieldIdentificadorCursada.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

    jLabel18.setText("Periodo");

    jTextFieldPeriodoCursada.setEditable(false);
    jTextFieldPeriodoCursada.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jLabel19.setText("Asignatura");

    jTextFieldNombreAsignaturaCursada.setEditable(false);
    jTextFieldNombreAsignaturaCursada.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTextFieldIDAsignaturaCursada.setEditable(false);
    jTextFieldIDAsignaturaCursada.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jLabel20.setText("Dia");

    jTextFieldInicioCursada.setEditable(false);
    jTextFieldInicioCursada.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jLabel21.setText("Hora Inicio");

    jLabel22.setText("Hora Finalizacion");

    jTextFieldFinCursada.setEditable(false);
    jTextFieldFinCursada.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTableAlumnosCursada.setModel(new TableModelAlumno());
    jScrollPane11.setViewportView(jTableAlumnosCursada);

    jLabel23.setText("Alumnos");

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

    jComboBoxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" }));

    javax.swing.GroupLayout jPanelResultadosCursadaLayout = new javax.swing.GroupLayout(jPanelResultadosCursada);
    jPanelResultadosCursada.setLayout(jPanelResultadosCursadaLayout);
    jPanelResultadosCursadaLayout.setHorizontalGroup(
      jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
          .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButtonAgregarProfesorCursada))
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
                .addComponent(jButtonModificarCursada)))
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
            .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel14)
              .addComponent(jLabel19)
              .addComponent(jLabel18))
            .addGap(17, 17, 17)
            .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jTextFieldIdentificadorCursada)
              .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
                .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jTextFieldPeriodoCursada)
                  .addComponent(jTextFieldIDAsignaturaCursada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
                    .addComponent(jLabel20)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBoxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(14, 14, 14)
                    .addComponent(jLabel21)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextFieldInicioCursada, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel22)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextFieldFinCursada))
                  .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
                    .addComponent(jTextFieldNombreAsignaturaCursada)
                    .addGap(18, 18, 18)
                    .addComponent(jButtonCambiarAsignaturaCursada))))))
          .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
          .addGroup(jPanelResultadosCursadaLayout.createSequentialGroup()
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButtonAgregarAlumnoCursada)))
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
          .addComponent(jButtonAgregarProfesorCursada))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanelResultadosCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel23)
          .addComponent(jButtonAgregarAlumnoCursada))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
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
        .addGroup(jPanelCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanelBuscarCursada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(18, 18, 18)
        .addComponent(jPanelResultadosCursada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );
    jPanelCursadaLayout.setVerticalGroup(
      jPanelCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelCursadaLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
      JOptionPane.showMessageDialog(this, e.getMessage() + ", por favor vuelva a ingresarlo");
    }
  }//GEN-LAST:event_jButtonBuscarAlumnoActionPerformed

  private void jButtonNuevoAlumnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonNuevoAlumnoActionPerformed
  {//GEN-HEADEREND:event_jButtonNuevoAlumnoActionPerformed
    this.accionAceptar = Ventana.NUEVO;
    this.jButtonCancelarAlumnoActionPerformed(evt);
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

    this.jButtonBuscarAlumno.setEnabled(false);
    this.jTextFieldBuscarAlumno.setEditable(false);
    this.jTextFieldBuscarAlumno.setText("");

    DefaultTableModel aux = (DefaultTableModel) this.jTableAlumnoAlumno.getModel();
    aux.setRowCount(0);
    DefaultTableModel aux2 = (DefaultTableModel) this.jTableHistoria.getModel();
    aux2.setRowCount(0);

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
      this.jTextFieldNombreAlumno.setEditable(true);
      this.jTextFieldDomicilioAlumno.setEditable(true);
      this.jTextFieldMailAlumno.setEditable(true);
      this.jButtonAgregarHistoria.setEnabled(true);
      this.jButtonEliminarHistoria.setEnabled(true);
      this.jButtonEliminarAlumno.setEnabled(false);
      this.jButtonNuevoAlumno.setEnabled(false);
      this.jButtonAceptarAlumno.setEnabled(true);
      this.jButtonCancelarAlumno.setEnabled(true);
      this.accionAceptar = Ventana.MODIFICAR;

      this.jButtonBuscarAlumno.setEnabled(false);
      this.jTextFieldBuscarAlumno.setEditable(false);
      this.jTextFieldBuscarAlumno.setText("");

      DefaultTableModel aux = (DefaultTableModel) this.jTableAlumnoAlumno.getModel();
      aux.setRowCount(0);
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
        this.receptor.baja(this.jTextFieldLegajoAlumno.getText(), Receptor.ALUMNO);
        DefaultTableModel aux = (DefaultTableModel) this.jTableHistoria.getModel();
        aux.setRowCount(0);
        DefaultTableModel aux2 = (DefaultTableModel) this.jTableAlumnoAlumno.getModel();
        aux2.setRowCount(0);
        this.jButtonCancelarAlumnoActionPerformed(evt);
      }
      catch (NoEncontradoException e)
      {
        JOptionPane.showMessageDialog(this, e.getMessage());
      }
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione un alumno para poder eliminarlo");
  }//GEN-LAST:event_jButtonEliminarAlumnoActionPerformed

  private void jButtonCancelarAlumnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelarAlumnoActionPerformed
  {//GEN-HEADEREND:event_jButtonCancelarAlumnoActionPerformed
    this.jTextFieldLegajoAlumno.setText("");
    this.jTextFieldNombreAlumno.setText("");
    this.jTextFieldNombreAlumno.setEditable(false);
    this.jTextFieldDomicilioAlumno.setText("");
    this.jTextFieldDomicilioAlumno.setEditable(false);
    this.jTextFieldMailAlumno.setText("");
    this.jTextFieldMailAlumno.setEditable(false);
    this.jButtonAgregarHistoria.setEnabled(false);
    this.jButtonEliminarHistoria.setEnabled(false);
    this.jButtonAceptarAlumno.setEnabled(false);
    this.jButtonEliminarAlumno.setEnabled(true);
    this.jButtonModificarAlumno.setEnabled(true);
    this.jButtonNuevoAlumno.setEnabled(true);
    this.jButtonCancelarAlumno.setEnabled(false);

    this.jButtonBuscarAlumno.setEnabled(true);
    this.jTextFieldBuscarAlumno.setEditable(true);
    this.jTextFieldBuscarAlumno.setText("");

    DefaultTableModel aux = (DefaultTableModel) this.jTableHistoria.getModel();
    aux.setRowCount(0);

  }//GEN-LAST:event_jButtonCancelarAlumnoActionPerformed

  private void jButtonAceptarAlumnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAceptarAlumnoActionPerformed
  {//GEN-HEADEREND:event_jButtonAceptarAlumnoActionPerformed
    try
    {
      DefaultTableModel aux;
      int n, i;
      Asignatura elemento;
      switch (this.accionAceptar)
      {
        case Ventana.NUEVO:
          Alumno nuevo =
            new Alumno(this.jTextFieldNombreAlumno.getText(), this.jTextFieldDomicilioAlumno.getText(),
                       this.jTextFieldMailAlumno.getText());

          aux = (DefaultTableModel) this.jTableHistoria.getModel();
          n = aux.getRowCount();
          for (i = 0; i < n; i++)
          {
            elemento = (Asignatura) this.receptor.buscar(this.jTableHistoria.getValueAt(i, 0), Receptor.ASIGNATURA);
            nuevo.agregarHistoria(elemento);
          }
          this.receptor.alta(nuevo, Receptor.ALUMNO);
          this.jButtonCancelarAlumnoActionPerformed(evt);

          break;
        case Ventana.MODIFICAR:
          Alumno modif =
            new Alumno(this.jTextFieldNombreAlumno.getText(), this.jTextFieldDomicilioAlumno.getText(),
                       this.jTextFieldMailAlumno.getText());
          aux = (DefaultTableModel) this.jTableHistoria.getModel();
          n = aux.getRowCount();

          modif.setLegajo(this.jTextFieldLegajoAlumno.getText());
          Alumno viejo = (Alumno) this.receptor.buscar(modif.getLegajo(), Receptor.ALUMNO);

          ArrayList<Asignatura> nuevaHistoria = new ArrayList<Asignatura>();
          for (i = 0; i < n; i++)
          {
            nuevaHistoria.add((Asignatura) this.receptor.buscar(this.jTableHistoria.getValueAt(i, 0),
                                                                Receptor.ASIGNATURA));
          }
          Iterator<Asignatura> asignaturasViejas = viejo.historiaAcademica();
          while (asignaturasViejas.hasNext())
          {
            Asignatura auxiliar = asignaturasViejas.next();
            if (!nuevaHistoria.contains(auxiliar))
              asignaturasViejas.remove();
            else
              nuevaHistoria.remove(auxiliar);
          }
          Iterator<Asignatura> nuevas = nuevaHistoria.iterator();
          while (nuevas.hasNext())
            viejo.agregarHistoria(nuevas.next());

          this.receptor.modificacion(modif, Receptor.ALUMNO);
          this.jButtonCancelarAlumnoActionPerformed(evt);
          break;
      }
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
      JOptionPane.showMessageDialog(this, e.getMessage() + ", por favor vuelva a ingresarlo");
    }

  }//GEN-LAST:event_jButtonBuscarProfesorActionPerformed

  private void jButtonNuevoProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonNuevoProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonNuevoProfesorActionPerformed
    this.accionAceptar = Ventana.NUEVO;
    this.jButtonCancelarProfesorActionPerformed(evt);
    this.jButtonEliminarProfesor.setEnabled(false);
    this.jButtonModificarProfesor.setEnabled(false);

    this.jButtonAgregarCompetencia.setEnabled(true);
    this.jButtonEliminarCompetencia.setEnabled(true);
    this.jButtonCancelarProfesor.setEnabled(true);
    this.jButtonAceptarProfesor.setEnabled(true);

    this.jTextFieldLegajoProfesor.setText("");
    this.jTextFieldNombreProfesor.setEditable(true);
    this.jTextFieldNombreProfesor.setText("");
    this.jTextFieldDomicilioProfesor.setEditable(true);
    this.jTextFieldDomicilioProfesor.setText("");
    this.jTextFieldMailProfesor.setEditable(true);
    this.jTextFieldMailProfesor.setText("");
    this.jTextFieldTelefonoProfesor.setEditable(true);
    this.jTextFieldTelefonoProfesor.setText("");
    DefaultTableModel aux = (DefaultTableModel) this.jTableProfesorProfesor.getModel();
    aux.setRowCount(0);
    DefaultTableModel aux2 = (DefaultTableModel) this.jTableCompetencia.getModel();
    aux2.setRowCount(0);

    this.jButtonBuscarProfesor.setEnabled(false);
    this.jTextFieldBuscarProfesor.setEditable(false);
    this.jTextFieldBuscarProfesor.setText("");

    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonNuevoProfesorActionPerformed

  private void jButtonEliminarProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonEliminarProfesorActionPerformed
    if (!this.jTextFieldLegajoProfesor
             .getText()
             .equals(""))
    {
      try
      {
        this.receptor.baja(this.jTextFieldLegajoProfesor.getText(), Receptor.PROFESOR);
        this.jButtonCancelarProfesorActionPerformed(evt);
        DefaultTableModel aux = (DefaultTableModel) this.jTableProfesorProfesor.getModel();
        aux.setRowCount(0);
        DefaultTableModel aux2 = (DefaultTableModel) this.jTableCompetencia.getModel();
        aux2.setRowCount(0);
      }
      catch (NoEncontradoException e)
      {
        JOptionPane.showMessageDialog(this, e.getMessage());
      }
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione un profesor para poder eliminarlo");
  }//GEN-LAST:event_jButtonEliminarProfesorActionPerformed

  private void jButtonAgregarCompetenciaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAgregarCompetenciaActionPerformed
  {//GEN-HEADEREND:event_jButtonAgregarCompetenciaActionPerformed
    new DialogAsignatura(this, this.receptor, this.jTableCompetencia, new TableModelAsignatura());  
  }//GEN-LAST:event_jButtonAgregarCompetenciaActionPerformed

  private void jButtonAceptarProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAceptarProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonAceptarProfesorActionPerformed
    try
    {
      DefaultTableModel aux;
      int n, i;
      Asignatura elemento;
      switch (this.accionAceptar)
      {
        case Ventana.NUEVO:

          Profesor nuevo =
            new Profesor(this.jTextFieldNombreProfesor.getText(), this.jTextFieldDomicilioProfesor.getText(),
                         this.jTextFieldMailProfesor.getText(), this.jTextFieldTelefonoProfesor.getText());
          aux = (DefaultTableModel) this.jTableCompetencia.getModel();
          n = aux.getRowCount();
          for (i = 0; i < n; i++)
          {
            elemento = (Asignatura) this.receptor.buscar(this.jTableCompetencia.getValueAt(i, 0), Receptor.ASIGNATURA);
            nuevo.agregarCompetencia(elemento);
          }
          this.receptor.alta(nuevo, Receptor.PROFESOR);
          this.jButtonCancelarProfesorActionPerformed(evt);

          break;
        case Ventana.MODIFICAR:
        Profesor modif =
          new Profesor(this.jTextFieldNombreProfesor.getText(), this.jTextFieldDomicilioProfesor.getText(),
                       this.jTextFieldMailProfesor.getText(), this.jTextFieldTelefonoProfesor.getText());
          aux = (DefaultTableModel) this.jTableCompetencia.getModel();
          n = aux.getRowCount();

          modif.setLegajo(this.jTextFieldLegajoProfesor.getText());
          Profesor viejo = (Profesor) this.receptor.buscar(modif.getLegajo(), Receptor.PROFESOR);

          ArrayList<Asignatura> nuevaCompetencia = new ArrayList<Asignatura>();
          for (i = 0; i < n; i++)
          {
            nuevaCompetencia.add((Asignatura) this.receptor.buscar(this.jTableCompetencia.getValueAt(i, 0),
                                                                Receptor.ASIGNATURA));
          }
          Iterator<Asignatura> asignaturasViejas = viejo.competencias();
          while (asignaturasViejas.hasNext())
          {
            Asignatura auxiliar = asignaturasViejas.next();
            if (!nuevaCompetencia.contains(auxiliar))
              asignaturasViejas.remove();
            else
              nuevaCompetencia.remove(auxiliar);
          }
          Iterator<Asignatura> nuevas = nuevaCompetencia.iterator();
          while (nuevas.hasNext())
            viejo.agregarCompetencia(nuevas.next());

          this.receptor.modificacion(modif, Receptor.PROFESOR);
          this.jButtonCancelarProfesorActionPerformed(evt);
          break;
      }
    }
    catch (NoEncontradoException | ClaveYaExistenteException | DatoInvalidoException e)
    {
      JOptionPane.showMessageDialog(this, e.getMessage());
    }

    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonAceptarProfesorActionPerformed

  private void jButtonCancelarProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelarProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonCancelarProfesorActionPerformed

    this.jButtonEliminarCompetencia.setEnabled(false);
    this.jButtonAgregarCompetencia.setEnabled(false);
    this.jButtonAceptarProfesor.setEnabled(false);
    this.jButtonCancelarProfesor.setEnabled(false);

    this.jTextFieldLegajoProfesor.setText("");
    this.jTextFieldNombreProfesor.setText("");
    this.jTextFieldNombreProfesor.setEditable(false);
    this.jTextFieldDomicilioProfesor.setText("");
    this.jTextFieldDomicilioProfesor.setEditable(false);
    this.jTextFieldMailProfesor.setText("");
    this.jTextFieldMailProfesor.setEditable(false);
    this.jTextFieldTelefonoProfesor.setText("");
    this.jTextFieldTelefonoProfesor.setEditable(false);

    this.jButtonEliminarProfesor.setEnabled(true);
    this.jButtonModificarProfesor.setEnabled(true);
    this.jButtonNuevoProfesor.setEnabled(true);

    DefaultTableModel aux = ((DefaultTableModel) this.jTableCompetencia.getModel());
    aux.setRowCount(0);
    this.jButtonBuscarProfesor.setEnabled(true);
    this.jTextFieldBuscarProfesor.setEditable(true);
    this.jTextFieldBuscarProfesor.setText("");

  }//GEN-LAST:event_jButtonCancelarProfesorActionPerformed

  private void jButtonModificarProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonModificarProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonModificarProfesorActionPerformed
    if (!this.jTextFieldLegajoProfesor
             .getText()
             .equals(""))
    {
      this.jButtonEliminarProfesor.setEnabled(false);
      this.jButtonNuevoProfesor.setEnabled(false);

      this.jTextFieldNombreProfesor.setEditable(true);
      this.jTextFieldDomicilioProfesor.setEditable(true);
      this.jTextFieldMailProfesor.setEditable(true);
      this.jTextFieldTelefonoProfesor.setEditable(true);

      this.jButtonAgregarCompetencia.setEnabled(true);
      this.jButtonEliminarCompetencia.setEnabled(true);
      this.jButtonAceptarProfesor.setEnabled(true);
      this.jButtonCancelarProfesor.setEnabled(true);
      this.accionAceptar = Ventana.MODIFICAR;
      
      DefaultTableModel aux = ((DefaultTableModel) this.jTableProfesorProfesor.getModel());
      aux.setRowCount(0);
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione un profesor para poder modificarlo");
  }//GEN-LAST:event_jButtonModificarProfesorActionPerformed

  private void jTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_jTabbedPaneStateChanged
  {//GEN-HEADEREND:event_jTabbedPaneStateChanged
    JTabbedPane aux = (JTabbedPane) evt.getSource();
    if (aux.getTitleAt(aux.getSelectedIndex()).equals(Ventana.ALUMNO))
      this.focus = Ventana.ALUMNO;
    else if (aux.getTitleAt(aux.getSelectedIndex()).equals(Ventana.PROFESOR))
      this.focus = Ventana.PROFESOR;
    else if (aux.getTitleAt(aux.getSelectedIndex()).equals(Ventana.ASIGNATURA))
      this.focus = Ventana.ASIGNATURA;
    else if (aux.getTitleAt(aux.getSelectedIndex()).equals(Ventana.CURSADA))
      this.focus = Ventana.CURSADA;
    //1 TODO add your handling code here:
  }//GEN-LAST:event_jTabbedPaneStateChanged

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
      JOptionPane.showMessageDialog(this, e.getMessage() + ", por favor vuelva a ingresarlo");
    }
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonBuscarAsignaturaActionPerformed

  private void jButtonNuevoAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonNuevoAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonNuevoAsignaturaActionPerformed
    this.accionAceptar = Ventana.NUEVO;
    this.jButtonEliminarAsignatura.setEnabled(false);
    this.jButtonModificarAsignatura.setEnabled(false);

    this.jButtonAgregarCorrelativa.setEnabled(true);
    this.jButtonEliminarCorrelativa.setEnabled(true);
    this.jButtonAceptarAsignatura.setEnabled(true);
    this.jButtonCancelarAsignatura.setEnabled(true);
    this.jTextFieldNombreAsignatura.setEditable(true);
    this.jTextFieldIdentificadorAsignatura.setText("");
    this.jTextFieldNombreAsignatura.setText("");
    
    DefaultTableModel aux = (DefaultTableModel) this.jTableAsignaturaAsignatura.getModel();
    aux.setRowCount(0);
    DefaultTableModel aux2 = (DefaultTableModel) this.jTableCorrelativas.getModel();
    aux2.setRowCount(0);

    this.jButtonBuscarAsignatura.setEnabled(false);
    this.jTextFieldBuscarAsignatura.setEditable(false);
    this.jTextFieldBuscarAsignatura.setText("");
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonNuevoAsignaturaActionPerformed

  private void jButtonEliminarAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonEliminarAsignaturaActionPerformed
    if (!this.jTextFieldIdentificadorAsignatura
             .getText()
             .equals(""))
    {
      try
      {
        this.receptor.baja(this.jTextFieldIdentificadorAsignatura.getText(), Receptor.ASIGNATURA);
        this.jButtonCancelarAsignaturaActionPerformed(evt);
        DefaultTableModel aux = (DefaultTableModel) this.jTableAsignaturaAsignatura.getModel();
        aux.setRowCount(0);
        DefaultTableModel aux2 = (DefaultTableModel) this.jTableCorrelativas.getModel();
        aux2.setRowCount(0);
      }
      catch (NoEncontradoException e)
      {
        JOptionPane.showMessageDialog(this, e.getMessage());
      }
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione una asignatura para poder eliminarla");
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonEliminarAsignaturaActionPerformed

  private void jButtonAgregarCorrelativaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAgregarCorrelativaActionPerformed
  {//GEN-HEADEREND:event_jButtonAgregarCorrelativaActionPerformed
    new DialogAsignatura(this, this.receptor, this.jTableCorrelativas, new TableModelAsignatura());
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonAgregarCorrelativaActionPerformed

  private void jButtonAceptarAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAceptarAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonAceptarAsignaturaActionPerformed
    switch (this.accionAceptar)
    {
      case Ventana.NUEVO:
        try
        {
          Asignatura nuevo = new Asignatura(this.jTextFieldNombreAsignatura.getText());
          DefaultTableModel aux = (DefaultTableModel) this.jTableCorrelativas.getModel();
          int n = aux.getRowCount();
          int i;
          Asignatura elemento;
          for (i = 0; i < n; i++)
          {
            elemento = (Asignatura) this.receptor.buscar(this.jTableCorrelativas.getValueAt(i, 0), Receptor.ASIGNATURA);
            nuevo.agregarCorrelativa(elemento);
          }
          this.receptor.alta(nuevo, Receptor.ASIGNATURA);
          this.jButtonCancelarAsignaturaActionPerformed(evt);
        }
        catch (NoEncontradoException | ClaveYaExistenteException | DatoInvalidoException e)
        {
          JOptionPane.showMessageDialog(this, e.getMessage());
        }
        break;
      case Ventana.MODIFICAR:
        try
        {
          Asignatura modif = new Asignatura(this.jTextFieldNombreAsignatura.getText());
          DefaultTableModel aux = (DefaultTableModel) this.jTableCompetencia.getModel();
          int n = aux.getRowCount();
          int i;
          Asignatura elemento;
          for (i = 0; i < n; i++)
          {
            elemento = (Asignatura) this.receptor.buscar(this.jTableCorrelativas.getValueAt(i, 0), Receptor.ASIGNATURA);
            modif.agregarCorrelativa(elemento);
          }
          modif.setIdentificacion(this.jTextFieldLegajoProfesor.getText());
          this.receptor.modificacion(modif, Receptor.ASIGNATURA);
          this.jButtonCancelarProfesorActionPerformed(evt);
        }
        catch (DatoInvalidoException | NoEncontradoException | ClaveYaExistenteException e)
        {
          JOptionPane.showMessageDialog(this, e.getMessage());
        }
        break;
    }
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonAceptarAsignaturaActionPerformed

  private void jButtonCancelarAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelarAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonCancelarAsignaturaActionPerformed
    this.jTextFieldIdentificadorAsignatura.setText("");
    this.jTextFieldNombreAsignatura.setText("");
    this.jTextFieldNombreAsignatura.setEditable(false);
    
    this.jButtonAgregarCorrelativa.setEnabled(false);
    this.jButtonEliminarCorrelativa.setEnabled(false);
    
    this.jButtonEliminarAsignatura.setEnabled(true);
    this.jButtonModificarAsignatura.setEnabled(true);
    this.jButtonNuevoAsignatura.setEnabled(true);

    this.jButtonCancelarAsignatura.setEnabled(false);
    this.jButtonAceptarAsignatura.setEnabled(false);
    DefaultTableModel aux = (DefaultTableModel) this.jTableCorrelativas.getModel();
    aux.setRowCount(0);

    this.jButtonBuscarAsignatura.setEnabled(true);
    this.jTextFieldBuscarAsignatura.setEditable(true);
    this.jTextFieldBuscarAsignatura.setText("");
    
  }//GEN-LAST:event_jButtonCancelarAsignaturaActionPerformed

  private void jButtonModificarAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonModificarAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonModificarAsignaturaActionPerformed
    if (!this.jTextFieldIdentificadorAsignatura
             .getText()
             .equals(""))
    {
      this.jTextFieldNombreAsignatura.setEditable(true);
      
      this.jButtonAgregarCorrelativa.setEnabled(true);
      this.jButtonEliminarCorrelativa.setEnabled(true);
      this.jButtonEliminarAsignatura.setEnabled(false);
      this.jButtonNuevoAsignatura.setEnabled(false);
      this.jButtonAceptarAsignatura.setEnabled(true);
      this.jButtonCancelarAsignatura.setEnabled(true);
      this.accionAceptar = Ventana.MODIFICAR;
      
      DefaultTableModel aux = (DefaultTableModel) this.jTableAsignaturaAsignatura.getModel();
      aux.setRowCount(0);

      this.jButtonBuscarAsignatura.setEnabled(false);
      this.jTextFieldBuscarAsignatura.setEditable(false);
      this.jTextFieldBuscarAsignatura.setText("");
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione una asignatura para poder modificarlo");
    // TODO add your handling code here:
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
      JOptionPane.showMessageDialog(this, e.getMessage() + ", por favor vuelva a ingresarlo");
    }
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonBuscarCursadaActionPerformed

  private void jButtonNuevoCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonNuevoCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonNuevoCursadaActionPerformed
    this.accionAceptar = Ventana.NUEVO;
    this.jButtonEliminarCursada.setEnabled(false);
    this.jButtonModificarCursada.setEnabled(false);
    this.jButtonAgregarAlumnoCursada.setEnabled(true);
    this.jButtonAgregarProfesorCursada.setEnabled(true);
    this.jButtonCancelarCursada.setEnabled(true);
    this.jButtonAceptarCursada.setEnabled(true);
    this.jButtonAgregarAlumnoCursada.setEnabled(true);
    this.jButtonAgregarProfesorCursada.setEnabled(true);

    this.jTextFieldIdentificadorCursada.setText("");
    this.jTextFieldIDAsignaturaCursada.setText("");
    this.jTextFieldNombreAsignaturaCursada.setText("");
    this.jTextFieldPeriodoCursada.setEditable(true);
    this.jTextFieldPeriodoCursada.setText("");
    this.jComboBoxDia.setEditable(true);
    this.jTextFieldInicioCursada.setEditable(true);
    this.jTextFieldInicioCursada.setText("");
    this.jTextFieldFinCursada.setEditable(true);
    this.jTextFieldFinCursada.setText("");
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonNuevoCursadaActionPerformed

  private void jButtonEliminarCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonEliminarCursadaActionPerformed
    if (!this.jTextFieldIdentificadorCursada
             .getText()
             .equals(""))
    {
      try
      {
        this.receptor.baja(this.jTextFieldIdentificadorCursada.getText(), Receptor.CURSADA);
      }
      catch (NoEncontradoException e)
      {
        JOptionPane.showMessageDialog(this, e.getMessage());
      }
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione una asignatura para poder eliminarla");
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonEliminarCursadaActionPerformed

  private void jButtonAgregarProfesorCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAgregarProfesorCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonAgregarProfesorCursadaActionPerformed
    new DialogProfesor(this, this.receptor, this.jTableProfesoresCursada, new TableModelProfesor());
  }//GEN-LAST:event_jButtonAgregarProfesorCursadaActionPerformed

  private void jButtonAceptarCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAceptarCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonAceptarCursadaActionPerformed
    switch (this.accionAceptar)
    {
      case Ventana.NUEVO:
        try
        {
          Cursada nuevo =
            new Cursada((Asignatura) this.receptor.buscar(this.jTextFieldIDAsignaturaCursada.getText(),
                                                          Receptor.ASIGNATURA), this.jTextFieldPeriodoCursada.getText(),
                        Dia.parseDia(this.jComboBoxDia.getItemAt(this.jComboBoxDia.getSelectedIndex())),
                        this.jTextFieldInicioCursada.getText(), this.jTextFieldFinCursada.getText());
          DefaultTableModel aux = (DefaultTableModel) this.jTableAlumnosCursada.getModel();
          int n = aux.getRowCount();
          int i;
          Alumno alu;
          for (i = 0; i < n; i++)
          {
            alu = (Alumno) this.receptor.buscar(this.jTableAlumnosCursada.getValueAt(i, 0), Receptor.ALUMNO);
            nuevo.altaAlumno(alu);
          }
          aux = (DefaultTableModel) this.jTableProfesoresCursada.getModel();
          n = aux.getRowCount();
          Profesor prof;
          for (i = 0; i < n; i++)
          {
            prof = (Profesor) this.receptor.buscar(this.jTableProfesoresCursada.getValueAt(i, 0), Receptor.PROFESOR);
            nuevo.altaProfesor(prof);
          }
          this.receptor.alta(nuevo, Receptor.CURSADA);
          this.jButtonCancelarAsignaturaActionPerformed(evt);
        }
        catch (NoEncontradoException | ClaveYaExistenteException | DatoInvalidoException e)
        {
          JOptionPane.showMessageDialog(this, e.getMessage());
        }
        break;
      case Ventana.MODIFICAR:
        try
        {
          Cursada nuevo =
            new Cursada((Asignatura) this.receptor.buscar(this.jTextFieldIDAsignaturaCursada.getText(),
                                                          Receptor.ASIGNATURA), this.jTextFieldPeriodoCursada.getText(),
                        Dia.parseDia(this.jComboBoxDia.getItemAt(this.jComboBoxDia.getSelectedIndex())),
                        this.jTextFieldInicioCursada.getText(), this.jTextFieldFinCursada.getText());
          DefaultTableModel aux = (DefaultTableModel) this.jTableAlumnosCursada.getModel();
          int n = aux.getRowCount();
          int i;
          Alumno alu;
          for (i = 0; i < n; i++)
          {
            alu = (Alumno) this.receptor.buscar(this.jTableAlumnosCursada.getValueAt(i, 0), Receptor.ALUMNO);
            nuevo.altaAlumno(alu);
          }
          aux = (DefaultTableModel) this.jTableProfesoresCursada.getModel();
          n = aux.getRowCount();
          Profesor prof;
          for (i = 0; i < n; i++)
          {
            prof = (Profesor) this.receptor.buscar(this.jTableProfesoresCursada.getValueAt(i, 0), Receptor.PROFESOR);
            nuevo.altaProfesor(prof);
          }
          this.receptor.modificacion(nuevo, Receptor.CURSADA);
          this.jButtonCancelarAsignaturaActionPerformed(evt);
        }
        catch (NoEncontradoException | ClaveYaExistenteException | DatoInvalidoException e)
        {
          JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonAceptarCursadaActionPerformed

  private void jButtonCancelarCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelarCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonCancelarCursadaActionPerformed
    this.jTextFieldIdentificadorCursada.setText("");
    this.jTextFieldIDAsignaturaCursada.setText("");
    this.jTextFieldNombreAsignaturaCursada.setText("");
    this.jTextFieldPeriodoCursada.setText("");
    this.jTextFieldInicioCursada.setText("");
    this.jTextFieldFinCursada.setText("");

    this.jTextFieldPeriodoCursada.setEditable(false);
    this.jComboBoxDia.setEditable(false);
    this.jTextFieldInicioCursada.setEditable(false);
    this.jTextFieldFinCursada.setEditable(false);

    this.jButtonAgregarAlumnoCursada.setEnabled(false);
    this.jButtonAgregarProfesorCursada.setEnabled(false);
    this.jButtonAceptarCursada.setEnabled(false);
    this.jButtonEliminarCursada.setEnabled(true);
    this.jButtonModificarCursada.setEnabled(true);
    this.jButtonNuevoCursada.setEnabled(true);
    this.jButtonCancelarCursada.setEnabled(false);
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonCancelarCursadaActionPerformed

  private void jButtonModificarCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonModificarCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonModificarCursadaActionPerformed
    if (!this.jTextFieldIdentificadorCursada
             .getText()
             .equals(""))
    {
      this.jTextFieldPeriodoCursada.setEditable(true);
      this.jComboBoxDia.setEditable(true);
      this.jTextFieldInicioCursada.setEditable(true);
      this.jTextFieldFinCursada.setEditable(true);

      this.jButtonAgregarAlumnoCursada.setEnabled(true);
      this.jButtonAgregarProfesorCursada.setEnabled(true);
      this.jButtonEliminarCursada.setEnabled(true);
      this.jButtonNuevoCursada.setEnabled(true);
      this.jButtonAceptarCursada.setEnabled(true);
      this.jButtonCancelarCursada.setEnabled(true);
      this.accionAceptar = Ventana.MODIFICAR;
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione un alumno para poder modificarlo");
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonModificarCursadaActionPerformed

  private void jButtonAgregarAlumnoCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAgregarAlumnoCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonAgregarAlumnoCursadaActionPerformed
    new DialogAlumno(this, this.receptor, this.jTableAlumnosCursada, new TableModelAlumno());
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonAgregarAlumnoCursadaActionPerformed

  private void jButtonCambiarAsignaturaCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCambiarAsignaturaCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonCambiarAsignaturaCursadaActionPerformed

    new DialogAsignaturaCursada(this, this.receptor, this.jTextFieldIDAsignaturaCursada,
                                this.jTextFieldNombreAsignaturaCursada);
    //new DialogAsignatura(this, this.receptor, this.jTableCorrelativas, new TableModelAsignatura());
  }//GEN-LAST:event_jButtonCambiarAsignaturaCursadaActionPerformed

  private void jButtonEliminarHistoriaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarHistoriaActionPerformed
  {//GEN-HEADEREND:event_jButtonEliminarHistoriaActionPerformed
    if (this.jTableHistoria.getSelectedRow() != -1)
    {
      TableModelAsignatura aux = (TableModelAsignatura) this.jTableHistoria.getModel();
      aux.eliminarFila(this.jTableHistoria.getSelectedRow());
      this.jTableHistoria.repaint();
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione una asignatura para poder eliminarla de la historia");
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonEliminarHistoriaActionPerformed

  private void jButtonEliminarCompetenciaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarCompetenciaActionPerformed
  {//GEN-HEADEREND:event_jButtonEliminarCompetenciaActionPerformed
    if (this.jTableCompetencia.getSelectedRow() != -1)
    {
      TableModelAsignatura aux = (TableModelAsignatura) this.jTableCompetencia.getModel();
      aux.eliminarFila(this.jTableCompetencia.getSelectedRow());
      this.jTableCompetencia.repaint();
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione una asignatura para poder eliminarla de la historia");
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonEliminarCompetenciaActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[])
  {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
    try
    {
      for (javax.swing.UIManager.LookAndFeelInfo info: javax.swing
                                                            .UIManager
                                                            .getInstalledLookAndFeels())
      {
        if ("Nimbus".equals(info.getName()))
        {
          javax.swing
               .UIManager
               .setLookAndFeel(info.getClassName());
          break;
        }
      }
    }
    catch (ClassNotFoundException ex)
    {
      java.util
          .logging
          .Logger
          .getLogger(Ventana.class.getName())
          .log(java.util
                   .logging
                   .Level
                   .SEVERE, null, ex);
    }
    catch (InstantiationException ex)
    {
      java.util
          .logging
          .Logger
          .getLogger(Ventana.class.getName())
          .log(java.util
                   .logging
                   .Level
                   .SEVERE, null, ex);
    }
    catch (IllegalAccessException ex)
    {
      java.util
          .logging
          .Logger
          .getLogger(Ventana.class.getName())
          .log(java.util
                   .logging
                   .Level
                   .SEVERE, null, ex);
    }
    catch (javax.swing.UnsupportedLookAndFeelException ex)
    {
      java.util
          .logging
          .Logger
          .getLogger(Ventana.class.getName())
          .log(java.util
                   .logging
                   .Level
                   .SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
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
  private javax.swing.JButton jButtonEliminarAsignatura;
  private javax.swing.JButton jButtonEliminarCompetencia;
  private javax.swing.JButton jButtonEliminarCorrelativa;
  private javax.swing.JButton jButtonEliminarCursada;
  private javax.swing.JButton jButtonEliminarHistoria;
  private javax.swing.JButton jButtonEliminarProfesor;
  private javax.swing.JButton jButtonModificarAlumno;
  private javax.swing.JButton jButtonModificarAsignatura;
  private javax.swing.JButton jButtonModificarCursada;
  private javax.swing.JButton jButtonModificarProfesor;
  private javax.swing.JButton jButtonNuevoAlumno;
  private javax.swing.JButton jButtonNuevoAsignatura;
  private javax.swing.JButton jButtonNuevoCursada;
  private javax.swing.JButton jButtonNuevoProfesor;
  private javax.swing.JComboBox<String> jComboBoxDia;
  private javax.swing.JDialog jDialog1;
  private javax.swing.JDialog jDialog2;
  private javax.swing.JDialog jDialog3;
  private javax.swing.JDialog jDialog4;
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
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JPanel jPanelAlumno;
  private javax.swing.JPanel jPanelAlumno1;
  private javax.swing.JPanel jPanelAsignatura;
  private javax.swing.JPanel jPanelBuscarAlumno;
  private javax.swing.JPanel jPanelBuscarAsignatura;
  private javax.swing.JPanel jPanelBuscarCursada;
  private javax.swing.JPanel jPanelBuscarProfesor;
  private javax.swing.JPanel jPanelCursada;
  private javax.swing.JPanel jPanelProfesor;
  private javax.swing.JPanel jPanelResultadosAlumno;
  private javax.swing.JPanel jPanelResultadosAsignatura;
  private javax.swing.JPanel jPanelResultadosCursada;
  private javax.swing.JPanel jPanelResultadosProfesor;
  private javax.swing.JScrollPane jScrollPane10;
  private javax.swing.JScrollPane jScrollPane11;
  private javax.swing.JScrollPane jScrollPane3;
  private javax.swing.JScrollPane jScrollPane4;
  private javax.swing.JScrollPane jScrollPane5;
  private javax.swing.JScrollPane jScrollPane6;
  private javax.swing.JScrollPane jScrollPane7;
  private javax.swing.JScrollPane jScrollPane8;
  private javax.swing.JScrollPane jScrollPane9;
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
