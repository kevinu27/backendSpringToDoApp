package gm.tareas.servicio;

import gm.tareas.modelo.Subtarea;
import gm.tareas.repositorio.SubtareaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubtareaServicio implements ISubtareaServicio {

    @Autowired
    private SubtareaRepositorio subtareaRepositorio;

    @Override
    public List<Subtarea> listarSubtareas() {
        return this.subtareaRepositorio.findAll();
    }

    @Override
    public Subtarea buscarSubtareaPorId(Integer idSubtarea) {
        Subtarea subtarea = this.subtareaRepositorio.findById(idSubtarea).orElse(null);
        return subtarea;
    }

    @Override
    public Subtarea guardarSubtarea(Subtarea subtarea) {
        return this.subtareaRepositorio.save(subtarea);
    }

    @Override
    public void eliminarSubtareaPorId(Integer idSubtarea) {
        this.subtareaRepositorio.deleteById(idSubtarea);
    }
}
