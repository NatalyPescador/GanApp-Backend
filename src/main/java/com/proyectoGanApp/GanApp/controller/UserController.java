package com.proyectoGanApp.GanApp.controller;

import com.proyectoGanApp.GanApp.auth.AuthResponse;
import com.proyectoGanApp.GanApp.auth.AuthService;
import com.proyectoGanApp.GanApp.auth.LoginRequest;
import com.proyectoGanApp.GanApp.auth.RegisterRequest;
import com.proyectoGanApp.GanApp.model.UserEntity;
import com.proyectoGanApp.GanApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/GanApp")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private final AuthService authService;

    @GetMapping("/users")
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public UserEntity saveUser(@RequestBody UserEntity userEntity){
        String encodepass = Base64.getEncoder().encodeToString(userEntity.getPassword().getBytes());
        userEntity.setPassword(encodepass);
        return userRepository.save(userEntity);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> logIn(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signIn(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

}
