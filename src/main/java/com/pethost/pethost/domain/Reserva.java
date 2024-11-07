package com.pethost.pethost.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_RESERVAS")
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 36, nullable = false, unique = true)
    private String uid;

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

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioClient;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioAnfitriao;

}
