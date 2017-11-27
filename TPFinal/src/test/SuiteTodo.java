package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ SuiteVerificaciones.class, SuiteModificaciones.class, ColeccionesCoberturaFaltanteTest.class,
                      BusquedaTest.class, BajasSistemaTest.class, AltasSistema.class
    })
public class SuiteTodo
{
}
