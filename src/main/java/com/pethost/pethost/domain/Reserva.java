package com.finampy.pethost.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_RESERVAS")
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 36, nullable = false, unique = true)
    private String uid;

    @Column(length = 36, nullable = false)
    private String uidClient;

    @Column(length = 36, nullable = false)
    private String uidAnfitriao;

    @Column(length = 36, nullable = false)
    private String uidPet;

    @Column(nullable = false)
    private LocalDateTime dataEntrada;

    @Column(nullable = false)
    private LocalDateTime dataSaida;

    @Column(length = 50, nullable = false)
    private String tipoReserva;

    @Column(nullable = false)
    private String valor;

    @Column(length = 20, nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Getters e Setters

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUidClient() {
        return uidClient;
    }

    public void setUidClient(String uidClient) {
        this.uidClient = uidClient;
    }

    public String getUidAnfitriao() {
        return uidAnfitriao;
    }

    public void setUidAnfitriao(String uidAnfitriao) {
        this.uidAnfitriao = uidAnfitriao;
    }

    public String getUidPet() {
        return uidPet;
    }

    public void setUidPet(String uidPet) {
        this.uidPet = uidPet;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(String tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
