package vista;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogAltaAlumno
  extends DialogAlta
{
  public DialogAltaAlumno(Receptor receptor)
  {
    super(receptor);
  }
  
  @Override
  public void generaCampos(JPanel elementos)
  {
    int i;
    elementos.setLayout(new GridLayout(4, 3));
    this.campos = new JTextField[3];
    for (i = 0; i < this.campos.length; i++)
    {
      this.campos[i] = new JTextField();
    }

    elementos.add(new JLabel("Descripcion"));
    elementos.add(new JLabel("Campo"));
    elementos.add(new JLabel("Formato"));

    elementos.add(new JLabel("Apellido y Nombre"));
    elementos.add(campos[0]);
    elementos.add(new JLabel("---"));

    elementos.add(new JLabel("Domicilio"));
    elementos.add(campos[1]);
    elementos.add(new JLabel("---"));

    elementos.add(new JLabel("Mail"));
    elementos.add(campos[2]);
    elementos.add(new JLabel("AAAA@AAAAA"));
  }

  @Override
  public Object generaObjeto()
  {
    
    return null;
  }
}
