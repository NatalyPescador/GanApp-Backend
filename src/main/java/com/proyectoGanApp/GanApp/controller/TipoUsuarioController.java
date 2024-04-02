package com.proyectoGanApp.GanApp.controller;


import com.proyectoGanApp.GanApp.model.TipoUsuarioEntity;
import com.proyectoGanApp.GanApp.repository.TipoUsuarioRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/GanApp")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @GetMapping("/TipoUsuario")
    public List<TipoUsuarioEntity> listarTipoUsuarios()
    { return tipoUsuarioRepository.findAll(); }

    @PostMapping("/TipoUsuario")
    public TipoUsuarioEntity crearTipoUsuario(@RequestBody TipoUsuarioEntity tipoUsuario){
        return tipoUsuarioRepository.save(tipoUsuario);

    }
}
