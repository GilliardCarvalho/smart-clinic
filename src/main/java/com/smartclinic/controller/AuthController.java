package com.smartclinic.controller;

import com.smartclinic.model.User;
import com.smartclinic.service.UserService;
import com.smartclinic.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    // Registro de novo usuário
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Usuário já existe."));
        }

        User newUser = userService.registerUser(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(Map.of(
                "message", "Usuário registrado com sucesso!",
                "username", newUser.getUsername()
        ));
    }

    // Autenticação (login)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        boolean authenticated = userService.authenticate(user.getUsername(), user.getPassword());
        if (authenticated) {
            String token = jwtService.generateToken(user.getUsername());
            return ResponseEntity.ok(Map.of(
                    "message", "Login bem-sucedido!",
                    "token", token,
                    "username", user.getUsername()
            ));
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Credenciais inválidas."));
        }
    }
}
