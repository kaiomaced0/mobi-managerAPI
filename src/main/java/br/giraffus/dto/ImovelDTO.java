package br.giraffus.dto;

public record ImovelDTO(
        Integer qtdQuartos,
        Integer qtdGaragem,
        String descricao,
        Long idProprietario,
        EnderecoDTO enderecoDTO,
        Long categoriaImovel,
        Double valorIptu,
        Double valorTotalImovel
) {
}
