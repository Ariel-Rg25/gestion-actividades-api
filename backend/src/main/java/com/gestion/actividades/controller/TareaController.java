package com.gestion.actividades.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.gestion.actividades.model.Tarea;
import com.gestion.actividades.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    //Listar todas las tareas de un proyecto
    @GetMapping("/projects/{projectId}/tasks")
    public List<Tarea> listarTareasPorProyecto(@PathVariable Long projectId) {
        return tareaService.listarPorProyecto(projectId);
    }

    // Crear tarea dentro de un proyecto
    @PostMapping("/projects/{projectId}/tasks")
    public Tarea crearTarea(@PathVariable Long projectId, @RequestBody Tarea tarea) {
        return tareaService.guardarTarea(projectId, tarea);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Tarea> obtenerTarea(@PathVariable Long id) {
        return tareaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Actualizar tarea
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id, @RequestBody Tarea tareaDetails) {
        return tareaService.obtenerPorId(id)
                .map(tarea -> {
                    tarea.setTitulo(tareaDetails.getTitulo());
                    tarea.setDescripcion(tareaDetails.getDescripcion());
                    tarea.setEstado(tareaDetails.getEstado());
                    tarea.setPrioridad(tareaDetails.getPrioridad());
                    tarea.setFechaInicio(tareaDetails.getFechaInicio());
                    tarea.setFechaFin(tareaDetails.getFechaFin());
                    return ResponseEntity.ok(tareaService.actualizar(tarea));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        tareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}