package com.gestion.actividades.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // Esto genera getters y setters automáticamente gracias a Lombok
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    
    private String email;
}