package com.smartclinic.controller;

import com.smartclinic.model.User;
import com.smartclinic.repository.UserRepository;
import com.smartclinic.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginData) {
        Optional<User> optionalUser = userRepository.findByUsername(loginData.getUsername());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            boolean passwordMatch = BCrypt.checkpw(loginData.getPassword(), user.getPassword());

            if (passwordMatch) {
                String token = jwtService.generateToken(user.getUsername());
                return ResponseEntity.ok(Map.of("token", token));
            }
        }

        return ResponseEntity.status(401).body(Map.of("message", "Credenciais inválidas"));
    }


}
