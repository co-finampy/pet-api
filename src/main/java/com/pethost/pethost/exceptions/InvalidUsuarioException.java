package com.pethost.pethost.exceptions;

public class InvalidUsuarioException extends RuntimeException {
    public InvalidUsuarioException() {
        super("Os dados do usuário são inválidos.");
    }
}
