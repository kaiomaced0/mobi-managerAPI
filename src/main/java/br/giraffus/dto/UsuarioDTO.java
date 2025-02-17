package br.giraffus.dto;

import java.time.LocalDate;

public record UsuarioDTO(
    String nome,
    String email,
    String login,
    String senha,
    String telefone,
    String cpf,
    LocalDate dataNascimento
) {
}
