package com.pethost.pethost.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private String idUsuario;
    private String email;
    private String nome;
    private String token;
    private String expiraEm;
}
