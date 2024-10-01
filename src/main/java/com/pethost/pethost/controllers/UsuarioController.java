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
        var nome = "esse é nosso endpoint";
        return nome;
    }

    // Buscar todos os usuários
    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Buscar um usuário por UID
    @GetMapping("/{uid}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String uid) {
        Optional<Usuario> usuario = usuarioService.findByUid(uid);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo usuário
    @PostMapping("/create")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario createdUsuario = usuarioService.save(usuario);
        return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);
    }

    // Atualizar um usuário existente
    @PutMapping("/update/{uid}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable String uid, @RequestBody Usuario usuario) {
        Optional<Usuario> updatedUsuario = usuarioService.update(uid, usuario);
        return updatedUsuario.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar um usuário por UID
    @DeleteMapping("/delete/{uid}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String uid) {
        boolean isDeleted = usuarioService.deleteByUid(uid);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
