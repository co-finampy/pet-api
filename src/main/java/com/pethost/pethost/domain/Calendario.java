package com.pethost.pethost.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_calendario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calendario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ Agora o banco gera o ID automaticamente
    private Long uid;

    // 🔥 Relacionamento correto: Um calendário pertence a um único usuário (anfitrião)
    @OneToOne
    @JoinColumn(name = "usuario_uid", referencedColumnName = "uid")
    @JsonBackReference
    private Usuario usuario;

    // ✅ Apenas as datas disponíveis (sem horário)
    @ElementCollection
    @CollectionTable(name = "tb_calendario_dias", joinColumns = @JoinColumn(name = "calendario_uid"))
    @Column(name = "dia")
    private List<LocalDate> dias;
}
