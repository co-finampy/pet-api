package com.pethost.pethost.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "TB_CALENDARIO")
public class Calendario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "idAnfitriao")
    private Long idAnfitriao;

    @Column(name = "dias")
    private List<LocalDateTime> dias;
}
