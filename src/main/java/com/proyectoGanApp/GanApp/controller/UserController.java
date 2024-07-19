package com.proyectoGanApp.GanApp.controller;

import com.proyectoGanApp.GanApp.dto.*;
import com.proyectoGanApp.GanApp.service.ChatService;
import com.proyectoGanApp.GanApp.service.SessionService;
import com.proyectoGanApp.GanApp.model.UserEntity;
import com.proyectoGanApp.GanApp.repository.UserRepository;
import com.proyectoGanApp.GanApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("/GanApp")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;
    private final SessionService sessionService;

    @Autowired
    private UserService userService;


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

    @GetMapping("/user/findById")
    public UserDto viewUser(@RequestParam Long id){
        logger.info("Buscando usuario por id {}", id);
        try{
            UserDto user = userService.getUserById(id);
            logger.info("Usuario encontrado con exito: {}", user);
            return user;
        }catch(Exception e){
            logger.info("Error al buscar el usuario: {}. El error es: {}", id, e.getMessage());
            return null;
        }
    }
    @PostMapping("/user/upgradeUser")
    public ResponseEntity<String> updateUser(@RequestBody UserDto updatedUser) {
        try {
            userService.updateUser(updatedUser);
            logger.info("Usuario actualizado con éxito: {}", updatedUser);
            return ResponseEntity.ok("Usuario actualizado correctamente");
        } catch (Exception e) {
            logger.error("Error al actualizar el usuario: {}. El error es: {}", updatedUser.getUserId(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el usuario");
        }
    }
}


     

