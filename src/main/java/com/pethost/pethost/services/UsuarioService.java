package com.pethost.pethost.services;

import com.pethost.pethost.domain.Calendario;
import com.pethost.pethost.domain.Usuario;
import com.pethost.pethost.dtos.RegistrarUsuarioDto;
import com.pethost.pethost.dtos.UsuarioUpdateDto;
import com.pethost.pethost.exceptions.InvalidUsuarioException;
import com.pethost.pethost.exceptions.UsuarioNotFoundException;
import com.pethost.pethost.repositories.CalendarioRepository;
import com.pethost.pethost.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CalendarioRepository calendarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // ✅ Listar TODOS os usuários
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // ✅ Listar apenas anfitriões
    public List<Usuario> listarAnfitrioes() {
        return usuarioRepository.findByTipoUsuario("ANFITRIAO");
    }

    // ✅ Buscar usuário por UID
    public Usuario findByUid(String uid) {
        return usuarioRepository.findByUid(uid)
                .orElseThrow(() -> new UsuarioNotFoundException());
    }

    // ✅ Buscar usuário por Email
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNotFoundException());
    }

    // ✅ Buscar usuário por CPF
    public Usuario findByCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new UsuarioNotFoundException());
    }

    // ✅ Criar usuário
// ✅ Criar usuário (usuário novo pode começar como ANFITRIÃO)
    public Usuario criarUsuario(RegistrarUsuarioDto dto, boolean isAnfitriao) {
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new InvalidUsuarioException();
        }
        if (dto.getSenha() == null || dto.getSenha().isBlank()) {
            throw new InvalidUsuarioException();
        }
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new InvalidUsuarioException();
        }

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new InvalidUsuarioException();
        }

        // 🔥 Define o tipo de usuário dependendo do formulário de origem
        String tipoUsuario = isAnfitriao ? "ANFITRIAO" : "USUARIO";

        Usuario usuario = Usuario.builder()
                .email(dto.getEmail())
                .senha(passwordEncoder.encode(dto.getSenha())) // 🔒 Criptografa a senha
                .nome(dto.getNome())
                .tipoUsuario(tipoUsuario) // 🔥 Agora pode ser USUARIO ou ANFITRIAO
                .build();

        return usuarioRepository.save(usuario);
    }


    // ✅ Atualizar usuário
    public Usuario update(String uid, UsuarioUpdateDto updatedUsuario) {
        Usuario existingUsuario = findByUid(uid);

        if (updatedUsuario.getNome() != null && !updatedUsuario.getNome().isEmpty()) {
            existingUsuario.setNome(updatedUsuario.getNome());
        }
        if (updatedUsuario.getTelefone() != null) {
            existingUsuario.setTelefone(updatedUsuario.getTelefone());
        }
        if (updatedUsuario.getEndereco() != null) {
            existingUsuario.setEndereco(updatedUsuario.getEndereco());
        }
        if (updatedUsuario.getNumero() != null) {
            existingUsuario.setNumero(updatedUsuario.getNumero());
        }
        if (updatedUsuario.getComplemento() != null) {
            existingUsuario.setComplemento(updatedUsuario.getComplemento());
        }
        if (updatedUsuario.getBairro() != null) {
            existingUsuario.setBairro(updatedUsuario.getBairro());
        }
        if (updatedUsuario.getCidade() != null) {
            existingUsuario.setCidade(updatedUsuario.getCidade());
        }
        if (updatedUsuario.getEstado() != null) {
            existingUsuario.setEstado(updatedUsuario.getEstado());
        }
        if (updatedUsuario.getCep() != null) {
            existingUsuario.setCep(updatedUsuario.getCep());
        }
        if (updatedUsuario.getRg() != null) {
            existingUsuario.setRg(updatedUsuario.getRg());
        }
        if (updatedUsuario.getCpf() != null) {
            existingUsuario.setCpf(updatedUsuario.getCpf());
        }
        if (updatedUsuario.getDataNascimento() != null) {
            existingUsuario.setDataNascimento(updatedUsuario.getDataNascimento());
        }
        if (updatedUsuario.getFotoUrl() != null) {
            existingUsuario.setFotoUrl(updatedUsuario.getFotoUrl());
        }
        if (updatedUsuario.getValorDiaria() != null) {
            existingUsuario.setValorDiaria(updatedUsuario.getValorDiaria());
        }

        // ✅ Atualizar ou Criar o calendário de disponibilidade do usuário
        if (updatedUsuario.getDiasDisponiveis() != null && !updatedUsuario.getDiasDisponiveis().isEmpty()) {
            if (existingUsuario.getCalendario() == null) {
                Calendario novoCalendario = new Calendario();
                novoCalendario.setUsuario(existingUsuario);
                novoCalendario.setDias(updatedUsuario.getDiasDisponiveis());
                existingUsuario.setCalendario(novoCalendario);
            } else {
                existingUsuario.getCalendario().setDias(updatedUsuario.getDiasDisponiveis());
            }
        }

        return usuarioRepository.save(existingUsuario);
    }

    // ✅ Transformar usuário comum em Anfitrião
    public Usuario tornarAnfitriao(String uid, UsuarioUpdateDto dto) {
        Usuario usuario = findByUid(uid);

        // ✅ Atualiza para Anfitrião
        usuario.setTipoUsuario("ANFITRIAO");

        // ✅ Atualiza os demais campos do perfil
        if (dto.getEndereco() != null) usuario.setEndereco(dto.getEndereco());
        if (dto.getNumero() != null) usuario.setNumero(dto.getNumero());
        if (dto.getComplemento() != null) usuario.setComplemento(dto.getComplemento());
        if (dto.getBairro() != null) usuario.setBairro(dto.getBairro());
        if (dto.getCidade() != null) usuario.setCidade(dto.getCidade());
        if (dto.getEstado() != null) usuario.setEstado(dto.getEstado());
        if (dto.getCep() != null) usuario.setCep(dto.getCep());
        if (dto.getRg() != null) usuario.setRg(dto.getRg());
        if (dto.getCpf() != null) usuario.setCpf(dto.getCpf());
        if (dto.getDataNascimento() != null) usuario.setDataNascimento(dto.getDataNascimento());
        if (dto.getValorDiaria() != null) usuario.setValorDiaria(dto.getValorDiaria());
        if (dto.getFotoUrl() != null) usuario.setFotoUrl(dto.getFotoUrl());

        // ✅ Atualizar ou Criar o calendário de disponibilidade do usuário
        if (dto.getDiasDisponiveis() != null && !dto.getDiasDisponiveis().isEmpty()) {
            if (usuario.getCalendario() == null) {
                Calendario novoCalendario = new Calendario();
                novoCalendario.setUsuario(usuario);
                novoCalendario.setDias(dto.getDiasDisponiveis());
                usuario.setCalendario(novoCalendario);
            } else {
                usuario.getCalendario().setDias(dto.getDiasDisponiveis());
            }
        }

        return usuarioRepository.save(usuario);
    }


    // ✅ Deletar usuário por UID
    public void deleteByUid(String uid) {
        Usuario usuario = findByUid(uid);
        usuarioRepository.delete(usuario);
    }
}
