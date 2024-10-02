package com.pethost.pethost.exceptions;

public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException() {
        super("Pet não encontrado.");
    }
}
