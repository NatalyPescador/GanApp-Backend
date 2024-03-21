package com.proyectoGanApp.GanApp.controller;

import com.proyectoGanApp.GanApp.auth.AuthResponse;
import com.proyectoGanApp.GanApp.auth.ForgotPasswordRequest;
import com.proyectoGanApp.GanApp.service.AuthService;
import com.proyectoGanApp.GanApp.auth.LoginRequest;
import com.proyectoGanApp.GanApp.auth.RegisterRequest;
import com.proyectoGanApp.GanApp.model.UserEntity;
import com.proyectoGanApp.GanApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/GanApp")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private final AuthService authService;

    @GetMapping("/usuarios")
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> signIn(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/iniciosesion")
    public ResponseEntity<AuthResponse> logIn(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("recuperarcontraseña")
    public ResponseEntity<AuthResponse> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return ResponseEntity.ok(authService.forgotPassword(request));

    }
}
