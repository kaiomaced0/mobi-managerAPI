package br.giraffus.dto;

public record PlanoDTO(
    String nome,
    Double preco,
    String descricao,
    Integer maxUsuarios,
    Integer maxContratos,
    Integer maxImoveis,
    Integer maxArmazenamentoDocumentosMb
) {}