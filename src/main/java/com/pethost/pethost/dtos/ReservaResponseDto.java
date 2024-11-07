package com.pethost.pethost.dtos;

import java.time.LocalDateTime;

public class ReservaResponseDto {

    private String uid;
    private Long uidClient;
    private Long uidAnfitriao;
    private String uidPet;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private String tipoReserva;
    private String valor;
    private String status;
    private LocalDateTime createdAt;

    public ReservaResponseDto(String uid, Long uidClient, Long uidAnfitriao, String uidPet,
                              LocalDateTime dataEntrada, LocalDateTime dataSaida, String tipoReserva,
                              String valor, String status, LocalDateTime createdAt) {
        this.uid = uid;
        this.uidClient = uidClient;
        this.uidAnfitriao = uidAnfitriao;
        this.uidPet = uidPet;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.tipoReserva = tipoReserva;
        this.valor = valor;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters e Setters
    public String getUid() { return uid; }
    public void setUid(String uid) { this.uid = uid; }

    public Long getUidClient() { return uidClient; }
    public void setUidClient(Long uidClient) { this.uidClient = uidClient; }

    public Long getUidAnfitriao() { return uidAnfitriao; }
    public void setUidAnfitriao(Long uidAnfitriao) { this.uidAnfitriao = uidAnfitriao; }

    public String getUidPet() { return uidPet; }
    public void setUidPet(String uidPet) { this.uidPet = uidPet; }

    public LocalDateTime getDataEntrada() { return dataEntrada; }
    public void setDataEntrada(LocalDateTime dataEntrada) { this.dataEntrada = dataEntrada; }

    public LocalDateTime getDataSaida() { return dataSaida; }
    public void setDataSaida(LocalDateTime dataSaida) { this.dataSaida = dataSaida; }

    public String getTipoReserva() { return tipoReserva; }
    public void setTipoReserva(String tipoReserva) { this.tipoReserva = tipoReserva; }

    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
