package com.pethost.pethost.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarPetRequestDto {
    private String tipoPet;
    private String nomePet;
    private String raca;
    private String genero;
    private String tamanho;
    private String dataNascimento;
    private Boolean vacina;
    private Boolean castrado;
    private String foto;
    private String criadoEm;
    private String uidUsuario;
}
