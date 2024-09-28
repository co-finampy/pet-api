package com.pethost.pethost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pethost.pethost.domain.Pet;



public interface PetRepository extends JpaRepository<Pet, Long> {

    Pet findById(long id);

}
