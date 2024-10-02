package com.pethost.pethost.exceptions;

public class ResourceConflictException extends RuntimeException {
    public ResourceConflictException() {
        super("Conflito de recurso: a ação não pode ser completada devido a um conflito.");
    }
}
