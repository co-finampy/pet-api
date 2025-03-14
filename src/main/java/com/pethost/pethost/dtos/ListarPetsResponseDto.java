package com.pethost.pethost.dtos;

import com.pethost.pethost.domain.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListarPetsResponseDto {
    private Long id;
    private String tipoPet;
    private String nomePet;
    private String raca;
    private String genero;
    private String tamanho;
    private String dataNascimento;
    private Boolean vacina;
    private Boolean castrado;
    private String foto;
    private String criadoEm;
    private String uidUsuario;

    // Método estático para converter um objeto Pet em um DTO
    public static ListarPetsResponseDto fromPet(Pet pet) {
        return ListarPetsResponseDto.builder()
                .id(pet.getId())
                .tipoPet(pet.getTipoPet())
                .nomePet(pet.getNomePet())
                .raca(pet.getRaca())
                .genero(pet.getGenero())
                .tamanho(pet.getTamanho())
                .dataNascimento(Optional.ofNullable(pet.getDataNascimento())
                        .map(Object::toString)
                        .orElse(null)) // 🔥 Evita NullPointerException
                .vacina(pet.getVacina()) // 🔥 Usa `getVacina()` corretamente
                .castrado(pet.getCastrado()) // 🔥 Usa `getCastrado()` corretamente
                .foto(pet.getFoto())
                .criadoEm(Optional.ofNullable(pet.getCriadoEm())
                        .map(dt -> dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .orElse(null)) // 🔥 Formata a data corretamente
                .uidUsuario(Optional.ofNullable(pet.getUsuario())
                        .map(usuario -> usuario.getUid())
                        .orElse(null)) // 🔥 Garante que UID do usuário sempre seja preenchido
                .build();
    }
}
