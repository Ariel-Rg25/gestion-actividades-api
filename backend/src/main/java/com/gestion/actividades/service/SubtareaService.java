package com.gestion.actividades.service;

import com.gestion.actividades.model.Subtarea;
import com.gestion.actividades.model.Tarea;
import com.gestion.actividades.repository.SubtareaRepository;
import com.gestion.actividades.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubtareaService {

    @Autowired
    private SubtareaRepository subtareaRepository;

    @Autowired
    private TareaRepository tareaRepository;

    public Subtarea guardarSubtarea(Long tareaId, Subtarea subtarea) {
        Tarea tarea = tareaRepository.findById(tareaId)
                .orElseThrow(() -> new RuntimeException("Tarea padre no encontrada"));
        
        subtarea.setTarea(tarea);
        return subtareaRepository.save(subtarea);
    }

    public List<Subtarea> listarPorTarea(Long tareaId) {
        return subtareaRepository.findByTareaId(tareaId);
    }

    public Subtarea actualizar(Subtarea subtarea) {
        return subtareaRepository.save(subtarea);
    }

    public void eliminar(Long id) {
        subtareaRepository.deleteById(id);
    }
    
    public Optional<Subtarea> obtenerPorId(Long id) {
        return subtareaRepository.findById(id);
    }
}