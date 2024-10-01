package com.pethost.pethost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pethost.pethost.domain.Pet;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findById(Long id);

}
