package com.pethost.pethost.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_CALENDARIO")
public class Calendario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String uid;

    private String uidAnfitriao;

    @ElementCollection // Usado para coleções de tipos básicos
    @CollectionTable(name = "TB_CALENDARIO_DIAS", joinColumns = @JoinColumn(name = "calendario_uid"))
    @Column(name = "dia")
    private List<LocalDateTime> dias;

    // Getters e Setters

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUidAnfitriao() {
        return uidAnfitriao;
    }

    public void setUidAnfitriao(String uidAnfitriao) {
        this.uidAnfitriao = uidAnfitriao;
    }

    public List<LocalDateTime> getDias() {
        return dias;
    }

    public void setDias(List<LocalDateTime> dias) {
        this.dias = dias;
    }
}
