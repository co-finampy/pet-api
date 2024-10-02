package com.pethost.pethost.services;

import com.pethost.pethost.domain.Calendario;
import com.pethost.pethost.domain.Usuario;
import com.pethost.pethost.exceptions.UsuarioNotFoundException;
import com.pethost.pethost.exceptions.InvalidUsuarioException;
import com.pethost.pethost.repositories.CalendarioRepository;
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
    private CalendarioRepository calendarioRepository;

    // Encontrar todos os usuários
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Encontrar um usuário pelo UID
    public Usuario findByUid(String uid) {
        return usuarioRepository.findById(uid).orElseThrow(UsuarioNotFoundException::new);
    }

    // Salvar um novo usuário
    public Usuario save(Usuario usuario) {
        // Verificar se o nome e o e-mail são válidos
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new InvalidUsuarioException();
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new InvalidUsuarioException();
        }

        // Verificar se o Calendario já existe, se não existir, criá-lo
        Calendario calendario = usuario.getDatasDisponiveis();
        if (calendario != null) {
            Optional<Calendario> existingCalendario = calendarioRepository.findByUid(calendario.getUid());
            if (existingCalendario.isEmpty()) {
                calendario = calendarioRepository.save(calendario);
            } else {
                calendario = existingCalendario.get();
            }
            usuario.setDatasDisponiveis(calendario);
        }

        return usuarioRepository.save(usuario);
    }

    // Atualizar um usuário existente
    public Usuario update(String uid, Usuario updatedUsuario) {
        Usuario existingUsuario = findByUid(uid); // Lança exceção se o usuário não for encontrado

        if (updatedUsuario.getNome() == null || updatedUsuario.getNome().isEmpty()) {
            throw new InvalidUsuarioException();
        }
        if (updatedUsuario.getEmail() == null || updatedUsuario.getEmail().isEmpty()) {
            throw new InvalidUsuarioException();
        }

        existingUsuario.setNome(updatedUsuario.getNome());
        existingUsuario.setEmail(updatedUsuario.getEmail());
        existingUsuario.setSenha(updatedUsuario.getSenha());
        existingUsuario.setTelefone(updatedUsuario.getTelefone());
        existingUsuario.setTipoUsuario(updatedUsuario.getTipoUsuario());
        existingUsuario.setEndereco(updatedUsuario.getEndereco());
        existingUsuario.setFotoUrl(updatedUsuario.getFotoUrl());
        existingUsuario.setDatasDisponiveis(updatedUsuario.getDatasDisponiveis());

        return usuarioRepository.save(existingUsuario);
    }

    // Deletar um usuário pelo UID
    public boolean deleteByUid(String uid) {
        Usuario usuario = findByUid(uid); // Lança exceção se o usuário não for encontrado
        usuarioRepository.delete(usuario);
        return true;
    }
}
