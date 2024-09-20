package com.pethost.pethost.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String teste() {
        var nome = "esse é nosso endpoint";
        return nome;
    }
}
