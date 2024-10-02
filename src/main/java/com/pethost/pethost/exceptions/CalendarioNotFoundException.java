package com.pethost.pethost.exceptions;

public class CalendarioNotFoundException extends RuntimeException {
    public CalendarioNotFoundException() {
        super("Calendário não encontrado para o anfitrião especificado.");
    }
}
