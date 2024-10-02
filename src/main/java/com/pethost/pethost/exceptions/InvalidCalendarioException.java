package com.pethost.pethost.exceptions;

public class InvalidCalendarioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidCalendarioException() {
        super("Os dados do calendário são inválidos");
    }
}
