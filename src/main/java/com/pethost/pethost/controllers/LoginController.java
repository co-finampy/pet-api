package com.pethost.pethost.controllers;

import com.pethost.pethost.config.security.JwtService;
import com.pethost.pethost.domain.Usuario;
import com.pethost.pethost.dtos.RegistrarUsuarioDto;
import com.pethost.pethost.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;
    private final JwtService jwtService;

    @PostMapping("/registrar")
    public ResponseEntity<RegistrarUsuarioDto> registrar(@RequestBody RegistrarUsuarioDto registrarUsuarioDto){
        Usuario usuarioRegistrado = loginService.registrarUsuario(registrarUsuarioDto);
        RegistrarUsuarioDto usuarioDto = RegistrarUsuarioDto.builder()
                .email(registrarUsuarioDto.getEmail())
                .nome(registrarUsuarioDto.getNome())
                .senha(registrarUsuarioDto.getSenha())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDto);
    }

}
