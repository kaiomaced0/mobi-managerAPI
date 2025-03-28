package br.giraffus.dto;

public record EmpresaDTO(
    String nome,
    UsuarioDTO usuarioDTO,
    Long planoId,
    Long contaId
) {}