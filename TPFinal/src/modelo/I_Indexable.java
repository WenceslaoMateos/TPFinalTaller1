package modelo;

import excepciones.DatoInvalidoException;


public interface I_Indexable
{
    public abstract Object getClavePrimaria();

    public abstract Object getClaveSecundaria();
    
    public abstract void modificarDatos(I_Indexable modif) throws DatoInvalidoException;
}
