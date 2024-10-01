package com.pethost.pethost.services;

import com.pethost.pethost.domain.Calendario;
import com.pethost.pethost.domain.Usuario;
import com.pethost.pethost.repositories.CalendarioRepository; // Certifique-se de ter esse repositório
import com.pethost.pethost.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CalendarioRepository calendarioRepository; // Adicione o repositório de Calendario

    // Encontrar todos os usuários
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Encontrar um usuário pelo UID
    public Optional<Usuario> findByUid(String uid) {
        return usuarioRepository.findByUid(uid);
    }

    // Salvar um novo usuário
    public Usuario save(Usuario usuario) {
        // Verificar se o Calendario já existe, se não existir, criá-lo
        Calendario calendario = usuario.getDatasDisponiveis();
        if (calendario != null) {
            // Verificar se já existe um calendário com o mesmo UID
            Optional<Calendario> existingCalendario = calendarioRepository.findByUid(calendario.getUid());
            if (existingCalendario.isEmpty()) {
                // Se não existir, salvar o novo calendário
                calendario = calendarioRepository.save(calendario);
            } else {
                // Se existir, associá-lo ao usuário
                calendario = existingCalendario.get();
            }
            usuario.setDatasDisponiveis(calendario); // Atribuir o calendário ao usuário
        }

        return usuarioRepository.save(usuario);
    }

    // Atualizar um usuário existente
    public Optional<Usuario> update(String uid, Usuario updatedUsuario) {
        return usuarioRepository.findByUid(uid).map(existingUsuario -> {
            existingUsuario.setNome(updatedUsuario.getNome());
            existingUsuario.setEmail(updatedUsuario.getEmail());
            existingUsuario.setSenha(updatedUsuario.getSenha());
            existingUsuario.setTelefone(updatedUsuario.getTelefone());
            existingUsuario.setTipoUsuario(updatedUsuario.getTipoUsuario());
            existingUsuario.setEndereco(updatedUsuario.getEndereco());
            existingUsuario.setFotoUrl(updatedUsuario.getFotoUrl());
            existingUsuario.setDatasDisponiveis(updatedUsuario.getDatasDisponiveis());
            return usuarioRepository.save(existingUsuario);
        });
    }

    // Deletar um usuário pelo UID
    public boolean deleteByUid(String uid) {
        return usuarioRepository.findByUid(uid).map(usuario -> {
            usuarioRepository.delete(usuario);
            return true;
        }).orElse(false);
    }
}
