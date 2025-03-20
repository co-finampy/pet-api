package com.pethost.pethost.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CalendarioDisponibilidadeDto {
    private String uidAnfitriao; // ✅ ID do anfitrião que define as datas disponíveis
    private List<LocalDate> dias; // ✅ Lista de datas disponíveis (Apenas a data, sem horário)

    // ✅ Construtor padrão
    public CalendarioDisponibilidadeDto() {}

    // ✅ Construtor com parâmetros
    public CalendarioDisponibilidadeDto(String uidAnfitriao, List<LocalDate> dias) {
        this.uidAnfitriao = uidAnfitriao;
        this.dias = dias;
    }
}
