package com.pethost.pethost.controllers;

import com.pethost.pethost.domain.Pet;
import com.pethost.pethost.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/pets")
@Tag(name = "Rotas de Pets")
@CrossOrigin(origins = "*")
public class PetsController {

    @Autowired
    PetService petService;

    @GetMapping("")
    @Operation(summary = "Rota de listar pets" , description = "Responsavel por listar  pet ")
    public List<Pet> listarPets() {
        return petService.findAllPets();
    }

    @GetMapping("{id}")
    public ResponseEntity<Pet> listarPetUnico(@PathVariable(value = "id") long id) {
        Pet pet = petService.buscarPorId(id);
        if (pet != null) {
            return ResponseEntity.ok(pet);
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        }
    }

    @PostMapping("")
    public Pet criarPet(@RequestBody Pet pet) {
        return petService.criarPet(pet);
    }

    @DeleteMapping("/{id}")
    public void deletarPets(@PathVariable(value = "id") long id) {
        petService.deletarPet(id);
    }

    @PutMapping("")
    public Pet atualizarPets(@RequestBody Pet pet) {
        return petService.atualizarPet(pet);
    }
}
