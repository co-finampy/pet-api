package com.pethost.pethost.repositories;

import com.pethost.pethost.domain.Calendario; // Ajustado o pacote para o correto
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, String> {
    Optional<Calendario> findByUid(String uid);
}
