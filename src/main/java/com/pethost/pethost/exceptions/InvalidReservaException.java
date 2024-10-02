package com.pethost.pethost.exceptions;

public class InvalidReservaException extends RuntimeException {
    public InvalidReservaException() {
        super("Os dados da reserva são inválidos.");
    }
}
