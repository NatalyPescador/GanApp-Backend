package com.proyectoGanApp.GanApp.service;

import com.proyectoGanApp.GanApp.auth.AuthResponse;
import com.proyectoGanApp.GanApp.auth.ForgotPasswordRequest;
import com.proyectoGanApp.GanApp.auth.LoginRequest;
import com.proyectoGanApp.GanApp.auth.RegisterRequest;
import com.proyectoGanApp.GanApp.jwt.JwtService;
import com.proyectoGanApp.GanApp.model.TipoUsuario;
import com.proyectoGanApp.GanApp.model.UserEntity;
import com.proyectoGanApp.GanApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JavaMailSender javaMailSender;

    public AuthResponse forgotPassword(ForgotPasswordRequest request) {
        UserDetails user = userRepository.findByCorreo(request.getCorreo()).orElseThrow();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ganapp2024@gmail.com");
        message.setTo(request.getCorreo());
        message.setSubject("Recuperación de contraseña");
        message.setText("Ingresa el siguiente código para restablecer tu contraseña");

        javaMailSender.send(message);

        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword()));
        UserDetails user = userRepository.findByCorreo(request.getCorreo()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
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

    }
}
