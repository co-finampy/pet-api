package com.pethost.pethost.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReservaNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleReservaNotFoundException(ReservaNotFoundException ex) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .nome("Reserva Não Encontrada")
                .codStatus(HttpStatus.NOT_FOUND.value())
                .mensagem(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidReservaException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidReservaException(InvalidReservaException ex) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .nome("Reserva Inválida")
                .codStatus(HttpStatus.BAD_REQUEST.value())
                .mensagem(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CalendarioNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleCalendarioNotFoundException(CalendarioNotFoundException ex) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .nome("Calendário Não Encontrado")
                .codStatus(HttpStatus.NOT_FOUND.value())
                .mensagem(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIndisponivelException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIndisponivelException(DataIndisponivelException ex) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .nome("Data Indisponível")
                .codStatus(HttpStatus.CONFLICT.value())
                .mensagem(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUsuarioNotFoundException(UsuarioNotFoundException ex) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .nome("Usuário Não Encontrado")
                .codStatus(HttpStatus.NOT_FOUND.value())
                .mensagem(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUsuarioException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidUsuarioException(InvalidUsuarioException ex) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .nome("Usuário Inválido")
                .codStatus(HttpStatus.BAD_REQUEST.value())
                .mensagem(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OperationNotAllowedException.class)
    public ResponseEntity<ApiErrorResponse> handleOperationNotAllowedException(OperationNotAllowedException ex) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .nome("Operação Não Permitida")
                .codStatus(HttpStatus.FORBIDDEN.value())
                .mensagem(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceConflictException(ResourceConflictException ex) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .nome("Conflito de Recurso")
                .codStatus(HttpStatus.CONFLICT.value())
                .mensagem(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PetNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlePetNotFoundException(PetNotFoundException ex) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .nome("Pet Não Encontrado")
                .codStatus(HttpStatus.NOT_FOUND.value())
                .mensagem(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
