
package vista;

import excepciones.ClaveYaExistenteException;
import excepciones.DatoInvalidoException;
import excepciones.NoEncontradoException;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.HeadlessException;

import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import modelo.Alumno;
import modelo.Asignatura;
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
  }

  public void setReceptor(Receptor receptor)
  {
    this.receptor = receptor;
  }

  @Override
  public void valueChanged(ListSelectionEvent e)
  {
    if (this.focus.equals(Ventana.ALUMNO))
    {
      Alumno elemento;
      try
      {
        elemento =
          (Alumno) this.receptor.buscar(this.jTableAlumnoAlumno.getValueAt(this.jTableAlumnoAlumno.getSelectedRow(), 0),
                                        Receptor.ALUMNO);
        this.jTextFieldLegajoAlumno.setText(elemento.getLegajo());
        this.jTextFieldNombreAlumno.setText(elemento.getApellidoNombre());
        this.jTextFieldDomicilioAlumno.setText(elemento.getDomicilio());
        this.jTextFieldMailAlumno.setText(elemento.getMail());

        Iterator<Asignatura> asignaturas = elemento.historiaAcademica();
        Asignatura aux;
        DefaultTableModel model = (DefaultTableModel) this.jTableHistoria.getModel();
        int n = model.getRowCount();
        int i;
        for (i = n; i > 0; i--)
          model.removeRow(i);
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
        int n = model.getRowCount();
        int i;
        for (i = n; i > 0; i--)
          model.removeRow(i);
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
      ;
    }
    else if (this.focus.equals(Ventana.CURSADA))
    {
      ;
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
    jButtonAgregarCorrelativas = new javax.swing.JButton();
    jButtonAceptarAsignatura = new javax.swing.JButton();
    jButtonCancelarAsignatura = new javax.swing.JButton();
    jButtonModificarAsignatura = new javax.swing.JButton();
    jPanel3 = new javax.swing.JPanel();
    jScrollPane8 = new javax.swing.JScrollPane();
    jTableAsignaturaAsignatura = new javax.swing.JTable();
    jPanelCursada = new javax.swing.JPanel();
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

    jTableAlumnoAlumno.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][]
      {

      },
      new String []
      {
        "Legajo", "Apellido y Nombre", "Domicilio", "Mail"
      }
    )
    {
      Class[] types = new Class []
      {
        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean []
      {
        false, false, false, false
      };

      public Class getColumnClass(int columnIndex)
      {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex)
      {
        return canEdit [columnIndex];
      }
    });
    jScrollPane3.setViewportView(jTableAlumnoAlumno);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        .addContainerGap())
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

    jTableHistoria.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][]
      {

      },
      new String []
      {
        "Identificador", "Nombre"
      }
    )
    {
      Class[] types = new Class []
      {
        java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean []
      {
        false, false
      };

      public Class getColumnClass(int columnIndex)
      {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex)
      {
        return canEdit [columnIndex];
      }
    });
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
                .addComponent(jButtonAgregarHistoria))))
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
        .addGroup(jPanelResultadosAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanelResultadosAlumnoLayout.createSequentialGroup()
            .addComponent(jLabel6)
            .addGap(18, 18, 18))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelResultadosAlumnoLayout.createSequentialGroup()
            .addComponent(jButtonAgregarHistoria)
            .addGap(2, 2, 2)))
        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addGroup(jPanelResultadosAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButtonAceptarAlumno)
          .addComponent(jButtonCancelarAlumno))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    jTableProfesorProfesor.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][]
      {

      },
      new String []
      {
        "Legajo", "Apellido y Nombre", "Domicilio", "Mail", "Telefono"
      }
    )
    {
      Class[] types = new Class []
      {
        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean []
      {
        false, false, false, false, false
      };

      public Class getColumnClass(int columnIndex)
      {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex)
      {
        return canEdit [columnIndex];
      }
    });
    jScrollPane5.setViewportView(jTableProfesorProfesor);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
        .addContainerGap())
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

    jTableCompetencia.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][]
      {

      },
      new String []
      {
        "Identificador", "Nombre"
      }
    )
    {
      Class[] types = new Class []
      {
        java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean []
      {
        false, false
      };

      public Class getColumnClass(int columnIndex)
      {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex)
      {
        return canEdit [columnIndex];
      }
    });
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
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButtonAgregarCompetencia)))
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
        .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel10)
          .addComponent(jButtonAgregarCompetencia))
        .addGap(18, 18, 18)
        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addGroup(jPanelResultadosProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jButtonCancelarProfesor)
          .addComponent(jButtonAceptarProfesor))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
      .addGap(0, 506, Short.MAX_VALUE)
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

    jTableCorrelativas.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][]
      {

      },
      new String []
      {
        "Identificador", "Nombre"
      }
    )
    {
      Class[] types = new Class []
      {
        java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean []
      {
        false, false
      };

      public Class getColumnClass(int columnIndex)
      {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex)
      {
        return canEdit [columnIndex];
      }
    });
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

    jButtonAgregarCorrelativas.setText("Agregar...");
    jButtonAgregarCorrelativas.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAgregarCorrelativasActionPerformed(evt);
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

    javax.swing.GroupLayout jPanelResultadosAsignaturaLayout = new javax.swing.GroupLayout(jPanelResultadosAsignatura);
    jPanelResultadosAsignatura.setLayout(jPanelResultadosAsignaturaLayout);
    jPanelResultadosAsignaturaLayout.setHorizontalGroup(
      jPanelResultadosAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelResultadosAsignaturaLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelResultadosAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanelResultadosAsignaturaLayout.createSequentialGroup()
            .addGroup(jPanelResultadosAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(jPanelResultadosAsignaturaLayout.createSequentialGroup()
                .addComponent(jButtonAceptarAsignatura)
                .addGap(18, 18, 18)
                .addComponent(jButtonCancelarAsignatura))
              .addGroup(jPanelResultadosAsignaturaLayout.createSequentialGroup()
                .addComponent(jButtonNuevoAsignatura)
                .addGap(18, 18, 18)
                .addComponent(jButtonEliminarAsignatura)
                .addGap(18, 18, 18)
                .addComponent(jButtonModificarAsignatura)))
            .addGap(0, 0, Short.MAX_VALUE))
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
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButtonAgregarCorrelativas)))
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
        .addGap(90, 90, 90)
        .addGroup(jPanelResultadosAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel16)
          .addComponent(jButtonAgregarCorrelativas))
        .addGap(18, 18, 18)
        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addGroup(jPanelResultadosAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jButtonCancelarAsignatura)
          .addComponent(jButtonAceptarAsignatura))
        .addContainerGap(26, Short.MAX_VALUE))
    );

    jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTableAsignaturaAsignatura.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][]
      {

      },
      new String []
      {
        "Legajo", "Apellido y Nombre", "Domicilio", "Mail", "Telefono"
      }
    )
    {
      Class[] types = new Class []
      {
        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean []
      {
        false, false, false, false, false
      };

      public Class getColumnClass(int columnIndex)
      {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex)
      {
        return canEdit [columnIndex];
      }
    });
    jScrollPane8.setViewportView(jTableAsignaturaAsignatura);

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
    );
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
        .addContainerGap())
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

    javax.swing.GroupLayout jPanelCursadaLayout = new javax.swing.GroupLayout(jPanelCursada);
    jPanelCursada.setLayout(jPanelCursadaLayout);
    jPanelCursadaLayout.setHorizontalGroup(
      jPanelCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 1135, Short.MAX_VALUE)
    );
    jPanelCursadaLayout.setVerticalGroup(
      jPanelCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 506, Short.MAX_VALUE)
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
    this.jButtonEliminarAlumno.setEnabled(false);
    this.jButtonModificarAlumno.setEnabled(false);
    this.jButtonAgregarHistoria.setEnabled(true);
    this.jButtonCancelarAlumno.setEnabled(true);
    this.jTextFieldNombreAlumno.setEditable(true);
    this.jTextFieldDomicilioAlumno.setEditable(true);
    this.jButtonAceptarAlumno.setEnabled(true);
    this.jTextFieldMailAlumno.setEditable(true);
    this.jTextFieldLegajoAlumno.setText("");
    this.jTextFieldNombreAlumno.setText("");
    this.jTextFieldDomicilioAlumno.setText("");
    this.jTextFieldMailAlumno.setText("");
  }//GEN-LAST:event_jButtonNuevoAlumnoActionPerformed

  private void jButtonAgregarHistoriaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAgregarHistoriaActionPerformed
  {//GEN-HEADEREND:event_jButtonAgregarHistoriaActionPerformed
    new DialogAsignatura(this,this.receptor, this.jTableHistoria, new TableModelAsignatura());  
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
      this.jButtonEliminarAlumno.setEnabled(true);
      this.jButtonNuevoAlumno.setEnabled(true);
      this.jButtonAceptarAlumno.setEnabled(true);
      this.jButtonCancelarAlumno.setEnabled(true);
      this.accionAceptar = Ventana.MODIFICAR;
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
    this.jButtonAceptarAlumno.setEnabled(false);
    this.jButtonEliminarAlumno.setEnabled(true);
    this.jButtonModificarAlumno.setEnabled(true);
    this.jButtonNuevoAlumno.setEnabled(true);
    this.jButtonCancelarAlumno.setEnabled(false);
  }//GEN-LAST:event_jButtonCancelarAlumnoActionPerformed

  private void jButtonAceptarAlumnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAceptarAlumnoActionPerformed
  {//GEN-HEADEREND:event_jButtonAceptarAlumnoActionPerformed
    switch (this.accionAceptar)
    {
      case Ventana.NUEVO:
        try
        {
          Alumno nuevo =
            new Alumno(this.jTextFieldNombreAlumno.getText(), this.jTextFieldDomicilioAlumno.getText(),
                       this.jTextFieldMailAlumno.getText());
          int n = this.jTableHistoria.getRowCount();
          int i;
          Asignatura elemento;
          for (i = 1; i <= n; i++)
          {
            elemento = (Asignatura) this.receptor.buscar(this.jTableHistoria.getValueAt(i, 0), Receptor.ASIGNATURA);
            nuevo.agregarHistoria(elemento);
          }
          this.receptor.alta(nuevo, Receptor.ALUMNO);
          this.jButtonCancelarAlumnoActionPerformed(evt);
        }
        catch (NoEncontradoException | ClaveYaExistenteException | DatoInvalidoException e)
        {
          JOptionPane.showMessageDialog(this, e.getMessage());
        }
        break;
      case Ventana.MODIFICAR:
        try
        {
          Alumno modif =
            new Alumno(this.jTextFieldNombreAlumno.getText(), this.jTextFieldDomicilioAlumno.getText(),
                       this.jTextFieldMailAlumno.getText());
          int n = this.jTableHistoria.getRowCount();
          int i;
          Asignatura elemento;
          for (i = 1; i <= n; i++)
          {
            elemento = (Asignatura) this.receptor.buscar(this.jTableHistoria.getValueAt(i, 0), Receptor.ASIGNATURA);
            modif.agregarHistoria(elemento);
          }
          modif.setLegajo(this.jTextFieldLegajoAlumno.getText());
          this.receptor.modificacion(modif, Receptor.ALUMNO);
          this.jButtonCancelarAlumnoActionPerformed(evt);
        }
        catch (DatoInvalidoException | NoEncontradoException | ClaveYaExistenteException e)
        {
          JOptionPane.showMessageDialog(this, e.getMessage());
        }
        break;
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
    this.jButtonEliminarProfesor.setEnabled(false);
    this.jButtonModificarProfesor.setEnabled(false);
    this.jButtonAgregarCompetencia.setEnabled(true);
    this.jButtonCancelarProfesor.setEnabled(true);
    this.jTextFieldNombreProfesor.setEditable(true);
    this.jTextFieldDomicilioProfesor.setEditable(true);
    this.jButtonAceptarProfesor.setEnabled(true);
    this.jTextFieldMailProfesor.setEditable(true);
    this.jTextFieldTelefonoProfesor.setEditable(true);
    this.jTextFieldLegajoProfesor.setText("");
    this.jTextFieldNombreProfesor.setText("");
    this.jTextFieldDomicilioProfesor.setText("");
    this.jTextFieldMailProfesor.setText("");
    this.jTextFieldTelefonoProfesor.setText("");
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
      }
      catch (NoEncontradoException e)
      {
        JOptionPane.showMessageDialog(this, e.getMessage());
      }
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione un alumno para poder eliminarlo");
  }//GEN-LAST:event_jButtonEliminarProfesorActionPerformed

  private void jButtonAgregarCompetenciaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAgregarCompetenciaActionPerformed
  {//GEN-HEADEREND:event_jButtonAgregarCompetenciaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonAgregarCompetenciaActionPerformed

  private void jButtonAceptarProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAceptarProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonAceptarProfesorActionPerformed
    switch (this.accionAceptar)
    {
      case Ventana.NUEVO:
        try
        {
          Profesor nuevo =
            new Profesor(this.jTextFieldNombreProfesor.getText(), this.jTextFieldDomicilioProfesor.getText(),
                         this.jTextFieldMailProfesor.getText(), this.jTextFieldTelefonoProfesor.getText());
          int n = this.jTableCompetencia.getRowCount();
          int i;
          Asignatura elemento;
          for (i = 1; i <= n; i++)
          {
            elemento = (Asignatura) this.receptor.buscar(this.jTableCompetencia.getValueAt(i, 0), Receptor.ASIGNATURA);
            nuevo.agregarCompetencia(elemento);
          }
          this.receptor.alta(nuevo, Receptor.PROFESOR);
          this.jButtonCancelarProfesorActionPerformed(evt);
        }
        catch (NoEncontradoException | ClaveYaExistenteException | DatoInvalidoException e)
        {
          JOptionPane.showMessageDialog(this, e.getMessage());
        }
        break;
      case Ventana.MODIFICAR:
        try
        {
          Profesor modif =
            new Profesor(this.jTextFieldNombreProfesor.getText(), this.jTextFieldDomicilioProfesor.getText(),
                         this.jTextFieldMailProfesor.getText(), this.jTextFieldTelefonoProfesor.getText());
          int n = this.jTableCompetencia.getRowCount();
          int i;
          Asignatura elemento;
          for (i = 1; i <= n; i++)
          {
            elemento = (Asignatura) this.receptor.buscar(this.jTableCompetencia.getValueAt(i, 0), Receptor.ASIGNATURA);
            modif.agregarCompetencia(elemento);
          }
          modif.setLegajo(this.jTextFieldLegajoProfesor.getText());
          this.receptor.modificacion(modif, Receptor.PROFESOR);
          this.jButtonCancelarProfesorActionPerformed(evt);
        }
        catch (DatoInvalidoException | NoEncontradoException | ClaveYaExistenteException e)
        {
          JOptionPane.showMessageDialog(this, e.getMessage());
        }
        break;
    }
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonAceptarProfesorActionPerformed

  private void jButtonCancelarProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelarProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonCancelarProfesorActionPerformed
    this.jTextFieldLegajoProfesor.setText("");
    this.jTextFieldNombreProfesor.setText("");
    this.jTextFieldNombreProfesor.setEditable(false);
    this.jTextFieldDomicilioProfesor.setText("");
    this.jTextFieldDomicilioProfesor.setEditable(false);
    this.jTextFieldMailProfesor.setText("");
    this.jTextFieldMailProfesor.setEditable(false);
    this.jTextFieldTelefonoProfesor.setText("");
    this.jTextFieldTelefonoProfesor.setEditable(false);
    this.jButtonAgregarCompetencia.setEnabled(false);
    this.jButtonAceptarProfesor.setEnabled(false);
    this.jButtonEliminarProfesor.setEnabled(true);
    this.jButtonModificarProfesor.setEnabled(true);
    this.jButtonNuevoProfesor.setEnabled(true);
    this.jButtonCancelarProfesor.setEnabled(false);

  }//GEN-LAST:event_jButtonCancelarProfesorActionPerformed

  private void jButtonModificarProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonModificarProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonModificarProfesorActionPerformed
    if (!this.jTextFieldLegajoProfesor
             .getText()
             .equals(""))
    {
      this.jTextFieldNombreProfesor.setEditable(true);
      this.jTextFieldDomicilioProfesor.setEditable(true);
      this.jTextFieldMailProfesor.setEditable(true);
      this.jTextFieldTelefonoProfesor.setEditable(true);
      this.jButtonAgregarCompetencia.setEnabled(true);
      this.jButtonEliminarProfesor.setEnabled(true);
      this.jButtonNuevoProfesor.setEnabled(true);
      this.jButtonAceptarProfesor.setEnabled(true);
      this.jButtonCancelarProfesor.setEnabled(true);
      this.accionAceptar = Ventana.MODIFICAR;
    }
    else
      JOptionPane.showMessageDialog(this, "Seleccione un alumno para poder modificarlo");
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
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonBuscarAsignaturaActionPerformed

  private void jButtonNuevoAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonNuevoAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonNuevoAsignaturaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonNuevoAsignaturaActionPerformed

  private void jButtonEliminarAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonEliminarAsignaturaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonEliminarAsignaturaActionPerformed

  private void jButtonAgregarCorrelativasActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAgregarCorrelativasActionPerformed
  {//GEN-HEADEREND:event_jButtonAgregarCorrelativasActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonAgregarCorrelativasActionPerformed

  private void jButtonAceptarAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAceptarAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonAceptarAsignaturaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonAceptarAsignaturaActionPerformed

  private void jButtonCancelarAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelarAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonCancelarAsignaturaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonCancelarAsignaturaActionPerformed

  private void jButtonModificarAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonModificarAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonModificarAsignaturaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonModificarAsignaturaActionPerformed

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
  private javax.swing.JButton jButtonAceptarProfesor;
  private javax.swing.JButton jButtonAgradecimientos;
  private javax.swing.JButton jButtonAgregarCompetencia;
  private javax.swing.JButton jButtonAgregarCorrelativas;
  private javax.swing.JButton jButtonAgregarHistoria;
  private javax.swing.JButton jButtonBuscarAlumno;
  private javax.swing.JButton jButtonBuscarAsignatura;
  private javax.swing.JButton jButtonBuscarProfesor;
  private javax.swing.JButton jButtonCancelarAlumno;
  private javax.swing.JButton jButtonCancelarAsignatura;
  private javax.swing.JButton jButtonCancelarProfesor;
  private javax.swing.JButton jButtonEliminarAlumno;
  private javax.swing.JButton jButtonEliminarAsignatura;
  private javax.swing.JButton jButtonEliminarProfesor;
  private javax.swing.JButton jButtonModificarAlumno;
  private javax.swing.JButton jButtonModificarAsignatura;
  private javax.swing.JButton jButtonModificarProfesor;
  private javax.swing.JButton jButtonNuevoAlumno;
  private javax.swing.JButton jButtonNuevoAsignatura;
  private javax.swing.JButton jButtonNuevoProfesor;
  private javax.swing.JDialog jDialog1;
  private javax.swing.JDialog jDialog2;
  private javax.swing.JDialog jDialog3;
  private javax.swing.JDialog jDialog4;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel12;
  private javax.swing.JLabel jLabel13;
  private javax.swing.JLabel jLabel16;
  private javax.swing.JLabel jLabel2;
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
  private javax.swing.JPanel jPanelAlumno;
  private javax.swing.JPanel jPanelAlumno1;
  private javax.swing.JPanel jPanelAsignatura;
  private javax.swing.JPanel jPanelBuscarAlumno;
  private javax.swing.JPanel jPanelBuscarAsignatura;
  private javax.swing.JPanel jPanelBuscarProfesor;
  private javax.swing.JPanel jPanelCursada;
  private javax.swing.JPanel jPanelProfesor;
  private javax.swing.JPanel jPanelResultadosAlumno;
  private javax.swing.JPanel jPanelResultadosAsignatura;
  private javax.swing.JPanel jPanelResultadosProfesor;
  private javax.swing.JScrollPane jScrollPane3;
  private javax.swing.JScrollPane jScrollPane4;
  private javax.swing.JScrollPane jScrollPane5;
  private javax.swing.JScrollPane jScrollPane6;
  private javax.swing.JScrollPane jScrollPane7;
  private javax.swing.JScrollPane jScrollPane8;
  private javax.swing.JTabbedPane jTabbedPane;
  private javax.swing.JTable jTableAlumnoAlumno;
  private javax.swing.JTable jTableAsignaturaAsignatura;
  private javax.swing.JTable jTableCompetencia;
  private javax.swing.JTable jTableCorrelativas;
  private javax.swing.JTable jTableHistoria;
  private javax.swing.JTable jTableProfesorProfesor;
  private javax.swing.JTextField jTextFieldBuscarAlumno;
  private javax.swing.JTextField jTextFieldBuscarAsignatura;
  private javax.swing.JTextField jTextFieldBuscarProfesor;
  private javax.swing.JTextField jTextFieldDomicilioAlumno;
  private javax.swing.JTextField jTextFieldDomicilioProfesor;
  private javax.swing.JTextField jTextFieldIdentificadorAsignatura;
  private javax.swing.JTextField jTextFieldLegajoAlumno;
  private javax.swing.JTextField jTextFieldLegajoProfesor;
  private javax.swing.JTextField jTextFieldMailAlumno;
  private javax.swing.JTextField jTextFieldMailProfesor;
  private javax.swing.JTextField jTextFieldNombreAlumno;
  private javax.swing.JTextField jTextFieldNombreAsignatura;
  private javax.swing.JTextField jTextFieldNombreProfesor;
  private javax.swing.JTextField jTextFieldTelefonoProfesor;
  // End of variables declaration//GEN-END:variables
}
