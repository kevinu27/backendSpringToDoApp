package gm.tareas.controlador;

import gm.tareas.modelo.Tarea;
import gm.tareas.servicio.TareaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//http://localhost:8080/tarea-app
@RequestMapping("tarea-app")
@CrossOrigin(value = "http://localhost:3000")
public class TareaControlador {

    private static final Logger logger = LoggerFactory.getLogger(TareaControlador.class);

    @Autowired
    private TareaServicio tareaServicio;
//http://localhost:8080/tarea-app/tareas
    @GetMapping("/tareas")
    public List<Tarea> obtenerTareas(){
        List<Tarea> tareas = this.tareaServicio.listarTareas();
        logger.info("Tareas obtenidas:");
        tareas.forEach((tarea -> logger.info(tarea.toString())));
        return tareas;
    }
    @PostMapping("/tareas")
    public Tarea agregarTarea(@RequestBody Tarea tarea){
        logger.info("Tarea a agregar: " + tarea);
        return this.tareaServicio.guardarTarea(tarea);
    }

}
