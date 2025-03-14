package com.pethost.pethost.controllers;

import com.pethost.pethost.domain.Pet;
import com.pethost.pethost.dtos.CriarPetRequestDto;
import com.pethost.pethost.dtos.ListarPetsResponseDto;
import com.pethost.pethost.services.PetService;
import com.pethost.pethost.services.UsuarioService;
import com.pethost.pethost.utils.DataUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/pets")
@Tag(name = "Rotas de Pets")
@CrossOrigin(origins = "*")
public class PetsController {

    @Autowired
    private PetService petService;

    @Autowired
    private UsuarioService usuarioService;

    // 📌 Listar todos os pets
    @GetMapping("/listar")
    @Operation(summary = "Listar pets", description = "Responsável por listar todos os pets")
    public ResponseEntity<List<ListarPetsResponseDto>> listarPets() {
        List<Pet> pets = petService.findAllPets();

        // 🔥 Converte para DTO antes de retornar a resposta
        List<ListarPetsResponseDto> responseDtos = pets.stream()
                .map(ListarPetsResponseDto::fromPet)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDtos);
    }

    // 📌 Listar todos os pets de um usuário por UID
    @GetMapping("{uid}/listar")
    @Operation(summary = "Listar pets por UID do usuário", description = "Responsável por listar todos os pets de um usuário pelo UID")
    public ResponseEntity<List<ListarPetsResponseDto>> listarPetsByOwnerUid(@PathVariable(value = "uid") String uid) {
        List<Pet> pets = petService.findAllPetsByUid(uid);

        // 🔥 Converte para DTO antes de retornar a resposta
        List<ListarPetsResponseDto> responseDtos = pets.stream()
                .map(ListarPetsResponseDto::fromPet)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDtos);
    }

    // 📌 Criar um novo pet associado a um usuário
    @PostMapping("/criar")
    @Operation(summary = "Criar pet", description = "Responsável por criar um pet associado a um usuário")
    public ResponseEntity<ListarPetsResponseDto> criarPet(@RequestBody CriarPetRequestDto criarPetRequestDto) {
        Pet pet = Pet.builder()
                .tipoPet(criarPetRequestDto.getTipoPet())
                .nomePet(criarPetRequestDto.getNomePet())
                .raca(criarPetRequestDto.getRaca())
                .genero(criarPetRequestDto.getGenero())
                .tamanho(criarPetRequestDto.getTamanho())
                .dataNascimento(DataUtils.converterData(criarPetRequestDto.getDataNascimento()))
                .vacina(criarPetRequestDto.getVacina())
                .castrado(criarPetRequestDto.getCastrado())
                .foto(criarPetRequestDto.getFoto())
                .criadoEm(LocalDateTime.now())
                .usuario(usuarioService.findByUid(criarPetRequestDto.getUidUsuario())) // 🔥 Associa o usuário corretamente
                .build();

        // Salva no banco de dados
        Pet petSalvo = petService.criarPet(pet);

        // 🔥 Retorna o DTO convertido
        return ResponseEntity.status(HttpStatus.CREATED).body(ListarPetsResponseDto.fromPet(petSalvo));
    }

    // 📌 Atualizar um pet existente
    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar pet", description = "Responsável por atualizar um pet existente")
    public ResponseEntity<ListarPetsResponseDto> atualizarPets(@RequestBody Pet petAtualizado) {
        Optional<Pet> petExistente = Optional.ofNullable(petService.buscarPorId(petAtualizado.getId()));

        if (petExistente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Pet pet = petExistente.get();

        // 🔥 Mantém o usuário original para evitar perda de relacionamento
        pet.setUsuario(petExistente.get().getUsuario());

        // Atualiza os campos do pet

        // Atualiza os campos do pet
        pet.setTipoPet(petAtualizado.getTipoPet()); // 🔥 AGORA `tipoPet` É ATUALIZADO!
        pet.setNomePet(petAtualizado.getNomePet());
        pet.setRaca(petAtualizado.getRaca());
        pet.setGenero(petAtualizado.getGenero());
        pet.setTamanho(petAtualizado.getTamanho());
        pet.setDataNascimento(petAtualizado.getDataNascimento());
        pet.setVacina(petAtualizado.getVacina());
        pet.setCastrado(petAtualizado.getCastrado());
        pet.setFoto(petAtualizado.getFoto());


        // Salva as alterações
        Pet petSalvo = petService.atualizarPet(pet);

        // 🔥 Retorna o DTO atualizado
        return ResponseEntity.ok(ListarPetsResponseDto.fromPet(petSalvo));
    }

    // 📌 Deletar um pet por ID
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar pet", description = "Responsável por deletar um pet por ID")
    public ResponseEntity<Void> deletarPets(@PathVariable(value = "id") long id) {
        boolean isDeleted = petService.deletarPet(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
