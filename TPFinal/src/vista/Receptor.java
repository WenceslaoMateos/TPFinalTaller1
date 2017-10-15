package vista;

import modelo.Sistema;


public class Receptor
{
  private Sistema modelo;
  private VentanaPrincipal vista;
  public static final int ALTA_ALUMNO=0;
  public static final int ALTA_PROFESOR=1;
  public static final int ALTA_ASIGNATURA=2;
  

  public Receptor(Sistema modelo, VentanaPrincipal vista)
  {
    super();
    this.modelo = modelo;
    this.vista = vista;
  }

  public void alta(Object obj, int comando)
  {
    switch(comando)
    {
      case Receptor.ALTA_ALUMNO:
        
        break;
    }
  }
}
