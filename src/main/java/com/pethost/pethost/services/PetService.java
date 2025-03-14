package com.pethost.pethost.services;

import com.pethost.pethost.domain.Pet;
import com.pethost.pethost.exceptions.PetNotFoundException;
import com.pethost.pethost.exceptions.InvalidPetException;
import com.pethost.pethost.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    // 📌 Método para listar todos os pets
    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    // 📌 Método para listar pets por UID do dono
    public List<Pet> findAllPetsByUid(String uid) {
        return petRepository.findByOwnerUid(uid);
    }

    // 📌 Criar um novo pet
    public Pet criarPet(Pet pet) {
        if (pet.getNomePet() == null || pet.getNomePet().isEmpty()) {
            throw new InvalidPetException();
        }
        return petRepository.save(pet);
    }

    // 📌 Deletar um pet (Agora retorna `true` se for bem-sucedido)
    public boolean deletarPet(long id) {
        if (!petRepository.existsById(id)) {
            throw new PetNotFoundException();
        }
        petRepository.deleteById(id);
        return true;
    }

    // 📌 Atualizar um pet
    public Pet atualizarPet(Pet pet) {
        if (!petRepository.existsById(pet.getId())) {
            throw new PetNotFoundException();
        }
        if (pet.getNomePet() == null || pet.getNomePet().isEmpty()) {
            throw new InvalidPetException();
        }
        return petRepository.save(pet);
    }

    // 📌 Buscar um pet por ID
    public Pet buscarPorId(long id) {
        return petRepository.findById(id);
    }
}
