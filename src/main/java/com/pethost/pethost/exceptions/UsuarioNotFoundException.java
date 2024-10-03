package com.pethost.pethost.exceptions;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException() {
        super("Usuário não encontrado.");
    }
}
