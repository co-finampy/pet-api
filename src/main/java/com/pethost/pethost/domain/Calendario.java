package com.pethost.pethost.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "TB_CALENDARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calendario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long uid;

    private String uidAnfitriao;

    @ElementCollection // Usado para coleções de tipos básicos
    @CollectionTable(name = "TB_CALENDARIO_DIAS", joinColumns = @JoinColumn(name = "calendario_uid"))
    @Column(name = "dia")
    private List<LocalDateTime> dias;
}