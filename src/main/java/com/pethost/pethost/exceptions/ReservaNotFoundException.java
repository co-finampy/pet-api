package com.pethost.pethost.exceptions;

public class ReservaNotFoundException extends RuntimeException {
    public ReservaNotFoundException() {
        super("Reserva não encontrada com o UID especificado.");
    }
}
