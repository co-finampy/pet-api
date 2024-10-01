package com.pethost.pethost.domain;

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


    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
