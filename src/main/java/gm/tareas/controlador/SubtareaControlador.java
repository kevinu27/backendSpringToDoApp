package gm.tareas.controlador;
import gm.tareas.excepcion.RecursoNoEncontradoExcepcion;
import gm.tareas.modelo.Subtarea;
import gm.tareas.servicio.SubtareaServicio;
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
@CrossOrigin(value = "http://localhost:5173")
public class SubtareaControlador {

    private static final Logger logger = LoggerFactory.getLogger(SubtareaControlador.class);

    @Autowired
    private SubtareaServicio subtareaServicio;
    //http://localhost:8080/tarea-app/tareas
    @GetMapping("/subtareas")
    public List<Subtarea> obtenerTareas(){
        List<Subtarea> subtareas = this.subtareaServicio.listarSubtareas();
        logger.info("Subtareas obtenidas:");
        subtareas.forEach((subtarea -> logger.info(subtarea.toString())));
        return subtareas;
    }
    @PostMapping("/subtareas")
    public Subtarea agregarSubtarea(@RequestBody Subtarea subtarea){
        logger.info("Subtareas a agregar: " + subtarea);
        return this.subtareaServicio.guardarSubtarea(subtarea);
    }
    @GetMapping("/subtarea/{id}")
    public ResponseEntity<Subtarea> obtenersubTareaPorID(
            @PathVariable int id){
        Subtarea subtarea = this.subtareaServicio.buscarSubtareaPorId(id);
        logger.info("id de la tarea a obtern: " + id + subtarea);
        if(subtarea != null)
            return ResponseEntity.ok(subtarea);
        else
            throw new RecursoNoEncontradoExcepcion("no se encontro el id " + id);
    }

//    @PutMapping("/subtarea/{id}")
//    public ResponseEntity<Subtarea> actualizarTarea(
//            @PathVariable int id,
//            @RequestBody Subtarea tareaRecibidas
//    ){
//        Subtarea subtarea = this.subtareaServicio.buscarSubtareaPorId(id);
////        subtarea.setDescripcion(subtareaServicio.getDescripcion());
//        subtarea.setCompletada(subtareaServicio.getCompletada());
////        tarea.setSubtareas(tareaRecibida.getSubtareas());
//        subtarea.setNombre(subtareaServicio.getNombre());
//        this.subtareaServicio.guardarSubtarea(subtarea);
//        return ResponseEntity.ok(subtarea);
//    }

    @DeleteMapping("/subtarea/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarSubtarea(@PathVariable int id){
        Subtarea subtarea = subtareaServicio.buscarSubtareaPorId(id);
        this.subtareaServicio.eliminarSubtareaPorId(subtarea.getIdSubtarea());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        logger.info("id a elimar: " + id);
        return ResponseEntity.ok(respuesta);
    }
}
