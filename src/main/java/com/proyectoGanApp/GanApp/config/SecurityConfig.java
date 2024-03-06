package com.proyectoGanApp.GanApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration //Metodos que van a estar con la annotation Bean
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //Restringir el acceso a las rutas
        return http
                .csrf(csrf -> csrf.disable()) //Nos pide un token de este tipo es para que no nos lo solicite
                .authorizeHttpRequests(authRequest ->
                        authRequest.requestMatchers("/GanApp/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(withDefaults()).build();
    }
}
