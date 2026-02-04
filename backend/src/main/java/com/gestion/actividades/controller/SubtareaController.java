package com.gestion.actividades.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.gestion.actividades.model.Subtarea;
import com.gestion.actividades.service.SubtareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SubtareaController {

    @Autowired
    private SubtareaService subtareaService;

    //  Listar subtareas de una tarea
    @GetMapping("/tasks/{taskId}/subtasks")
    public List<Subtarea> listarSubtareas(@PathVariable Long taskId) {
        return subtareaService.listarPorTarea(taskId);
    }

    // Crear subtarea
    @PostMapping("/tasks/{taskId}/subtasks")
    public Subtarea crearSubtarea(@PathVariable Long taskId, @RequestBody Subtarea subtarea) {
        return subtareaService.guardarSubtarea(taskId, subtarea);
    }

    // Actualizar subtarea
    @PutMapping("/subtasks/{id}")
    public ResponseEntity<Subtarea> actualizarSubtarea(@PathVariable Long id, @RequestBody Subtarea subDetails) {
        return subtareaService.obtenerPorId(id)
                .map(subtarea -> {
                    subtarea.setTitulo(subDetails.getTitulo());
                    subtarea.setDescripcion(subDetails.getDescripcion());
                    subtarea.setEstado(subDetails.getEstado());
                    subtarea.setPrioridad(subDetails.getPrioridad());
                    return ResponseEntity.ok(subtareaService.actualizar(subtarea));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/subtasks/{id}")
    public ResponseEntity<Void> eliminarSubtarea(@PathVariable Long id) {
        subtareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}