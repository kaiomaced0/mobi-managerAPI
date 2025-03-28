package br.giraffus.dto;

public record CidadeResponseDTO(
        Long id,
        String nome,
        EstadoResponseDTO estado
) {
}
