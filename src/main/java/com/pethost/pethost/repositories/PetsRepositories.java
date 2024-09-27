package com.pethost.pethost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pethost.pethost.domain.Pets;



public interface PetsRepositories extends JpaRepository<Pets, Long> {

    Pets  findById(long id);



}
