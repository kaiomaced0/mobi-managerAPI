package br.giraffus.dto;

public record ContaDTO(
    String cpf,
    String cnpj,
    String nome,
    String nomeFantasia
) {}