package br.giraffus.dto;

import br.giraffus.model.Cidade;

public record CidadeResponseDTO(
        Long id,
        String nome,
        EstadoResponseDTO estado
) {
}
