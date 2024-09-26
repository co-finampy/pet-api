package com.pethost.pethost.controllers;

import com.pethost.pethost.domain.Pets;
import com.pethost.pethost.repositories.PetsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class PetsController {

    @Autowired
    private PetsRepositories petsRepositories;

    @GetMapping("/pets")
    public List<Pets> listarPets() {
        return petsRepositories.findAll();
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<Pets> listarPetUnico(@PathVariable(value = "id") long id) {
        Pets pet = petsRepositories.findById(id);
        if (pet != null) {
            return ResponseEntity.ok(pet);
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        }
    }

    @PostMapping("/pets")
    public Pets salvarPet(@RequestBody Pets pet) {
        return petsRepositories.save(pet);
    }

    @DeleteMapping("/pets")
    public void deletarPets(@RequestBody Pets pets) {
        petsRepositories.delete(pets);
    }

    @PutMapping("/pets")
    public Pets atualizarPets(@RequestBody Pets pets) {
        return petsRepositories.save(pets);
    }
}
