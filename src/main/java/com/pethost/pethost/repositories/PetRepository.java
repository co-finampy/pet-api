package com.pethost.pethost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pethost.pethost.domain.Pet;
import org.springframework.stereotype.Repository;


@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Pet findById(long id);
}
