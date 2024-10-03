package com.pethost.pethost.controllers;

import com.pethost.pethost.domain.Pet;
import com.pethost.pethost.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/pets")
@Tag(name = "Rotas de Pets")
@CrossOrigin(origins = "*")
public class PetsController {

    @Autowired
    private PetService petService;

    // Listar todos os pets
    @GetMapping("/listar")
    @Operation(summary = "Listar pets", description = "Responsável por listar todos os pets")
    public ResponseEntity<List<Pet>> listarPets() {
        List<Pet> pets = petService.findAllPets();
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
    @Operation(summary = "Criar pet", description = "Responsável por criar um novo pet")
    public ResponseEntity<Pet> criarPet(@RequestBody Pet pet) {
        Pet createdPet = petService.criarPet(pet);
        return new ResponseEntity<>(createdPet, HttpStatus.CREATED);
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
