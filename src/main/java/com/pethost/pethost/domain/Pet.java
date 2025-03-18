package com.pethost.pethost.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PETS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // ✅ Permite criar objetos com um builder
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tipoPet;
    private String nomePet;
    private String raca;
    private String genero;
    private String tamanho;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    private boolean vacina;
    private boolean castrado;
    private String foto;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference // 🔥 Evita loops de serialização
    private Usuario usuario;

    // ✅ Define `criadoEm` automaticamente antes de salvar no banco
    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
    }
}
