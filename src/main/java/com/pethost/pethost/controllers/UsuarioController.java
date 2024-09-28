package com.pethost.pethost.controllers;

import com.pethost.pethost.domain.Usuario;
import com.pethost.pethost.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> listarUsuarios() {
        var nome = "esse é nosso endpoint";
        return usuarioService.findAllUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario>listarUsuarioUnico(@PathVariable(value = "id") long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        try{
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.criarUsuario(usuario);
    }

    @PutMapping()
    public Usuario atualizarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(usuario);
    }
}
