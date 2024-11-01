package com.pethost.pethost.controllers;

import com.pethost.pethost.domain.Pet;
import com.pethost.pethost.domain.Usuario;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/pets")
@Tag(name = "Rotas de Pets")
@CrossOrigin(origins = "*")
public class PetsController {

    @Autowired
    private PetService petService;

    @Autowired
    private UsuarioService usuarioService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Listar todos os pets
    @GetMapping("/listar")
    @Operation(summary = "Listar pets", description = "Responsável por listar todos os pets")
    public ResponseEntity<List<Pet>> listarPets() {
        List<Pet> pets = petService.findAllPets();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    // Listar todos os pets baseado em um Uid de usuario
    @GetMapping("{uid}/listar")
    @Operation(summary = "Listar pets por Uid do usuario", description = "Responsável por listar todos os pets baseado em um Uid de usuario")
    public ResponseEntity<List<Pet>> listarPetsByWonerUid(@PathVariable(value = "uid") String uid) {
        List<Pet> pets = petService.findAllPetsByUid(uid);
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    // Buscar um pet por ID
    @GetMapping("/buscar/{id}")
    @Operation(summary = "Buscar pet por ID", description = "Responsável por buscar um único pet pelo ID")
    public ResponseEntity<Pet> listarPetUnico(@PathVariable(value = "id") long id) {
        Optional<Pet> pet = Optional.ofNullable(petService.buscarPorId(id));
        return pet.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo pet
    @PostMapping("/criar")
    @Operation(summary = "Buscar pet por ID", description = "Responsável por buscar um único pet pelo ID")
    public ResponseEntity<Pet> criarPet(@RequestBody CriarPetRequestDto criarPetRequestDto) {
        // Converte CriarPetRequestDto para Pet
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
                .usuario(usuarioService.findByUid(criarPetRequestDto.getUidUsuario())) // Associa o usuário
                .build();

        // Chama o serviço para salvar o pet
        petService.criarPet(pet);

        return ResponseEntity.status(HttpStatus.CREATED).body(pet);
    }

    // Atualizar um pet existente
    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar pet", description = "Responsável por atualizar um pet existente")
    public ResponseEntity<Pet> atualizarPets(@RequestBody Pet pet) {
        Optional<Pet> updatedPet = Optional.ofNullable(petService.atualizarPet(pet));
        return updatedPet.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar um pet por ID
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar pet", description = "Responsável por deletar um pet por ID")
    public ResponseEntity<Void> deletarPets(@PathVariable(value = "id") long id) {
        boolean isDeleted = petService.deletarPet(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
