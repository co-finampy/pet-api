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

    // Método para listar todos os pets
    public List<ListarPetsResponseDto> findAllPets() {
        List<Pet> petsData = petRepository.findAll();
        List<ListarPetsResponseDto> petsResponse = petsData.stream().map(
            pet -> new  ListarPetsResponseDto (
                pet.getId(),
                pet.getTipoPet(),
                pet.getNomePet(),
                pet.getRaca(),
                pet.getGenero(),
                pet.getTamanho(),
                pet.getDataNascimento(),
                pet.getVacina(),
                pet.getCastrado(),
                pet.getFoto(),
                pet.getCriadoEm(),
                pet.getUidUsiario()
                )
            ).collect(Collectors.toList());
        return petsResponse;
    }

    public List<Pet> findAllPetsByUid(String uid) {
        List<Pet> petsData = petRepository.findByOwnerUid(uid);
        List<ListarPetsResponseDto> petsResponse = petsData.stream().map(
            pet -> new  ListarPetsResponseDto (
                pet.getId(),
                pet.getTipoPet(),
                pet.getNomePet(),
                pet.getRaca(),
                pet.getGenero(),
                pet.getTamanho(),
                pet.getDataNascimento(),
                pet.getVacina(),
                pet.getCastrado(),
                pet.getFoto(),
                pet.getCriadoEm(),
                pet.getUidUsiario()
                )
            ).collect(Collectors.toList());
        return petsResponse;
    }

    // Método para criar um novo pet
    public Pet criarPet(Pet pet) {
        if (pet.getNomePet() == null || pet.getNomePet().isEmpty()) {
            throw new InvalidPetException(); // Lança exceção se o nome do pet for inválido
        }
        return petRepository.save(pet);
    }

    // Método para deletar um pet
    public boolean deletarPet(long id) {
        if (!petRepository.existsById(id)) {
            throw new PetNotFoundException(); // Lança exceção se o pet não existir
        }
        petRepository.deleteById(id);
        return false;
    }

    // Método para atualizar um pet
    public ListarPetsResponseDto atualizarPet(Pet pet) {
        if (!petRepository.existsById(pet.getId())) {
            throw new PetNotFoundException(); // Lança exceção se o pet não existir
        }
        if (pet.getNomePet() == null || pet.getNomePet().isEmpty()) {
            throw new InvalidPetException(); // Lança exceção se os dados do pet forem inválidos
        }
        
        Pet petData = petRepository.save(pet);

        ListarPetsResponseDto response = new ListarPetsResponseDto (
                petData.getId(),
                petData.getTipoPet(),
                petData.getNomePet(),
                petData.getRaca(),
                petData.getGenero(),
                petData.getTamanho(),
                petData.getDataNascimento(),
                petData.getVacina(),
                petData.getCastrado(),
                petData.getFoto(),
                petData.getCriadoEm(),
                petData.getUidUsiario()
                );
        return response;
    }

    // Método para buscar um pet por ID
    public Pet buscarPorId(long id) {
        Optional<Pet> pet = Optional.ofNullable(petRepository.findById(id));
        if (pet.isEmpty()) {
            throw new PetNotFoundException(); // Lança exceção se o pet não for encontrado
        }
        return pet.get();
    }
}
