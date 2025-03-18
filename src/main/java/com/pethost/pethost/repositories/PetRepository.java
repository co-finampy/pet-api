package com.pethost.pethost.repositories;

import com.pethost.pethost.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByUsuarioEmail(String email); // ✅ Agora busca pelo e-mail
}
