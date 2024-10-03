package com.pethost.pethost.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiErrorResponse {
    private String nome;
    private Integer codStatus;
    private String mensagem;
}
