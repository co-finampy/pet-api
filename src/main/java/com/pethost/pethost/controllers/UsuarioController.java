package com.pethost.pethost.controllers;

import com.pethost.pethost.domain.Usuario;
import com.pethost.pethost.dtos.RegistrarUsuarioDto;
import com.pethost.pethost.dtos.UsuarioUpdateDto;
import com.pethost.pethost.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
@Tag(name = "Rotas Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    @Operation(summary = "Listar usuários", description = "Responsável por listar todos os usuários")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/buscar/id/{uid}")
    @Operation(summary = "Buscar usuário por ID", description = "Responsável por buscar um único usuário pelo ID")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String uid) {
        Usuario usuario = usuarioService.findByUid(uid);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/buscar/email/{email}")
    @Operation(summary = "Buscar usuário por Email", description = "Responsável por buscar um único usuário pelo Email")
    public ResponseEntity<Usuario> getUsuarioByEmail(@PathVariable String email) {
        Usuario usuario = usuarioService.findByEmail(email);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/buscar/cpf/{cpf}")
    @Operation(summary = "Buscar usuário por CPF", description = "Responsável por buscar um único usuário pelo CPF")
    public ResponseEntity<Usuario> getUsuarioByCpf(@PathVariable String cpf) {
        Usuario usuario = usuarioService.findByCpf(cpf);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/tornar-anfitriao/{uid}")
    @Operation(summary = "Tornar usuário um anfitrião", description = "Atualiza um usuário para o tipo ANFITRIÃO")
    public ResponseEntity<Usuario> tornarAnfitriao(
            @PathVariable String uid,
            @RequestBody UsuarioUpdateDto usuarioDto) {

        Usuario usuarioAtualizado = usuarioService.tornarAnfitriao(uid, usuarioDto);
        return ResponseEntity.ok(usuarioAtualizado);
    }


    @PostMapping("/criar")
    @Operation(summary = "Criar usuário", description = "Responsável por criar um usuário")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody RegistrarUsuarioDto usuarioDto) {
        Usuario createdUsuario = usuarioService.criarUsuario(usuarioDto, false); // Cria como USUARIO
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
    }

    @PostMapping("/criar-anfitriao")
    @Operation(summary = "Criar usuário como anfitrião", description = "Criar um novo usuário diretamente como anfitrião")
    public ResponseEntity<Usuario> criarUsuarioAnfitriao(@RequestBody RegistrarUsuarioDto usuarioDto) {
        Usuario createdUsuario = usuarioService.criarUsuario(usuarioDto, true); // Cria como ANFITRIAO
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
    }

    @PutMapping("/atualizar/{uid}")
    @Operation(summary = "Atualizar usuário", description = "Responsável por atualizar um usuário por ID")
    public ResponseEntity<Usuario> atualizarUsuario(
            @PathVariable String uid,
            @RequestBody UsuarioUpdateDto usuarioDto) {

        Usuario updatedUsuario = usuarioService.update(uid, usuarioDto);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/deletar/{uid}")
    @Operation(summary = "Deletar usuário", description = "Responsável por deletar um usuário por ID")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String uid) {
        usuarioService.deleteByUid(uid);
        return ResponseEntity.noContent().build();
    }
}
