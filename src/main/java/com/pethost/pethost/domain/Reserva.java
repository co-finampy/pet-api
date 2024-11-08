package com.pethost.pethost.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
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

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(length = 500, nullable = false)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "usuarioClient_id")
    @JsonIgnore
    private Usuario usuarioClient;

    @ManyToOne
    @JoinColumn(name = "usuarioAnfitriao_id")
    @JsonIgnore
    private Usuario usuarioAnfitriao;

    @PrePersist
    public void prePersist() {
        if (this.uid == null || this.uid.isEmpty()) {
            this.uid = UUID.randomUUID().toString();
        }
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
