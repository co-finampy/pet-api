package com.finampy.pethost.repository;

import com.finampy.pethost.domain.Pets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetsRepository extends JpaRepository<Pets, Long> {

}

