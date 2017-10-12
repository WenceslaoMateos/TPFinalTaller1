package modelo;


public class Sistema
{
  IndiceDoble<String, String, Alumno> alumnos;
  IndiceDoble<String, String, Profesor> profesores;
  IndiceDoble<String, String, Asignatura> planDeEstudio;
  IndiceDoble<String, String, Cursada> calendario;

  public void setAlumnos(IndiceDoble<String, String, Alumno> alumnos)
  {
    this.alumnos = alumnos;
  }

  public IndiceDoble<String, String, Alumno> getAlumnos()
  {
    return alumnos;
  }

  public void setProfesores(IndiceDoble<String, String, Profesor> profesores)
  {
    this.profesores = profesores;
  }

  public IndiceDoble<String, String, Profesor> getProfesores()
  {
    return profesores;
  }

  public void setPlanDeEstudio(IndiceDoble<String, String, Asignatura> planDeEstudio)
  {
    this.planDeEstudio = planDeEstudio;
  }

  public IndiceDoble<String, String, Asignatura> getPlanDeEstudio()
  {
    return planDeEstudio;
  }

  public void setCalendario(IndiceDoble<String, String, Cursada> calendario)
  {
    this.calendario = calendario;
  }

  public IndiceDoble<String, String, Cursada> getCalendario()
  {
    return calendario;
  }
}
