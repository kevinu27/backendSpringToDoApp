package gm.tareas.servicio;
import gm.tareas.modelo.Subtarea;
import gm.tareas.modelo.Tarea;

import java.util.List;

public interface ISubtareaServicio {
    public List<Subtarea> listarSubtareas();

//    List<Subtarea> listarTareas();

    public Subtarea buscarSubtareaPorId(Integer idSubtarea);
    public Subtarea guardarSubtarea(Subtarea subtarea);
    public void eliminarSubtareaPorId(Integer idSubtarea);
}
