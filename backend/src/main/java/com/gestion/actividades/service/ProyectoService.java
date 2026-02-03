package com.gestion.actividades.service;

import com.gestion.actividades.model.Proyecto;
import com.gestion.actividades.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    public List<Proyecto> listarTodos() {
        return proyectoRepository.findAll();
    }

    public Proyecto guardar(Proyecto proyecto) {
        
        return proyectoRepository.save(proyecto);
    }

    public Optional<Proyecto> obtenerPorId(Long id) {
        return proyectoRepository.findById(id);
    }

    public void eliminar(Long id) {
        proyectoRepository.deleteById(id);
    }
}