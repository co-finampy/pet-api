package com.pethost.pethost.controllers;

import com.pethost.pethost.domain.Usuario;
import com.pethost.pethost.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint de teste
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String teste() {
        return "esse é nosso endpoint";
    }

    // Listar todos os usuários
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Buscar um usuário por UID
    @GetMapping("/buscar/{uid}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long uid) {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioService.findByUid(uid));
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo usuário
    @PostMapping("/criar")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario createdUsuario = usuarioService.save(usuario);
        return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);
    }

    // Atualizar um usuário existente
    @PutMapping("/atualizar/{uid}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long uid, @RequestBody Usuario usuario) {
        Optional<Usuario> updatedUsuario = Optional.ofNullable(usuarioService.update(uid, usuario));
        return updatedUsuario.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar um usuário por UID
    @DeleteMapping("/deletar/{uid}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long uid) {
        boolean isDeleted = usuarioService.deleteByUid(uid);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
