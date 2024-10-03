package com.pethost.pethost.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "TB_PETS")
@Data
@NoArgsConstructor
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String tipoPet;

    private String nomePet;

    private String raca;

    private String genero;

    private String tamanho;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;  // Renamed for clarity

    private boolean vacina;

    private boolean castrado;

    private String foto;

    private LocalDateTime criadoEm;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
