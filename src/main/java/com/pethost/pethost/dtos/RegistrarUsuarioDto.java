package com.pethost.pethost.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrarUsuarioDto {
    private String email;
    private String senha;
    private String nome;
}
