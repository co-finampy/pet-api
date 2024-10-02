package com.pethost.pethost.exceptions;

public class DataIndisponivelException extends RuntimeException {
    public DataIndisponivelException() {
        super("A data selecionada está indisponível.");
    }
}
