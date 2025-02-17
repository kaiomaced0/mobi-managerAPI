package br.giraffus.dto;

import br.giraffus.model.Imovel;

public record ImovelResponseDTO(
        Long id,
        Integer qtdQuartos,
        Integer qtdGaragem,
        String descricao,
        ProprietarioResponseDTO proprietario,
        EnderecoDTO enderecoDTO,
        String categoriaImovel,
        Double valorIptu,
        Double valorTotalImovel
) {
    public static ImovelResponseDTO toDTO(Imovel imovel) {
        return new ImovelResponseDTO(
                imovel.getId(),
                imovel.getQtdQuartos(),
                imovel.getQtdGaragem(),
                imovel.getDescricao(),
                imovel.getProprietario() != null ? ProprietarioResponseDTO.toDTO(imovel.getProprietario()) : null,
                null,
                imovel.getCategoriaImovel().getLabel(),
                imovel.getValorIptu(),
                imovel.getValorTotalImovel()
        );
    }
}
