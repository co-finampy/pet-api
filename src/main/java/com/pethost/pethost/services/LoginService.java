package com.pethost.pethost.services;

import com.pethost.pethost.domain.Usuario;
import com.pethost.pethost.dtos.LoginRequestDto;
import com.pethost.pethost.dtos.RegistrarUsuarioDto;
import com.pethost.pethost.exceptions.InvalidLoginException;
import com.pethost.pethost.exceptions.InvalidUsuarioException;
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

        if (usuarioRepository.findByEmail(resgistrarUsuarioDto.getEmail()).isPresent()) {
            throw new InvalidUsuarioException();
        }
        Usuario usuario = Usuario.builder()
                .nome(resgistrarUsuarioDto.getNome())
                .senha(passwordEncoder.encode(resgistrarUsuarioDto.getSenha()))
                .email(resgistrarUsuarioDto.getEmail())
                .build();
        return usuarioRepository.save(usuario);
    }

    public Usuario autenticar(LoginRequestDto loginRequestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getSenha())
            );
        } catch (Exception e) {
            throw new InvalidLoginException("Falha na autenticação. Verifique suas credenciais.");
        }
        return usuarioRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                () -> new InvalidLoginException("Usuário não encontrado com o email: " + loginRequestDto.getEmail())
        );
    }
}
