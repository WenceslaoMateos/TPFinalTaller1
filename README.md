# TPFinalTaller1
# Integrantes: Braulio Pablos y Wenceslao Mateos.

Aclararaciones respecto al trabajo:

Se tomaron las siguientes decisiones, tras consultar con la cátedra de la materia:

1- Agregar un atributo de apellido y nombre al profesor.
2- Agregar una hora de finalización a las cursadas, acompañando así el atributo dado por la SRS que se interpretó como hora de inicio.
3- El espacio físico para las cursadas es ilimitado (se suponen aulas infinitas) y no hay problema con que dos cursadas sean concurrentes. Las verificaciones de superposición de horarios solo se aplican a profesores y alumnos.
4- Agregar una precorrelativa a una asignatura no implica eliminar dicha materia o alguna de sus correlativas de la historia académica de un alumno que no tenga la precorrelativa añadida. Tampoco provoca la baja de un alumno en una cursada bajo las mismas condiciones.
5- Las correlatividades circulares son un error provenientes de uso del sistema y no son comprobadas.
6- Los legajos e identificaciones son autogenerados por el sistema e inmodificables.

-------------------------------------------------------------------------
V2 changelog:
-Se corrigieron los errores encontrados en el test de caja negra que fueron marcados en rojo en el archivo con los resultados anexo. Los demás errores están resueltos a nivel de integración o son problemas de los casos de prueba.

V1 changelog:
-Se pulió la documentación, el modelo y la vista en función a las anomalías encontradas en la inspección.
-Se corrigió el error al eliminar a los profesores de la cursadas en las que participaban cuando se les elimina una competencia.
-Se estandarizó la interacción de la clase Vista con Receptor en cuanto al manejo de colecciones internas, delegando la responsabilidad de los controles a este último.
-Se modificó el comportamiento de los TableModel para no poder agregar el mismo elemento más de una vez.
-Se agregó la excepción DiaValidoException.
