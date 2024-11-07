package com.pethost.pethost.exceptions;

public class ReservaNotFoundException extends RuntimeException {
    public ReservaNotFoundException(String s) {
        super("Reserva não encontrada com o UID especificado.");
    }

    public ReservaNotFoundException() {

    }
}
