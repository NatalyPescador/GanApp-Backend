package com.proyectoGanApp.GanApp.controller;


import com.proyectoGanApp.GanApp.model.CategoriaEntity;
import com.proyectoGanApp.GanApp.repository.CategoriaRepository;
import com.proyectoGanApp.GanApp.repository.UserRepository;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/GanApp")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categoria")
    public List<CategoriaEntity> listarCategoria()
    { return categoriaRepository.findAll(); }

    @GetMapping("/categoria/{typeServiceId}")
    public List<CategoriaEntity> listarCategorias(@PathVariable Long typeServiceId){
        return categoriaRepository.getCategoryByServiceType(typeServiceId);
    }

    @PostMapping("/categoria")
    public CategoriaEntity saveCategoria (@RequestBody CategoriaEntity categoriaEntity){
        return categoriaRepository.save(categoriaEntity);
    }
}