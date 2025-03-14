package com.pethost.pethost.controllers;

import com.pethost.pethost.domain.Pet;
import com.pethost.pethost.dtos.ListarPetsResponseDto;
import com.pethost.pethost.dtos.CriarPetRequestDto;
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

    // 📌 Listar todos os pets (Agora retorna List<Pet> corretamente)
    @GetMapping("/listar")
    @Operation(summary = "Listar pets", description = "Responsável por listar todos os pets")
    public ResponseEntity<List<Pet>> listarPets() {
        List<Pet> pets = petService.findAllPets();
        return ResponseEntity.ok(pets);
    }

    // 📌 Listar todos os pets de um usuário por UID (Agora retorna List<Pet>)
    @GetMapping("{uid}/listar")
    @Operation(summary = "Listar pets por UID do usuário", description = "Responsável por listar todos os pets de um usuário pelo UID")
    public ResponseEntity<List<Pet>> listarPetsByOwnerUid(@PathVariable(value = "uid") String uid) {
        List<Pet> pets = petService.findAllPetsByUid(uid);
        return ResponseEntity.ok(pets);
    }

    // 📌 Criar um novo pet associado a um usuário
    @PostMapping("/criar")
    @Operation(summary = "Criar pet", description = "Responsável por criar um pet associado a um usuário")
    public ResponseEntity<Pet> criarPet(@RequestBody CriarPetRequestDto criarPetRequestDto) {
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
                .usuario(usuarioService.findByUid(criarPetRequestDto.getUidUsuario()))
                .build();

        Pet petSalvo = petService.criarPet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(petSalvo);
    }

    // 📌 Atualizar um pet existente
    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar pet", description = "Responsável por atualizar um pet existente")
    public ResponseEntity<Pet> atualizarPets(@RequestBody Pet petAtualizado) {
        Pet petSalvo = petService.atualizarPet(petAtualizado);
        if (petSalvo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(petSalvo);
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
