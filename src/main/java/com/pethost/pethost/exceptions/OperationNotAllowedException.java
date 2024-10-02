package com.pethost.pethost.exceptions;

public class OperationNotAllowedException extends RuntimeException {
    public OperationNotAllowedException() {
        super("Operação não permitida.");
    }
}
