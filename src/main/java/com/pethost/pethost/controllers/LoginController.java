package com.pethost.pethost.controllers;

import com.pethost.pethost.config.security.JwtService;
import com.pethost.pethost.domain.Usuario;
import com.pethost.pethost.dtos.LoginRequestDto;
import com.pethost.pethost.dtos.LoginResponseDto;
import com.pethost.pethost.dtos.RegistrarUsuarioDto;
import com.pethost.pethost.services.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "Rotas de login")
@CrossOrigin("*")
public class LoginController {

    private final LoginService loginService;
    private final JwtService jwtService;

    @PostMapping("/registrar")
    @Operation(summary = "Registrar Usuario", description = "Responsável por registrar um usuario parcial")
    public ResponseEntity<RegistrarUsuarioDto> registrar(@RequestBody RegistrarUsuarioDto registrarUsuarioDto){
        Usuario usuarioRegistrado = loginService.registrarUsuario(registrarUsuarioDto);

        RegistrarUsuarioDto usuarioDto = RegistrarUsuarioDto.builder()
                .email(registrarUsuarioDto.getEmail())
                .nome(registrarUsuarioDto.getNome())
                .senha(registrarUsuarioDto.getSenha())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDto);
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Responsável por autenticar usuario")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        Usuario usuarioAutenticado = loginService.autenticar(loginRequestDto);
        String jwtToken = jwtService.generateToken(usuarioAutenticado);

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .idUsuario(usuarioAutenticado.getUid())
                .email(usuarioAutenticado.getEmail())
                .nome(usuarioAutenticado.getNome())
                .token(jwtToken)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
    }
}
