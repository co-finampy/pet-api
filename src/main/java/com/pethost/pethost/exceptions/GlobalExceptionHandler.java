package com.pethost.pethost.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReservaNotFoundException.class)
    public ResponseEntity<String> handleReservaNotFoundException(ReservaNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidReservaException.class)
    public ResponseEntity<String> handleInvalidReservaException(InvalidReservaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CalendarioNotFoundException.class)
    public ResponseEntity<String> handleCalendarioNotFoundException(CalendarioNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIndisponivelException.class)
    public ResponseEntity<String> handleDataIndisponivelException(DataIndisponivelException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<String> handleUsuarioNotFoundException(UsuarioNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUsuarioException.class)
    public ResponseEntity<String> handleInvalidUsuarioException(InvalidUsuarioException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OperationNotAllowedException.class)
    public ResponseEntity<String> handleOperationNotAllowedException(OperationNotAllowedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<String> handleResourceConflictException(ResourceConflictException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
