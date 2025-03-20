package com.pethost.pethost.repositories;

import com.pethost.pethost.domain.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Long> {

    // 🔍 Buscar um calendário pelo UID do anfitrião (usando relacionamento com Usuario)
    Optional<Calendario> findByUsuarioUid(String uidAnfitriao);
}
