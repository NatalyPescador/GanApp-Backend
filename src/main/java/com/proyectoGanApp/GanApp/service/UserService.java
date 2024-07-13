package com.proyectoGanApp.GanApp.service;

import com.proyectoGanApp.GanApp.controller.UserController;
import com.proyectoGanApp.GanApp.dto.UserDto;
import com.proyectoGanApp.GanApp.model.UserEntity;
import com.proyectoGanApp.GanApp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    public UserDto getUserById(Long id){
        logger.info("Inicio UserService busqueda de usuario por id: {}", id);
        try {
            UserEntity user = userRepository.getReferenceById(id);
            UserDto userDto = new UserDto();
            userDto.setUserId(user.getUserId());
            userDto.setNombreCompleto(user.getNombreCompleto());
            userDto.setCorreo(user.getCorreo());
            userDto.setNumeroTelefono(user.getNumeroTelefono());
            return userDto;
        } catch (Exception e){
            logger.info("Error al buscar usuario desde el UserService: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    public void updateUser(UserDto userDto) {
        logger.info("Inicio UserService actualización de usuario con id: {}", id);
        try {
            Optional<UserEntity> userEntityOptional = userRepository.findById(userDto.getUserId());
            if (userEntityOptional.isPresent()) {
                UserEntity userEntity = userEntityOptional.get();
                userEntity.setNombreCompleto(userDto.getNombreCompleto());
                userEntity.setCorreo(userDto.getCorreo());
                userEntity.setNumeroTelefono(userDto.getNumeroTelefono());
                userRepository.save(userEntity);
                logger.info("Usuario actualizado con éxito: {}", userEntity);
            } else {
                logger.error("Usuario no encontrado con id: {}", id);
                throw new RuntimeException("Usuario no encontrado");
            }
        } catch (Exception e) {
            logger.error("Error al actualizar usuario: {}. Error: {}", id, e.getMessage());
            throw new RuntimeException("Error al actualizar usuario");
        }
    }
}
