package br.giraffus.dto;

public record UsuarioDTO(
    String nome,
    String email,
    String senha,
    String login,
    String cargo
) {}