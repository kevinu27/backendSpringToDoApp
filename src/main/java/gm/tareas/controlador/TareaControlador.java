package gm.tareas.controlador;

import gm.tareas.excepcion.RecursoNoEncontradoExcepcion;
import gm.tareas.modelo.Tarea;
import gm.tareas.servicio.TareaServicio;
//import org.hibernate.mapping.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
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
    @GetMapping("/tarea/{id}")
    public ResponseEntity<Tarea> obtenerTareaPorID(
            @PathVariable int id){
        Tarea tarea = this.tareaServicio.buscarTareaPorId(id);
        logger.info("id de la tarea a obtern: " + id + tarea);
        if(tarea != null)
            return ResponseEntity.ok(tarea);
        else
            throw new RecursoNoEncontradoExcepcion("no se encontro el id " + id);
    }

    @PutMapping("/tarea/{id}")
    public ResponseEntity<Tarea> actualizarTarea(
            @PathVariable int id,
            @RequestBody Tarea tareaRecibida
    ){
        Tarea tarea = this.tareaServicio.buscarTareaPorId(id);
        tarea.setDescripcion(tareaRecibida.getDescripcion());
        tarea.setCompletada(tareaRecibida.getCompletada());
        tarea.setSubtarea(tareaRecibida.getSubtarea());
        this.tareaServicio.guardarTarea(tarea);
        return ResponseEntity.ok(tarea);
    }

    @DeleteMapping("/tarea/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarTarea(@PathVariable int id){
        Tarea tarea = tareaServicio.buscarTareaPorId(id);
        this.tareaServicio.eliminarTareaPorId(tarea.getIdTarea());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        logger.info("id a elimar: " + id);
        return ResponseEntity.ok(respuesta);
    }
}
