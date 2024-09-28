package com.pethost.pethost.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Column(name = "email")
    @Email(message = "E-mail inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    @Column(name = "senha")
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 3, message = "A senha deve ter no mínimo 3 caracteres")
    private String senha;

    @Column(name = "token")
    private String token;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "tipoUsuario")
    private String tipoUsuario;

    @Column(name = "endereço")
    private String endereco;

    @Column(name = "foto URL")
    private String fotoUrl;

}
