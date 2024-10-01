package com.pethost.pethost.repositories;

import com.pethost.pethost.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método personalizado para encontrar usuário por UID
    Optional<Usuario> findByUid(String uid);
}