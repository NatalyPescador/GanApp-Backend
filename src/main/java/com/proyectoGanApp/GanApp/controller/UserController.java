package com.proyectoGanApp.GanApp.controller;

import com.proyectoGanApp.GanApp.auth.*;
import com.proyectoGanApp.GanApp.service.AuthService;
import com.proyectoGanApp.GanApp.model.UserEntity;
import com.proyectoGanApp.GanApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {
            AuthResponse response = authService.register(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(AuthResponse.builder().errorMessage(e.getMessage()).build());
        }
    }

    @PostMapping("/inicio-sesion")
    public ResponseEntity<AuthResponse> logIn(@RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(AuthResponse.builder().errorMessage(e.getMessage()).build());
        }
    }

    @PostMapping("/olvidar-contraseña")
    public ResponseEntity<AuthResponse> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            AuthResponse response = authService.forgotPassword(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(AuthResponse.builder().errorMessage(e.getMessage()).build());
        }
    }

    @PostMapping("restablecer-contraseña")
    public ResponseEntity<AuthResponse> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            AuthResponse response = authService.resetPassword(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(AuthResponse.builder().errorMessage(e.getMessage()).build());
        }
    }
}
