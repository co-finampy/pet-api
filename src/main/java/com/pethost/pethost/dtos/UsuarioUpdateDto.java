package com.pethost.pethost.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UsuarioUpdateDto {
    private String nome;
    private String telefone;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String rg;
    private String cpf;
    private LocalDate dataNascimento;
    private String fotoUrl;
    private BigDecimal valorDiaria;

    // ✅ Correção: Apenas a lista de datas disponíveis
    private List<LocalDate> diasDisponiveis;
}
