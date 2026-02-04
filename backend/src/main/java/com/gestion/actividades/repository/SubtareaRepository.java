package com.gestion.actividades.repository;

import com.gestion.actividades.model.Subtarea;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubtareaRepository extends JpaRepository<Subtarea, Long> {
    List<Subtarea> findByTareaId(Long tareaId);
}