package com.pethost.pethost.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaResponseDto {

    private String uid;
    private String uidClient;
    private String uidAnfitriao;
    private String uidPet;
    private String dataEntrada;
    private String dataSaida;
    private String tipoReserva;
    private String valor;
    private String status;
    private String createdAt;

}