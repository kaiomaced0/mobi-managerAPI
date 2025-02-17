package br.giraffus.dto;

import br.giraffus.model.Endereco;

public record EnderecoResponseDTO(
        Long id,
        String quadra,
        String rua,
        String cep,
        String descricao,
        String bairro,
        String numero,
        CidadeResponseDTO cidade
) {
    public static EnderecoResponseDTO toDTO(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getQuadra(),
                endereco.getRua(),
                endereco.getCep(),
                endereco.getDescricao(),
                endereco.getBairro(),
                endereco.getNumero(),
                endereco.getCidade() != null ? new CidadeResponseDTO(endereco.getCidade().getId(), endereco.getCidade().getNome(), new EstadoResponseDTO(endereco.getCidade().getEstado().getId(), endereco.getCidade().getEstado().getSigla(), endereco.getCidade().getEstado().getNome())) : null
        );
    }
}
