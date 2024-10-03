package com.pethost.pethost.controllers;

import com.pethost.pethost.domain.Usuario;
import com.pethost.pethost.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/usuarios")
@Tag(name = "Rotas Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar todos os usuários
    @GetMapping("/listar")
    @Operation(summary = "Listar usuarios", description = "Responsável por listar todos os usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Buscar um usuário por UID
    @GetMapping("/buscar/{uid}")
    @Operation(summary = "Buscar usuario por ID", description = "Responsável por buscar um unico usuario pelo ID")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String uid) {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioService.findByUid(uid));
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo usuário
    @PostMapping("/criar")
    @Operation(summary = "Criar usuario", description = "Responsável por criar um usuario")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario createdUsuario = usuarioService.save(usuario);
        return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);
    }

    // Atualizar um usuário existente
    @PutMapping("/atualizar/{uid}")
    @Operation(summary = "Atualizar usuario", description = "Responsável por atualizar um usuario por ID")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable String uid, @RequestBody Usuario usuario) {
        Optional<Usuario> updatedUsuario = Optional.ofNullable(usuarioService.update(uid, usuario));
        return updatedUsuario.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar um usuário por UID
    @DeleteMapping("/deletar/{uid}")
    @Operation(summary = "Deletar usuario", description = "Responsável por deletar um usuario por ID")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String uid) {
        boolean isDeleted = usuarioService.deleteByUid(uid);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
