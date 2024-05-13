package com.proyectoGanApp.GanApp.controller;

import com.proyectoGanApp.GanApp.dto.*;
import com.proyectoGanApp.GanApp.service.SessionService;
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
    private final SessionService sessionService;

    @GetMapping("/usuarios")
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/registro")
    public ResponseEntity<ResponseDto> signIn(@RequestBody RegisterDto request) {
        try {
            ResponseDto response = sessionService.register(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.builder().errorMessage(e.getMessage()).build());
        }
    }

    @PostMapping("/inicio-sesion")
    public ResponseEntity<ResponseDto> logIn(@RequestBody LoginDto request) {
        try {
            ResponseDto response = sessionService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.builder().errorMessage(e.getMessage()).build());
        }
    }

    @PostMapping("/olvidar-contraseña")
    public ResponseEntity<ResponseDto> forgotPassword(@RequestBody ForgotPasswordDto request) {
        try {
            ResponseDto response = sessionService.forgotPassword(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.builder().errorMessage(e.getMessage()).build());
        }
    }

    @PostMapping("restablecer-contraseña")
    public ResponseEntity<ResponseDto> resetPassword(@RequestBody ResetPasswordDto request) {
        try {
            ResponseDto response = sessionService.resetPassword(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.builder().errorMessage(e.getMessage()).build());
        }
    }
}
