package com.pethost.pethost.services;

import com.pethost.pethost.domain.Calendario;
import com.pethost.pethost.domain.Usuario;
import com.pethost.pethost.exceptions.UsuarioNotFoundException;
import com.pethost.pethost.exceptions.InvalidUsuarioException;
import com.pethost.pethost.repositories.CalendarioRepository;
import com.pethost.pethost.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CalendarioRepository calendarioRepository;

    // ✅ Encontrar todos os usuários
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // ✅ Encontrar um usuário pelo UID
    public Usuario findByUid(String uid) {
        return usuarioRepository.findByUid(uid)
                .orElseThrow(() -> new UsuarioNotFoundException());
    }

    // ✅ Encontrar um usuário pelo Email
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNotFoundException());
    }

    // ✅ Criar um novo usuário
    public Usuario save(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new InvalidUsuarioException();
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new InvalidUsuarioException();
        }

        // Verifica e salva o calendário, se houver
        if (usuario.getDatasDisponiveis() != null) {
            Calendario calendario = usuario.getDatasDisponiveis();
            Optional<Calendario> existingCalendario = calendarioRepository.findByUid(calendario.getUid());

            usuario.setDatasDisponiveis(existingCalendario.orElseGet(() -> calendarioRepository.save(calendario)));
        }

        return usuarioRepository.save(usuario);
    }

    // ✅ Atualizar um usuário existente
    public Usuario update(String uid, Usuario updatedUsuario) {
        Usuario existingUsuario = findByUid(uid);

        if (updatedUsuario.getNome() != null && !updatedUsuario.getNome().isEmpty()) {
            existingUsuario.setNome(updatedUsuario.getNome());
        }
        if (updatedUsuario.getEmail() != null && !updatedUsuario.getEmail().isEmpty()) {
            existingUsuario.setEmail(updatedUsuario.getEmail());
        }
        if (updatedUsuario.getSenha() != null) {
            existingUsuario.setSenha(updatedUsuario.getSenha());
        }
        if (updatedUsuario.getTelefone() != null) {
            existingUsuario.setTelefone(updatedUsuario.getTelefone());
        }
        if (updatedUsuario.getTipoUsuario() != null) {
            existingUsuario.setTipoUsuario(updatedUsuario.getTipoUsuario());
        }
        if (updatedUsuario.getEndereco() != null) {
            existingUsuario.setEndereco(updatedUsuario.getEndereco());
        }
        if (updatedUsuario.getFotoUrl() != null) {
            existingUsuario.setFotoUrl(updatedUsuario.getFotoUrl());
        }

        return usuarioRepository.save(existingUsuario);
    }

    // ✅ Deletar um usuário pelo UID
    public boolean deleteByUid(String uid) {
        Usuario usuario = findByUid(uid);
        usuarioRepository.delete(usuario);
        return true;
    }
}
