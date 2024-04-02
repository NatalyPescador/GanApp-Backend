package com.proyectoGanApp.GanApp.controller;


import com.proyectoGanApp.GanApp.model.TipoServicioEntity;
import com.proyectoGanApp.GanApp.repository.TipoServicioRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/GanApp")
public class TipoServicioController {

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    @GetMapping("/TipoServicio")
    public List<TipoServicioEntity> listarservicios()
    { return tipoServicioRepository.findAll(); }

    @PostMapping("/TipoServicio")
    public TipoServicioEntity crearServicio(@RequestBody TipoServicioEntity TipoServicio){
        return tipoServicioRepository.save(TipoServicio);

    }
}
