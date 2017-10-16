
package vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


/**
 *
 * @author wence
 */
public class VentanaPrincipal
  extends javax.swing.JFrame
{
  private Receptor receptor;

  /** Creates new form VentanaPrincipal */
  public VentanaPrincipal()
  {
    initComponents();
    this.setExtendedState(MAXIMIZED_VERT);
    this.setResizable(false);
    this.setTitle("Programa de Gestion de asignaturas");
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.addWindowListener(new WindowAdapter()
    {

      @Override
      public void windowClosing(WindowEvent e)
      {
        super.windowClosing(e);
        VentanaPrincipal.this.receptor.guardar();
        VentanaPrincipal.this.dispose();
      }
    });
  }

  public void setReceptor(Receptor receptor)
  {
    this.receptor = receptor;
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  private void initComponents()//GEN-BEGIN:initComponents
  {

    buttonGroup1 = new javax.swing.ButtonGroup();
    buttonGroup2 = new javax.swing.ButtonGroup();
    jPanelAlumno = new javax.swing.JPanel();
    jButtonAltaAlumno = new javax.swing.JButton();
    jButtonUbicarAlumno = new javax.swing.JButton();
    jPanelProfesor = new javax.swing.JPanel();
    jButtonAltaProfesor = new javax.swing.JButton();
    jButtonUbicarProfesor = new javax.swing.JButton();
    jPanelCursada = new javax.swing.JPanel();
    jButtonAltaCursada = new javax.swing.JButton();
    jButtonUbicarCursada = new javax.swing.JButton();
    jPanelAsignatura = new javax.swing.JPanel();
    jButtonAltaAsignatura = new javax.swing.JButton();
    jButtonUbicarAsignatura = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jPanelAlumno.setBorder(javax.swing.BorderFactory.createTitledBorder("Alumno"));

    jButtonAltaAlumno.setText("Alta");
    jButtonAltaAlumno.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseClicked(java.awt.event.MouseEvent evt)
      {
        jButtonAltaAlumnoMouseClicked(evt);
      }
    });
    jButtonAltaAlumno.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAltaAlumnoActionPerformed(evt);
      }
    });

    jButtonUbicarAlumno.setText("Ubicar");
    jButtonUbicarAlumno.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseClicked(java.awt.event.MouseEvent evt)
      {
        jButtonUbicarAlumnoMouseClicked(evt);
      }
    });

    javax.swing.GroupLayout jPanelAlumnoLayout = new javax.swing.GroupLayout(jPanelAlumno);
    jPanelAlumno.setLayout(jPanelAlumnoLayout);
    jPanelAlumnoLayout.setHorizontalGroup(
      jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelAlumnoLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jButtonAltaAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
          .addComponent(jButtonUbicarAlumno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanelAlumnoLayout.setVerticalGroup(
      jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelAlumnoLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jButtonAltaAlumno)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonUbicarAlumno)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanelProfesor.setBorder(javax.swing.BorderFactory.createTitledBorder("Profesor"));

    jButtonAltaProfesor.setText("Alta");
    jButtonAltaProfesor.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAltaProfesorActionPerformed(evt);
      }
    });

    jButtonUbicarProfesor.setText("Ubicar");

    javax.swing.GroupLayout jPanelProfesorLayout = new javax.swing.GroupLayout(jPanelProfesor);
    jPanelProfesor.setLayout(jPanelProfesorLayout);
    jPanelProfesorLayout.setHorizontalGroup(
      jPanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelProfesorLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jButtonAltaProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
          .addComponent(jButtonUbicarProfesor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanelProfesorLayout.setVerticalGroup(
      jPanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelProfesorLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jButtonAltaProfesor)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonUbicarProfesor)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanelCursada.setBorder(javax.swing.BorderFactory.createTitledBorder("Cursada"));

    jButtonAltaCursada.setText("Alta");
    jButtonAltaCursada.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAltaCursadaActionPerformed(evt);
      }
    });

    jButtonUbicarCursada.setText("Ubicar");

    javax.swing.GroupLayout jPanelCursadaLayout = new javax.swing.GroupLayout(jPanelCursada);
    jPanelCursada.setLayout(jPanelCursadaLayout);
    jPanelCursadaLayout.setHorizontalGroup(
      jPanelCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelCursadaLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jButtonAltaCursada, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
          .addComponent(jButtonUbicarCursada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanelCursadaLayout.setVerticalGroup(
      jPanelCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelCursadaLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jButtonAltaCursada)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonUbicarCursada)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanelAsignatura.setBorder(javax.swing.BorderFactory.createTitledBorder("Asignatura"));

    jButtonAltaAsignatura.setText("Alta");
    jButtonAltaAsignatura.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAltaAsignaturaActionPerformed(evt);
      }
    });

    jButtonUbicarAsignatura.setText("Ubicar");

    javax.swing.GroupLayout jPanelAsignaturaLayout = new javax.swing.GroupLayout(jPanelAsignatura);
    jPanelAsignatura.setLayout(jPanelAsignaturaLayout);
    jPanelAsignaturaLayout.setHorizontalGroup(
      jPanelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelAsignaturaLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jButtonAltaAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
          .addComponent(jButtonUbicarAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanelAsignaturaLayout.setVerticalGroup(
      jPanelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelAsignaturaLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jButtonAltaAsignatura)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonUbicarAsignatura)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanelAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(44, 44, 44)
        .addComponent(jPanelProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(44, 44, 44)
        .addComponent(jPanelAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(44, 44, 44)
        .addComponent(jPanelCursada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addComponent(jPanelAsignatura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanelCursada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanelProfesor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanelAlumno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }//GEN-END:initComponents

  private void jButtonAltaAlumnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAltaAlumnoActionPerformed
  {//GEN-HEADEREND:event_jButtonAltaAlumnoActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonAltaAlumnoActionPerformed

  private void jButtonAltaProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAltaProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonAltaProfesorActionPerformed
    new DialogAltaProfesor(this.receptor);
  }//GEN-LAST:event_jButtonAltaProfesorActionPerformed

  private void jButtonAltaCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAltaCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonAltaCursadaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonAltaCursadaActionPerformed

  private void jButtonAltaAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAltaAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonAltaAsignaturaActionPerformed
    new DialogAltaAsignatura(this.receptor);
  }//GEN-LAST:event_jButtonAltaAsignaturaActionPerformed
      
  private void jButtonAltaAlumnoMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButtonAltaAlumnoMouseClicked
  {//GEN-HEADEREND:event_jButtonAltaAlumnoMouseClicked
    new DialogAltaAlumno(this.receptor); 
  }//GEN-LAST:event_jButtonAltaAlumnoMouseClicked

  private void jButtonUbicarAlumnoMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButtonUbicarAlumnoMouseClicked
  {//GEN-HEADEREND:event_jButtonUbicarAlumnoMouseClicked
    new DialogUbicarAlumno(this.receptor);
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonUbicarAlumnoMouseClicked

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
          .getLogger(VentanaPrincipal.class.getName())
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
          .getLogger(VentanaPrincipal.class.getName())
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
          .getLogger(VentanaPrincipal.class.getName())
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
          .getLogger(VentanaPrincipal.class.getName())
          .log(java.util
                   .logging
                   .Level
                   .SEVERE, null, ex);
    }
    //</editor-fold>
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.ButtonGroup buttonGroup1;
  private javax.swing.ButtonGroup buttonGroup2;
  private javax.swing.JButton jButtonAltaAlumno;
  private javax.swing.JButton jButtonAltaAsignatura;
  private javax.swing.JButton jButtonAltaCursada;
  private javax.swing.JButton jButtonAltaProfesor;
  private javax.swing.JButton jButtonUbicarAlumno;
  private javax.swing.JButton jButtonUbicarAsignatura;
  private javax.swing.JButton jButtonUbicarCursada;
  private javax.swing.JButton jButtonUbicarProfesor;
  private javax.swing.JPanel jPanelAlumno;
  private javax.swing.JPanel jPanelAsignatura;
  private javax.swing.JPanel jPanelCursada;
  private javax.swing.JPanel jPanelProfesor;
  // End of variables declaration//GEN-END:variables
}
