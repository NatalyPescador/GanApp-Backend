package com.proyectoGanApp.GanApp.service;

import com.proyectoGanApp.GanApp.auth.*;
import com.proyectoGanApp.GanApp.jwt.JwtService;
import com.proyectoGanApp.GanApp.model.PasswordResetToken;
import com.proyectoGanApp.GanApp.model.TipoUsuario;
import com.proyectoGanApp.GanApp.model.UserEntity;
import com.proyectoGanApp.GanApp.repository.PasswordResetTokenRepository;
import com.proyectoGanApp.GanApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JavaMailSender javaMailSender;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public AuthResponse resetPassword(ResetPasswordRequest request) {
        if (!request.getNewPassword().equals(request.getConfirmedPassword())) {
            throw new RuntimeException("Las contraseñas no coinciden");
        }

        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(request.getToken());

        UserEntity usuario = passwordResetToken.getUsuario();
        usuario.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(usuario);

        passwordResetTokenRepository.delete(passwordResetToken);

        String token = jwtService.getToken(usuario);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse forgotPassword(ForgotPasswordRequest request) {
        UserEntity user = userRepository.findByCorreo(request.getCorreo()).orElseThrow();

        String randomToken = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(randomToken);
        passwordResetToken.setUsuario(user);
        passwordResetToken.setExpiryDate(new Date(System.currentTimeMillis() + 3600000)); // Caduca en 1 hora
        passwordResetTokenRepository.save(passwordResetToken);

        SimpleMailMessage message = getSimpleMailMessage(request, user, randomToken);

        javaMailSender.send(message);

        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    private static SimpleMailMessage getSimpleMailMessage(ForgotPasswordRequest request, UserEntity user, String randomToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ganapp2024@gmail.com");
        message.setTo(request.getCorreo());
        message.setSubject("Recuperación de contraseña");
        message.setText("Señor(a) usuario(a) " + user.getNombreCompleto() + "\n\n\n" +
                "Hemos recibido una solicitud para restablecer su contraseña de acceso a GanApp.\n\n" +
                "Por su seguridad hemos generado una contraseña temporal que le permitirá cambiar su contraseña\n\n" +
                "Ingrese el siguiente código para restablecer tu contraseña: " + randomToken + "\n\n" +
                "Tenga en cuenta que este código solamente está disponible por un lapso de 1 hora.\n\n\n" +
                "Cordialmente,\n" +
                "Equipo de soporte GanApp");
        return message;
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword()));
        UserDetails user = userRepository.findByCorreo(request.getCorreo()).orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrecto"));
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        try {
            UserEntity user = UserEntity.builder()
                    .nombreCompleto(request.getNombreCompleto())
                    .correo(request.getCorreo())
                    .numeroTelefono(request.getNumeroTelefono())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .tipoUsuario(TipoUsuario.USER)
                    .build();

            userRepository.save(user);

            return AuthResponse.builder()
                    .token(jwtService.getToken(user))
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("El correo ingresado ya se encuentra registrado");
        }
    }
}
