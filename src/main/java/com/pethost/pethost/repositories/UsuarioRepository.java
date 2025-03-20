package com.pethost.pethost.repositories;

import com.pethost.pethost.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByUid(String uid);  // ✅ Busca pelo UID
    Optional<Usuario> findByEmail(String email); // ✅ Busca pelo Email
    Optional<Usuario> findByCpf(String cpf);  // ✅ Busca pelo CPF
    void deleteByUid(String uid);  // ✅ Excluir usuário pelo UID

    // 🔥 Busca todos os usuários do tipo "ANFITRIAO"
    List<Usuario> findByTipoUsuario(String tipoUsuario);
}
