package com.proyectoGanApp.GanApp.model;

import com.proyectoGanApp.GanApp.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Users", uniqueConstraints = {@UniqueConstraint(columnNames = {"Correo"})})
public class UserEntity implements UserDetails { //Para trabajar con la autenticacion

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "Nombre_Completo", nullable = false)
    private String nombreCompleto;

    @Column(name = "Correo", nullable = false)
    private String correo;

    @Column(name = "Numero_De_Celular", nullable = false)
    private String numeroTelefono;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    //Metodos que se implementan por la interfaz
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((tipoUsuario.name())));
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
