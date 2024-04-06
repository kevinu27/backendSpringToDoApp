package gm.tareas.repositorio;

import gm.tareas.modelo.Subtarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtareaRepositorio  extends JpaRepository<Subtarea, Integer> {
}
