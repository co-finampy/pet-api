package com.pethost.pethost.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "TB_PETS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private Date dataNascimento;  

    private boolean vacina;
    private boolean castrado;
    private String foto;
    private LocalDateTime criadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;
}
