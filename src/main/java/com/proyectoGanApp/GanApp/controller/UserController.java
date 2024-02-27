package com.proyectoGanApp.GanApp.controller;

import com.proyectoGanApp.GanApp.model.UserEntity;
import com.proyectoGanApp.GanApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/GanApp")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<UserEntity> listaUsuarios(){
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public UserEntity saveUser(@RequestBody UserEntity user){
        String encodepass = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
        user.setPassword(encodepass);
        return userRepository.save(user);
    }
}
