package com.proyectoGanApp.GanApp.controller;


import com.proyectoGanApp.GanApp.model.UserEntity;
import com.proyectoGanApp.GanApp.repository.UserRepository;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/GanApp")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<UserEntity> listarUsuarios()
    { return userRepository.findAll(); }

    @PostMapping("/users")
    public UserEntity saveUser (@RequestBody UserEntity userEntity){
        String encodepass = Base64.getEncoder().encodeToString(userEntity.getPassword().getBytes());
        userEntity.setPassword(encodepass);
        return userRepository.save(userEntity);
    }
}
