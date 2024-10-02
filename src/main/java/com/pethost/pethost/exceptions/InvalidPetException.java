package com.pethost.pethost.exceptions;

public class InvalidPetException extends RuntimeException {
    public InvalidPetException() {
        super("Dados do pet inválidos.");
    }
}
