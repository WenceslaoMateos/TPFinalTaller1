
package vista;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author wence
 */
public class VentanaPrincipal
  extends javax.swing.JFrame
  implements Observer
{

  /** Creates new form VentanaPrincipal */
  public VentanaPrincipal()
  {
    initComponents();
    //this.setSize(this.getMaximumSize());
    this.setExtendedState(MAXIMIZED_VERT);
    this.setResizable(false);
    this.setTitle("Programa de Gestion de asignaturas");
  }

  @Override
  public void update(Observable o, Object arg)
  {
    // TODO Implement this method
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  private void initComponents()//GEN-BEGIN:initComponents
  {

    jPanelAlumno = new javax.swing.JPanel();
    jButtonAltaAlumno = new javax.swing.JButton();
    jButtonBajaAlumno = new javax.swing.JButton();
    jButtonModificacionAlumno = new javax.swing.JButton();
    jButtonUbicarAlumno = new javax.swing.JButton();
    jPanelProfesor = new javax.swing.JPanel();
    jButtonAltaProfesor = new javax.swing.JButton();
    jButtonBajaProfesor = new javax.swing.JButton();
    jButtonModificacionProfesor = new javax.swing.JButton();
    jButtonUbicarProfesor = new javax.swing.JButton();
    jPanelCursada = new javax.swing.JPanel();
    jButtonAltaCursada = new javax.swing.JButton();
    jButtonBajaCursada = new javax.swing.JButton();
    jButtonModificacionCursada = new javax.swing.JButton();
    jButtonUbicarCursada = new javax.swing.JButton();
    jPanelAsignatura = new javax.swing.JPanel();
    jButtonAltaAsignatura = new javax.swing.JButton();
    jButtonBajaAsignatura = new javax.swing.JButton();
    jButtonModificacionAsignatura = new javax.swing.JButton();
    jButtonUbicarAsignatura = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTextPane1 = new javax.swing.JTextPane();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jPanelAlumno.setBorder(javax.swing.BorderFactory.createTitledBorder("Alumno"));

    jButtonAltaAlumno.setText("Alta");
    jButtonAltaAlumno.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAltaAlumnoActionPerformed(evt);
      }
    });

    jButtonBajaAlumno.setText("Baja");
    jButtonBajaAlumno.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonBajaAlumnoActionPerformed(evt);
      }
    });

    jButtonModificacionAlumno.setText("Modificacion");
    jButtonModificacionAlumno.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonModificacionAlumnoActionPerformed(evt);
      }
    });

    jButtonUbicarAlumno.setText("Ubicar");

    javax.swing.GroupLayout jPanelAlumnoLayout = new javax.swing.GroupLayout(jPanelAlumno);
    jPanelAlumno.setLayout(jPanelAlumnoLayout);
    jPanelAlumnoLayout.setHorizontalGroup(
      jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelAlumnoLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jButtonModificacionAlumno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
          .addComponent(jButtonBajaAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jButtonAltaAlumno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jButtonUbicarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanelAlumnoLayout.setVerticalGroup(
      jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelAlumnoLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jButtonAltaAlumno)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonBajaAlumno)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonModificacionAlumno)
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

    jButtonBajaProfesor.setText("Baja");
    jButtonBajaProfesor.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonBajaProfesorActionPerformed(evt);
      }
    });

    jButtonModificacionProfesor.setText("Modificacion");
    jButtonModificacionProfesor.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonModificacionProfesorActionPerformed(evt);
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
          .addComponent(jButtonModificacionProfesor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
          .addComponent(jButtonBajaProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jButtonAltaProfesor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jButtonUbicarProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanelProfesorLayout.setVerticalGroup(
      jPanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelProfesorLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jButtonAltaProfesor)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonBajaProfesor)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonModificacionProfesor)
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

    jButtonBajaCursada.setText("Baja");
    jButtonBajaCursada.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonBajaCursadaActionPerformed(evt);
      }
    });

    jButtonModificacionCursada.setText("Modificacion");
    jButtonModificacionCursada.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonModificacionCursadaActionPerformed(evt);
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
          .addComponent(jButtonModificacionCursada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
          .addComponent(jButtonBajaCursada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jButtonAltaCursada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jButtonUbicarCursada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanelCursadaLayout.setVerticalGroup(
      jPanelCursadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelCursadaLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jButtonAltaCursada)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonBajaCursada)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonModificacionCursada)
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

    jButtonBajaAsignatura.setText("Baja");
    jButtonBajaAsignatura.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonBajaAsignaturaActionPerformed(evt);
      }
    });

    jButtonModificacionAsignatura.setText("Modificacion");
    jButtonModificacionAsignatura.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseClicked(java.awt.event.MouseEvent evt)
      {
        jButtonModificacionAsignaturaMouseClicked(evt);
      }
    });
    jButtonModificacionAsignatura.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonModificacionAsignaturaActionPerformed(evt);
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
          .addComponent(jButtonModificacionAsignatura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
          .addComponent(jButtonBajaAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jButtonAltaAsignatura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jButtonUbicarAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanelAsignaturaLayout.setVerticalGroup(
      jPanelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelAsignaturaLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jButtonAltaAsignatura)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonBajaAsignatura)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonModificacionAsignatura)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonUbicarAsignatura)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jScrollPane1.setViewportView(jTextPane1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jPanelAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(44, 44, 44)
            .addComponent(jPanelProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(44, 44, 44)
            .addComponent(jPanelAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(44, 44, 44)
            .addComponent(jPanelCursada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addComponent(jPanelProfesor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanelAsignatura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanelCursada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanelAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(18, 18, 18)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
        .addContainerGap())
    );

    pack();
  }//GEN-END:initComponents

  private void jButtonAltaAlumnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAltaAlumnoActionPerformed
  {//GEN-HEADEREND:event_jButtonAltaAlumnoActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonAltaAlumnoActionPerformed

  private void jButtonBajaAlumnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBajaAlumnoActionPerformed
  {//GEN-HEADEREND:event_jButtonBajaAlumnoActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonBajaAlumnoActionPerformed

  private void jButtonModificacionAlumnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonModificacionAlumnoActionPerformed
  {//GEN-HEADEREND:event_jButtonModificacionAlumnoActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonModificacionAlumnoActionPerformed

  private void jButtonAltaProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAltaProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonAltaProfesorActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonAltaProfesorActionPerformed

  private void jButtonBajaProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBajaProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonBajaProfesorActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonBajaProfesorActionPerformed

  private void jButtonModificacionProfesorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonModificacionProfesorActionPerformed
  {//GEN-HEADEREND:event_jButtonModificacionProfesorActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonModificacionProfesorActionPerformed

  private void jButtonAltaCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAltaCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonAltaCursadaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonAltaCursadaActionPerformed

  private void jButtonBajaCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBajaCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonBajaCursadaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonBajaCursadaActionPerformed

  private void jButtonModificacionCursadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonModificacionCursadaActionPerformed
  {//GEN-HEADEREND:event_jButtonModificacionCursadaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonModificacionCursadaActionPerformed

  private void jButtonAltaAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAltaAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonAltaAsignaturaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonAltaAsignaturaActionPerformed

  private void jButtonBajaAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBajaAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonBajaAsignaturaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonBajaAsignaturaActionPerformed

  private void jButtonModificacionAsignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonModificacionAsignaturaActionPerformed
  {//GEN-HEADEREND:event_jButtonModificacionAsignaturaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonModificacionAsignaturaActionPerformed

  private void jButtonModificacionAsignaturaMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButtonModificacionAsignaturaMouseClicked
  {//GEN-HEADEREND:event_jButtonModificacionAsignaturaMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonModificacionAsignaturaMouseClicked

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

    /* Create and display the form */
    java.awt
        .EventQueue
        .invokeLater(new Runnable()
      {
        public void run()
        {
          new VentanaPrincipal().setVisible(true);
        }
      });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonAltaAlumno;
  private javax.swing.JButton jButtonAltaAsignatura;
  private javax.swing.JButton jButtonAltaCursada;
  private javax.swing.JButton jButtonAltaProfesor;
  private javax.swing.JButton jButtonBajaAlumno;
  private javax.swing.JButton jButtonBajaAsignatura;
  private javax.swing.JButton jButtonBajaCursada;
  private javax.swing.JButton jButtonBajaProfesor;
  private javax.swing.JButton jButtonModificacionAlumno;
  private javax.swing.JButton jButtonModificacionAsignatura;
  private javax.swing.JButton jButtonModificacionCursada;
  private javax.swing.JButton jButtonModificacionProfesor;
  private javax.swing.JButton jButtonUbicarAlumno;
  private javax.swing.JButton jButtonUbicarAsignatura;
  private javax.swing.JButton jButtonUbicarCursada;
  private javax.swing.JButton jButtonUbicarProfesor;
  private javax.swing.JPanel jPanelAlumno;
  private javax.swing.JPanel jPanelAsignatura;
  private javax.swing.JPanel jPanelCursada;
  private javax.swing.JPanel jPanelProfesor;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextPane jTextPane1;
  // End of variables declaration//GEN-END:variables
}
