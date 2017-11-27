package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ QuitarCompetenciaAProfesorTest.class, ModificacionesSimplesTest.class,
                      ColeccionesInternasConElementosTest.class, BajaProfesorEnCursadaTest.class,
                      BajaAlummnoEnCursadaTest.class, AgregarProfesorEnCursada.class, AgregarHistoriaTest.class,
                      AgregarCorrelativaTest.class, AgregarCompetenciaTest.class, AgregarAlumnoEnCursada.class
    })
public class SuiteModificaciones
{
}
