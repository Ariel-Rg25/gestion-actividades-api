package com.gestion.actividades.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.gestion.actividades.model.Proyecto;
import com.gestion.actividades.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    
    @GetMapping
    public List<Proyecto> listarProyectos() {
        return proyectoService.listarTodos();
    }

    
    @PostMapping
    public Proyecto crearProyecto(@RequestBody Proyecto proyecto) {
        return proyectoService.guardar(proyecto);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> obtenerProyecto(@PathVariable Long id) {
        return proyectoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> actualizarProyecto(@PathVariable Long id, @RequestBody Proyecto proyectoDetails) {
        return proyectoService.obtenerPorId(id)
                .map(proyecto -> {
                    proyecto.setTitulo(proyectoDetails.getTitulo());
                    proyecto.setDescripcion(proyectoDetails.getDescripcion());
                    proyecto.setEstado(proyectoDetails.getEstado());
                    proyecto.setPrioridad(proyectoDetails.getPrioridad());
                    proyecto.setFechaInicio(proyectoDetails.getFechaInicio());
                    proyecto.setFechaFin(proyectoDetails.getFechaFin());
                    
                    return ResponseEntity.ok(proyectoService.guardar(proyecto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}