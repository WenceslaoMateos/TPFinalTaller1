package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ValidaCursadaTest.class, ProfesorDisponibleTest.class, CorrelativasAprobadasTest.class,
                      CompruebaCorrelativasTest.class, AlumnoDisponibleTest.class, AceptaCompetenciaTest.class
    })
public class SuiteVerificaciones
{
}
