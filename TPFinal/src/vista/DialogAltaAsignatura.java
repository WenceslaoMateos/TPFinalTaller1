package vista;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Asignatura;

public class DialogAltaAsignatura
    extends DialogAlta
{
    public DialogAltaAsignatura(Receptor receptor)
    {
        super(receptor);
    }

    @Override
    public void generaCampos(JPanel elementos)
    {
        elementos.setLayout(new GridLayout(2, 3));
        this.campos = new JTextField[1];
        this.campos[0] = new JTextField();

        elementos.add(new JLabel("Descripcion"));
        elementos.add(new JLabel("Campo"));
        elementos.add(new JLabel("Formato"));

        elementos.add(new JLabel("Nombre"));
        elementos.add(campos[0]);
        elementos.add(new JLabel("---"));
    }

    @Override
    public Object generaObjeto()
    {
        return new Asignatura(this.campos[0].getText());
    }

    @Override
    public int getComando()
    {
        return Receptor.ASIGNATURA;
    }
}
