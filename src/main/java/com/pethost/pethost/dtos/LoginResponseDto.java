package com.pethost.pethost.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private Long idUsuario;
    private String email;
    private String senha;
    private String nome;
    private String token;
}