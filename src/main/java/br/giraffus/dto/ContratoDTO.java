package br.giraffus.dto;

import java.time.LocalDate;

public record ContratoDTO(
        String diaVencimento,
        LocalDate dataInicioContrato,
        LocalDate dataFimContrato,
        Long idImovel,
        Long idInquilino
) {
}
