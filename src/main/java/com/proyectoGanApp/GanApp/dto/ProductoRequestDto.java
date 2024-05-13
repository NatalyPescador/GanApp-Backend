package com.proyectoGanApp.GanApp.dto;


import com.proyectoGanApp.GanApp.model.ProductoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductoRequestDto {

    private MultipartFile file;
    private ProductoEntity product;

}
