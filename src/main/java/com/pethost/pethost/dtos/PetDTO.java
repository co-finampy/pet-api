package com.pethost.pethost.dtos;

import com.pethost.pethost.domain.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {
    private Long id;
    private String tipoPet;
    private String nomePet;
    private String raca;
    private String genero;
    private String tamanho;
    private LocalDate dataNascimento;
    private boolean vacina;
    private boolean castrado;
    private String foto;
    private LocalDateTime criadoEm;
    private String uidUsuario; // 🔥 Vincula ao UID do usuário dono do pet

    // ✅ Adicionando um novo construtor que aceita um objeto `Pet`
    public PetDTO(Pet pet) {
        this.id = pet.getId();
        this.tipoPet = pet.getTipoPet();
        this.nomePet = pet.getNomePet();
        this.raca = pet.getRaca();
        this.genero = pet.getGenero();
        this.tamanho = pet.getTamanho();
        this.dataNascimento = pet.getDataNascimento();
        this.vacina = pet.isVacina();
        this.castrado = pet.isCastrado();
        this.foto = pet.getFoto();
        this.criadoEm = pet.getCriadoEm();
        this.uidUsuario = pet.getUsuario() != null ? pet.getUsuario().getUid() : null;
    }
}
