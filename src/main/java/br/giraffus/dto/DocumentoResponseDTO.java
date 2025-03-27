package br.giraffus.dto;

import br.giraffus.model.Documento;

public record DocumentoResponseDTO(
    Long id,
    String nome,
    String tipo,
    Long pagamentoEmpresaId
) {
    public static DocumentoResponseDTO toDTO(Documento documento) {
        return new DocumentoResponseDTO(
            documento.getId(),
            documento.getNome(),
            documento.getTipo(),
            documento.getPagamentoEmpresa().getId()
        );
    }
}