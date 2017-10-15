package lanzador;

import modelo.Sistema;

import persistencia.SerializadorXML;

import vista.Receptor;
import vista.VentanaPrincipal;

public class Lanzador
{
    public Lanzador()
    {
        super();
    }

    public static void main(String[] args)
    {
        VentanaPrincipal vista = new VentanaPrincipal();
        Sistema modelo = SerializadorXML.cargar();
        Receptor receptor = new Receptor(modelo, vista);
        vista.setReceptor(receptor);
    }
}
