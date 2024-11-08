package com.pethost.pethost.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarReservaRequestDto {

    private String uid;              // Campo para o UID fornecido manualmente
    private String uidClient;
    private String uidAnfitriao;
    private String uidPet;
    private String dataEntrada;
    private String dataSaida;
    private String tipoReserva;
    private String valor;
    private String status;
    private String createdAt;
    private String observacoes;
}
