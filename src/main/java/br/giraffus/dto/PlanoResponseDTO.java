package br.giraffus.dto;

import br.giraffus.model.Plano;

public record PlanoResponseDTO(
    Long id,
    String nome,
    Double preco,
    String descricao,
    Integer maxUsuarios,
    Integer maxContratos,
    Integer maxImoveis,
    Integer maxArmazenamentoDocumentosMb
) {
    public static PlanoResponseDTO toDTO(Plano plano) {
        return new PlanoResponseDTO(
            plano.getId(),
            plano.getNome(),
            plano.getPreco(),
            plano.getDescricao(),
            plano.getMaxUsuarios(),
            plano.getMaxContratos(),
            plano.getMaxImoveis(),
            plano.getMaxArmazenamentoDocumentosMb()
        );
    }
}