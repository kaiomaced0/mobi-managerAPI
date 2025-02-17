package br.giraffus.dto;

import br.giraffus.model.Contrato;

import java.time.LocalDate;

public record ContratoResponseDTO(
        Long id,
        String diaVencimento,
        LocalDate dataInicioContrato,
        LocalDate dataFimContrato,
        ImovelResponseDTO imovel,
        InquilinoResponseDTO inquilino
) {
    public static ContratoResponseDTO toDTO(Contrato contrato) {
        return new ContratoResponseDTO(
                contrato.getId(),
                contrato.getDiaVencimento(),
                contrato.getDataInicioContrato(),
                contrato.getDataFimContrato(),
                contrato.getImovel() != null ? ImovelResponseDTO.toDTO(contrato.getImovel()) : null,
                contrato.getInquilino() != null ? InquilinoResponseDTO.toDTO(contrato.getInquilino()) : null
        );
    }
}
