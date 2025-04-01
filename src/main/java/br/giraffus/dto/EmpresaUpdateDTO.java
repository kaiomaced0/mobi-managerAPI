package br.giraffus.dto;

public record EmpresaUpdateDTO(
    String nome,
    Long planoId,
    Long contaId
) {}