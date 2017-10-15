package vista;

import controlador.ControlSistema;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class DialogAlta
    extends JDialog
{
    protected JTextField[] campos;
    protected JButton aceptar;
    protected JButton cancelar;
    
    public DialogAlta()
    {
        JPanel finalizacion = new JPanel();
        JPanel elementos = new JPanel();
        Container contenedor;
        this.setSize(200, 200);
        this.setVisible(true);
        contenedor = this.getContentPane();
        contenedor.setLayout(new BorderLayout());
        
        contenedor.add(elementos, BorderLayout.CENTER);
        this.generaCampos(elementos);
        
        contenedor.add(finalizacion, BorderLayout.SOUTH);
        this.aceptar = new JButton("Aceptar");
        finalizacion.add(this.aceptar);
        this.cancelar = new JButton("Cancelar");
        finalizacion.add(this.cancelar);
        this.aceptar.setSize(50, 100);
        this.cancelar.setSize(50, 100);
    }
    
    public abstract void generaCampos(JPanel elementos);
    
    public void setControlador(ControlSistema controlador)
    {
        this.aceptar.addActionListener(controlador);
    }
}
