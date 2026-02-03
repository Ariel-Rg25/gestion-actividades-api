package com.gestion.actividades.controller;

import com.gestion.actividades.model.Usuario;
import com.gestion.actividades.repository.UsuarioRepository;
import com.gestion.actividades.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        
        
        return usuarioRepository.findByUsername(username)
            .map(user -> {
                String token = jwtUtils.generateToken(username);
                return ResponseEntity.ok(Map.of("token", token));
            })
            .orElse(ResponseEntity.status(401).body(Map.of("error", "Usuario no encontrado")));
    }
}