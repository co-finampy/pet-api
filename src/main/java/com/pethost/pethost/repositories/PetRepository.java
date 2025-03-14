package com.pethost.pethost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pethost.pethost.domain.Pet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Pet findById(long id);

    // 🔥 CORRIGIDO: Agora carrega o usuário junto com o pet usando JOIN FETCH
    @Query("SELECT p FROM Pet p JOIN FETCH p.usuario WHERE p.usuario.uid = :uid")
    List<Pet> findByOwnerUid(@Param("uid") String uid);
}
