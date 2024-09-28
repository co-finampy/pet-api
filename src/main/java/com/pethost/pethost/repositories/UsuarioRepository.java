package com.pethost.pethost.repositories;

import com.pethost.pethost.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
      Usuario findById(long id);
}
