package com.pethost.pethost.services;

import com.pethost.pethost.domain.Pet;
import com.pethost.pethost.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    public Pet criarPet(Pet pet) {
        return petRepository.save(pet);
    }

    public void deletarPet(long id) {
        petRepository.deleteById(id);
    }

    public Pet atualizarPet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet buscarPorId(long id) {
        return petRepository.findById(id);
    }
}
