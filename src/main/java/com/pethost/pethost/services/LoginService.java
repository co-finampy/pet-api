package com.pethost.pethost.services;

import com.pethost.pethost.domain.Usuario;
import com.pethost.pethost.dtos.LoginRequestDto;
import com.pethost.pethost.dtos.RegistrarUsuarioDto;
import com.pethost.pethost.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final UsuarioRepository usuarioRepository;
    private  final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public Usuario registrarUsuario(RegistrarUsuarioDto resgistrarUsuarioDto) {
        Usuario usuario = Usuario.builder()
                .nome(resgistrarUsuarioDto.getNome())
                .senha(passwordEncoder.encode(resgistrarUsuarioDto.getSenha()))
                .email(resgistrarUsuarioDto.getEmail())
                .build();
        return usuarioRepository.save(usuario);
    }

    public Usuario autenticar(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getSenha())
        );
        return usuarioRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow();
    }
}
