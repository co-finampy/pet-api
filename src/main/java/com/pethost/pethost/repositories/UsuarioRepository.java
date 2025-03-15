package com.pethost.pethost.repositories;

import com.pethost.pethost.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByUid(String uid); // ✅ Busca pelo UID
    Optional<Usuario> findByEmail(String email); // ✅ Agora busca pelo Email também
}
