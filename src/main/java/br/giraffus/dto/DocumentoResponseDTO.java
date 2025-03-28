package br.giraffus.dto;

import br.giraffus.model.Documento;

public record DocumentoResponseDTO(
    Long id,
    String nome,
    String arquivo,
    String tipo,
    String descricao
) {
    public static DocumentoResponseDTO toDTO(Documento documento) {
        return new DocumentoResponseDTO(
            documento.getId(),
            documento.getTitulo(),
            documento.getNomeArquivo(),
            documento.getTipo(),
            documento.getDescricao()
        );
    }
}